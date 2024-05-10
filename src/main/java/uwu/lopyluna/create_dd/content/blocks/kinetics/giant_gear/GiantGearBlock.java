package uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear;

import com.simibubi.create.content.kinetics.base.RotatedPillarKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresBlockEntityTypes;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@SuppressWarnings({"deprecation", "all"})
public class GiantGearBlock extends RotatedPillarKineticBlock implements IBE<GiantGearBlockEntity> {
    public GiantGearBlock(Properties properties) {
        super(properties);
    }

    public Direction.Axis getAxisForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).getValue(AXIS);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.PASS;
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
}
