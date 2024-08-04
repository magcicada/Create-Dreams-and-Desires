package uwu.lopyluna.create_dd.mixins;


import com.simibubi.create.api.connectivity.ConnectivityHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_reservoir.FluidReservoirBlock;

@Mixin(value = Block.class)
public class MixinBlockFluidResBlock {

    @Inject(at = @At("HEAD"), method = "shouldRenderFace", cancellable = true)
    private static void create_dd$shouldRenderFace(BlockState pState, BlockGetter pLevel, BlockPos pOffset, Direction pFace, BlockPos pPos, CallbackInfoReturnable<Boolean> cir) {
        if (pState.getBlock() instanceof FluidReservoirBlock) {
            boolean isConnected = ConnectivityHandler.isConnected(pLevel, pPos, pPos.relative(pFace));
            boolean isConnectedOpposite = ConnectivityHandler.isConnected(pLevel, pPos, pPos.relative(pFace.getOpposite()));
            boolean isConnectedAdditional = ConnectivityHandler.isConnected(pLevel, pPos, pPos.relative(pFace, 1));
            boolean isConnectedOppositeAdditional = ConnectivityHandler.isConnected(pLevel, pPos, pPos.relative(pFace.getOpposite(), 1));
            cir.setReturnValue(!(isConnected || isConnectedOpposite || isConnectedAdditional || isConnectedOppositeAdditional));
        }
    }
}
