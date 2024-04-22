package uwu.lopyluna.create_dd.registry;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import uwu.lopyluna.create_dd.DesiresCreate;

import java.util.*;

@SuppressWarnings({"all"})
public class DesiresPartialModels {

	public static final PartialModel

		RUBBER_BELT_PULLEY = block("rubber_belt_pulley"),
		RUBBER_BELT_START = block("rubber_belt/start"),
		RUBBER_BELT_MIDDLE = block("rubber_belt/middle"),
		RUBBER_BELT_END = block("rubber_belt/end"),
		RUBBER_BELT_START_BOTTOM = block("rubber_belt/start_bottom"),
		RUBBER_BELT_MIDDLE_BOTTOM = block("rubber_belt/middle_bottom"),
		RUBBER_BELT_END_BOTTOM = block("rubber_belt/end_bottom"),
		RUBBER_BELT_DIAGONAL_START = block("rubber_belt/diagonal_start"),
		RUBBER_BELT_DIAGONAL_MIDDLE = block("rubber_belt/diagonal_middle"),
		RUBBER_BELT_DIAGONAL_END = block("rubber_belt/diagonal_end"),
		ANDESITE_RUBBER_BELT_COVER_X = block("rubber_belt_cover/andesite_belt_cover_x"),
		BRASS_RUBBER_BELT_COVER_X = block("rubber_belt_cover/brass_belt_cover_x"),
		INDUSTRIAL_RUBBER_BELT_COVER_X = block("rubber_belt_cover/brass_belt_cover_x"),
		ANDESITE_RUBBER_BELT_COVER_Z = block("rubber_belt_cover/andesite_belt_cover_z"),
		BRASS_RUBBER_BELT_COVER_Z = block("rubber_belt_cover/brass_belt_cover_z"),
		INDUSTRIAL_RUBBER_BELT_COVER_Z = block("rubber_belt_cover/brass_belt_cover_z"),

		INDUSTRIAL_FAN_INNER = block("industrial_fan/propeller"),
		COG_CRANK_HANDLE = block("cog_crank/handle"),
		HYDRAULIC_PRESS_HEAD = block("hydraulic_press/head"),
		LUMBER_SAW_BLADE_HORIZONTAL_ACTIVE = block("lumber_saw/blade_horizontal_active"),
		LUMBER_SAW_BLADE_HORIZONTAL_INACTIVE = block("lumber_saw/blade_horizontal_inactive"),
		LUMBER_SAW_BLADE_HORIZONTAL_REVERSED = block("lumber_saw/blade_horizontal_reversed"),
		LUMBER_SAW_BLADE_VERTICAL_ACTIVE = block("lumber_saw/blade_vertical_active"),
		LUMBER_SAW_BLADE_VERTICAL_INACTIVE = block("lumber_saw/blade_vertical_inactive"),
		LUMBER_SAW_BLADE_VERTICAL_REVERSED = block("lumber_saw/blade_vertical_reversed"),
		WEAKENED_HARVESTER_BLADE = block("weakened_harvester/blade"),
		RUBBER_BELT_FUNNEL_FLAP = block("rubber_belt_funnel/flap"),
		RUBBER_BELT_TUNNEL_FLAP = block("rubber_belt_tunnel/flap"),

		BACKPACK_PACKS = block("backpack/packs"),

		ENGINE_PISTON = block("furnace_engine/piston"),
		ENGINE_LINKAGE = block("furnace_engine/linkage"),
		ENGINE_CONNECTOR = block("furnace_engine/shaft_connector"),
		BOILER_GAUGE = block("furnace_engine/gauge"),
		BOILER_GAUGE_DIAL = block("furnace_engine/gauge_dial");
	;

	public static final Map<DyeColor, PartialModel> BACKPACK_FLAP = new EnumMap<>(DyeColor.class);
	public static final Map<ResourceLocation, Couple<PartialModel>> FOLDING_DOORS = new HashMap<>();

	static {
		for (DyeColor color : DyeColor.values())
			BACKPACK_FLAP.put(color, block("backpack/flap/" + Lang.asId(color.name())));
		
		//putFoldingDoor("andesite_door");
	}

	private static void putFoldingDoor(String path) {
		FOLDING_DOORS.put(DesiresCreate.asResource(path),
			Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
	}

	private static PartialModel block(String path) {
		return new PartialModel(DesiresCreate.asResource("block/" + path));
	}

	private static PartialModel entity(String path) {
		return new PartialModel(DesiresCreate.asResource("entity/" + path));
	}

	public static void init() {
		// init static fields
	}

}
