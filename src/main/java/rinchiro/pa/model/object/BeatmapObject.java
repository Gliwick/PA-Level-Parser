package rinchiro.pa.model.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.Point;
import rinchiro.pa.model.serializer.ListToStringSerializer;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BeatmapObject {

	private String id;

	@JsonProperty("p")
	private String parentId;

	@JsonProperty("pt")
	private String parentType;

	@JsonProperty("po")
	@JsonSerialize(using = ListToStringSerializer.class)
	@Builder.ObtainVia(method = "copyParentOffset")
	private List<BigDecimal> parentOffset;

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
	@Builder.ObtainVia(method = "copyOrigin")
	private Point origin;

	@JsonProperty("ed")
	@Builder.ObtainVia(method = "copyEditorData")
	private EditorData editorData;

	@Builder.ObtainVia(method = "copyEvents")
	private Events events;

	private List<BigDecimal> copyParentOffset() {
		return parentOffset == null ? null : new ArrayList<BigDecimal>(parentOffset);
	}

	private Point copyOrigin() {
		return origin == null ? null : origin.toBuilder().build();
	}

	private EditorData copyEditorData() {
		return editorData == null ? null : editorData.toBuilder().build();
	}

	private Events copyEvents() {
		return events.toBuilder().build();
	}
}
