package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class DKinetics extends ConfigBase {

	public final ConfigInt maxRubberBeltLength = i(32, 2, "maxRubberBeltLength", Comments.maxRubberBeltLength);
	public final ConfigFloat cogCrankHungerMultiplier = f(.01f, 0, 1, "cogCrankHungerMultiplier", Comments.cogCrankHungerMultiplier);

	public final ConfigBool weakenedHarvestPartiallyGrown = b(true, "weakenedHarvestPartiallyGrown", Comments.weakenedHarvestPartiallyGrown);
	public final ConfigBool weakenedHarvesterReplants = b(false, "weakenedHarvesterReplants", Comments.weakenedHarvesterReplants);

	public final ConfigGroup stats = group(1, "stats", Comments.stats);

	public final DStress stressValues = nested(1, DStress::new, Comments.stress);

	@Override
	public String getName() {
		return "kinetics";
	}

	private static class Comments {
		static String maxRubberBeltLength = "Maximum length in blocks of Rubber Belts.";
		static String cogCrankHungerMultiplier = "multiplier used for calculating exhaustion from speed when a Cog Crank is turned.";
		static String weakenedHarvestPartiallyGrown = "Whether Weakened Harvesters should break crops that aren't fully grown.";
		static String weakenedHarvesterReplants = "Whether Weakened Harvesters should replant crops after harvesting.";
		static String stats = "Configure speed/capacity levels for requirements and indicators.";
		static String rpm = "[in Revolutions per Minute]";
		static String stress = "Fine tune the kinetic stats of individual components";
	}

}
