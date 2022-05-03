package rinchiro.pa;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import rinchiro.pa.transformers.ObjectSpacer;

@Slf4j
public class Main {

	public static void main(String... args) {
		ObjectSpacer spacer = args.length < 3 ? new ObjectSpacer()
				: new ObjectSpacer(Integer.valueOf(args[1]), new BigDecimal(args[2]));

		Level level = new Level(args[0]);
		level.read();
		level.transform(spacer);
		level.write();
		log.info("script finished successfully");
	}
}
