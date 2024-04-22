package uwu.lopyluna.create_dd.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.DesiresTags.forgeItemTag;
import static uwu.lopyluna.create_dd.registry.helper.ItemTransformer.itemEntry;
import static uwu.lopyluna.create_dd.registry.helper.ItemTransformer.itemEntryTagged;

public class DesiresItems {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
	}


	public static final ItemEntry<Item>
			OVERBURDEN_BLEND = itemEntry("overburden_blend");


	//public static final ItemEntry<Item>
	//		RAW_TIN = itemEntryTagged("raw_tin", forgeItemTag("raw_materials/tin"), forgeItemTag("raw_materials"));



	// Load this class

	public static void register() {}

}
