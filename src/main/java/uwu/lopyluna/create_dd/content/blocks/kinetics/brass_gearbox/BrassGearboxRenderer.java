package uwu.lopyluna.create_dd.content.blocks.kinetics.brass_gearbox;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BrassGearboxRenderer extends KineticBlockEntityRenderer<BrassGearboxBlockEntity> {
    public BrassGearboxRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(BrassGearboxBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
        if (Backend.canUseInstancing(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();
        Block block = blockState.getBlock();
        if (!(block instanceof IRotate def))
            return;

        Direction.Axis axisX =  Direction.Axis.X;
        Direction.Axis axisY =  Direction.Axis.Y;
        Direction.Axis axisZ =  Direction.Axis.Z;
        BlockPos pos = be.getBlockPos();
        float angleX = getAngleForTe(be, pos, axisX);
        float angleY = getAngleForTe(be, pos, axisY);
        float angleZ = getAngleForTe(be, pos, axisZ);


        for (Direction d : Iterate.directions) {
            if (!def.hasShaftTowards(be.getLevel(), be.getBlockPos(), blockState, d))
                continue;
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), d);
            boolean bX = d == Direction.EAST || d == Direction.WEST;
            boolean bY = d == Direction.UP || d == Direction.DOWN;
            boolean bZ = d == Direction.NORTH || d == Direction.SOUTH;
            if (bX) kineticRotationTransform(shaft, be, axisX, angleX, light);
            if (bY) kineticRotationTransform(shaft, be, axisY, angleY, light);
            if (bZ) kineticRotationTransform(shaft, be, axisZ, angleZ, light);

            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
        }

    }
}
