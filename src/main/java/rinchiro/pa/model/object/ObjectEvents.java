package rinchiro.pa.model.object;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.keyframe.ColorKeyframe;
import rinchiro.pa.model.keyframe.Keyframe1D;
import rinchiro.pa.model.keyframe.Keyframe2D;
import rinchiro.pa.model.keyframe.KeyframeBase;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ObjectEvents {

	@JsonProperty("pos")
	@Builder.ObtainVia(method = "copyPosition")
	private List<Keyframe2D> position;

	@JsonProperty("sca")
	@Builder.ObtainVia(method = "copyScale")
	private List<Keyframe2D> scale;

	@JsonProperty("rot")
	@Builder.ObtainVia(method = "copyRotation")
	private List<Keyframe1D> rotation;

	@JsonProperty("col")
	@Builder.ObtainVia(method = "copyColor")
	private List<ColorKeyframe> color;

	public enum Type {
		POSITION, SCALE, ROTATION, COLOR;
	}

	public Stream<KeyframeBase> streamKeyframes() {
		return Stream.concat(
				Stream.concat(
						Stream.concat(position.stream(), scale.stream()),
						rotation.stream()),
				color.stream());
	}

	public List<? extends KeyframeBase> getKeyframes(Type type) {
		switch (type) {
		case POSITION:
			return position;
		case SCALE:
			return scale;
		case ROTATION:
			return rotation;
		case COLOR:
			return color;
		default:
			throw new IllegalArgumentException();
		}
	}

	@SuppressWarnings("unchecked")
	public void setKeyframes(Type type, List<? extends KeyframeBase> keyframes) {
		switch (type) {
		case POSITION:
			this.position = (List<Keyframe2D>) keyframes;
			return;
		case SCALE:
			this.scale = (List<Keyframe2D>) keyframes;
			return;
		case ROTATION:
			this.rotation = (List<Keyframe1D>) keyframes;
			return;
		case COLOR:
			this.color = (List<ColorKeyframe>) keyframes;
			return;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	private List<Keyframe2D> copyPosition() {
		return position.stream().map(Keyframe2D::copy).collect(Collectors.toList());
	}
	
	private List<Keyframe2D> copyScale() {
		return scale.stream().map(Keyframe2D::copy).collect(Collectors.toList());
	}
	
	private List<Keyframe1D> copyRotation() {
		return rotation.stream().map(Keyframe1D::copy).collect(Collectors.toList());
	}
	
	private List<ColorKeyframe> copyColor() {
		return color.stream().map(ColorKeyframe::copy).collect(Collectors.toList());
	}
}
