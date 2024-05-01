package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.flywheel.FlywheelBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.PoweredFlywheelBlock;
import uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine.PoweredFlywheelBlockEntity;

@Mixin(value = RotatedPillarKineticBlock.class,remap = false)
public abstract class MixinRotatedPillarKineticBlock {


    @Inject(at = @At("HEAD"), method = "getStateForPlacement",cancellable = true)
    private void getStateForPlacement(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir){
        if (AllBlocks.FLYWHEEL.is(context.getItemInHand().getItem()) && PoweredFlywheelBlock.stillValid(AllBlocks.FLYWHEEL.getDefaultState().setValue(RotatedPillarKineticBlock.AXIS, context.getNearestLookingDirection().getAxis()),context.getLevel(),context.getClickedPos())){
            cir.setReturnValue(PoweredFlywheelBlock.getEquivalent(AllBlocks.FLYWHEEL.getDefaultState(),context.getNearestLookingDirection().getAxis()));
        }
    }
}
