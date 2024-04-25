package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankBlock;
import uwu.lopyluna.create_dd.content.blocks.curiosities.FanSailBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileCTBehaviour;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileItem;

import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

public class DesiresBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
	}
	//Horizontal Tank -> Fluid Keg
	//Vertical Vault -> Item Stockpile

	public static final BlockEntry<CogCrankBlock> COG_CRANK = REGISTRATE.block("cog_crank", CogCrankBlock::new)
			.initialProperties(SharedProperties::wooden)
			.properties(p -> p.color(MaterialColor.PODZOL))
			.transform(axeOrPickaxe())
			.blockstate(BlockStateGen.axisBlockProvider(true))
			.transform(BlockStressDefaults.setCapacity(8.0))
			.transform(BlockStressDefaults.setGeneratorSpeed(CogCrankBlock::getSpeedRange))
			.tag(AllTags.AllBlockTags.BRITTLE.tag)
			.recipe((ctx, prov) -> ShapelessRecipeBuilder.shapeless(ctx.getEntry(), 1)
					.requires(AllBlocks.HAND_CRANK.get())
					.requires(AllBlocks.COGWHEEL.get())
					.unlockedBy("has_item", RegistrateRecipeProvider.has(ctx.get()))
					.save(prov))
			.onRegister(ItemUseOverrides::addBlock)
			.item()
			.transform(customItemModel())
			.register();

	public static final BlockEntry<ItemStockpileBlock> ITEM_STOCKPILE = REGISTRATE.block("item_stockpile", ItemStockpileBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_BLUE)
					.sound(SoundType.NETHERITE_BLOCK)
					.explosionResistance(1200))
			.transform(pickaxeOnly())
			.blockstate((c, p) -> p.getVariantBuilder(c.get())
					.forAllStates(s -> ConfiguredModel.builder()
							.modelFile(AssetLookup.standardModel(c, p))
							.build()))
			.onRegister(connectedTextures(ItemStockpileCTBehaviour::new))
			.item(ItemStockpileItem::new)
			.build()
			.register();


	public static final BlockEntry<FanSailBlock> SPLASHING_SAIL =
			REGISTRATE.block("splashing_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SPLASHING.tag)
					.simpleItem()
					.register();

	public static final BlockEntry<FanSailBlock> HAUNTING_SAIL =
			REGISTRATE.block("haunting_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.properties(p -> p.lightLevel(s -> 8))
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_HAUNTING.tag)
					.simpleItem()
					.register();

	public static final BlockEntry<FanSailBlock> SMOKING_SAIL =
			REGISTRATE.block("smoking_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.properties(p -> p.lightLevel(s -> 8))
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SMOKING.tag)
					.simpleItem()
					.register();

	public static final BlockEntry<FanSailBlock> BLASTING_SAIL =
			REGISTRATE.block("blasting_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.properties(p -> p.lightLevel(s -> 12))
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_BLASTING.tag)
					.simpleItem()
					.register();

	public static final BlockEntry<FanSailBlock> SEETHING_SAIL =
			REGISTRATE.block("seething_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.properties(p -> p.lightLevel(s -> 15))
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SEETHING.tag)
					.simpleItem()
					.register();

	public static final BlockEntry<FanSailBlock> FREEZING_SAIL =
			REGISTRATE.block("freezing_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_FREEZING.tag)
					.simpleItem()
					.register();


	// Load this class

	public static void register() {}

}
