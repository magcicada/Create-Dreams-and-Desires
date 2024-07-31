package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import org.spongepowered.asm.mixin.Mixin;

//FOR LARGE COG TO WORM GEAR

@Mixin(value = KineticBlockEntity.class, remap = false)
public abstract class MixinKineticBlockEntity {

    //@Shadow public abstract float propagateRotationTo(KineticBlockEntity target, BlockState stateFrom, BlockState stateTo, BlockPos diff, boolean connectedViaAxes, boolean connectedViaCogs);*

    //@Inject(method = "propagateRotationTo(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;ZZ)F",*
    //        at=@At("HEAD"), cancellable = true)
    //public void propagateRotationTo(KineticBlockEntity target, BlockState stateFrom, BlockState stateTo, BlockPos diff,*
    //                                boolean connectedViaAxes, boolean connectedViaCogs, CallbackInfoReturnable<Float> cir) {
    //    if (AllBlocks.LARGE_COGWHEEL.has(stateFrom)) {
    //        float defaultModifier = propagateRotationTo(target, stateFrom, stateTo, diff, connectedViaAxes, connectedViaCogs);

    //        if (connectedViaAxes)
    //            cir.setReturnValue(defaultModifier);
    //        if (!DesiresBlocks.WORM_GEAR.has(stateTo))
    //            cir.setReturnValue(defaultModifier);

    //        Direction direction = Direction.getNearest(diff.getX(), diff.getY(), diff.getZ());
    //        if (stateFrom.getValue(CogWheelBlock.AXIS) != direction.getOpposite().getAxis())
    //            cir.setReturnValue(defaultModifier);

    //        cir.setReturnValue(getLargeCogModifier(stateTo.getValue(GantryShaftBlock.FACING), stateFrom.getValue(CogWheelBlock.AXIS)));
    //    }

    //}

}
