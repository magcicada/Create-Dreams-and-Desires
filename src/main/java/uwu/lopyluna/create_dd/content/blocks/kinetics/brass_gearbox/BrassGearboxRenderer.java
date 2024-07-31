package uwu.lopyluna.create_dd.content.blocks.kinetics.brass_gearbox;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresPartialModels;

public class BrassGearboxRenderer extends KineticBlockEntityRenderer<BrassGearboxBlockEntity> {
    public BrassGearboxRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }



    @Override
    protected void renderSafe(BrassGearboxBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {

        if (Backend.canUseInstancing(be.getLevel()))
            return;

        BlockState blockState = be.getBlockState();
        Direction.Axis axisX =  Direction.Axis.X;
        Direction.Axis axisY =  Direction.Axis.Y;
        Direction.Axis axisZ =  Direction.Axis.Z;
        BlockPos pos = be.getBlockPos();
        float angleX = getAngleForTe(be, pos, axisX);
        float angleY = getAngleForTe(be, pos, axisY);
        float angleZ = getAngleForTe(be, pos, axisZ);

        if (!blockState.getValue(BrassGearboxBlock.UP_SHAFT)) {
            ms.pushPose();
            CachedBufferer.partial(DesiresPartialModels.TOP_BRASS_PANEL, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid())
                );
            ms.popPose();
        }
        if (!blockState.getValue(BrassGearboxBlock.DOWN_SHAFT)) {
            ms.pushPose();
            CachedBufferer.partial(DesiresPartialModels.BOTTOM_BRASS_PANEL, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid())
                );
            ms.popPose();
        }
        if (!blockState.getValue(BrassGearboxBlock.NORTH_SHAFT)) {
            ms.pushPose();
            CachedBufferer.partial(DesiresPartialModels.NORTH_BRASS_PANEL, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid())
                );
            ms.popPose();
        }
        if (!blockState.getValue(BrassGearboxBlock.EAST_SHAFT)) {
            ms.pushPose();
            CachedBufferer.partial(DesiresPartialModels.EAST_BRASS_PANEL, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid())
                );
            ms.popPose();
        }
        if (!blockState.getValue(BrassGearboxBlock.SOUTH_SHAFT)) {
            ms.pushPose();
            CachedBufferer.partial(DesiresPartialModels.SOUTH_BRASS_PANEL, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid())
                );
            ms.popPose();
        }
        if (!blockState.getValue(BrassGearboxBlock.WEST_SHAFT)) {
            ms.pushPose();
            CachedBufferer.partial(DesiresPartialModels.WEST_BRASS_PANEL, be.getBlockState())
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.solid())
                );
            ms.popPose();
        }

        if (blockState.getValue(BrassGearboxBlock.UP_SHAFT)) {
            ms.pushPose();
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), Direction.UP);
            kineticRotationTransform(shaft, be, axisX, angleY, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
            ms.popPose();
        }
        if (blockState.getValue(BrassGearboxBlock.DOWN_SHAFT)) {
            ms.pushPose();
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), Direction.DOWN);
            kineticRotationTransform(shaft, be, axisX, angleY, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
            ms.popPose();
        }
        if (blockState.getValue(BrassGearboxBlock.NORTH_SHAFT)) {
            ms.pushPose();
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), Direction.NORTH);
            kineticRotationTransform(shaft, be, axisX, angleZ, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
            ms.popPose();
        }
        if (blockState.getValue(BrassGearboxBlock.EAST_SHAFT)) {
            ms.pushPose();
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), Direction.EAST);
            kineticRotationTransform(shaft, be, axisX, angleX, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
            ms.popPose();
        }
        if (blockState.getValue(BrassGearboxBlock.SOUTH_SHAFT)) {
            ms.pushPose();
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), Direction.SOUTH);
            kineticRotationTransform(shaft, be, axisX, angleZ, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
            ms.popPose();
        }
        if (blockState.getValue(BrassGearboxBlock.WEST_SHAFT)) {
            ms.pushPose();
            SuperByteBuffer shaft = CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, be.getBlockState(), Direction.WEST);
            kineticRotationTransform(shaft, be, axisX, angleX, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
            ms.popPose();
        }
    }
}
