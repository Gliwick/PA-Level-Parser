package rinchiro.pa.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.object.BeatmapObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prefab {

	private String name;

	@JsonFormat(shape = Shape.STRING)
	private Integer type;

	private String id;

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal offset;

	private List<BeatmapObject> objects;
}
