package uwu.lopyluna.create_dd.content.blocks.kinetics.industrial_fan_block;

import com.simibubi.create.content.kinetics.KineticNetwork;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import java.util.List;

@SuppressWarnings({"all"})
public class IndustrialFanBlockEntity extends EncasedFanBlockEntity {

    public boolean reActivateSource;
    protected boolean isGenerator;
    protected boolean updateGenerator;

    public IndustrialFanBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        isGenerator = false;
        updateGenerator = false;
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        if (!wasMoved)
            isGenerator = compound.getBoolean("Generating");
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        compound.putBoolean("Generating", isGenerator);
        super.write(compound, clientPacket);
    }

    @Override
    public float calculateAddedStressCapacity() {
        return isGenerator ? super.calculateAddedStressCapacity() : 0;
    }

    @Override
    public float calculateStressApplied() {
        return isGenerator ? 0 : super.calculateStressApplied();
    }

    @Override
    public float getGeneratedSpeed() {
        return isGenerator ? 64 : 0;
    }

    public void queueGeneratorUpdate() {
        updateGenerator = true;
    }

    public void updateGenerator() {
        BlockState blockState = getBlockState();
        boolean shouldGenerate = DesiresBlocks.INDUSTRIAL_FAN.has(blockState);

        if (shouldGenerate && blockState.getValue(IndustrialFanBlock.FACING) != Direction.DOWN && !blockBelowIsHot())
            shouldGenerate = false;

        if (shouldGenerate)
            shouldGenerate = level != null
                    && blockBelowIsHot()
                    && level.hasSignal(worldPosition, Direction.DOWN)
                    && level.hasNeighborSignal(worldPosition.below())
                    && blockState.getValue(IndustrialFanBlock.FACING) == Direction.DOWN;

        if (shouldGenerate == isGenerator)
            return;
        isGenerator = shouldGenerate;
        updateGeneratedRotation();
    }

    public boolean blockBelowIsHot() {
        FluidState fluidState = level.getFluidState(worldPosition.below());
        if (DesiresTags.AllFluidTags.INDUSTRIAL_FAN_HEATER.matches(fluidState)) {
            return true;
        }
        BlockState blockState = level.getBlockState(worldPosition.below());
        if (DesiresTags.AllBlockTags.INDUSTRIAL_FAN_HEATER.matches(blockState)) {
            if (blockState.hasProperty(BlazeBurnerBlock.HEAT_LEVEL) && !blockState.getValue(BlazeBurnerBlock.HEAT_LEVEL).isAtLeast(BlazeBurnerBlock.HeatLevel.FADING)) {
                return false;
            }
            return true;
        }
        return false;
    }

//GeneratingKineticBlockEntity stuff

    protected void notifyStressCapacityChange(float capacity) {
        getOrCreateNetwork().updateCapacityFor(this, capacity);
    }

    @Override
    public void removeSource() {
        if (hasSource() && isSource())
            reActivateSource = true;
        super.removeSource();
    }

    @Override
    public void setSource(BlockPos source) {
        super.setSource(source);
        BlockEntity blockEntity = level.getBlockEntity(source);
        if (!(blockEntity instanceof KineticBlockEntity))
            return;
        KineticBlockEntity sourceBE = (KineticBlockEntity) blockEntity;
        if (reActivateSource && Math.abs(sourceBE.getSpeed()) >= Math.abs(getGeneratedSpeed()))
            reActivateSource = false;
    }

    @Override
    public void tick() {
        super.tick();

        if (reActivateSource) {
            updateGeneratedRotation();
            reActivateSource = false;
        }

        if (updateGenerator) {
            updateGenerator = false;
            updateGenerator();
        }

        if (isGenerator || getSpeed() == 0)
            return;
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        boolean added = super.addToGoggleTooltip(tooltip, isPlayerSneaking);
        if (!IRotate.StressImpact.isEnabled())
            return added;

        float stressBase = calculateAddedStressCapacity();
        if (Mth.equal(stressBase, 0))
            return added;

        Lang.translate("gui.goggles.generator_stats")
                .forGoggles(tooltip);
        Lang.translate("tooltip.capacityProvided")
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip);

        float speed = getTheoreticalSpeed();
        if (speed != getGeneratedSpeed() && speed != 0)
            stressBase *= getGeneratedSpeed() / speed;
        speed = Math.abs(speed);

        float stressTotal = stressBase * speed;

        Lang.number(stressTotal)
                .translate("generic.unit.stress")
                .style(ChatFormatting.AQUA)
                .space()
                .add(Lang.translate("gui.goggles.at_current_speed")
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);

        return true;
    }


    public void updateGeneratedRotation() {
        float speed = getGeneratedSpeed();
        float prevSpeed = this.speed;

        if (level == null || level.isClientSide)
            return;

        if (prevSpeed != speed) {
            if (!hasSource()) {
                IRotate.SpeedLevel levelBefore = IRotate.SpeedLevel.of(this.speed);
                IRotate.SpeedLevel levelafter = IRotate.SpeedLevel.of(speed);
                if (levelBefore != levelafter)
                    effects.queueRotationIndicators();
            }

            applyNewSpeed(prevSpeed, speed);
        }

        if (hasNetwork() && speed != 0) {
            KineticNetwork network = getOrCreateNetwork();
            notifyStressCapacityChange(calculateAddedStressCapacity());
            getOrCreateNetwork().updateStressFor(this, calculateStressApplied());
            network.updateStress();
        }

        onSpeedChanged(prevSpeed);
        sendData();
    }


    public void applyNewSpeed(float prevSpeed, float speed) {

        // Speed changed to 0
        if (speed == 0) {
            if (hasSource()) {
                notifyStressCapacityChange(0);
                getOrCreateNetwork().updateStressFor(this, calculateStressApplied());
                return;
            }
            detachKinetics();
            setSpeed(0);
            setNetwork(null);
            return;
        }

        // Now turning - create a new Network
        if (prevSpeed == 0) {
            setSpeed(speed);
            setNetwork(createNetworkId());
            attachKinetics();
            return;
        }

        // Change speed when overpowered by other generator
        if (hasSource()) {

            // Staying below Overpowered speed
            if (Math.abs(prevSpeed) >= Math.abs(speed)) {
                if (Math.signum(prevSpeed) != Math.signum(speed))
                    level.destroyBlock(worldPosition, true);
                return;
            }

            // Faster than attached network -> become the new source
            detachKinetics();
            setSpeed(speed);
            source = null;
            setNetwork(createNetworkId());
            attachKinetics();
            return;
        }

        // Reapply source
        detachKinetics();
        setSpeed(speed);
        attachKinetics();
    }

    public Long createNetworkId() {
        return worldPosition.asLong();
    }
}
