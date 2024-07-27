package uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

import java.util.List;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.AXIS;

public class GiantGearBlockEntity extends KineticBlockEntity {
    public GiantGearBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    //idfk what i am doing lmao
    //i really want to learn the propagateRotation stuff

    @Override
    public float propagateRotationTo(KineticBlockEntity target, BlockState stateFrom, BlockState stateTo, BlockPos diff, boolean connectedViaAxes, boolean connectedViaCogs) {


        final Direction direction = Direction.getNearest(diff.getX(), diff.getY(), diff.getZ());

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

        float defaultModifier =
                super.propagateRotationTo(target, stateFrom, stateTo, diff, connectedViaAxes, connectedViaCogs);


        if (isLargeToLargeGear(stateFrom, stateTo, diff)) {
            Direction.Axis sourceAxis = stateFrom.getValue(AXIS);
            Direction.Axis targetAxis = stateTo.getValue(AXIS);
            int sourceAxisDiff = sourceAxis.choose(diff.getX(), diff.getY(), diff.getZ());
            int targetAxisDiff = targetAxis.choose(diff.getX(), diff.getY(), diff.getZ());

            return sourceAxisDiff > 0 ^ targetAxisDiff > 0 ? -1 : 1;
        }


        return defaultModifier;
    }

    private static boolean isLargeToLargeGear(BlockState from, BlockState to, BlockPos diff) {
        if (!DesiresBlocks.GIANT_GEAR.has(from) || !DesiresBlocks.GIANT_GEAR.has(to))
            return false;
        Direction.Axis fromAxis = from.getValue(AXIS);
        Direction.Axis toAxis = to.getValue(AXIS);
        if (fromAxis == toAxis)
            return false;
        for (Direction.Axis axis : Direction.Axis.values()) {
            int axisDiff = axis.choose(diff.getX(), diff.getY(), diff.getZ());
            if (axis == fromAxis || axis == toAxis) {
                if (axisDiff == 0)
                    return false;

            } else if (axisDiff != 0)
                return false;
        }
        return true;
    }

    @Override
    public List<BlockPos> addPropagationLocations(IRotate block, BlockState state, List<BlockPos> neighbours) {
        if (!canPropagateDiagonally(block, state))
            return neighbours;

        Direction.Axis axis = block.getRotationAxis(state);
        BlockPos.betweenClosedStream(new BlockPos(-1, -1, -1), new BlockPos(1, 1, 1))
                .forEach(offset -> {
                    if (axis.choose(offset.getX(), offset.getY(), offset.getZ()) != 0)
                        return;
                    if (offset.distSqr(BlockPos.ZERO) != 2)
                        return;
                    neighbours.add(worldPosition.offset(offset));
                });
        return neighbours;
    }
}
