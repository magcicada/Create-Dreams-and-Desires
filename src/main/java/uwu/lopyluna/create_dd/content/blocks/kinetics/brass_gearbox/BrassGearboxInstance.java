package uwu.lopyluna.create_dd.content.blocks.kinetics.brass_gearbox;

import com.jozufozu.flywheel.api.InstanceData;
import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.Materials;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.foundation.render.AllMaterialSpecs;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.create_dd.registry.DesiresPartialModels;

import java.util.EnumMap;
import java.util.Map;


public class BrassGearboxInstance extends KineticBlockEntityInstance<BrassGearboxBlockEntity> {

    protected final EnumMap<Direction, RotatingData> keys;
    protected Direction sourceFacing;

    public BrassGearboxInstance(MaterialManager materialManager, BrassGearboxBlockEntity blockEntity) {
        super(materialManager, blockEntity);

        keys = new EnumMap<>(Direction.class);

        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = world.getBrightness(LightLayer.SKY, pos);
        updateSourceFacing(blockEntity);

        if (!blockState.getValue(BrassGearboxBlock.UP_SHAFT)) materialManager.defaultSolid().material(Materials.TRANSFORMED)
                .getModel(DesiresPartialModels.TOP_BRASS_PANEL, blockState).createInstance()
                .setBlockLight(blockLight)
                .setSkyLight(blockLight);
        if (!blockState.getValue(BrassGearboxBlock.DOWN_SHAFT)) materialManager.defaultSolid().material(Materials.TRANSFORMED)
                .getModel(DesiresPartialModels.BOTTOM_BRASS_PANEL, blockState).createInstance()
                .setBlockLight(blockLight)
                .setSkyLight(blockLight);
        if (!blockState.getValue(BrassGearboxBlock.NORTH_SHAFT)) materialManager.defaultSolid().material(Materials.TRANSFORMED)
                .getModel(DesiresPartialModels.NORTH_BRASS_PANEL, blockState).createInstance()
                .setBlockLight(blockLight)
                .setSkyLight(blockLight);
        if (!blockState.getValue(BrassGearboxBlock.EAST_SHAFT)) materialManager.defaultSolid().material(Materials.TRANSFORMED)
                .getModel(DesiresPartialModels.EAST_BRASS_PANEL, blockState).createInstance()
                .setBlockLight(blockLight)
                .setSkyLight(blockLight);
        if (!blockState.getValue(BrassGearboxBlock.SOUTH_SHAFT)) materialManager.defaultSolid().material(Materials.TRANSFORMED)
                .getModel(DesiresPartialModels.SOUTH_BRASS_PANEL, blockState).createInstance()
                .setBlockLight(blockLight)
                .setSkyLight(blockLight);
        if (!blockState.getValue(BrassGearboxBlock.WEST_SHAFT)) materialManager.defaultSolid().material(Materials.TRANSFORMED)
                .getModel(DesiresPartialModels.WEST_BRASS_PANEL, blockState).createInstance()
                .setBlockLight(blockLight)
                .setSkyLight(blockLight);

        Block block = blockEntity.getBlockState().getBlock();
        if (!(block instanceof IRotate def))
            return;

        for (Direction d : Iterate.directions) {
            final Direction.Axis axis = d.getAxis();

            if (!def.hasShaftTowards(blockEntity.getLevel(), blockEntity.getBlockPos(), blockState, d))
                continue;

            RotatingData key = materialManager.defaultSolid().material(AllMaterialSpecs.ROTATING)
                    .getModel(AllPartialModels.SHAFT_HALF, blockState, d).createInstance();

            key.setRotationAxis(Direction.get(Direction.AxisDirection.POSITIVE, axis).step())
                    .setRotationalSpeed(getSpeed(d, blockEntity))
                    .setRotationOffset(getRotationOffset(axis))
                    .setPosition(getInstancePosition())
                    .setBlockLight(blockLight)
                    .setSkyLight(skyLight);

            keys.put(d, key);
        }
    }


    private float getSpeed(Direction direction, BrassGearboxBlockEntity blockEntity) {
        float speed = blockEntity.getSpeed();

        if (speed != 0 && sourceFacing != null) {
            if (sourceFacing.getAxis() == direction.getAxis())
                speed *= 1;
            else if (sourceFacing.getAxisDirection() == direction.getAxisDirection())
                speed *= 1;
        }
        return speed;
    }

    protected void updateSourceFacing(BrassGearboxBlockEntity blockEntity) {
        if (blockEntity.hasSource()) {
            BlockPos source = blockEntity.source != null ? blockEntity.source.subtract(pos) : null;
            sourceFacing = source != null ? Direction.getNearest(source.getX(), source.getY(), source.getZ()) : null;
        } else {
            sourceFacing = null;
        }
    }

    @Override
    public void update() {
        updateSourceFacing(blockEntity);
        for (Map.Entry<Direction, RotatingData> key : keys.entrySet()) {
            Direction direction = key.getKey();
            Direction.Axis axis = direction.getAxis();

            updateRotation(key.getValue(), axis, getSpeed(direction, blockEntity));
        }
    }

    @Override
    public void updateLight() {
        relight(pos, keys.values().stream());
    }

    @Override
    public void remove() {
        keys.values().forEach(InstanceData::delete);
        keys.clear();
    }
}
