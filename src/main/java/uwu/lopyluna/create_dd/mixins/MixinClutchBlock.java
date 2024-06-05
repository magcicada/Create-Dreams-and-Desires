package uwu.lopyluna.create_dd.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.kinetics.transmission.ClutchBlock;
import com.simibubi.create.content.kinetics.transmission.GearshiftBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ClutchBlock.class)
public class MixinClutchBlock extends GearshiftBlock {
    public MixinClutchBlock(Properties properties) {
        super(properties);
    }



    @WrapOperation(method = "neighborChanged(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/core/BlockPos;Z)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    public boolean create_dd$neighborChangedSetBlock(Level instance, BlockPos pPos, BlockState pNewState, int pFlags, Operation<Boolean> original, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) Level worldIn, @Local(argsOnly = true) BlockPos pos, @Local(argsOnly = true) Block blockIn, @Local(argsOnly = true) BlockPos fromPos, @Local(argsOnly = true) boolean isMoving) {

        detachKinetics(worldIn, pos, true);
        original.call(instance, pPos, pNewState, 2);
        return isMoving;
    }

    @Redirect(method = "neighborChanged(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/core/BlockPos;Z)V",
            at = @At(value = "INVOKE",
                    target = "Lcom/simibubi/create/content/kinetics/transmission/GearshiftBlock;detachKinetics(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Z)V"))
    public void create_dd$neighborChangedDetachKinetics(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
    }






    /**
     * @author luna
     * @reason fix
     */
    @Overwrite
    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        if (worldIn.isClientSide)
            return;

        boolean previouslyPowered = state.getValue(POWERED);
        if (previouslyPowered != worldIn.hasNeighborSignal(pos)) {
            detachKinetics(worldIn, pos, true);
            worldIn.setBlock(pos, state.cycle(POWERED), 2);
        }
    }
}
