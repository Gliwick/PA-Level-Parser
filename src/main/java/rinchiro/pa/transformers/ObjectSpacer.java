package rinchiro.pa.transformers;

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
import rinchiro.pa.model.object.Events;

@Slf4j
public class ObjectSpacer implements ObjectTransformer {

	private final int maxObjectGroupSize;
	private final BigDecimal step;

	public ObjectSpacer() {
		this(20, new BigDecimal("0.005"));
	}

	public ObjectSpacer(int maxObjectGroupSize, BigDecimal step) {
		this.maxObjectGroupSize = maxObjectGroupSize;
		this.step = step;
	}

	public List<BeatmapObject> transform(List<BeatmapObject> objects) {
		Map<BigDecimal, List<BeatmapObject>> timeToObjects = objects.stream().collect(Collectors.groupingBy(
				obj -> obj.getStartTime().setScale(3, RoundingMode.HALF_UP), LinkedHashMap::new, Collectors.toList()));

		List<BeatmapObject> newObjects = new ArrayList<>(objects.size());

		for (BigDecimal time : timeToObjects.keySet()) {
			Map<Boolean, List<BeatmapObject>> emptyToObjects = timeToObjects.get(time).stream()
					.collect(Collectors.groupingBy(obj -> obj.getType() == BeatmapObject.Type.EMPTY));
			for (boolean isEmpty : emptyToObjects.keySet()) {
				List<BeatmapObject> objectsGroup = emptyToObjects.get(isEmpty);
				if (!isEmpty && objectsGroup.size() >= maxObjectGroupSize
						&& time.compareTo(step.multiply(BigDecimal.valueOf(objectsGroup.size()))) >= 0) {
					log.info("spacing out {} objects at {}", objectsGroup.size(), time);
					newObjects.addAll(spaceOut(time, objectsGroup));
				} else {
					newObjects.addAll(objectsGroup);
				}
			}
		}

		return newObjects;
	}

	private List<BeatmapObject> spaceOut(BigDecimal time, List<BeatmapObject> objects) {
		int i = 2;
		int sum = objects.size() + 2 * i - 1;

		for (BeatmapObject obj : objects) {
			log.trace("object {}", obj.getName());
			BigDecimal preInterval = step.multiply(BigDecimal.valueOf(i));
			BigDecimal postInterval = step.multiply(BigDecimal.valueOf(sum - i));

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

			Events events = obj.getEvents();
			for (Events.Type type : Events.Type.values()) {
				@SuppressWarnings("unchecked")
				List<KeyframeBase> keyframes = (List<KeyframeBase>) events.getKeyframes(type);

				// shift first keyframe
				KeyframeBase firstKeyframe = keyframes.get(0);
				firstKeyframe.setTime(preInterval);
				firstKeyframe.setEaseType(EaseType.Instant);

				// shift all other keyframes
				keyframes.stream()
						.skip(1)
						.forEach(keyframe -> {
							keyframe.setTime(keyframe.getTime().add(preInterval));
						});

				if (type == Events.Type.SCALE) {
					// set zero scale kf before autokill happens so that object will be killed later
					if (autokillType != AutokillType.NO_AUTOKILL && fixedTimeOffset.compareTo(BigDecimal.ZERO) > 0) {
						BigDecimal lastKeyframeTime = keyframes.stream()
								.map(KeyframeBase::getTime)
								.max(BigDecimal::compareTo)
								.orElse(BigDecimal.ZERO)
								.add(preInterval);

						Keyframe2D lastKeyframe = new Keyframe2D();
						lastKeyframe.setTime(fixedTimeOffset.max(lastKeyframeTime.add(new BigDecimal("0.001"))));
						lastKeyframe.setEaseType(EaseType.Instant);
						keyframes.add(lastKeyframe);
					}
				}

				// add new first keyframe
				switch (type) {
				case POSITION:
				case COLOR:
					keyframes.add(0, keyframes.get(0));
					break;
				case SCALE:
					keyframes.add(0, new Keyframe2D());
				case ROTATION:
					keyframes.add(0, new Keyframe1D());
					break;
				}
			}

			obj.setStartTime(time.add(preInterval.negate()));

			if (fixedTimeOffset.compareTo(BigDecimal.ZERO) <= 0) {
				obj.setAutokillType(AutokillType.LAST_KF);
				obj.setAutokillOffset(BigDecimal.ZERO);
			} else if (autokillType != AutokillType.NO_AUTOKILL) {
				if (autokillType == AutokillType.LAST_KF) {
					obj.setAutokillType(AutokillType.LAST_KF_OFFSET);
				}

				BigDecimal newAko = autokillOffset.add(postInterval);
				if (autokillType == AutokillType.FIXED_TIME) {
					newAko = newAko.add(preInterval);
				}
				obj.setAutokillOffset(newAko);
			}

			i += 1;
		}
		return objects;
	}
}
