package rinchiro.pa.model.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EditorData {

	private Boolean shrink;
	
	@JsonFormat(shape = Shape.STRING)
	private Integer bin;
	
	@JsonFormat(shape = Shape.STRING)
	private Integer layer;

	private Boolean locked;
}
