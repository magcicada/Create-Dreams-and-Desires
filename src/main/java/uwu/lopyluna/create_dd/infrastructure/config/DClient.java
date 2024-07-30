package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

@SuppressWarnings({"unused"})
public class DClient extends ConfigBase {

	public final ConfigGroup client = group(0, "client",
			Comments.client);

	public final ConfigGroup equipments = group(1, "equipments", Comments.equipments);
	public final ConfigBool invertDeforesterSawFunction = b(false, "invertDeforesterSawFunction",
			Comments.invertDeforesterSawFunction);
	public final ConfigBool invertExcavationDrillFunction = b(false, "invertExcavationDrillFunction",
			Comments.invertDeforesterSawFunction);


	public final ConfigBool equipmentsDebug = b(false, "equipmentsDebug",
			Comments.equipmentsDebug);

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


		static String equipments = "Configure Equipment settings";
		static String invertDeforesterSawFunction = "Invert Deforester Saw activation function";
		static String invertExcavationDrillFunction = "Invert Excavation Drill activation function";
		static String equipmentsDebug = "Debug Equipments (only works in Creative mode)";


		static String fluidFogSettings = "Configure your vision range when submerged in Create Dream n' Desire's custom fluids";
		static String sapTransparencyMultiplier = "The vision range through honey will be multiplied by this factor";


		static String ponder = "Ponder settings";
	}

}
