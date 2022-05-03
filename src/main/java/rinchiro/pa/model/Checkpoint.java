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
public class Checkpoint {

	private final boolean active = false;

	private String name;

	@JsonProperty("t")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal time;

	@JsonProperty("pos")
	private Point position;
}
