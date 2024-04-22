package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.logistics.vault.ItemVaultBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlockEntity;

@Mixin(Contraption.class)
public abstract class MixinContraption {

    @Shadow
    protected abstract BlockPos toLocalPos(BlockPos globalPos);

    @Inject(at = @At("HEAD"), method = "getBlockEntityNBT(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/nbt/CompoundTag;", cancellable = true)
    protected void getBlockEntityNBT(Level world, BlockPos pos, CallbackInfoReturnable<CompoundTag> cir) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ItemStockpileBlockEntity) {
            CompoundTag nbt = blockEntity.saveWithFullMetadata();
            nbt.remove("x");
            nbt.remove("y");
            nbt.remove("z");

            if (nbt.contains("Controller"))
                nbt.put("Controller",
                        NbtUtils.writeBlockPos(toLocalPos(NbtUtils.readBlockPos(nbt.getCompound("Controller")))));

            cir.setReturnValue(nbt);
        }
    }
}
