package rinchiro.pa.model.keyframe;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScaleRandomKeyframe2D extends Keyframe2D {

	@JsonProperty("rx")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal scaleMin;
	
	@JsonProperty("ry")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal scaleMax;
	
	@JsonProperty("rz")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal interval;
	
	@JsonProperty("r")
	protected RandomType randomType;
	
	@Override
	public ScaleRandomKeyframe2D copy() {
		ScaleRandomKeyframe2D copy = new ScaleRandomKeyframe2D();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setX(x);
		copy.setY(y);
		copy.setScaleMin(scaleMin);
		copy.setScaleMax(scaleMax);
		copy.setInterval(interval);
		copy.setRandomType(randomType);
		return copy;
	}
}
