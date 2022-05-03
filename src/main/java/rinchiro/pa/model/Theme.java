package rinchiro.pa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
	
	private String id;
	
	private String name;
	
	private Color gui;
	
	private Color bg;
	
	private List<Color> players;
	
	@JsonProperty("objs")
	private List<Color> objects;
	
	@JsonProperty("bgs")
	private List<Color> backgrounds;
}
