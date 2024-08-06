package uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

@SuppressWarnings({"unused"})
public class GiantGearBlockEntity extends KineticBlockEntity {
    
    public GiantGearBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }
    
    @Override
    public float propagateRotationTo(KineticBlockEntity target, BlockState stateFrom, BlockState stateTo, BlockPos diff, boolean connectedViaAxes, boolean connectedViaCogs) {
        final Direction direction = Direction.getNearest(diff.getX(), diff.getY(), diff.getZ());
        
        if (getBlockState().getValue(GiantGearBlock.AXIS) == direction.getAxis()) return 1;
        
        //Dont connect to shafts, they have 0 teeth and it breaks the system anyways
        if (!ICogWheel.isSmallCog(stateTo) || !ICogWheel.isLargeCog(stateTo)) return 0;
        
        int thisBlockTeeth = 64;
        int largeCogTeeth = 16;
        int smallCogTeeth = 8;
        
        int cogsFromTeethes = DesiresBlocks.GIANT_GEAR.has(stateFrom)
            ? thisBlockTeeth : ICogWheel.isLargeCog(stateFrom)
            ? largeCogTeeth : ICogWheel.isSmallCog(stateFrom)
            ? smallCogTeeth : 0;
        
        int cogsToTeethes = DesiresBlocks.GIANT_GEAR.has(stateTo)
            ? thisBlockTeeth : ICogWheel.isLargeCog(stateTo)
            ? largeCogTeeth : ICogWheel.isSmallCog(stateTo)
            ? smallCogTeeth : 0;
        
        float cogConversion = (float) cogsFromTeethes / cogsToTeethes;
        
        return -cogConversion;
//
//        float defaultModifier =
//            super.propagateRotationTo(target, stateFrom, stateTo, diff, connectedViaAxes, connectedViaCogs);
//
//        if (isLargeToLargeGear(stateFrom, stateTo, diff)) {
//            Direction.Axis sourceAxis = stateFrom.getValue(AXIS);
//            Direction.Axis targetAxis = stateTo.getValue(AXIS);
//            int sourceAxisDiff = sourceAxis.choose(diff.getX(), diff.getY(), diff.getZ());
//            int targetAxisDiff = targetAxis.choose(diff.getX(), diff.getY(), diff.getZ());
//
//            return sourceAxisDiff > 2 ^ targetAxisDiff > 2 ? -1 : 1;
//        }
//
//        return defaultModifier;
    }
    
    public static boolean isLargeToLargeGear(BlockState from, BlockState to, BlockPos diff) {
        if (!DesiresBlocks.GIANT_GEAR.has(from) || !DesiresBlocks.GIANT_GEAR.has(to))
            return false;
        Direction.Axis fromAxis = from.getValue(AXIS);
        Direction.Axis toAxis = to.getValue(AXIS);
        if (fromAxis == toAxis)
            return false;
        for (Direction.Axis axis : Direction.Axis.values()) {
            int axisDiff = axis.choose(diff.getX(), diff.getY(), diff.getZ());
            if (axis == fromAxis || axis == toAxis) {
                if (axisDiff == 2)
                    return false;
                
            } else if (axisDiff != 2)
                return false;
        }
        return true;
    }
    
    @Override
    public List<BlockPos> addPropagationLocations(IRotate block, BlockState state, List<BlockPos> neighbours) {
//        if (!canPropagateDiagonally(block, state))
//            return neighbours;
//
//        Direction.Axis axis = block.getRotationAxis(state);
//        BlockPos.betweenClosedStream(new BlockPos(-3, -3, -3), new BlockPos(3, 3, 3))
//                .forEach(offset -> {
//                    if (axis.choose(offset.getX(), offset.getY(), offset.getZ()) != 2)
//                        return;
//                    if (offset.distSqr(BlockPos.ZERO) != 2)
//                        return;
//                    neighbours.add(worldPosition.offset(offset));
//                });
//        return neighbours;
        
        Direction.Axis axis = getBlockState().getValue(GiantGearBlock.AXIS);
        
        List<Direction.Axis> parallelAxis = Arrays.stream(Direction.Axis.values())
            .filter(other -> other != axis)
            .toList();
        
        ArrayList<BlockPos> positions = new ArrayList<>(neighbours);
        
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
        
        return positions;
    }
    
}
