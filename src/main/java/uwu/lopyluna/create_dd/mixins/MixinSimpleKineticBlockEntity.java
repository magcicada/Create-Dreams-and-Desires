package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear.GiantGearBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(value = SimpleKineticBlockEntity.class, remap = false)
public class MixinSimpleKineticBlockEntity extends KineticBlockEntity {
    
    public MixinSimpleKineticBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }
    
    @Inject(method = "addPropagationLocations", at = @At("RETURN"), cancellable = true)
    private void addPropagationLocations(IRotate block, BlockState state, List<BlockPos> neighbours, CallbackInfoReturnable<List<BlockPos>> cir) {
        Direction.Axis axis = getBlockState().getValue(GiantGearBlock.AXIS);
        
        List<Direction.Axis> parallelAxis = Arrays.stream(Direction.Axis.values())
            .filter(other -> other != axis)
            .toList();
        
        ArrayList<BlockPos> positions = new ArrayList<>(cir.getReturnValue());
        
        for (Direction.AxisDirection axisDirectionA : Direction.AxisDirection.values()) {
            for (Direction.AxisDirection axisDirectionB : Direction.AxisDirection.values()) {
                Direction directionA = Direction.fromAxisAndDirection(parallelAxis.get(0), axisDirectionA);
                Direction directionB = Direction.fromAxisAndDirection(parallelAxis.get(1), axisDirectionB);
                
                positions.add(
                    getBlockPos()
                        .relative(directionA, 3)
                        .relative(directionB, 2)
                );
                positions.add(
                    getBlockPos()
                        .relative(directionA, 2)
                        .relative(directionB, 3)
                );
            }
        }
        
        cir.setReturnValue(positions);
    }
    
}
