package rinchiro.pa.model.object;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.Point;

@Data
@Builder(toBuilder = true, access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BeatmapObject {

	private static final int ID_LENGTH = 16;

	@Builder.Default
	private String id = generateId();

	@JsonProperty("p")
	private String parentId;

	@JsonProperty("pt")
	private ParentType parentType;

	@JsonProperty("po")
	private ParentOffset parentOffset;

	@JsonProperty("pid")
	private String prefabId;

	@JsonProperty("piid")
	private String prefabInstanceId;

	@JsonProperty("d")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Integer renderDepth;

	@JsonProperty("st")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal startTime;

	@JsonInclude(Include.NON_EMPTY)
	private String name;

	public enum Type {
		NORMAL, HELPER, DECORATION, EMPTY;
	}

	@JsonProperty("ot")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Type type;

	public enum AutokillType {
		@Deprecated
		NO_AUTOKILL, LAST_KF, LAST_KF_OFFSET, FIXED_TIME, SONG_TIME;
	}

	@JsonProperty("akt")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private AutokillType autokillType;

	@JsonProperty("ako")
	private BigDecimal autokillOffset;

	@JsonUnwrapped
	private Shape shape;

	private String text;

	@JsonProperty("o")
	private Point origin;

	@JsonProperty("ed")
	private EditorData editorData;

	private Events events;

	private static String generateId() {
		return RandomStringUtils.randomAscii(ID_LENGTH);
	}

	public BeatmapObject copy() {
		return toBuilder()
				.id(generateId())
				.origin(origin == null ? null : origin.toBuilder().build())
				.editorData(editorData == null ? null : editorData.toBuilder().build())
				.events(events.toBuilder().build())
				.build();
	}
}
