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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearBlock;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

@Mixin(value = RotationPropagator.class, remap = false)
public class MixinRotationPropagator {

    //@Inject(method = "getRotationSpeedModifier(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;)F",
    //        at = @At("TAIL"),
    //        cancellable = true
    //)
    //private static void getRotationSpeedModifier(KineticBlockEntity from, KineticBlockEntity to, CallbackInfoReturnable<Float> cir) {
    //    final BlockState stateFrom = from.getBlockState();
    //    final BlockState stateTo = to.getBlockState();
//
    //    final BlockPos diff = to.getBlockPos()
    //            .subtract(from.getBlockPos());
//
    //    if (create_DnD$isGiantToGiantGear(stateFrom, stateTo, diff)) {
    //        Direction.Axis sourceAxis = stateFrom.getValue(AXIS);
    //        Direction.Axis targetAxis = stateTo.getValue(AXIS);
    //        int sourceAxisDiff = sourceAxis.choose(diff.getX(), diff.getY(), diff.getZ());
    //        int targetAxisDiff = targetAxis.choose(diff.getX(), diff.getY(), diff.getZ());
//
    //        cir.setReturnValue((float) (sourceAxisDiff > 0 ^ targetAxisDiff > 0 ? -1 : 1));
    //    }
    //}
//
//
    //@Unique
    //private static boolean create_DnD$isGiantToGiantGear(BlockState from, BlockState to, BlockPos diff) {
    //    if (!(DesiresBlocks.GIANT_GEAR.has(from)) || !(DesiresBlocks.GIANT_GEAR.has(to)))
    //        return false;
    //    Direction.Axis fromAxis = from.getValue(AXIS);
    //    Direction.Axis toAxis = to.getValue(AXIS);
    //    if (fromAxis == toAxis)
    //        return false;
    //    for (Direction.Axis axis : Direction.Axis.values()) {
    //        int axisDiff = axis.choose(diff.getX(), diff.getY(), diff.getZ());
    //        if (axis == fromAxis || axis == toAxis) {
    //            return axisDiff != 0;
//
    //        } else return axisDiff == 0;
    //    }
    //    return true;
    //}
}
