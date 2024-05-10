package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.base.CutoutRotatingInstance;
import com.simibubi.create.content.kinetics.base.HalfShaftInstance;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlockEntity;
import com.simibubi.create.content.kinetics.motor.CreativeMotorRenderer;
import com.simibubi.create.content.kinetics.transmission.SplitShaftInstance;
import com.simibubi.create.content.kinetics.transmission.SplitShaftRenderer;
import com.simibubi.create.content.kinetics.waterwheel.LargeWaterWheelBlockEntity;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelInstance;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.hydraulic_press.HydraulicPressBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.hydraulic_press.HydraulicPressInstance;
import uwu.lopyluna.create_dd.content.blocks.kinetics.hydraulic_press.HydraulicPressRenderer;
import uwu.lopyluna.create_dd.content.blocks.kinetics.industrial_fan_block.IndustrialFanBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.industrial_fan_block.IndustrialFanInstance;
import uwu.lopyluna.create_dd.content.blocks.kinetics.industrial_fan_block.IndustrialFanRemderer;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankInstance;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankRenderer;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.*;
import uwu.lopyluna.create_dd.content.blocks.kinetics.kinetic_motor.KineticMotorBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.kinetic_motor.KineticMotorRenderer;
import uwu.lopyluna.create_dd.content.blocks.kinetics.transmission.InverseBoxBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlockEntity;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

public class DesiresBlockEntityTypes {

	public static final BlockEntityEntry<IndustrialFanBlockEntity> INDUSTRIAL_FAN = REGISTRATE
			.blockEntity("industrial_fan", IndustrialFanBlockEntity::new)
			.instance(() -> IndustrialFanInstance::new, false)
			.validBlocks(DesiresBlocks.INDUSTRIAL_FAN)
			.renderer(() -> IndustrialFanRemderer::new)
			.register();

	public static final BlockEntityEntry<HydraulicPressBlockEntity> HYDRAULIC_PRESS = REGISTRATE
			.blockEntity("hydraulic_press", HydraulicPressBlockEntity::new)
			.instance(() -> HydraulicPressInstance::new)
			.validBlocks(DesiresBlocks.HYDRAULIC_PRESS)
			.renderer(() -> HydraulicPressRenderer::new)
			.register();

	public static final BlockEntityEntry<CogCrankBlockEntity> COG_CRANK = REGISTRATE
			.blockEntity("cog_crank", CogCrankBlockEntity::new)
			.instance(() -> CogCrankInstance::new)
			.validBlocks(DesiresBlocks.COG_CRANK)
			.renderer(() -> CogCrankRenderer::new)
			.register();

	public static final BlockEntityEntry<ItemStockpileBlockEntity> ITEM_STOCKPILE = REGISTRATE
			.blockEntity("item_stockpile", ItemStockpileBlockEntity::new)
			.validBlocks(DesiresBlocks.ITEM_STOCKPILE)
			.register();

	public static final BlockEntityEntry<FluidKegBlockEntity> FLUID_KEG = REGISTRATE
			.blockEntity("fluid_keg", FluidKegBlockEntity::new)
			.validBlocks(DesiresBlocks.FLUID_KEG)
			.register();

	public static final BlockEntityEntry<FurnaceEngineBlockEntity> FURNACE_ENGINE = REGISTRATE
			.blockEntity("furnace_engine", FurnaceEngineBlockEntity::new)
			.instance(()->FurnaceEngineInstance::new)
			.validBlocks(DesiresBlocks.FURNACE_ENGINE)
			.renderer(()-> FurnaceEngineRenderer::new)
			.register();

	public static final BlockEntityEntry<PoweredFlywheelBlockEntity> POWERED_FLYWHEEL = REGISTRATE
			.blockEntity("powered_flywheel", PoweredFlywheelBlockEntity::new)
			.instance(() -> PoweredFlywheelInstance::new, false)
			.validBlocks(DesiresBlocks.POWERED_FLYWHEEL)
			.renderer(() -> PoweredFlywheelRenderer::new)
			.register();

	public static final BlockEntityEntry<InverseBoxBlockEntity> INVERSE_BOX = REGISTRATE
			.blockEntity("inverse_box", InverseBoxBlockEntity::new)
			.instance(() -> SplitShaftInstance::new, false)
			.validBlocks(DesiresBlocks.INVERSE_BOX)
			.renderer(() -> SplitShaftRenderer::new)
			.register();

	public static final BlockEntityEntry<KineticMotorBlockEntity> KINETIC_MOTOR = REGISTRATE
			.blockEntity("motor", KineticMotorBlockEntity::new)
			.instance(() -> HalfShaftInstance::new, false)
			.validBlocks(DesiresBlocks.KINETIC_MOTOR)
			.renderer(() -> KineticMotorRenderer::new).register();


	public static final BlockEntityEntry<GiantGearBlockEntity> GIANT_GEAR = REGISTRATE
			.blockEntity("giant_gear", GiantGearBlockEntity::new)
			.instance(() -> CutoutRotatingInstance::new, false)
			.validBlocks(DesiresBlocks.GIANT_GEAR)
			.renderer(() -> KineticBlockEntityRenderer::new)
			.register();

	public static void register() {}
}
