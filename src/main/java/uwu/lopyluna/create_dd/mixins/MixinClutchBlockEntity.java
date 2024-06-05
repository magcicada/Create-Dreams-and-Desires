package uwu.lopyluna.create_dd.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.simibubi.create.content.kinetics.transmission.ClutchBlockEntity;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ClutchBlockEntity.class)
public class MixinClutchBlockEntity extends SplitShaftBlockEntity {

    public MixinClutchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public float getRotationSpeedModifier(Direction face) {
        throw new AssertionError();
    }

    @ModifyExpressionValue(method = "getRotationSpeedModifier(Lnet/minecraft/core/Direction;)F",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"))
    public boolean create_dd$getRotationSpeedModifier(Property<?> pProperty, Operation<Boolean> original) {
        boolean inverted = !getBlockState().getValue(BlockStateProperties.INVERTED);
        return ((original.call(pProperty) && !inverted) || !original.call(pProperty) && inverted);
    }

}
