package rinchiro.pa.model.serializer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListToStringSerializer extends StdSerializer<List> {

	private static final long serialVersionUID = 5601518120238765542L;

	public ListToStringSerializer() {
		super(List.class);
	}

	@Override
	public void serialize(List value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (value == null) {
			gen.writeNull();
		} else {
			String[] strings = ((List<Object>) value).stream().map(Object::toString).toArray(String[]::new);
			gen.writeArray(strings, 0, strings.length);
		}
	}
}
