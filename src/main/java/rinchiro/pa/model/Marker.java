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
public class Marker {

	@Builder.Default
	private final boolean active = true;

	private String name;

	private String desc;

	@JsonProperty("col")
	@JsonFormat(shape = Shape.STRING)
	private Integer color;

	@JsonProperty("t")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal time;
}
