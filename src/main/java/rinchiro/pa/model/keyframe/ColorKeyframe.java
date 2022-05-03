package rinchiro.pa.model.keyframe;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ColorKeyframe extends KeyframeBase {

	@JsonProperty("x")
	@JsonFormat(shape = Shape.STRING)
	private Integer color;

	@Override
	public ColorKeyframe copy() {
		ColorKeyframe copy = new ColorKeyframe();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setColor(color);
		return copy;
	}
}
