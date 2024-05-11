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
        BlockPos pos = context.getClickedPos();
        Direction.Axis axis = ((GiantGearBlock) getBlock()).getAxisForPlacement(context);
        Vec3 contract = Vec3.atLowerCornerOf(Direction.get(Direction.AxisDirection.POSITIVE, axis)
                .getNormal());

        boolean axis_x = axis == Direction.Axis.X;
        boolean axis_y = axis == Direction.Axis.Y;
        boolean axis_z = axis == Direction.Axis.Z;

        int inflate_yz = !axis_x ? 2 : 1;
        int inflate_xz = !axis_y ? 2 : 1;
        int inflate_xy = !axis_z ? 2 : 1;

        Vec3 inflateZAxis = new Vec3 (inflate_yz, inflate_xz, inflate_xy);
        if (!(context.getPlayer()instanceof LocalPlayer localPlayer))
            return;
        CreateClient.OUTLINER.showAABB(Pair.of("waterwheel", pos), new AABB(pos).inflate(inflateZAxis.x, inflateZAxis.y, inflateZAxis.z)
                        .deflate(contract.x, contract.y, contract.z))
                .colored(0xFF_ff5d6c);
        Lang.translate("large_water_wheel.not_enough_space")
                .color(0xFF_ff5d6c)
                .sendStatus(localPlayer);
    }
}
