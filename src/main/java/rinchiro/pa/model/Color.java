package rinchiro.pa.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Color {

	private int red;
	private int green;
	private int blue;

	@JsonCreator
	public static Color fromString(String value) {
		if (value == null) {
			return null;
		}
		return Color.builder()
				.red(Integer.parseInt(value.substring(0, 2), 16))
				.green(Integer.parseInt(value.substring(2, 4), 16))
				.blue(Integer.parseInt(value.substring(4, 6), 16))
				.build();
	}

	@JsonValue
	public String toJsonValue() {
		return String.format("%02X%02X%02X", red, green, blue);
	}
}
