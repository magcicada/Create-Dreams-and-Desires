package uwu.lopyluna.create_dd.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.items.equipment.gilded_rose_tools.*;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.helper.ItemTransformer.itemEntry;

@SuppressWarnings({"unused"})
public class DesiresItems {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
	}

	public static final ItemEntry<Item>
			OVERBURDEN_BLEND = itemEntry("overburden_blend");


	public static final ItemEntry<GRSwordItem> GILDED_ROSE_SWORD = REGISTRATE.item("gilded_rose_sword",
					p -> new GRSwordItem(DesireTiers.GILDED_ROSE, 3, -2.4F, p))
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/handheld")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.tag)
			.tag(DesiresTags.AllItemTags.SWORD.tag)
			.register();

	public static final ItemEntry<GRPickaxeItem> GILDED_ROSE_PICKAXE = REGISTRATE.item("gilded_rose_pickaxe",
					p -> new GRPickaxeItem(DesireTiers.GILDED_ROSE, 1, -2.8F, p))
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/handheld")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.tag)
			.tag(DesiresTags.AllItemTags.PICKAXE.tag)
			.register();

	public static final ItemEntry<GRAxeItem> GILDED_ROSE_AXE = REGISTRATE.item("gilded_rose_axe",
					p -> new GRAxeItem(DesireTiers.GILDED_ROSE, 6.0F, -3.0F, p))
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/handheld")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.tag)
			.tag(DesiresTags.AllItemTags.AXE.tag)
			.register();

	public static final ItemEntry<GRShovelItem> GILDED_ROSE_SHOVEL = REGISTRATE.item("gilded_rose_shovel",
					p -> new GRShovelItem(DesireTiers.GILDED_ROSE, 1.5F, -3.0F, p))
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/handheld")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.tag)
			.tag(DesiresTags.AllItemTags.SHOVEL.tag)
			.register();

	public static final ItemEntry<GRHoeItem> GILDED_ROSE_HOE = REGISTRATE.item("gilded_rose_hoe",
					p -> new GRHoeItem(DesireTiers.GILDED_ROSE, -2, -3.0F, p))
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/handheld")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.tag)
			.tag(DesiresTags.AllItemTags.HOE.tag)
			.register();



	// Load this class

	public static void register() {}

}
