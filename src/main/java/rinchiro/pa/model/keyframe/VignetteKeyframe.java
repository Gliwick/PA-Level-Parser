package rinchiro.pa.model.keyframe;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rinchiro.pa.model.serializer.BooleanZeroOneDeserializer;
import rinchiro.pa.model.serializer.BooleanZeroOneSerializer;

@Data
@EqualsAndHashCode(callSuper = true)
public class VignetteKeyframe extends KeyframeBase {

	@JsonProperty("x")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal intensity;

	@JsonProperty("y")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal smoothness;

	@JsonProperty("z")
	@JsonSerialize(using = BooleanZeroOneSerializer.class)
	@JsonDeserialize(using = BooleanZeroOneDeserializer.class)
	private boolean round;
	
	@JsonProperty("x2")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal roundness;
	
	@JsonProperty("y2")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal x;
	
	@JsonProperty("z2")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal y;
	
	@Override
	public VignetteKeyframe copy() {
		VignetteKeyframe copy = new VignetteKeyframe();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setX(x);
		copy.setY(y);
		copy.setIntensity(intensity);
		copy.setSmoothness(smoothness);
		copy.setRound(round);
		copy.setRoundness(roundness);
		return copy;
	}
}
