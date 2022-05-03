package rinchiro.pa.model.keyframe;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public abstract class KeyframeBase {

	@JsonProperty("t")
	@JsonFormat(shape = Shape.STRING)
	protected BigDecimal time = BigDecimal.ZERO;

	public enum EaseType {
		Linear, Instant,
		InSine, OutSine, InOutSine,
		InElastic, OutElastic, InOutElastic,
		InBack, OutBack, InOutBack,
		InBounce, OutBounce, InOutBounce,
		InQuad, OutQuad, InOutQuad,
		InCirc, OutCirc, InOutCirc,
		InExpo, OutExpo, InOutExpo;
	}

	@JsonProperty("ct")
	protected EaseType easeType;
	
	public abstract KeyframeBase copy();
}
