package uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine;

import com.simibubi.create.content.contraptions.bearing.WindmillBearingBlockEntity;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import com.simibubi.create.content.kinetics.steamEngine.SteamEngineBlock;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollOptionBehaviour;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.List;

public class FurnaceEngineBlockEntity extends SmartBlockEntity {
    protected ScrollOptionBehaviour<WindmillBearingBlockEntity.RotationDirection> movementDirection;
    public WeakReference<PoweredFlywheelBlockEntity> target = new WeakReference(null);
    public WeakReference<AbstractFurnaceBlockEntity> source = new WeakReference(null);
    float prevAngle = 0.0F;

    public FurnaceEngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        this.movementDirection = new ScrollOptionBehaviour<>(WindmillBearingBlockEntity.RotationDirection.class, Lang.translateDirect("contraptions.windmill.rotation_direction"), this, new FurnaceEngineValueBox());
        this.movementDirection.onlyActiveWhen(() -> {
            PoweredFlywheelBlockEntity flywheel = this.getFlywheel();
            return flywheel == null || !flywheel.hasSource();
        });
        this.movementDirection.withCallback(($) -> {
            this.onDirectionChanged();
        });
        behaviours.add(this.movementDirection);
    }

    @Override
    public void tick() {
        super.tick();
        PoweredFlywheelBlockEntity flywheel = this.getFlywheel();
        AbstractFurnaceBlockEntity furnace = this.getFurnace();

        if (furnace == null || flywheel == null) {
            if (level.isClientSide())
                return;
            if (flywheel == null)
                return;
            if (!flywheel.getBlockPos()
                    .subtract(worldPosition)
                    .equals(flywheel.enginePos))
                return;
            if (flywheel.engineEfficiency == 0)
                return;
            Direction facing = FurnaceEngineBlock.getFacing(getBlockState());
            if (level.isLoaded(worldPosition.relative(facing.getOpposite())))
                flywheel.update(worldPosition, 0, 0);
            return;
        }

        boolean verticalTarget = false;
        BlockState shaftState = flywheel.getBlockState();
        Direction.Axis targetAxis = Direction.Axis.X;
        if (shaftState.getBlock()instanceof IRotate ir)
            targetAxis = ir.getRotationAxis(shaftState);
        verticalTarget = targetAxis == Direction.Axis.Y;

        BlockState blockState = getBlockState();
        if (!DesiresBlocks.FURNACE_ENGINE.has(blockState))
            return;
        Direction facing = FurnaceEngineBlock.getFacing(blockState);
        if (facing.getAxis() == Direction.Axis.Y)
            facing = blockState.getValue(FurnaceEngineBlock.FACING);

        float efficiency = furnace.isLit() ? 1 : 0;
        if (efficiency > 0)

            award(AllAdvancements.STEAM_ENGINE);

        int conveyedSpeedLevel =
                efficiency == 0 ? 1 : verticalTarget ? 1 : (int) GeneratingKineticBlockEntity.convertToDirection(1, facing);
        if (targetAxis == Direction.Axis.Z)
            conveyedSpeedLevel *= -1;
        if (movementDirection.get() == WindmillBearingBlockEntity.RotationDirection.COUNTER_CLOCKWISE)
            conveyedSpeedLevel *= -1;

        float flywheelSpeed = flywheel.getTheoreticalSpeed();
        if (flywheel.hasSource() && flywheelSpeed != 0 && conveyedSpeedLevel != 0
                && (flywheelSpeed > 0) != (conveyedSpeedLevel > 0)) {
            movementDirection.setValue(1 - movementDirection.get()
                    .ordinal());
            conveyedSpeedLevel *= -1;
        }

        flywheel.update(worldPosition, conveyedSpeedLevel, efficiency);

    }

    private void onDirectionChanged() {}

    public PoweredFlywheelBlockEntity getFlywheel() {
        PoweredFlywheelBlockEntity shaft = this.target.get();
        if (shaft == null || shaft.isRemoved() || !shaft.canBePoweredBy(this.worldPosition)) {
            if (shaft != null) {
                this.target = new WeakReference(null);
            }

            Direction facing = FurnaceEngineBlock.getFacing(this.getBlockState());
            BlockEntity anyShaftAt = this.level.getBlockEntity(this.worldPosition.relative(facing, 2));
            if (anyShaftAt instanceof PoweredFlywheelBlockEntity) {
                PoweredFlywheelBlockEntity ps = (PoweredFlywheelBlockEntity)anyShaftAt;
                if (ps.canBePoweredBy(this.worldPosition)) {
                    shaft = ps;
                    this.target = new WeakReference(ps);
                }
            }
        }

        return shaft;
    }
    public AbstractFurnaceBlockEntity getFurnace() {
        AbstractFurnaceBlockEntity furnace = this.source.get();
        if (furnace == null || furnace.isRemoved()) {
            if (furnace != null) {
                this.source = new WeakReference(null);
            }

            Direction facing = FurnaceEngineBlock.getFacing(this.getBlockState());
            BlockEntity be = this.level.getBlockEntity(this.worldPosition.relative(facing.getOpposite()));
            if (be instanceof AbstractFurnaceBlockEntity) {
                AbstractFurnaceBlockEntity furnaceBe = (AbstractFurnaceBlockEntity)be;
                furnace = furnaceBe;
                this.source = new WeakReference(furnaceBe);
            }
        }

        return furnace;
    }
    @Nullable
    @OnlyIn(Dist.CLIENT)
    public Float getTargetAngle() {
        float angle = 0.0F;
        BlockState blockState = this.getBlockState();
        if (!DesiresBlocks.FURNACE_ENGINE.has(blockState)) {
            return null;
        } else {
            Direction facing = SteamEngineBlock.getFacing(blockState);
            PoweredFlywheelBlockEntity flywheel = this.getFlywheel();
            Direction.Axis facingAxis = facing.getAxis();
            Direction.Axis axis = Direction.Axis.Y;
            if (flywheel == null) {
                return null;
            } else {
                axis = KineticBlockEntityRenderer.getRotationAxisOf(flywheel);
                angle = KineticBlockEntityRenderer.getAngleForTe(flywheel, flywheel.getBlockPos(), axis);
                if (axis == facingAxis) {
                    return null;
                } else {
                    if (axis.isHorizontal() && facingAxis == Direction.Axis.X ^ facing.getAxisDirection() == Direction.AxisDirection.POSITIVE) {
                        angle *= -1.0F;
                    }

                    if (axis == Direction.Axis.X && facing == Direction.DOWN) {
                        angle *= -1.0F;
                    }

                    return angle;
                }
            }
        }
    }

    @Override
    public void remove() {
        PoweredFlywheelBlockEntity flywheel = getFlywheel();
        if (flywheel != null)
            flywheel.remove(worldPosition);
        super.remove();
    }
}
