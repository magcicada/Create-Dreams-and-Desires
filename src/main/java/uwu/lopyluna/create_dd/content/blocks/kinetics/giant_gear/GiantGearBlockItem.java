package uwu.lopyluna.create_dd.content.blocks.kinetics.giant_gear;

import com.simibubi.create.CreateClient;
import uwu.lopyluna.create_dd.registry.helper.Lang;
import com.simibubi.create.foundation.utility.Pair;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class GiantGearBlockItem extends BlockItem {
    public GiantGearBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public @NotNull InteractionResult place(@NotNull BlockPlaceContext ctx) {
        InteractionResult result = super.place(ctx);
        if (result != InteractionResult.FAIL)
            return result;
        Direction clickedFace = ctx.getClickedFace();
        if (clickedFace.getAxis() != ((GiantGearBlock) getBlock()).getAxisForPlacement(ctx))
            result = super.place(BlockPlaceContext.at(ctx, ctx.getClickedPos()
                    .relative(clickedFace), clickedFace));
        if (result == InteractionResult.FAIL && ctx.getLevel()
                .isClientSide())
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> showBounds(ctx));
        return result;
    }

    @OnlyIn(Dist.CLIENT)
    public void showBounds(BlockPlaceContext context) {
        if (!(context.getPlayer() instanceof LocalPlayer localPlayer))
            return;
        BlockPos pos = context.getClickedPos();
        Direction.Axis axis = ((GiantGearBlock) getBlock()).getAxisForPlacement(context);
        
        List<Direction.Axis> perpendicularDirections = Arrays.stream(Direction.Axis.values()).filter(other -> other != axis).toList();
        
        CreateClient.OUTLINER.showAABB(Pair.of("waterwheel-core", pos), new AABB(
                pos
                    .relative(perpendicularDirections.get(0), -2)
                    .relative(perpendicularDirections.get(1), -2),
                pos
                    .relative(perpendicularDirections.get(0), 2)
                    .relative(perpendicularDirections.get(1), 2)
                    .offset(1, 1, 1)
            ))
            .colored(0xFF_ff5d6c);
        
        for (int i = 0; i < 2; i++) {
            Direction.Axis primaryAxis = perpendicularDirections.get(i);
            Direction.Axis secondaryAxis = perpendicularDirections.get(i == 0 ? 1 : 0);
            
            CreateClient.OUTLINER.showAABB(Pair.of("waterwheel-"+primaryAxis.getName(), pos), new AABB(
                    pos
                        .relative(primaryAxis, -3)
                        .relative(secondaryAxis, -1),
                    pos
                        .relative(primaryAxis, 3)
                        .relative(secondaryAxis, 1)
                        .offset(1, 1, 1)
                ))
                .colored(0xFF_ff5d6c);
        }
        
        Lang.translate("large_water_wheel.not_enough_space")
                .color(0xFF_ff5d6c)
                .sendStatus(localPlayer);
    }
    
}
