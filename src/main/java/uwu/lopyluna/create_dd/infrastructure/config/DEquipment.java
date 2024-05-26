package uwu.lopyluna.create_dd.infrastructure.config;

import com.simibubi.create.foundation.config.ConfigBase;

public class DEquipment extends ConfigBase {
	//Plans to change this
	//public final ConfigInt maxSymmetryWandRange = i(50, 10, "maxSymmetryWandRange", Comments.symmetryRange);

//	public final ConfigInt excavationDrillMaximumBlocks = i(64, "excavationDrillMaximumBlocks", Comments.excavationDrillMaximumBlocks);
//	public final ConfigInt deforestingSawMaximumBlocks = i(128, "deforestingSawMaximumBlocks", Comments.deforestingSawMaximumBlocks);
//	public final ConfigInt backpackRange = i(4, 1, "backpackRange", Comments.backpackRange);
//	public final ConfigInt zapperUndoLogLength = i(5, 0, "zapperUndoLogLength", Comments.zapperUndoLogLength);

	@Override
	public String getName() {
		return "equipment";
	}

	private static class Comments {
		//static String symmetryRange = "The Maximum Distance to an active mirror for the symmetry wand to trigger.";
		static String excavationDrillMaximumBlocks =
				"The Maximum Amount of Blocks a Excavation Drill can Handle.";
		static String deforestingSawMaximumBlocks =
				"The Maximum Amount of Blocks a Deforesting Saw can Handle.";
		static String backpackRange =
				"The Maximum Distance at which a Backpack can interact with Players' Inventories.";
		static String zapperUndoLogLength =
				"The Maximum Amount of operations a Block Zapper can remember for undoing. (0 to disable undo)";
	}

}
