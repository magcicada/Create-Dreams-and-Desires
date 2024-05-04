package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.item.CombustibleItem;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.items.equipment.NameableRecordItem;
import uwu.lopyluna.create_dd.content.items.equipment.deforester_saw.DeforesterSawItem;
import uwu.lopyluna.create_dd.content.items.equipment.excavation_drill.ExcavationDrillItem;
import uwu.lopyluna.create_dd.content.items.equipment.gilded_rose_tools.*;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.DesiresTags.forgeItemTag;
import static uwu.lopyluna.create_dd.registry.DesiresTags.optionalTag;

@SuppressWarnings({"unused", "deprecation"})
public class DesiresItems {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
	}

	public static final ItemEntry<Item> BURY_BLEND = REGISTRATE.item("lapis_alloy", Item::new)
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/generated")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/bury_blend")))
			.tag(forgeItemTag("ingots/bury_blend"), forgeItemTag("ingots"), forgeItemTag("bury_blends"))
			.recipe((c, p) -> ShapelessRecipeBuilder.shapeless(c.get(), 2)
					.requires(Items.LAPIS_LAZULI)
					.requires(AllItems.CRUSHED_IRON.get())
					.requires(AllItems.CRUSHED_IRON.get())
					.requires(Items.LAPIS_LAZULI)
					.unlockedBy("has_" + getItemName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
					.save(p, DesiresCreate.asResource("crafting/bury_blend")))
			.lang("Bury Blend")
			.register();


	public static final ItemEntry<Item> DIAMOND_SHARD = REGISTRATE.item("diamond_shard", Item::new)
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/generated")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.tag(forgeItemTag("nuggets/diamond"), forgeItemTag("nuggets"))
			.recipe((c, p) -> {
				Item output = Items.DIAMOND;
				ShapedRecipeBuilder.shaped(output, 1)
					.pattern("CC")
					.pattern("CC")
						.define('C', c.get())
						.unlockedBy("has_" + getItemName(output), has(output))
						.save(p, DesiresCreate.asResource("crafting/" + getItemName(output) + "_from_" + c.getName()));
				ShapelessRecipeBuilder.shapeless(c.get(), 4)
						.requires(output)
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + getItemName(output)));
			})
			.register();

	public static final ItemEntry<CombustibleItem> COAL_PIECE = REGISTRATE.item("coal_piece", CombustibleItem::new)
			.onRegister(i -> i.setBurnTime(200))
			.model((c, p) -> p.withExistingParent(c.getId().getPath(),
					new ResourceLocation("item/generated")).texture("layer0",
					new ResourceLocation(DesiresCreate.MOD_ID,"item/" + c.getId().getPath())))
			.tag(forgeItemTag("nuggets/coal"), forgeItemTag("nuggets"))
			.recipe((c, p) -> {
				Item output = Items.COAL;
				ShapelessRecipeBuilder.shapeless(output, 1)
						.requires(c.get()).requires(c.get())
						.requires(c.get()).requires(c.get())
						.requires(c.get()).requires(c.get())
						.requires(c.get()).requires(c.get())
						.unlockedBy("has_" + getItemName(output), has(output))
						.save(p, DesiresCreate.asResource("crafting/" + getItemName(output) + "_from_" + c.getName()));
				ShapelessRecipeBuilder.shapeless(c.get(), 8)
						.requires(output)
						.unlockedBy("has_" + c.getName(), has(c.get()))
						.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + getItemName(output)));
			})
			.register();

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


	public static final ItemEntry<ExcavationDrillItem> EXCAVATION_DRILL = REGISTRATE.item("excavation_drill", ExcavationDrillItem::new)
			.model(AssetLookup.itemModelWithPartials())
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.PICKAXE.tag)
			.register();

	public static final ItemEntry<DeforesterSawItem> DEFORESTER_SAW = REGISTRATE.item("deforester_saw", DeforesterSawItem::new)
			.model(AssetLookup.itemModelWithPartials())
			.properties(p -> p.rarity(Rarity.UNCOMMON))
			.tag(DesiresTags.AllItemTags.AXE.tag)
			.register();

	public static final ItemEntry<NameableRecordItem> MUSIC_DISC_WALTZ_OF_THE_FLOWERS = REGISTRATE.item("music_disc_waltz_of_the_flowers",
					p -> new NameableRecordItem(10, DesiresSoundEvents.MUSIC_DISC_WALTZ_OF_THE_FLOWERS, p, 16600, "Tchaikovsky - Waltz of the Flowers"))
			.properties(p -> p.rarity(Rarity.RARE)
					.stacksTo(1))
			.recipe((c, p) -> p.stonecutting(DataIngredient.items(AllItems.ROSE_QUARTZ), c, 1))
			.tag(optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("minecraft", "music_discs")))
			.tag(optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("minecraft", "creeper_drop_music_discs")))
			.lang("Music Disc")
			.register();


	protected static String getItemName(ItemLike pItemLike) {
		return Registry.ITEM.getKey(pItemLike.asItem()).getPath();
	}

	// Load this class

	public static void register() {}

}
