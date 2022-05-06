package rinchiro.pa.transformer;

import java.util.List;

import rinchiro.pa.model.object.BeatmapObject;

public interface ObjectTransformer {

	public List<BeatmapObject> transform (List<BeatmapObject> objects);
}
