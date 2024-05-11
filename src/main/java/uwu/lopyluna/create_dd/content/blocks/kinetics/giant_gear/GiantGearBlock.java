package uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import uwu.lopyluna.create_dd.registry.DesiresBlockEntityTypes;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@SuppressWarnings({"deprecation", "all"})
public class GiantGearBlock extends RotatedPillarKineticBlock implements IBE<GiantGearBlockEntity> {

    public static final BooleanProperty EXTENSION = BooleanProperty.create("extension");
    public GiantGearBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(EXTENSION, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(EXTENSION));
    }

    public Direction.Axis getAxisForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).getValue(AXIS);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState,
                                  LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (pDirection != Direction.fromAxisAndDirection(pState.getValue(AXIS), Direction.AxisDirection.NEGATIVE))
            return pState;
        return pState.setValue(EXTENSION, pNeighborState.is(this));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == getRotationAxis(state);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(AXIS);
    }

    @Override
    public Class<GiantGearBlockEntity> getBlockEntityClass() {
        return GiantGearBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends GiantGearBlockEntity> getBlockEntityType() {
        return DesiresBlockEntityTypes.GIANT_GEAR.get();
    }


    @Override
    public float getParticleTargetRadius() {
        return 2.5f;
    }

    @Override
    public float getParticleInitialRadius() {
        return 2.25f;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState stateForPlacement = super.getStateForPlacement(context);
        BlockPos pos = context.getClickedPos();
        Direction.Axis axis = stateForPlacement.getValue(AXIS);
        boolean axis_x = axis == axis.X;
        boolean axis_y = axis == axis.Y;
        boolean axis_z = axis == axis.Z;

        int inflate_yz = !axis_x ? 2 : 1;
        int inflate_xz = !axis_y ? 2 : 1;
        int inflate_xy = !axis_z ? 2 : 1;

        for (int x = -inflate_yz; x <= inflate_yz; x++) {
            for (int y = -inflate_xz; y <= inflate_xz; y++) {
                for (int z = -inflate_xy; z <= inflate_xy; z++) {
                    if (axis.choose(x, y, z) != 0)
                        continue;
                    BlockPos offset = new BlockPos(x, y, z);
                    if (offset.equals(BlockPos.ZERO))
                        continue;
                    BlockState occupiedState = context.getLevel()
                            .getBlockState(pos.offset(offset));
                    if (!occupiedState.getMaterial()
                            .isReplaceable())
                        return null;
                }
            }
        }

        if (context.getLevel()
                .getBlockState(pos.relative(Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE)))
                .is(this))
            stateForPlacement = stateForPlacement.setValue(EXTENSION, true);

        return stateForPlacement;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.getBlockTicks()
                .hasScheduledTick(pos, this))
            level.scheduleTick(pos, this, 1);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        Direction.Axis axis = pState.getValue(AXIS);
        for (Direction side : Iterate.directions) {
            if (side.getAxis() == axis)
                continue;
            for (boolean secondary : Iterate.falseAndTrue) {
                Direction targetSide = secondary ? side.getClockWise(axis) : side;
                BlockPos structurePos = (secondary ? pPos.relative(side) : pPos).relative(targetSide);
                BlockState occupiedState = pLevel.getBlockState(structurePos);
                BlockState requiredStructure = DesiresBlocks.GIANT_GEAR_STRUCTURAL.getDefaultState()
                        .setValue(GiantGearStructuralBlock.FACING, targetSide.getOpposite());
                if (occupiedState == requiredStructure)
                    continue;
                if (!occupiedState.getMaterial()
                        .isReplaceable()) {
                    pLevel.destroyBlock(pPos, false);
                    return;
                }
                pLevel.setBlockAndUpdate(structurePos, requiredStructure);
            }
        }
    }


}
