package rinchiro.pa.model.object;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.Point;
import rinchiro.pa.model.Point1D;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrefabEvents {

	@JsonProperty("pos")
	private Point position;

	@JsonProperty("sca")
	private Point scale;

	@JsonProperty("rot")
	private Point1D rotation;
}
