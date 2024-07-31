package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

@SuppressWarnings({"unused"})
public class DRecipes extends ConfigBase {

	public final ConfigBool hydraulicBulkPressing = b(true, "hydraulicBulkPressing", Comments.hydraulicBulkPressing);
	public final ConfigInt hydraulicLavaDrainPressing = i(250, 1, 1000, "hydraulicLavaDrainPressing", Comments.hydraulicLavaDrainPressing);
	public final ConfigInt hydraulicFluidDrainPressing = i(1000, 1, 1000, "hydraulicFluidDrainPressing", Comments.hydraulicFluidDrainPressing);
	//public final ConfigBool lumberBulkCutting = b(true, "lumberBulkCutting", Comments.lumberBulkCutting);*
	//public final ConfigBool allowShapedSquareInHyPress = b(false, "allowShapedSquareInHyPress", Comments.allowShapedSquareInHyPress);*
	//public final ConfigBool allowStonecuttingOnLumberSaw = b(false, "allowStonecuttingOnLumberSaw", Comments.allowStonecuttingOnLumberSaw);*
	//public final ConfigBool allowWoodcuttingOnLumberSaw = b(true, "allowWoodcuttingOnLumberSaw", Comments.allowWoodcuttingOnLumberSaw);*
	//public final ConfigBool displayLogStrippingRecipes = b(true, "displayLogStrippingRecipes", Comments.displayLogStrippingRecipes);*

	@Override
	public String getName() {
		return "recipes";
	}

	private static class Comments {
		static String hydraulicBulkPressing = "Allow the Hydraulic Press to process entire stacks at a time.";
		static String hydraulicLavaDrainPressing = "Value Hydraulic Press to drain amount of lava of each bonk.";
		static String hydraulicFluidDrainPressing = "Value Hydraulic Press to drain amount of fluid of each bonk.";


		static String lumberBulkCutting = "Allow the Lumber Saw to process entire stacks at a time.";
		static String allowShapedSquareInHyPress =
			"Allow any single-ingredient 2x2 or 3x3 crafting recipes to be processed by a Mechanical Press + Basin.'This will not be Bulk'";
		static String allowStonecuttingOnLumberSaw =
			"Allow any stonecutting recipes to be processed by a Lumber Saw.";
		static String allowWoodcuttingOnLumberSaw =
			"Allow any Druidcraft woodcutter recipes to be processed by a Lumber Saw.";
		static String displayLogStrippingRecipes = "Display vanilla Log-stripping interactions in JEI.";
	}

}
