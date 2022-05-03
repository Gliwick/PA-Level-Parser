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
public class EditorData {

	@JsonProperty("timeline_pos")
	@Builder.Default
	private final String timelinePosition = "0";
	
	private List<Marker> markers;
}
