package rinchiro.pa.model.keyframe;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ThemeKeyframe extends KeyframeBase {

	@JsonProperty("x")
	@JsonFormat(shape = Shape.STRING)
	private String themeId;
	
	@Override
	public ThemeKeyframe copy() {
		ThemeKeyframe copy = new ThemeKeyframe();
		copy.setTime(time);
		copy.setEaseType(easeType);
		copy.setThemeId(themeId);
		return copy;
	}
}
