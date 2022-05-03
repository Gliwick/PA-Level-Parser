package rinchiro.pa.model.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class BooleanDeserializer extends StdDeserializer<Boolean> {

	private static final long serialVersionUID = -2503443375701373236L;

	public BooleanDeserializer() {
		super(Boolean.class);
	}

	@Override
	public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String s = p.getValueAsString();
		return s == null ? null : "True".equals(s);
	}
}
