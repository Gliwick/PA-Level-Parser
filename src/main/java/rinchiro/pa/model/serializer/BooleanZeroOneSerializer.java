package rinchiro.pa.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class BooleanZeroOneSerializer extends StdSerializer<Boolean> {

	private static final long serialVersionUID = 7309542512998591936L;

	public BooleanZeroOneSerializer() {
		super(Boolean.class);
	}

	@Override
	public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (value == null) {
			gen.writeNull();
		} else {
			gen.writeString(value ? "1" : "0");
		}
	}
}
