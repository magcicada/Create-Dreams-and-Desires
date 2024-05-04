package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.util.ForgeSoundType;
import uwu.lopyluna.create_dd.content.blocks.kinetics.HydraulicPress.HydraulicPressBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.IndustrialFanBlock.IndustrialFanBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankBlock;
import uwu.lopyluna.create_dd.content.blocks.curiosities.FanSailBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.FurnaceEngineBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.FurnaceEngineGenerator;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.PoweredFlywheelBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegCTBehaviour;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegItem;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileCTBehaviour;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileItem;

import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

@SuppressWarnings({"unused", "removal", "all"})
public class DesiresBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
	}

	public static final BlockEntry<CasingBlock> CREATIVE_CASING = REGISTRATE.block("creative_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> DesiresSpriteShifts.CREATIVE_CASING))
			.properties(p -> p.color(MaterialColor.COLOR_BLACK)
					.requiresCorrectToolForDrops())
			.properties(p -> p.sound(new ForgeSoundType(0.8f, .8f, () -> DesiresSoundEvents.CREATVEDITE_BREAK.get(),
					() -> DesiresSoundEvents.CREATVEDITE_STEP.get(), () -> DesiresSoundEvents.CREATVEDITE_PLACE.get(),
					() -> DesiresSoundEvents.CREATVEDITE_HIT.get(), () -> DesiresSoundEvents.CREATVEDITE_FALL.get())))
			.transform(pickaxeOnly())
			.properties(p -> p.lightLevel($ -> 5))
			.lang("Creative Casing")
			.simpleItem()
			.item()
			.properties(p -> p.rarity(Rarity.EPIC))
			.build()
			.register();

	public static final BlockEntry<CasingBlock> OVERBURDEN_CASING = REGISTRATE.block("overburden_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> DesiresSpriteShifts.OVERBURDEN_CASING))
			.properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_BLUE)
					.requiresCorrectToolForDrops()
					.sound(SoundType.NETHERITE_BLOCK))
			.transform(pickaxeOnly())
			.lang("Overburden Casing")
			.register();

	public static final BlockEntry<CasingBlock> HYDRAULIC_CASING = REGISTRATE.block("hydraulic_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> DesiresSpriteShifts.HYDRAULIC_CASING))
			.properties(p -> p.color(MaterialColor.COLOR_ORANGE)
					.requiresCorrectToolForDrops()
					.sound(SoundType.COPPER))
			.transform(pickaxeOnly())
			.lang("Hydraulic Casing")
			.register();

	public static final BlockEntry<CasingBlock> INDUSTRIAL_CASING = REGISTRATE.block("industrial_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> DesiresSpriteShifts.INDUSTRIAL_CASING))
			.properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN)
					.requiresCorrectToolForDrops()
					.sound(SoundType.NETHERITE_BLOCK))
			.transform(pickaxeOnly())
			.lang("Industrial Casing")
			.register();

	public static final BlockEntry<IndustrialFanBlock> INDUSTRIAL_FAN = REGISTRATE.block("industrial_fan", IndustrialFanBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.noOcclusion()
					.color(MaterialColor.TERRACOTTA_CYAN)
					.requiresCorrectToolForDrops()
					.sound(SoundType.NETHERITE_BLOCK))
			.blockstate(BlockStateGen.directionalBlockProvider(true))
			.addLayer(() -> RenderType::cutoutMipped)
			.transform(pickaxeOnly())
			.transform(BlockStressDefaults.setImpact(4.0))
			.transform(BlockStressDefaults.setCapacity(16))
			.item()
			.transform(customItemModel())
			.lang("Industrial Fan")
			.register();

	public static final BlockEntry<HydraulicPressBlock> HYDRAULIC_PRESS = REGISTRATE.block("hydraulic_press", HydraulicPressBlock::new)
			.initialProperties(SharedProperties::copperMetal)
			.properties(BlockBehaviour.Properties::noOcclusion)
			.properties(p -> p.noOcclusion().color(MaterialColor.TERRACOTTA_ORANGE))
			.transform(pickaxeOnly())
			.blockstate(BlockStateGen.horizontalBlockProvider(true))
			.transform(BlockStressDefaults.setImpact(8.0))
			.item(AssemblyOperatorBlockItem::new)
			.transform(customItemModel())
			.register();


	public static final BlockEntry<CogCrankBlock> COG_CRANK = REGISTRATE.block("cog_crank", CogCrankBlock::new)
			.initialProperties(SharedProperties::wooden)
			.properties(p -> p.color(MaterialColor.PODZOL))
			.transform(axeOrPickaxe())
			.blockstate(BlockStateGen.directionalBlockProvider(true))
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

	public static final BlockEntry<FluidKegBlock> FLUID_KEG = REGISTRATE.block("fluid_keg", FluidKegBlock::new)
			.initialProperties(SharedProperties::copperMetal)
			.properties(p -> p.noOcclusion().isRedstoneConductor((p1, p2, p3) -> true))
			.transform(pickaxeOnly())
			.blockstate((c, p) -> p.getVariantBuilder(c.get())
					.forAllStates(s -> ConfiguredModel.builder()
							.modelFile(AssetLookup.standardModel(c, p))
							.rotationY(s.getValue(FluidKegBlock.HORIZONTAL_AXIS) == Direction.Axis.X ? 90 : 0)
							.build()))
			.onRegister(connectedTextures(FluidKegCTBehaviour::new))
			.item(FluidKegItem::new)
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
					.lang("Splashing Catalyst Sail")
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
					.lang("Haunting Catalyst Sail")
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
					.lang("Smoking Catalyst Sail")
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
					.lang("Blasting Catalyst Sail")
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
					.tag(DesiresTags.AllBlockTags.INDUSTRIAL_FAN_HEATER.tag)
					.lang("Seething Catalyst Sail")
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
					.lang("Freezing Catalyst Sail")
					.simpleItem()
					.register();

	public static final BlockEntry<FanSailBlock> SANDING_SAIL =
			REGISTRATE.block("sanding_sail", FanSailBlock::sail)
					.initialProperties(SharedProperties::wooden)
					.properties(p -> p.color(MaterialColor.DIRT))
					.properties(p -> p.sound(SoundType.SCAFFOLDING)
							.noOcclusion())
					.transform(axeOnly())
					.blockstate(BlockStateGen.directionalBlockProvider(false))
					.tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
					.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
					.tag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SANDING.tag)
					.lang("Sanding Catalyst Sail")
					.simpleItem()
					.register();

	public static final BlockEntry<FurnaceEngineBlock> FURNACE_ENGINE =
			REGISTRATE.block("furnace_engine", FurnaceEngineBlock::new)
					.initialProperties(SharedProperties::softMetal)
					.properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN)
							.sound(SoundType.NETHERITE_BLOCK))
					.properties(BlockBehaviour.Properties::noOcclusion)
					.transform(pickaxeOnly())
					.tag(AllTags.AllBlockTags.BRITTLE.tag)
					.blockstate(new FurnaceEngineGenerator()::generate)
					.transform(BlockStressDefaults.setCapacity(128.0))
					.transform(BlockStressDefaults.setGeneratorSpeed(FurnaceEngineBlock::getSpeedRange))
					.item()
					.transform(ModelGen.customItemModel())
					.register();

	public static final BlockEntry<PoweredFlywheelBlock> POWERED_FLYWHEEL =
			REGISTRATE.block("powered_flywheel", PoweredFlywheelBlock::new)
					.initialProperties(SharedProperties::softMetal)
					.properties(p -> p.color(MaterialColor.METAL))
					.transform(pickaxeOnly())
					.blockstate(BlockStateGen.axisBlockProvider(false))
					.loot((lt, block) -> lt.dropOther(block, AllBlocks.FLYWHEEL.get()))
					.register();



	// Load this class

	public static void register() {}

}
