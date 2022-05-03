package rinchiro.pa.model.keyframe;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
public class RandomKeyframe2D extends Keyframe2D {

	@JsonProperty("rx")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal x2;
	
	@JsonProperty("ry")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal y2;
	
	@JsonProperty("rz")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal interval;
	
	@JsonProperty("r")
	protected RandomType randomType;
	
	@Override
	public RandomKeyframe2D copy() {
		RandomKeyframe2D copy = new RandomKeyframe2D();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setX(x);
		copy.setY(y);
		copy.setX2(x2);
		copy.setY2(y2);
		copy.setInterval(interval);
		copy.setRandomType(randomType);
		return copy;
	}
}
