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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "r", visible = true, defaultImpl = Keyframe2D.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = RandomKeyframe2D.class, name = "1"),
		@JsonSubTypes.Type(value = RandomKeyframe2D.class, name = "3"),
		@JsonSubTypes.Type(value = ScaleRandomKeyframe2D.class, name = "4") })
public class Keyframe2D extends Keyframe1D {

	@JsonFormat(shape = Shape.STRING)
	protected BigDecimal y = BigDecimal.ZERO;
	
	@Override
	public Keyframe2D copy() {
		Keyframe2D copy = new Keyframe2D();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setX(x);
		copy.setY(y);
		return copy;
	}
}
