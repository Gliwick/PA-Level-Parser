package rinchiro.pa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelData {

	@JsonProperty("level_version")
	private String levelVersion;
	
	@JsonProperty("background_color")
	@JsonFormat(shape = Shape.STRING)
	private Integer backgroundColor;
	
	@JsonProperty("follow_player")
	private boolean followPlayer;
	
	@JsonProperty("show_intro")
	private boolean showIntro;
}
