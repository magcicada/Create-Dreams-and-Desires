package uwu.lopyluna.create_dd.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.kinetics.transmission.GearshiftBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import uwu.lopyluna.create_dd.access.AccessGearshiftBlock;

@Mixin(value = GearshiftBlockEntity.class, remap = false)
public class MixinGearshiftBlockEntity extends SplitShaftBlockEntity {

    private MixinGearshiftBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        throw new AssertionError();
    }


    @ModifyExpressionValue(method = "getRotationSpeedModifier(Lnet/minecraft/core/Direction;)F",
            at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;hasSource()Z"))
    private boolean invertSource(boolean original) {
        return (original || ((AccessGearshiftBlock)((GearshiftBlockEntity) (Object) this).getBlockState().getBlock()).getInverted())
                && !((AccessGearshiftBlock)((GearshiftBlockEntity) (Object) this).getBlockState().getBlock()).getInverted();
    }


}
