package uwu.lopyluna.create_dd.block;

import com.simibubi.create.content.kinetics.base.HalfShaftInstance;
import com.simibubi.create.content.kinetics.transmission.SplitShaftInstance;
import com.simibubi.create.content.kinetics.transmission.SplitShaftRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import uwu.lopyluna.create_dd.block.BlockProperties.ReversedGearboxBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.accelerator_motor.AcceleratorMotorBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.accelerator_motor.AcceleratorMotorRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.cog_crank.CogCrankBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.cog_crank.CogCrankInstance;
import uwu.lopyluna.create_dd.block.BlockProperties.cog_crank.CogCrankRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.FlyWheelInstance;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.FlywheelBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.FlywheelRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.engine.EngineInstance;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.engine.EngineRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.flywheel.engine.FurnaceEngineBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.industrial_fan.IndustrialFanBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.industrial_fan.IndustrialFanRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.industrial_fan.IndustrialFanInstance;
import uwu.lopyluna.create_dd.block.BlockProperties.door.YIPPEESlidingDoorBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.door.YIPPEESlidingDoorRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.hydraulic_press.HYPressInstance;
import uwu.lopyluna.create_dd.block.BlockProperties.hydraulic_press.HydraulicPressBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.hydraulic_press.HydraulicPressRenderer;
import uwu.lopyluna.create_dd.block.BlockProperties.kinetic_motor.KineticMotorBlockEntity;
import uwu.lopyluna.create_dd.block.BlockProperties.kinetic_motor.KineticMotorRenderer;

import static uwu.lopyluna.create_dd.DDCreate.REGISTRATE;


public class DDBlockEntityTypes {

    public static final BlockEntityEntry<FurnaceEngineBlockEntity> FURNACE_ENGINE = REGISTRATE
            .blockEntity("furnace_engine", FurnaceEngineBlockEntity::new)
            .instance(() -> EngineInstance::new, false)
            .validBlocks(DDBlocks.FURNACE_ENGINE)
            .renderer(() -> EngineRenderer::new)
            .register();

    public static final BlockEntityEntry<FlywheelBlockEntity> FLYWHEEL = REGISTRATE
            .blockEntity("flywheel", FlywheelBlockEntity::new)
            .instance(() -> FlyWheelInstance::new, false)
            .validBlocks(DDBlocks.FLYWHEEL)
            .renderer(() -> FlywheelRenderer::new)
            .register();

    public static final BlockEntityEntry<YIPPEESlidingDoorBlockEntity> SLIDING_DOOR =
            REGISTRATE.blockEntity("sliding_door", YIPPEESlidingDoorBlockEntity::new)
                    .renderer(() -> YIPPEESlidingDoorRenderer::new)
                    .validBlocks(DDBlocks.rose_door, DDBlocks.smoked_door, DDBlocks.spirit_door)
                    .register();

    public static final BlockEntityEntry<IndustrialFanBlockEntity> BRONZE_ENCASED_FAN =
            REGISTRATE.blockEntity("industrial_fan", IndustrialFanBlockEntity::new)
                    .instance(() -> IndustrialFanInstance::new, false)
                    .validBlocks(DDBlocks.industrial_fan)
                    .renderer(() -> IndustrialFanRenderer::new)
                    .register();

    public static final BlockEntityEntry<ReversedGearboxBlockEntity> REVERSED_GEARSHIFT = REGISTRATE
            .blockEntity("gearshift", ReversedGearboxBlockEntity::new)
            .instance(() -> SplitShaftInstance::new, false)
            .validBlocks(DDBlocks.REVERSED_GEARSHIFT)
            .renderer(() -> SplitShaftRenderer::new)
            .register();

    public static final BlockEntityEntry<HydraulicPressBlockEntity> hydraulic_press = REGISTRATE
            .blockEntity("hydraulic_press", HydraulicPressBlockEntity::new)
            .instance(() -> HYPressInstance::new)
            .validBlocks(DDBlocks.hydraulic_press)
            .renderer(() -> HydraulicPressRenderer::new)
            .register();


    public static final BlockEntityEntry<KineticMotorBlockEntity> MOTOR = REGISTRATE
            .blockEntity("motor", KineticMotorBlockEntity::new)
            .instance(() -> HalfShaftInstance::new, false)
            .validBlocks(DDBlocks.KINETIC_MOTOR)
            .renderer(() -> KineticMotorRenderer::new)
            .register();

    public static final BlockEntityEntry<AcceleratorMotorBlockEntity> AC_MOTOR = REGISTRATE
            .blockEntity("accelerator_motor", AcceleratorMotorBlockEntity::new)
            .instance(() -> HalfShaftInstance::new, false)
            .validBlocks(DDBlocks.ACCELERATOR_MOTOR)
            .renderer(() -> AcceleratorMotorRenderer::new)
            .register();


    public static final BlockEntityEntry<CogCrankBlockEntity> cogCrank = REGISTRATE
            .blockEntity("cog_crank", CogCrankBlockEntity::new)
            .instance(() -> CogCrankInstance::new)
            .validBlocks(DDBlocks.cogCrank)
            .renderer(() -> CogCrankRenderer::new)
            .register();

    public static void register() {}
}
