package rinchiro.pa.transformer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import rinchiro.pa.model.keyframe.Keyframe1D;
import rinchiro.pa.model.keyframe.Keyframe2D;
import rinchiro.pa.model.keyframe.KeyframeBase;
import rinchiro.pa.model.keyframe.KeyframeBase.EaseType;
import rinchiro.pa.model.object.BeatmapObject;
import rinchiro.pa.model.object.BeatmapObject.AutokillType;
import rinchiro.pa.model.object.ObjectEvents;

@Slf4j
public class ObjectSpacer implements ObjectTransformer {

	private final int maxAllowedGroupSize;
	private final BigDecimal step;

	public ObjectSpacer() {
		this(1, new BigDecimal("0.01"));
	}

	public ObjectSpacer(int maxObjectGroupSize, BigDecimal step) {
		if (maxObjectGroupSize < 1) {
			throw new IllegalArgumentException("maxAllowedGroupSize must be at least 1");
		}
		this.maxAllowedGroupSize = maxObjectGroupSize;
		this.step = step;
	}

	public List<BeatmapObject> transform(List<BeatmapObject> objects) {
		Map<BigDecimal, List<BeatmapObject>> timeToObjects = objects.stream().collect(Collectors.groupingBy(
				obj -> obj.getStartTime().setScale(2, RoundingMode.HALF_UP), LinkedHashMap::new, Collectors.toList()));

		List<BeatmapObject> newObjects = new ArrayList<>(objects.size());

		for (BigDecimal time : timeToObjects.keySet()) {
			List<BeatmapObject> objectsGroup = timeToObjects.get(time);
			if (objectsGroup.size() > maxAllowedGroupSize
					&& time.compareTo(step.multiply(BigDecimal.valueOf(objectsGroup.size() + 2))) >= 0) {
				log.info("spacing out {} objects at {}", objectsGroup.size(), time);
				newObjects.addAll(spaceOut(time, objectsGroup));
			} else {
				newObjects.addAll(objectsGroup);
			}
		}

		return newObjects;
	}

	private List<BeatmapObject> spaceOut(BigDecimal time, List<BeatmapObject> objects) {
		int i = 2;
		int sum = objects.size() + 2 * i - 1;

		for (BeatmapObject obj : objects) {
			BigDecimal preInterval = step.multiply(BigDecimal.valueOf(i));
			BigDecimal postInterval = step.multiply(BigDecimal.valueOf(sum - i));
			shift(obj, preInterval, postInterval);
			i += 1;
		}

		return objects;
	}

	/**
	 * shifts objects spawn and kill time,
	 * while retaining absolute time for its keyframes
	 */
	private void shift(BeatmapObject obj, BigDecimal preSpawnInterval, BigDecimal postKillInterval) {
		log.trace("object {}", obj.getName());

		AutokillType autokillType = obj.getAutokillType();
		BigDecimal autokillOffset = autokillType == AutokillType.LAST_KF ? BigDecimal.ZERO : obj.getAutokillOffset();

		BigDecimal fixedTimeOffset = BigDecimal.ZERO;
		switch (autokillType) {
		case LAST_KF:
		case LAST_KF_OFFSET:
			fixedTimeOffset = obj.getEvents().streamKeyframes()
					.map(KeyframeBase::getTime)
					.max(BigDecimal::compareTo)
					.orElse(BigDecimal.ZERO)
					.add(autokillOffset);
			break;
		case FIXED_TIME:
			fixedTimeOffset = autokillOffset;
			break;
		case SONG_TIME:
			fixedTimeOffset = autokillOffset.add(obj.getStartTime().negate()).max(BigDecimal.ZERO);
			break;
		case NO_AUTOKILL:
			// 10 minutes should be enough for any sensible level
			// and autokill is deprecated anyway
			log.warn("You have a No Autokill object {} at {}", obj.getName(), obj.getStartTime());
			fixedTimeOffset = BigDecimal.valueOf(600);
			break;
		}

		ObjectEvents events = obj.getEvents();
		for (ObjectEvents.Type type : ObjectEvents.Type.values()) {
			@SuppressWarnings("unchecked")
			List<KeyframeBase> keyframes = (List<KeyframeBase>) events.getKeyframes(type);

			// shift all keyframes except first
			keyframes.stream()
					.skip(1)
					.forEach(keyframe -> {
						keyframe.setTime(keyframe.getTime().add(preSpawnInterval));
					});

			KeyframeBase zeroKeyframe = keyframes.get(0);

			// add new first keyframe
			KeyframeBase firstKeyframe = null;
			switch (type) {
			case POSITION:
			case SCALE:
			case COLOR:
				firstKeyframe = zeroKeyframe.copy();
				break;
			case ROTATION:
				firstKeyframe = new Keyframe1D();
				break;
			}

			firstKeyframe.setTime(preSpawnInterval);
			firstKeyframe.setEaseType(EaseType.Instant);
			keyframes.add(1, firstKeyframe);

			// adjust scale for non-empty objects so they'll only be visible at the same
			// period as before
			if (obj.getType() != BeatmapObject.Type.EMPTY && type == ObjectEvents.Type.SCALE) {
				Keyframe2D scaleKeyframe = (Keyframe2D) zeroKeyframe;
				scaleKeyframe.setX(BigDecimal.ZERO);
				scaleKeyframe.setY(BigDecimal.ZERO);

				// set zero scale kf at the time of previous autokill
				if (autokillType != AutokillType.NO_AUTOKILL && fixedTimeOffset.compareTo(BigDecimal.ZERO) > 0) {
					BigDecimal lastKeyframeTime = keyframes.stream()
							.map(KeyframeBase::getTime)
							.max(BigDecimal::compareTo)
							.orElse(BigDecimal.ZERO)
							.add(preSpawnInterval);

					Keyframe2D lastKeyframe = new Keyframe2D();
					lastKeyframe.setTime(fixedTimeOffset.max(lastKeyframeTime.add(new BigDecimal("0.001"))));
					lastKeyframe.setEaseType(EaseType.Instant);
					keyframes.add(lastKeyframe);
				}
			}
		}

		obj.setStartTime(obj.getStartTime().add(preSpawnInterval.negate()));

		if (obj.getType() == BeatmapObject.Type.EMPTY || fixedTimeOffset.compareTo(BigDecimal.ZERO) <= 0) {
			obj.setAutokillType(AutokillType.LAST_KF);
			obj.setAutokillOffset(BigDecimal.ZERO);
		} else if (autokillType != AutokillType.NO_AUTOKILL) {
			if (autokillType == AutokillType.LAST_KF) {
				obj.setAutokillType(AutokillType.LAST_KF_OFFSET);
			}

			BigDecimal newAko = autokillOffset.add(postKillInterval);
			if (autokillType == AutokillType.FIXED_TIME) {
				newAko = newAko.add(preSpawnInterval);
			}
			obj.setAutokillOffset(newAko);
		}
	}
}
