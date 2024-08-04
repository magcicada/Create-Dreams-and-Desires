package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearBlockEntity;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

@Mixin(value = RotationPropagator.class, remap = false)
public class MixinRotationPropagator {
    
    @Inject(method = "getRotationSpeedModifier(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;)F",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void giantGear$getRotationSpeedModifier(KineticBlockEntity from, KineticBlockEntity to, CallbackInfoReturnable<Float> cir) {
        final BlockState stateFrom = from.getBlockState();
        final BlockState stateTo = to.getBlockState();

        Block fromBlock = stateFrom.getBlock();
        Block toBlock = stateTo.getBlock();

        final IRotate definitionFrom = (IRotate) fromBlock;
        final IRotate definitionTo = (IRotate) toBlock;
        final BlockPos diff = to.getBlockPos()
                .subtract(from.getBlockPos());
        final Direction direction = Direction.getNearest(diff.getX(), diff.getY(), diff.getZ());
        final Level world = from.getLevel();

        boolean alignedAxes = true;
        for (Direction.Axis axis : Direction.Axis.values())
            if (axis != direction.getAxis())
                if (axis.choose(diff.getX(), diff.getY(), diff.getZ()) != 0)
                    alignedAxes = false;


        boolean connectedByGears = (!DesiresBlocks.GIANT_GEAR.has(stateFrom) || !DesiresBlocks.GIANT_GEAR.has(stateTo));

        boolean connectedByAxis =
                alignedAxes && definitionFrom.hasShaftTowards(world, from.getBlockPos(), stateFrom, direction)
                        && definitionTo.hasShaftTowards(world, to.getBlockPos(), stateTo, direction.getOpposite());

        if (from instanceof GiantGearBlockEntity fromState) {
            float custom = fromState.propagateRotationTo(to, stateFrom, stateTo, diff, connectedByAxis, connectedByGears);
            if (custom != 0)
                cir.setReturnValue(custom);
        }
    }

}
