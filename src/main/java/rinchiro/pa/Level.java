package rinchiro.pa;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.extern.slf4j.Slf4j;
import rinchiro.pa.model.LevelDescription;
import rinchiro.pa.model.Prefab;
import rinchiro.pa.model.serializer.BooleanDeserializer;
import rinchiro.pa.model.serializer.BooleanSerializer;
import rinchiro.pa.transformers.ObjectTransformer;

@Slf4j
public class Level {

	private static final String FILENAME = "level.lsb";
	private static final String NEW_FILENAME = "level2.lsb";
	private static final ObjectMapper MAPPER = new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL)
			.registerModule(new SimpleModule()
					.addSerializer(boolean.class, new BooleanSerializer())
					.addSerializer(Boolean.class, new BooleanSerializer())
					.addDeserializer(boolean.class, new BooleanDeserializer())
					.addDeserializer(Boolean.class, new BooleanDeserializer()));

	private final String dir;
	private LevelDescription desc = null;

	public Level(String dir) {
		this.dir = dir;
	}

	public void read() {
		try {
			try (InputStream s = Files.newInputStream(Paths.get(dir, FILENAME))) {
				desc = MAPPER.readValue(s, LevelDescription.class);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void write() {
		try {
			Files.writeString(Paths.get(dir, NEW_FILENAME), MAPPER.writeValueAsString(desc));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void transform(ObjectTransformer transformer) {
		for (Prefab prefab : desc.getPrefabs()) {
			log.info("transforming prefab {} ({} objects)", prefab.getName(), prefab.getObjects().size());
			prefab.setObjects(transformer.transform(prefab.getObjects()));
		}
		log.info("transforming beatmap ({} objects)", desc.getBeatmapObjects().size());
		desc.setBeatmapObjects(transformer.transform(desc.getBeatmapObjects()));
	}
}
