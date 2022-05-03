package rinchiro.pa.model.object;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Shape {

	@Getter
	@AllArgsConstructor
	public enum ShapeEnum {
		SQUARE(null, null), SQUARE_HOLLOW(null, 1), SQUARE_HOLLOW_THIN(null, 2),
		CIRCLE(1, null), CIRCLE_HOLLOW(1, 1), CIRCLE_HALF(1, 2), CIRCLE_HALF_ARC(1, 3), CIRCLE_HOLLOW_THIN(1, 4),
		CIRCLE_QUARTER(1, 5), CIRCLE_QUARTER_ARC(1, 6), CIRCLE_EIGHTH(1, 7), CIRCLE_EIGHTH_ARC(1, 8),
		TRIANGLE(2, null), TRIANGLE_HOLLOW(2, 1), TRIANGLE_RIGHT(2, 2), TRIANGLE_RIGHT_HOLLOW(2, 3),
		ARROW1(3, null), ARROW2(3, 1),
		TEXT(4, null),
		HEXAGON(5, null), HEXAGON_HOLLOW(5, 1), HEXAGON_HOLLOW_THIN(5, 2),
		HEXAGON_HALF(5, 3), HEXAGON_HALF_HOLLOW(5, 4), HEXAGON_HALF_HOLLOW_THIN(5, 5);

		private Integer shape;
		private Integer subShape;

		public static ShapeEnum fromValue(Integer shape, Integer subShape) {
			for (ShapeEnum s : values()) {
				if (Objects.equals(s.getShape(), shape) && (Objects.equals(s.getSubShape(), subShape))) {
					return s;
				}
			}
			return SQUARE;
		}
	}

	@JsonProperty("shape")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer shape;

	@JsonProperty("so")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer subShape;

	@JsonCreator
	public static Shape fromValue(@JsonProperty("shape") Integer shape, @JsonProperty("so") Integer subShape) {
		return fromEnum(ShapeEnum.fromValue(shape, subShape));
	}

	public static Shape fromEnum(ShapeEnum shape) {
		return new Shape(shape.getShape(), shape.getSubShape());
	}
}
