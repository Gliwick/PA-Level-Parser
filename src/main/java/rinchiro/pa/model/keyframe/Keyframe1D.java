package rinchiro.pa.model.keyframe;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "r", visible = true, defaultImpl = Keyframe1D.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = RandomKeyframe1D.class, name = "1"),
		@JsonSubTypes.Type(value = RandomKeyframe1D.class, name = "3") })
public class Keyframe1D extends KeyframeBase {

	@JsonFormat(shape = Shape.STRING)
	protected BigDecimal x = BigDecimal.ZERO;
	
	@Override
	public Keyframe1D copy() {
		Keyframe1D copy = new Keyframe1D();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setX(x);
		return copy;
	}
}
