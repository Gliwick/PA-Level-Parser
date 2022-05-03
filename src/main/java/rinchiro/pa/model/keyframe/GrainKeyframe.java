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
public class GrainKeyframe extends KeyframeBase {

	@JsonProperty("x")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal intensity;

	@JsonProperty("y")
	@JsonSerialize(using = BooleanZeroOneSerializer.class)
	@JsonDeserialize(using = BooleanZeroOneDeserializer.class)
	private boolean colored;

	@JsonProperty("z")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal size;

	@Override
	public GrainKeyframe copy() {
		GrainKeyframe copy = new GrainKeyframe();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setIntensity(intensity);
		copy.setColored(colored);
		copy.setSize(size);
		return copy;
	}
}
