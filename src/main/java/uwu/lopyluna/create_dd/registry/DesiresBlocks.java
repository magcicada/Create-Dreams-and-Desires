package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.motor.CreativeMotorGenerator;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.util.ForgeSoundType;
import uwu.lopyluna.create_dd.content.blocks.others.BoreBlock;
import uwu.lopyluna.create_dd.content.blocks.others.BoreBlockMovementBehaviour;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearBlockItem;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearStructuralBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.hydraulic_press.HydraulicPressBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.industrial_fan_block.IndustrialFanBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankBlock;
import uwu.lopyluna.create_dd.content.blocks.others.FanSailBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.FurnaceEngineBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.FurnaceEngineGenerator;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.PoweredFlywheelBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.kinetic_motor.KineticMotorBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.transmission.InverseBoxBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegCTBehaviour;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegItem;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileCTBehaviour;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileItem;

import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.DesiresPaletteBlocks.rawRubberDecorTag;
import static uwu.lopyluna.create_dd.registry.DesiresPaletteBlocks.rubberDecorTag;

@SuppressWarnings({"unused", "removal", "all"})
public class DesiresBlocks {

	public static final BlockEntry<Block> RAW_RUBBER_BLOCK = REGISTRATE.block("raw_rubber_block", Block::new)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_WHITE))
			.properties(p -> p.sound(new ForgeSoundType(0.9f, .75f, () -> DesiresSoundEvents.RUBBER_BREAK.get(),
					() -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(),
					() -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
			.properties(p -> p.strength(0.5f,1.5f))
			.lang("Block of Raw Rubber")
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.tag(rawRubberDecorTag)
			.build()
			.register();

	public static final BlockEntry<Block> RUBBER_BLOCK = REGISTRATE.block("rubber_block", Block::new)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
			.properties(p -> p.sound(new ForgeSoundType(0.9f, .6f, () -> DesiresSoundEvents.RUBBER_BREAK.get(),
					() -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(),
					() -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
			.properties(p -> p.strength(0.5f,1.5f))
			.lang("Block of Rubber")
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.tag(rubberDecorTag)
			.build()
			.register();

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
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
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
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.build()
			.register();

	public static final BlockEntry<CasingBlock> HYDRAULIC_CASING = REGISTRATE.block("hydraulic_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> DesiresSpriteShifts.HYDRAULIC_CASING))
			.properties(p -> p.color(MaterialColor.COLOR_ORANGE)
					.requiresCorrectToolForDrops()
					.sound(SoundType.COPPER))
			.transform(pickaxeOnly())
			.lang("Hydraulic Casing")
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.build()
			.register();

	public static final BlockEntry<CasingBlock> INDUSTRIAL_CASING = REGISTRATE.block("industrial_casing", CasingBlock::new)
			.transform(BuilderTransformers.casing(() -> DesiresSpriteShifts.INDUSTRIAL_CASING))
			.properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN)
					.requiresCorrectToolForDrops()
					.sound(SoundType.NETHERITE_BLOCK))
			.transform(pickaxeOnly())
			.lang("Industrial Casing")
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.build()
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
			.lang("Industrial Fan")
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.transform(customItemModel())
			.register();

	public static final BlockEntry<HydraulicPressBlock> HYDRAULIC_PRESS = REGISTRATE.block("hydraulic_press", HydraulicPressBlock::new)
			.initialProperties(SharedProperties::copperMetal)
			.properties(BlockBehaviour.Properties::noOcclusion)
			.properties(p -> p.noOcclusion().color(MaterialColor.TERRACOTTA_ORANGE))
			.transform(pickaxeOnly())
			.blockstate(BlockStateGen.horizontalBlockProvider(true))
			.transform(BlockStressDefaults.setImpact(8.0))
			.item(AssemblyOperatorBlockItem::new)
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.transform(customItemModel())
			.register();

	public static final BlockEntry<BoreBlock> BORE_BLOCK = REGISTRATE.block("bore_block", BoreBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.color(MaterialColor.STONE))
			.properties(p -> p.sound(new ForgeSoundType(0.9f, 1.25f, () -> SoundEvents.NETHERITE_BLOCK_BREAK,
					() -> SoundEvents.NETHERITE_BLOCK_STEP, () -> SoundEvents.NETHERITE_BLOCK_PLACE,
					() -> SoundEvents.NETHERITE_BLOCK_HIT, () -> SoundEvents.NETHERITE_BLOCK_FALL)))
			.onRegister(movementBehaviour(new BoreBlockMovementBehaviour()))
			.transform(pickaxeOnly())
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.build()
			.register();

	public static final BlockEntry<InverseBoxBlock> INVERSE_BOX = REGISTRATE.block("inverse_box", InverseBoxBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.noOcclusion().color(MaterialColor.PODZOL))
			.addLayer(() -> RenderType::cutoutMipped)
			.transform(BlockStressDefaults.setNoImpact())
			.transform(axeOrPickaxe())
			.blockstate(BlockStateGen.axisBlockProvider(true))
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
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
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.transform(customItemModel())
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
					.transform(BlockStressDefaults.setCapacity(256.0))
					.transform(BlockStressDefaults.setGeneratorSpeed(FurnaceEngineBlock::getSpeedRange))
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
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

	public static final BlockEntry<GiantGearBlock> GIANT_GEAR = REGISTRATE.block("giant_gear", GiantGearBlock::new)
			.initialProperties(SharedProperties::netheriteMetal)
			.properties(p -> p.noOcclusion().sound(SoundType.METAL).color(MaterialColor.COLOR_YELLOW))
			.transform(pickaxeOnly())
			.transform(BlockStressDefaults.setImpact(8.0))
			.blockstate(BlockStateGen.axisBlockProvider(true))
			.item(GiantGearBlockItem::new)
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
			.transform(customItemModel())
			.register();


	public static final BlockEntry<GiantGearStructuralBlock> GIANT_GEAR_STRUCTURAL = REGISTRATE.block("giant_gear_structure", GiantGearStructuralBlock::new)
			.initialProperties(SharedProperties::netheriteMetal)
			.blockstate((c, p) -> p.getVariantBuilder(c.get())
					.forAllStatesExcept(BlockStateGen.mapToAir(p), GiantGearStructuralBlock.FACING))
			.properties(p -> p.noOcclusion().sound(SoundType.METAL).color(MaterialColor.COLOR_YELLOW))
			.transform(pickaxeOnly())
			.lang("Giant Gear")
			.register();

	public static final BlockEntry<KineticMotorBlock> KINETIC_MOTOR = REGISTRATE
			.block("kinetic_motor", KineticMotorBlock::new)
			.initialProperties(SharedProperties::stone)
			.properties(p -> p.color(MaterialColor.COLOR_GRAY))
			.tag(AllTags.AllBlockTags.SAFE_NBT.tag)
			.transform(axeOrPickaxe())
			.blockstate(new CreativeMotorGenerator()::generate)
			.transform(BlockStressDefaults.setCapacity(48))
			.transform(BlockStressDefaults.setGeneratorSpeed(() -> Couple.create(0, 32)))
			.item()
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
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
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
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
			.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
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
					.item()
					.tab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB)
					.build()
					.register();


	// Load this class

	public static void register() {}

}
