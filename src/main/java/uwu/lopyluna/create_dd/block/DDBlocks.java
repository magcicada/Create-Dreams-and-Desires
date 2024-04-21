package uwu.lopyluna.create_dd.block;

import com.simibubi.create.*;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DDCreate;
import uwu.lopyluna.create_dd.DDTags;
import uwu.lopyluna.create_dd.block.BlockProperties.*;
import uwu.lopyluna.create_dd.block.BlockProperties.accelerator_motor.AcceleratorMotorBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.cog_crank.CogCrankBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.FlywheelBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.FlywheelGenerator;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.engine.FurnaceEngineBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.industrial_fan.IndustrialFanBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.copycat.BlockcopycatBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.copycat.BlockcopycatBlockModel;
import uwu.lopyluna.create_dd.block.BlockProperties.copycat.BlockcopycatSlab;
import uwu.lopyluna.create_dd.block.BlockProperties.copycat.BlockcopycatSlabModel;
import uwu.lopyluna.create_dd.block.BlockProperties.door.YIPPEESlidingDoorBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.hydraulic_press.HydraulicPressBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.kinetic_motor.KineticMotorBlock;
import uwu.lopyluna.create_dd.block.BlockProperties.wood.*;
import uwu.lopyluna.create_dd.block.BlockResources.DDBlockSpriteShifts;
import uwu.lopyluna.create_dd.creative.DDItemTab;
import uwu.lopyluna.create_dd.sounds.DDSoundEvents;
import uwu.lopyluna.create_dd.worldgen.Features.tree.RubberTreeGrower;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;
import static uwu.lopyluna.create_dd.DDCreate.REGISTRATE;

@SuppressWarnings({"unused", "removal", "all"})
public class DDBlocks {
    
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DDCreate.MOD_ID);
    
    static {
        REGISTRATE.creativeModeTab(() -> DDItemTab.BASE_CREATIVE_TAB);
    }

    public static final BlockEntry<CasingBlock> hydraulic_casing = REGISTRATE.block("hydraulic_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> DDBlockSpriteShifts.HYDRAULIC_CASING))
            .properties(p -> p.color(MaterialColor.COLOR_ORANGE))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.COPPER))
            .lang("Hydraulic Casing")
            .register();

    public static final BlockEntry<CasingBlock> industrial_casing = REGISTRATE.block("industrial_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> DDBlockSpriteShifts.INDUSTRIAL_CASING))
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK))
            .lang("Industrial Casing")
            .register();

    public static final BlockEntry<CasingBlock> overburden_casing = REGISTRATE.block("overburden_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> DDBlockSpriteShifts.OVERBURDEN_CASING))
            .properties(p -> p.color(MaterialColor.TERRACOTTA_LIGHT_BLUE))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK))
            .lang("Overburden Casing")
            .register();

    //MECHANICAL BLOCKS

    public static final BlockEntry<ReversedGearboxBlock> REVERSED_GEARSHIFT =
            REGISTRATE.block("reversed_gearshift", ReversedGearboxBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .properties(p -> p.color(MaterialColor.PODZOL))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(axeOrPickaxe())
                    .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, AssetLookup.forPowered(c, p)))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<IndustrialFanBlock> industrial_fan =
            REGISTRATE.block("industrial_fan", IndustrialFanBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.PODZOL)
                    .sound(SoundType.NETHERITE_BLOCK))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .blockstate(BlockStateGen.directionalBlockProvider(true))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(axeOrPickaxe())
                    .transform(BlockStressDefaults.setImpact(4.0))
                    .transform(BlockStressDefaults.setCapacity(16))
                    .simpleItem()
                    .register();

    public static final BlockEntry<FurnaceEngineBlock> FURNACE_ENGINE =
                    REGISTRATE.block("furnace_engine", FurnaceEngineBlock::new)
                    .initialProperties(SharedProperties::softMetal)
                    .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK))
                    .transform(axeOrPickaxe())
                    .tag(AllTags.AllBlockTags.BRITTLE.tag)
                    .blockstate(BlockStateGen.horizontalBlockProvider(true))
                    .transform(BlockStressDefaults.setCapacity(341.5))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<FlywheelBlock> FLYWHEEL =
            REGISTRATE.block("flywheel", FlywheelBlock::new)
                    .initialProperties(SharedProperties::softMetal)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK))
                    .transform(axeOrPickaxe())
                    .transform(BlockStressDefaults.setNoImpact())
                    .blockstate(new FlywheelGenerator()::generate)
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<HydraulicPressBlock> hydraulic_press =
            REGISTRATE.block("hydraulic_press", HydraulicPressBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.PODZOL)
                    .sound(SoundType.COPPER))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate(BlockStateGen.horizontalBlockProvider(true))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(BlockStressDefaults.setImpact(32.0))
                    .item(AssemblyOperatorBlockItem::new)
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<KineticMotorBlock> KINETIC_MOTOR =
            REGISTRATE.block("kinetic_motor", KineticMotorBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .transform(pickaxeOnly())
                    .transform(BlockStressDefaults.setCapacity(1.5))
                    .transform(BlockStressDefaults.setGeneratorSpeed(() -> Couple.create(0, 32)))
                    .simpleItem()
                    .register();

    public static final BlockEntry<AcceleratorMotorBlock> ACCELERATOR_MOTOR =
            REGISTRATE.block("accelerator_motor", AcceleratorMotorBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .transform(pickaxeOnly())
                    .transform(BlockStressDefaults.setCapacity(0))
                    .transform(BlockStressDefaults.setGeneratorSpeed(() -> Couple.create(0, 256)))
                    .simpleItem()
                    .register();

    public static final BlockEntry<CogCrankBlock> cogCrank = REGISTRATE.block("cog_crank", CogCrankBlock::new)
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

    public static final BlockEntry<FanSailBlock> splashing_sail =
            REGISTRATE.block("splashing_sail", FanSailBlock::sail)
                    .initialProperties(SharedProperties::wooden)
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .properties(p -> p.sound(SoundType.SCAFFOLDING)
                            .noOcclusion())
                    .transform(axeOnly())
                    .blockstate(BlockStateGen.directionalBlockProvider(false))
                    .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                    .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                    .tag(DDTags.AllBlockTags.splashing_type.tag)
                    .simpleItem()
                    .register();

    public static final BlockEntry<FanSailBlock> haunting_sail =
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
                    .tag(DDTags.AllBlockTags.haunting_type.tag)
                    .simpleItem()
                    .register();

    public static final BlockEntry<FanSailBlock> smoking_sail =
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
                    .tag(DDTags.AllBlockTags.smoking_type.tag)
                    .simpleItem()
                    .register();

    public static final BlockEntry<FanSailBlock> blasting_sail =
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
                    .tag(DDTags.AllBlockTags.blasting_type.tag)
                    .simpleItem()
                    .register();

    public static final BlockEntry<FanSailBlock> superheating_sail =
            REGISTRATE.block("superheating_sail", FanSailBlock::sail)
                    .initialProperties(SharedProperties::wooden)
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .properties(p -> p.sound(SoundType.SCAFFOLDING)
                            .noOcclusion())
                    .properties(p -> p.lightLevel(s -> 15))
                    .transform(axeOnly())
                    .blockstate(BlockStateGen.directionalBlockProvider(false))
                    .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                    .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                    .tag(DDTags.AllBlockTags.superheating_type.tag)
                    .simpleItem()
                    .register();

    public static final BlockEntry<FanSailBlock> freezing_sail =
            REGISTRATE.block("freezing_sail", FanSailBlock::sail)
                    .initialProperties(SharedProperties::wooden)
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .properties(p -> p.sound(SoundType.SCAFFOLDING)
                            .noOcclusion())
                    .transform(axeOnly())
                    .blockstate(BlockStateGen.directionalBlockProvider(false))
                    .tag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                    .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                    .tag(DDTags.AllBlockTags.freezing_type.tag)
                    .simpleItem()
                    .register();

    //Decoratives

    public static final BlockEntry<MetalScaffoldingBlock> TRAIN_SCAFFOLD =
            REGISTRATE.block("train_scaffolding", MetalScaffoldingBlock::new)
                    .transform(BuilderTransformers.scaffold("train",
                            () -> DataIngredient.tag(AllTags.forgeItemTag("plates/obsidian")), MaterialColor.COLOR_BLACK,
                            DDBlockSpriteShifts.TRAIN_SCAFFOLD, DDBlockSpriteShifts.TRAIN_SCAFFOLD_INSIDE, AllSpriteShifts.RAILWAY_CASING))
                    .register();
    public static final BlockEntry<MetalScaffoldingBlock> HYDRAULIC_SCAFFOLD =
            REGISTRATE.block("hydraulic_scaffolding", MetalScaffoldingBlock::new)
                    .transform(BuilderTransformers.scaffold("hydraulic",
                            () -> DataIngredient.tag(AllTags.forgeItemTag("ingots/copper")), MaterialColor.TERRACOTTA_YELLOW,
                            DDBlockSpriteShifts.HYDRAULIC_SCAFFOLD, DDBlockSpriteShifts.HYDRAULIC_SCAFFOLD_INSIDE, DDBlockSpriteShifts.HYDRAULIC_CASING))
                    .register();
    public static final BlockEntry<MetalScaffoldingBlock> OVERBURDEN_SCAFFOLD =
            REGISTRATE.block("overburden_scaffolding", MetalScaffoldingBlock::new)
                    .transform(BuilderTransformers.scaffold("overburden",
                            () -> DataIngredient.tag(AllTags.forgeItemTag("ingots/lapis_alloy")), MaterialColor.TERRACOTTA_YELLOW,
                            DDBlockSpriteShifts.OVERBURDEN_SCAFFOLD, DDBlockSpriteShifts.OVERBURDEN_SCAFFOLD_INSIDE, DDBlockSpriteShifts.OVERBURDEN_CASING))
                    .register();
    public static final BlockEntry<MetalScaffoldingBlock> INDUSTRIAL_SCAFFOLD =
            REGISTRATE.block("industrial_scaffolding", MetalScaffoldingBlock::new)
                    .transform(BuilderTransformers.scaffold("industrial",
                            () -> DataIngredient.tag(AllTags.forgeItemTag("ingots/industrial_iron")), MaterialColor.TERRACOTTA_YELLOW,
                            DDBlockSpriteShifts.INDUSTRIAL_SCAFFOLD, DDBlockSpriteShifts.INDUSTRIAL_SCAFFOLD_INSIDE, DDBlockSpriteShifts.INDUSTRIAL_CASING))
                    .register();

    public static final BlockEntry<BlockcopycatBlock> COPYCAT_BlOCK =
            REGISTRATE.block("copycat_block", BlockcopycatBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .onRegister(CreateRegistrate.blockModel(() -> BlockcopycatBlockModel::new))
                    .item()
                    .transform(customItemModel("copycat_base", "block"))
                    .register();

    public static final BlockEntry<BlockcopycatSlab> COPYCAT_SLAB =
            REGISTRATE.block("copycat_slab", BlockcopycatSlab::new)
                    .transform(BuilderTransformers.copycat())
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .onRegister(CreateRegistrate.blockModel(() -> BlockcopycatSlabModel::new))
                    .item()
                    .transform(customItemModel("copycat_base", "slab"))
                    .register();

    public static final BlockEntry<GlassBlock> ornate_iron_glass = REGISTRATE.block("ornate_iron_glass", GlassBlock::new)
            .transform(BuilderTransgender.blockv2(() -> DDBlockSpriteShifts.ornate_iron_glass, () -> DDBlockSpriteShifts.ornate_iron_glass_top))
            .initialProperties(() -> Blocks.GLASS)
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static final BlockEntry<ConnectedGlassPaneBlock> ornate_iron_glass_pane = REGISTRATE.block("ornate_iron_glass_pane", ConnectedGlassPaneBlock::new)
            .transform(BuilderTransgender.block(() -> DDBlockSpriteShifts.ornate_iron_glass))
            .initialProperties(() -> Blocks.GLASS)
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static final BlockEntry<Block> blueprint_block = REGISTRATE.block("blueprint_block", Block::new)
            .transform(BuilderTransgender.block(() -> DDBlockSpriteShifts.blueprint_block))
            .initialProperties(() -> Blocks.HAY_BLOCK)
            .properties(p -> p.color(MaterialColor.COLOR_LIGHT_BLUE))
            .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                    () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                    () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
            .properties(p -> p.strength(0.05f,0.5f))
            .lang("Block of Blueprint")
            .register();

    public static final BlockEntry<CarpetBlock> blueprint_carpet = REGISTRATE.block("blueprint_carpet", CarpetBlock::new)
            .transform(BuilderTransgender.block(() -> DDBlockSpriteShifts.blueprint_block))
            .initialProperties(() -> Blocks.HAY_BLOCK)
            .properties(p -> p.color(MaterialColor.COLOR_LIGHT_BLUE)
                    .noOcclusion())
            .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                    () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                    () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
            .properties(p -> p.strength(0.05f,0.5f))
            .lang("Block of Blueprint")
            .register();

    public static final BlockEntry<MysteriousCarpetBlock> mysterious_blueprint_carpet = REGISTRATE.block("mysterious_blueprint_carpet", MysteriousCarpetBlock::new)
            .transform(BuilderTransgender.block(() -> DDBlockSpriteShifts.blueprint_block))
            .initialProperties(() -> Blocks.HAY_BLOCK)
            .properties(p -> p.color(MaterialColor.COLOR_LIGHT_BLUE)
                    .noOcclusion()
                    .noCollission())
            .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                    () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                    () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
            .properties(p -> p.strength(0.05f,0.5f))
            .lang("Block of Blueprint")
            .register();

    //WOODSET BLOCKS


    //ROSE WOODSET

    public static final BlockEntry<CanBurnRotatedBlockPillar> rose_log = REGISTRATE.block("rose_log", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.OAK_LOG)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Log")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnRotatedBlockPillar> stripped_rose_log = REGISTRATE.block("stripped_rose_log", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Stripped Rose Log")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnRotatedBlockPillar> rose_wood = REGISTRATE.block("rose_wood", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnRotatedBlockPillar> stripped_rose_wood = REGISTRATE.block("stripped_rose_wood", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Stripped Rose Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnBlock> rose_planks = REGISTRATE.block("rose_planks", CanBurnBlock::new)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Planks")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnSlabBlock> rose_slab = REGISTRATE.block("rose_slab", CanBurnSlabBlock::new)
            .initialProperties(() -> Blocks.OAK_SLAB)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Slab")
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> rose_stairs = REGISTRATE.block("rose_stairs", p -> new StairBlock(DDBlocks.rose_planks::getDefaultState, p))
            .initialProperties(() -> Blocks.OAK_STAIRS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Stairs")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnFenceBlock> rose_fence = REGISTRATE.block("rose_fence", CanBurnFenceBlock::new)
            .initialProperties(() -> Blocks.OAK_FENCE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Fence")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnFenceGateBlock> rose_fence_gate = REGISTRATE.block("rose_fence_gate", CanBurnFenceGateBlock::new)
            .initialProperties(() -> Blocks.OAK_FENCE_GATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Fence Gate")
            .simpleItem()
            .register();
    public static final BlockEntry<YIPPEESlidingDoorBlock> rose_door =
            REGISTRATE.block("rose_door", p -> new YIPPEESlidingDoorBlock(p, true))
                    .initialProperties(() -> Blocks.OAK_DOOR)
                    .transform(BuilderTransgender.slidingDoor("rose"))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_RED)
                            .sound(SoundType.WOOD)
                            .noOcclusion())
                    .register();
    public static final BlockEntry<CanBurnTrapDoorBlock> rose_trapdoor = REGISTRATE.block("rose_trapdoor", CanBurnTrapDoorBlock::new)
            .initialProperties(() -> Blocks.OAK_TRAPDOOR)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED)
                    .noOcclusion())
            .addLayer(() -> RenderType::cutoutMipped)
            .lang("Rose Trapdoor")
            .simpleItem()
            .register();
    public static final BlockEntry<WoodButtonBlock> rose_button = REGISTRATE.block("rose_button", WoodButtonBlock::new)
            .initialProperties(() -> Blocks.OAK_BUTTON)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Button")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnPressurePlateBlock> rose_pressure_plate = REGISTRATE.block("rose_pressure_plate", p -> new CanBurnPressurePlateBlock(CanBurnPressurePlateBlock.Sensitivity.EVERYTHING, p))
            .initialProperties(() -> Blocks.OAK_PRESSURE_PLATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_RED))
            .lang("Rose Pressure Plate")
            .simpleItem()
            .register();


    //SMOKED WOODSET

    public static final BlockEntry<CanBurnRotatedBlockPillar> smoked_log = REGISTRATE.block("smoked_log", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.OAK_LOG)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Log")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnRotatedBlockPillar> stripped_smoked_log = REGISTRATE.block("stripped_smoked_log", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Stripped Smoked Log")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnRotatedBlockPillar> smoked_wood = REGISTRATE.block("smoked_wood", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnRotatedBlockPillar> stripped_smoked_wood = REGISTRATE.block("stripped_smoked_wood", CanBurnRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Stripped Smoked Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnBlock> smoked_planks = REGISTRATE.block("smoked_planks", CanBurnBlock::new)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Planks")
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> smoked_stairs = REGISTRATE.block("smoked_stairs", p -> new StairBlock(DDBlocks.smoked_planks::getDefaultState, p))
            .initialProperties(() -> Blocks.OAK_STAIRS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Stairs")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnSlabBlock> smoked_slab = REGISTRATE.block("smoked_slab", CanBurnSlabBlock::new)
            .initialProperties(() -> Blocks.OAK_SLAB)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Slab")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnFenceBlock> smoked_fence = REGISTRATE.block("smoked_fence", CanBurnFenceBlock::new)
            .initialProperties(() -> Blocks.OAK_FENCE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Fence")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnFenceGateBlock> smoked_fence_gate = REGISTRATE.block("smoked_fence_gate", CanBurnFenceGateBlock::new)
            .initialProperties(() -> Blocks.OAK_FENCE_GATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Fence Gate")
            .simpleItem()
            .register();
    public static final BlockEntry<YIPPEESlidingDoorBlock> smoked_door =
            REGISTRATE.block("smoked_door", p -> new YIPPEESlidingDoorBlock(p, true))
                    .initialProperties(() -> Blocks.OAK_DOOR)
                    .transform(BuilderTransgender.slidingDoor("smoked"))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN)
                            .sound(SoundType.WOOD)
                            .noOcclusion())
                    .register();
    public static final BlockEntry<CanBurnTrapDoorBlock> smoked_trapdoor = REGISTRATE.block("smoked_trapdoor", CanBurnTrapDoorBlock::new)
            .initialProperties(() -> Blocks.OAK_TRAPDOOR)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN)
                    .noOcclusion())
            .addLayer(() -> RenderType::cutoutMipped)
            .lang("Smoked Trapdoor")
            .simpleItem()
            .register();
    public static final BlockEntry<WoodButtonBlock> smoked_button = REGISTRATE.block("smoked_button", WoodButtonBlock::new)
            .initialProperties(() -> Blocks.OAK_BUTTON)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Button")
            .simpleItem()
            .register();
    public static final BlockEntry<CanBurnPressurePlateBlock> smoked_pressure_plate = REGISTRATE.block("smoked_pressure_plate", p -> new CanBurnPressurePlateBlock(CanBurnPressurePlateBlock.Sensitivity.EVERYTHING, p))
            .initialProperties(() -> Blocks.OAK_PRESSURE_PLATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_BROWN))
            .lang("Smoked Pressure Plate")
            .simpleItem()
            .register();

    //SPIRIT WOODSET

    public static final BlockEntry<NormalLogRotatedBlockPillar> spirit_log = REGISTRATE.block("spirit_log", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.WARPED_STEM)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Log")
            .simpleItem()
            .register();
    public static final BlockEntry<NormalLogRotatedBlockPillar> stripped_spirit_log = REGISTRATE.block("stripped_spirit_log", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_WARPED_STEM)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Stripped Spirit Log")
            .simpleItem()
            .register();
    public static final BlockEntry<NormalLogRotatedBlockPillar> spirit_wood = REGISTRATE.block("spirit_wood", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.WARPED_HYPHAE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<NormalLogRotatedBlockPillar> stripped_spirit_wood = REGISTRATE.block("stripped_spirit_wood", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_WARPED_HYPHAE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Stripped Spirit Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<Block> spirit_planks = REGISTRATE.block("spirit_planks", Block::new)
            .initialProperties(() -> Blocks.WARPED_PLANKS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Planks")
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> spirit_stairs = REGISTRATE.block("spirit_stairs", p -> new StairBlock(DDBlocks.spirit_planks::getDefaultState, p))
            .initialProperties(() -> Blocks.WARPED_STAIRS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Stairs")
            .simpleItem()
            .register();
    public static final BlockEntry<SlabBlock> spirit_slab = REGISTRATE.block("spirit_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.WARPED_SLAB)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Slab")
            .simpleItem()
            .register();
    public static final BlockEntry<FenceBlock> spirit_fence = REGISTRATE.block("spirit_fence", FenceBlock::new)
            .initialProperties(() -> Blocks.WARPED_FENCE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Fence")
            .simpleItem()
            .register();
    public static final BlockEntry<FenceGateBlock> spirit_fence_gate = REGISTRATE.block("spirit_fence_gate", FenceGateBlock::new)
            .initialProperties(() -> Blocks.WARPED_FENCE_GATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Fence Gate")
            .simpleItem()
            .register();
    public static final BlockEntry<YIPPEESlidingDoorBlock> spirit_door =
            REGISTRATE.block("spirit_door", p -> new YIPPEESlidingDoorBlock(p, true))
                    .initialProperties(() -> Blocks.WARPED_DOOR)
                    .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                            () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                            () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
                    .transform(BuilderTransgender.slidingDoor("spirit"))
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE)
                            .noOcclusion())
                    .register();
    public static final BlockEntry<TrapDoorBlock> spirit_trapdoor = REGISTRATE.block("spirit_trapdoor", TrapDoorBlock::new)
            .initialProperties(() -> Blocks.WARPED_TRAPDOOR)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE)
                    .noOcclusion())
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .addLayer(() -> RenderType::cutoutMipped)
            .lang("Spirit Trapdoor")
            .simpleItem()
            .register();
    public static final BlockEntry<WoodButtonBlock> spirit_button = REGISTRATE.block("spirit_button", WoodButtonBlock::new)
            .initialProperties(() -> Blocks.WARPED_BUTTON)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Button")
            .simpleItem()
            .register();
    public static final BlockEntry<PressurePlateBlock> spirit_pressure_plate = REGISTRATE.block("spirit_pressure_plate", p -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
            .initialProperties(() -> Blocks.WARPED_PRESSURE_PLATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_PURPLE))
            .properties(p -> p.sound(new ForgeSoundType(1, .7f, () -> SoundEvents.WOOD_BREAK,
                    () -> SoundEvents.STEM_STEP, () -> SoundEvents.WOOD_PLACE,
                    () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
            .lang("Spirit Pressure Plate")
            .simpleItem()
            .register();

    //RUBBER WOODSET

    public static final BlockEntry<SaplingBlock> rubber_sapling = REGISTRATE.block("rubber_sapling", p -> new SaplingBlock(new RubberTreeGrower(), p))
            .initialProperties(() -> Blocks.OAK_SAPLING)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Sapling")
            .addLayer(() -> RenderType::cutoutMipped)
            .simpleItem()
            .register();

    public static final BlockEntry<RubberLeavesBlock> rubber_leaves = REGISTRATE.block("rubber_leaves", RubberLeavesBlock::new)
            .initialProperties(() -> Blocks.AZALEA_LEAVES)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Leaves")
            .simpleItem()
            .register();

    public static final BlockEntry<NormalLogRotatedBlockPillar> rubber_log = REGISTRATE.block("rubber_log", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.OAK_LOG)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Log")
            .simpleItem()
            .register();
    public static final BlockEntry<NormalLogRotatedBlockPillar> stripped_rubber_log = REGISTRATE.block("stripped_rubber_log", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_OAK_LOG)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Stripped Rubber Log")
            .simpleItem()
            .register();
    public static final BlockEntry<NormalLogRotatedBlockPillar> rubber_wood = REGISTRATE.block("rubber_wood", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.OAK_WOOD)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<NormalLogRotatedBlockPillar> stripped_rubber_wood = REGISTRATE.block("stripped_rubber_wood", NormalLogRotatedBlockPillar::new)
            .initialProperties(() -> Blocks.STRIPPED_OAK_WOOD)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Stripped Rubber Wood")
            .simpleItem()
            .register();
    public static final BlockEntry<Block> rubber_planks = REGISTRATE.block("rubber_planks", Block::new)
            .initialProperties(() -> Blocks.OAK_PLANKS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Planks")
            .simpleItem()
            .register();
    public static final BlockEntry<SlabBlock> rubber_slab = REGISTRATE.block("rubber_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.OAK_SLAB)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Slab")
            .simpleItem()
            .register();
    public static final BlockEntry<StairBlock> rubber_stairs = REGISTRATE.block("rubber_stairs", p -> new StairBlock(DDBlocks.rubber_planks::getDefaultState, p))
            .initialProperties(() -> Blocks.OAK_STAIRS)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Stairs")
            .simpleItem()
            .register();
    public static final BlockEntry<FenceBlock> rubber_fence = REGISTRATE.block("rubber_fence", FenceBlock::new)
            .initialProperties(() -> Blocks.OAK_FENCE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Fence")
            .simpleItem()
            .register();
    public static final BlockEntry<FenceGateBlock> rubber_fence_gate = REGISTRATE.block("rubber_fence_gate", FenceGateBlock::new)
            .initialProperties(() -> Blocks.OAK_FENCE_GATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Fence Gate")
            .simpleItem()
            .register();
    public static final BlockEntry<DoorBlock> rubber_door =
            REGISTRATE.block("rubber_door", DoorBlock::new)
                    .initialProperties(() -> Blocks.OAK_DOOR)
                    .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN)
                            .sound(SoundType.WOOD)
                            .noOcclusion())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .simpleItem()
                    .register();
    public static final BlockEntry<TrapDoorBlock> rubber_trapdoor = REGISTRATE.block("rubber_trapdoor", TrapDoorBlock::new)
            .initialProperties(() -> Blocks.OAK_TRAPDOOR)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN)
                    .noOcclusion())
            .addLayer(() -> RenderType::cutoutMipped)
            .lang("Rubber Trapdoor")
            .simpleItem()
            .register();
    public static final BlockEntry<WoodButtonBlock> rubber_button = REGISTRATE.block("rubber_button", WoodButtonBlock::new)
            .initialProperties(() -> Blocks.OAK_BUTTON)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Button")
            .simpleItem()
            .register();
    public static final BlockEntry<PressurePlateBlock> rubber_pressure_plate = REGISTRATE.block("rubber_pressure_plate", p -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, p))
            .initialProperties(() -> Blocks.OAK_PRESSURE_PLATE)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_CYAN))
            .lang("Rubber Pressure Plate")
            .simpleItem()
            .register();
    
    
    public static final BlockEntry<Block> asphalt_block = REGISTRATE.block("asphalt_block", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .speedFactor(1.2F)
                    .jumpFactor(1.2F)
                    .friction(0.6F)
                    .color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .lang("Asphalt Block")
            .simpleItem()
            .register();

    public static final BlockEntry<HazardBlock> hazard_block = REGISTRATE.block("hazard_block", HazardBlock::new)
            .transform(BuilderTransgender.hazard(() -> DDBlockSpriteShifts.HAZARD))
            .properties(p -> p.destroyTime(1.25f)
                    .speedFactor(0.8F)
                    .jumpFactor(0.8F)
                    .color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .lang("Hazard Block")
            .register();

    public static final BlockEntry<HazardBlock> horizontal_hazard_block = REGISTRATE.block("horizontal_hazard_block", HazardBlock::new)
            .transform(BuilderTransgender.hazard(() -> DDBlockSpriteShifts.HORIZONTAL_HAZARD))
            .properties(p -> p.destroyTime(1.25f)
                    .speedFactor(0.8F)
                    .jumpFactor(0.8F)
                    .color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .lang("Horizontal Hazard Block")
            .register();

    public static final BlockEntry<HazardBlock> hazard_block_r = REGISTRATE.block("hazard_block_r", HazardBlock::new)
            .transform(BuilderTransgender.hazard(() -> DDBlockSpriteShifts.HAZARD_R))
            .properties(p -> p.destroyTime(1.25f)
                    .speedFactor(0.8F)
                    .jumpFactor(0.8F)
                    .color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .lang("Hazard Block")
            .register();

    public static final BlockEntry<HazardBlock> horizontal_hazard_block_r = REGISTRATE.block("horizontal_hazard_block_r", HazardBlock::new)
            .transform(BuilderTransgender.hazard(() -> DDBlockSpriteShifts.HORIZONTAL_HAZARD_R))
            .properties(p -> p.destroyTime(1.25f)
                    .speedFactor(0.8F)
                    .jumpFactor(0.8F)
                    .color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .lang("Horizontal Hazard Block")
            .register();

    public static final BlockEntry<Block> asurine_mossy_bricks = REGISTRATE.block("asurine_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.COLOR_BLUE))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> calcite_mossy_bricks = REGISTRATE.block("calcite_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_WHITE))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.CALCITE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> crimsite_mossy_bricks = REGISTRATE.block("crimsite_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.COLOR_RED))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> deepslate_mossy_bricks = REGISTRATE.block("deepslate_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.DEEPSLATE))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> diorite_mossy_bricks = REGISTRATE.block("diorite_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.QUARTZ))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> dripstone_mossy_bricks = REGISTRATE.block("dripstone_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_BROWN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.DRIPSTONE_BLOCK))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> gabbro_mossy_bricks = REGISTRATE.block("gabbro_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.TUFF))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> dolomite_mossy_bricks = REGISTRATE.block("dolomite_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_WHITE))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.TUFF))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> granite_mossy_bricks = REGISTRATE.block("granite_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_CYAN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> limestone_mossy_bricks = REGISTRATE.block("limestone_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.SAND))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> ochrum_mossy_bricks = REGISTRATE.block("ochrum_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_YELLOW))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.CALCITE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> potassic_mossy_bricks = REGISTRATE.block("potassic_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_BLUE))
            .properties(p -> p.sound(new ForgeSoundType(0.8f, 0.85f, () -> DDSoundEvents.ore_stone_break.get(),
                    () -> DDSoundEvents.ore_stone_step.get(), () -> DDSoundEvents.ore_stone_place.get(),
                    () -> DDSoundEvents.ore_stone_hit.get(), () -> DDSoundEvents.ore_stone_fall.get())))
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .simpleItem()
            .register();

    public static final BlockEntry<Block> scorchia_mossy_bricks = REGISTRATE.block("scorchia_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_GRAY))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> scoria_mossy_bricks = REGISTRATE.block("scoria_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.COLOR_BROWN))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> tuff_mossy_bricks = REGISTRATE.block("tuff_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_GRAY))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.TUFF))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> veridium_mossy_bricks = REGISTRATE.block("veridium_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.WARPED_NYLIUM))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.TUFF))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> weathered_limestone_mossy_bricks = REGISTRATE.block("weathered_limestone_mossy_bricks", Block::new)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.COLOR_LIGHT_GRAY))
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .simpleItem()
            .register();

    public static final BlockEntry<Block> potassic_cobble =
            REGISTRATE.block("potassic_cobble", Block::new)
                    .initialProperties(() -> Blocks.DEEPSLATE)
                    .properties(p -> p.destroyTime(2.25f).color(MaterialColor.TERRACOTTA_BLUE))
                    .properties(p -> p.sound(new ForgeSoundType(0.8f, 0.85f, () -> DDSoundEvents.ore_stone_break.get(),
                            () -> DDSoundEvents.ore_stone_step.get(), () -> DDSoundEvents.ore_stone_place.get(),
                            () -> DDSoundEvents.ore_stone_hit.get(), () -> DDSoundEvents.ore_stone_fall.get())))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .simpleItem()
                    .register();

    public static final BlockEntry<Block> asurine_cobble =
            REGISTRATE.block("asurine_cobble", Block::new)
                    .initialProperties(() -> Blocks.DEEPSLATE)
                    .properties(p -> p.destroyTime(2.25f).color(MaterialColor.COLOR_BLUE))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .simpleItem()
                    .register();

    public static final BlockEntry<Block> crimsite_cobble =
            REGISTRATE.block("crimsite_cobble", Block::new)
                    .initialProperties(() -> Blocks.DEEPSLATE)
                    .properties(p -> p.destroyTime(2.25f).color(MaterialColor.COLOR_RED))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .simpleItem()
                    .register();

    public static final BlockEntry<Block> ochrum_cobble =
            REGISTRATE.block("ochrum_cobble", Block::new)
                    .initialProperties(() -> Blocks.CALCITE)
                    .properties(p -> p.destroyTime(2.25f).color(MaterialColor.TERRACOTTA_YELLOW))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .simpleItem()
                    .register();

    public static final BlockEntry<Block> veridium_cobble =
            REGISTRATE.block("veridium_cobble", Block::new)
                    .initialProperties(() -> Blocks.TUFF)
                    .properties(p -> p.destroyTime(2.25f).color(MaterialColor.WARPED_NYLIUM))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .simpleItem()
                    .register();
    public static void register() {
    }
}
