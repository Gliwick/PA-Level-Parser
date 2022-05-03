package rinchiro.pa.model.object;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrefabObject {

	private String id;

	@JsonProperty("pid")
	private String prefabId;
	
	@JsonProperty("st")
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal startTime;
	
	@JsonProperty("ed")
	private EditorData editorData;
	
	@JsonProperty("e")
	private PrefabEvents events;
}
