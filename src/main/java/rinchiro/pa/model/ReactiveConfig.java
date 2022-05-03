package rinchiro.pa.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactiveConfig {

	public enum Type {
		LOW, MID, HIGH;
	}
	
	private Type type;

	@JsonFormat(shape = Shape.STRING)
	private BigDecimal scale;
}
