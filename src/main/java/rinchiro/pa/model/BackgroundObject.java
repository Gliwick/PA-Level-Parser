package rinchiro.pa.model;

import java.math.BigDecimal;

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
public class BackgroundObject {

	private boolean active;

	private String name;
	
	@Builder.Default
	private final String kind = "1";

	@JsonProperty("pos")
	private Point position;

	private Point size;

	@JsonProperty("rot")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal rotation;

	@JsonFormat(shape = Shape.STRING)
	private Integer color;

	@JsonFormat(shape = Shape.STRING)
	private Integer layer;

	private boolean fade;
	
	@JsonProperty("r_set")
	private ReactiveConfig reactiveConfig;
}
