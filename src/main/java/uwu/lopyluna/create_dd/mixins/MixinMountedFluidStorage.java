package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.contraptions.MountedFluidStorage;
import com.simibubi.create.foundation.fluid.SmartFluidTank;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_reservoir.FluidReservoirBlockEntity;

@Mixin(value = MountedFluidStorage.class, remap = false)
public class MixinMountedFluidStorage {

    @Shadow SmartFluidTank tank;
    @Shadow private BlockEntity blockEntity;
    @Shadow private boolean sendPacket = false;

    @Inject(at = @At("HEAD"), method = "canUseAsStorage(Lnet/minecraft/world/level/block/entity/BlockEntity;)Z", cancellable = true)
    private static void canUseAsStorage(BlockEntity be, CallbackInfoReturnable<Boolean> cir) {
        if (be instanceof FluidReservoirBlockEntity)
            cir.setReturnValue(((FluidReservoirBlockEntity) be).isController());
    }

    @Inject(at = @At("HEAD"), method = "createMountedTank(Lnet/minecraft/world/level/block/entity/BlockEntity;)Lcom/simibubi/create/foundation/fluid/SmartFluidTank;", cancellable = true)
    private void createMountedTank(BlockEntity be, CallbackInfoReturnable<SmartFluidTank> cir) {
        if (be instanceof FluidReservoirBlockEntity)
            cir.setReturnValue(new SmartFluidTank(
                    ((FluidReservoirBlockEntity) be).getTotalTankSize() * FluidReservoirBlockEntity.getCapacityMultiplier(),
                    this::onFluidStackChanged));
    }

    @Inject(at = @At("HEAD"), method = "updateFluid(Lnet/minecraftforge/fluids/FluidStack;)V")
    public void updateFluid(FluidStack fluid, CallbackInfo ci) {
        tank.setFluid(fluid);
        if (!(blockEntity instanceof FluidReservoirBlockEntity tankR))
            return;
        IFluidTank tankRInventory = tankR.getTankInventory();
        if (tankRInventory instanceof SmartFluidTank)
            ((SmartFluidTank) tankRInventory).setFluid(fluid);
    }

    @Shadow
    private void onFluidStackChanged(FluidStack fs) {
        sendPacket = true;
    }
}
