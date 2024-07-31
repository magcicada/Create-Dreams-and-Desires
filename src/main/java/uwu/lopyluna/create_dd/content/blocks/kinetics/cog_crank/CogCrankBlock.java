package uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.kinetics.crank.HandCrankBlock;
import com.simibubi.create.content.kinetics.simpleRelays.CogwheelBlockItem;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import uwu.lopyluna.create_dd.infrastructure.config.DesiresConfigs;
import uwu.lopyluna.create_dd.registry.DesiresBlockEntityTypes;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock.isValidCogwheelPosition;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CogCrankBlock extends HandCrankBlock
        implements ICogWheel {

    public CogCrankBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return AllShapes.SMALL_GEAR.get(state.getValue(FACING).getAxis());
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
                                 BlockHitResult hit) {
        if (player.isSpectator())
            return InteractionResult.PASS;
        if (player.getItemInHand(handIn).getItem() instanceof CogwheelBlockItem)
            return InteractionResult.PASS;
        withBlockEntityDo(worldIn, pos, be -> be.turn(player.isShiftKeyDown()));
        if (!player.getItemInHand(handIn)
                .is(AllItems.EXTENDO_GRIP.get()))
            player.causeFoodExhaustion(getRotationSpeed() * DesiresConfigs.server().kinetics.cogCrankHungerMultiplier.getF());

        if (player.getFoodData()
                .getFoodLevel() == 0)
            AllAdvancements.HAND_CRANK.awardTo(player);

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return isValidCogwheelPosition(ICogWheel.isLargeCog(state), worldIn, pos, state.getValue(FACING).getAxis());
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return false;
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING).getAxis();
    }

    @Override
    public BlockEntityType<? extends CogCrankBlockEntity> getBlockEntityType() {
        return DesiresBlockEntityTypes.COG_CRANK.get();
    }

    public static Couple<Integer> getSpeedRange() {
        return Couple.create(32, 32);
    }

}