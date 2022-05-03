package rinchiro.pa.model.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParentType {

	private boolean position;
	private boolean scale;
	private boolean rotation;

	private static char toChar(boolean b) {
		return b ? '1' : '0';
	}

	private static boolean fromChar(char c) {
		return c == '1';
	}

	@JsonCreator
	public static ParentType fromString(String value) {
		if (value == null) {
			return null;
		}
		return ParentType.builder()
				.position(fromChar(value.charAt(0)))
				.scale(fromChar(value.charAt(1)))
				.rotation(fromChar(value.charAt(2)))
				.build();
	}

	@JsonValue
	public String toJsonValue() {
		return String.format("%s%s%s", toChar(position), toChar(scale), toChar(rotation));
	}
}
