package rinchiro.pa.model.object;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParentOffset {

	@Builder.Default
	private BigDecimal position = BigDecimal.ZERO;
	@Builder.Default
	private BigDecimal scale = BigDecimal.ZERO;
	@Builder.Default
	private BigDecimal rotation = BigDecimal.ZERO;

	@JsonCreator
	public static ParentOffset fromList(List<String> value) {
		if (value == null) {
			return null;
		}
		return ParentOffset.builder()
				.position(new BigDecimal(value.get(0)))
				.scale(new BigDecimal(value.get(1)))
				.rotation(new BigDecimal(value.get(2)))
				.build();
	}

	@JsonValue
	public List<String> toJsonValue() {
		return List.of(position.toString(), scale.toString(), rotation.toString());
	}
}
