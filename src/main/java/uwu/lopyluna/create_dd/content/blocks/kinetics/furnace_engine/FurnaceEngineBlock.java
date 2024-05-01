package uwu.lopyluna.create_dd.content.blocks.kinetics.furnace_engine;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ShaftBlock;
import com.simibubi.create.content.kinetics.steamEngine.PoweredShaftBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uwu.lopyluna.create_dd.registry.DesiresBlockEntityTypes;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;

@Mod.EventBusSubscriber
public class FurnaceEngineBlock extends FaceAttachedHorizontalDirectionalBlock implements SimpleWaterloggedBlock, IWrenchable, IBE<FurnaceEngineBlockEntity> {
    public FurnaceEngineBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACE, AttachFace.FLOOR).setValue(FACING, Direction.NORTH).setValue(BlockStateProperties.WATERLOGGED, false));

    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return canAttach(pLevel, pPos, getConnectedDirection(pState).getOpposite()) && isValidPosition(pLevel,pPos.relative(getConnectedDirection(pState).getOpposite()),getConnectedDirection(pState));
    }

    public static boolean canAttach(LevelReader pReader, BlockPos pPos, Direction pDirection) {
        BlockPos blockpos = pPos.relative(pDirection);
        return pReader.getBlockState(blockpos).getBlock() instanceof AbstractFurnaceBlock;
    }

    public static boolean isValidPosition(LevelReader world, BlockPos pos, Direction facing) {
        for (Direction otherFacing : Iterate.directions) {
            if (otherFacing != facing) {
                BlockPos otherPos = pos.relative(otherFacing);
                BlockState otherState = world.getBlockState(otherPos);
                if (otherState.getBlock() instanceof FurnaceEngineBlock)
                    return false;
            }
        }
        return true;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACE, FACING, BlockStateProperties.WATERLOGGED));
    }
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState ifluidstate = level.getFluidState(pos);
        BlockState state = super.getStateForPlacement(context);
        return state == null ? null : state.setValue(BlockStateProperties.WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
    }
    @SubscribeEvent
    public static void usingFurnaceEngineOnFurnacePreventsGUI(PlayerInteractEvent.RightClickBlock event) {
        BlockItem blockItem;
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            blockItem = (BlockItem) item;
        } else {
            return;
        }
        if (blockItem.getBlock() != DesiresBlocks.FURNACE_ENGINE.get())
            return;
        BlockState state = event.getLevel().getBlockState(event.getPos());
        if (state.getBlock() instanceof AbstractFurnaceBlock)
            event.setUseBlock(Event.Result.DENY);
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    public static Direction getFacing(BlockState sideState) {
        return getConnectedDirection(sideState);
    }

    public static BlockPos getFlywheelPos(BlockState sideState, BlockPos pos) {
        return pos.relative(getConnectedDirection(sideState), 2);
    }

    public static boolean isFlywheelValid(BlockState state, BlockState shaft) {
        return (AllBlocks.FLYWHEEL.has(shaft) || DesiresBlocks.POWERED_FLYWHEEL.has(shaft)) && shaft.getValue(RotatedPillarKineticBlock.AXIS) != getFacing(state).getAxis();
    }

    @Override
    public Class<FurnaceEngineBlockEntity> getBlockEntityClass() {
        return FurnaceEngineBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends FurnaceEngineBlockEntity> getBlockEntityType() {
        return DesiresBlockEntityTypes.FURNACE_ENGINE.get();
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        BlockPos shaftPos = getFlywheelPos(pState, pPos);
        BlockState shaftState = pLevel.getBlockState(shaftPos);
        if (isFlywheelValid(pState, shaftState))
            pLevel.setBlock(shaftPos, PoweredFlywheelBlock.getEquivalent(shaftState,shaftState.getValue(RotatedPillarKineticBlock.AXIS)), 3);
    }
}
