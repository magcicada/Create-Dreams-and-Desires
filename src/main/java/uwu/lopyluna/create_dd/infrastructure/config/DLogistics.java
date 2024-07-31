package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

@SuppressWarnings("unused")
public class DLogistics extends ConfigBase {

//	public final ConfigInt mechanicalArmRange = i(5, 1, "mechanicalArmRange", Comments.mechanicalArmRange);*
//	public final ConfigInt calculationLinkRange = i(512, 1, "calculationLinkRange", Comments.calculationLinkRange);*
	public final ConfigInt verticalVaultCapacity = i(20, 1, "verticalVaultCapacity", Comments.verticalVaultCapacity);

	@Override
	public String getName() {
		return "logistics";
	}

	private static class Comments {
		static String defaultExtractionTimer =
			"The amount of ticks a funnel waits between item transferrals, when it is not re-activated by redstone.";
		static String calculationLinkRange = "Maximum possible range in blocks of Calculation Link connections.";
		static String mechanicalArmRange = "Maximum distance in blocks a Mechanical Arm can reach across.";
		static String verticalVaultCapacity = "The total amount of stacks a vault can hold per block in size.";
	}

}
