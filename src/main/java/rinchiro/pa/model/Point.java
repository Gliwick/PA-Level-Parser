package rinchiro.pa.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Point {

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal x;

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal y;
}
