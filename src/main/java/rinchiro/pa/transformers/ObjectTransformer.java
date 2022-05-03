package rinchiro.pa.transformers;

import java.util.List;

import rinchiro.pa.model.object.BeatmapObject;

public interface ObjectTransformer {

	public List<BeatmapObject> transform (List<BeatmapObject> objects);
}
