package rinchiro.pa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rinchiro.pa.model.object.BeatmapObject;
import rinchiro.pa.model.object.PrefabObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelDescription {

	@JsonProperty("ed")
	private EditorData editorData;
	
	@JsonProperty("prefab_objects")
	private List<PrefabObject> prefabObjects;
	
	@JsonProperty("level_data")
	private LevelData levelData;
	
	private List<Prefab> prefabs;
	
	private List<Theme> themes;
	
	private List<Checkpoint> checkpoints;
	
	@JsonProperty("beatmap_objects")
	private List<BeatmapObject> beatmapObjects;
	
	@JsonProperty("bg_objects")
	private List<BackgroundObject> backgroundObjects;
	
	private Events events;
}
