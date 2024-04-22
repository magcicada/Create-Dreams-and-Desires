package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.foundation.config.ui.ConfigAnnotations;

public class DClient extends ConfigBase {

	public final ConfigGroup client = group(0, "client",
			Comments.client);

	//no group
	public final ConfigFloat industrialFanParticleDensity = f(.5f, 0, 1, "industrialFanParticleDensity",
			Comments.industrialFanParticleDensity);

	// custom fluid fog
	public final ConfigGroup fluidFogSettings = group(1, "fluidFogSettings", Comments.fluidFogSettings);
	public final ConfigFloat sapTransparencyMultiplier =
		f(1, .125f, 128, "sap", Comments.sapTransparencyMultiplier);

	//ponder group
	public final ConfigGroup ponder = group(1, "ponder",
			Comments.ponder);

	@Override
	public String getName() {
		return "client";
	}

	private static class Comments {
		static String client = "Client-only settings - If you're looking for general settings, look inside your worlds serverconfig folder!";

		static String industrialFanParticleDensity = "Higher density means more spawned particles.";
		static String ponder = "Ponder settings";
		static String editingMode = "Show additional info in the ponder view and reload scene scripts more frequently.";
		static String fluidFogSettings = "Configure your vision range when submerged in Create Dream n' Desire's custom fluids";
		static String sapTransparencyMultiplier = "The vision range through honey will be multiplied by this factor";
	}

}
