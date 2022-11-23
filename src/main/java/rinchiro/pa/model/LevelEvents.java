package rinchiro.pa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.keyframe.GrainKeyframe;
import rinchiro.pa.model.keyframe.Keyframe1D;
import rinchiro.pa.model.keyframe.Keyframe2D;
import rinchiro.pa.model.keyframe.ThemeKeyframe;
import rinchiro.pa.model.keyframe.VignetteKeyframe;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelEvents {

	@JsonProperty("pos")
	private List<Keyframe2D> position;

	private List<Keyframe1D> zoom;

	@JsonProperty("rot")
	private List<Keyframe1D> rotation;

	private List<Keyframe2D> shake;
	
	private List<ThemeKeyframe> theme;
	
	private List<Keyframe1D> chroma;
	
	private List<Keyframe1D> bloom;
	
	private List<VignetteKeyframe> vignette;
	
	private List<Keyframe1D> lens;
	
	private List<GrainKeyframe> grain;
}
