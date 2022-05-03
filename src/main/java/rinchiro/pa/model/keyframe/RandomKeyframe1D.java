package rinchiro.pa.model.keyframe;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
public class RandomKeyframe1D extends Keyframe1D {

	@JsonProperty("rx")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal x2;
	
	@JsonProperty("rz")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal interval;
	
	@JsonProperty("r")
	protected RandomType randomType;
	
	@Override
	public RandomKeyframe1D copy() {
		RandomKeyframe1D copy = new RandomKeyframe1D();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setX(x);
		copy.setX2(x2);
		copy.setInterval(interval);
		copy.setRandomType(randomType);
		return copy;
	}
}
