package rinchiro.pa.model.keyframe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RandomType {

	NONE, RANGE, UNKNOWN, TOGGLE, SCALE;

	@Override
	@JsonValue
	public String toString() {
		return this == NONE ? null : String.valueOf(ordinal());
	}

	@JsonCreator
	public RandomType fromString(String s) {
		return s == null ? NONE : values()[Integer.valueOf(s)];
	}
}
