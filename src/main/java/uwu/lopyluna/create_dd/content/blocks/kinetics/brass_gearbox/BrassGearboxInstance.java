package uwu.lopyluna.create_dd.content.blocks.kinetics.brass_gearbox;

import com.jozufozu.flywheel.api.InstanceData;
import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.Material;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.EnumMap;
import java.util.Map;


public class BrassGearboxInstance extends KineticBlockEntityInstance<BrassGearboxBlockEntity> {

    protected final EnumMap<Direction, RotatingData> keys;
    protected Direction sourceFacing;

    public BrassGearboxInstance(MaterialManager materialManager, BrassGearboxBlockEntity blockEntity) {
        super(materialManager, blockEntity);

        keys = new EnumMap<>(Direction.class);

        Block block = blockState.getBlock();
        if (!(block instanceof IRotate def))
            return;

        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = world.getBrightness(LightLayer.SKY, pos);
        updateSourceFacing();

        Material<RotatingData> rotatingMaterial = getRotatingMaterial();

        for (Direction d : Iterate.directions) {
            final Direction.Axis axis = d.getAxis();

            if (!def.hasShaftTowards(blockEntity.getLevel(), blockEntity.getBlockPos(), blockState, d))
                continue;

            Instancer<RotatingData> shaft = rotatingMaterial.getModel(AllPartialModels.SHAFT_HALF, blockState, d);
            RotatingData key = shaft.createInstance();
            key.setRotationAxis(Direction.get(Direction.AxisDirection.POSITIVE, axis).step())
                    .setRotationalSpeed(getSpeed(d))
                    .setRotationOffset(getRotationOffset(axis)).setColor(blockEntity)
                    .setPosition(getInstancePosition())
                    .setBlockLight(blockLight)
                    .setSkyLight(skyLight);
            keys.put(d, key);
            //if (d == Direction.UP) keys.put(d, key);
            //if (d == Direction.DOWN) keys.put(d, key);
            //if (d == Direction.NORTH) keys.put(d, key);
            //if (d == Direction.EAST) keys.put(d, key);
            //if (d == Direction.SOUTH) keys.put(d, key);
            //if (d == Direction.WEST) keys.put(d, key);
        }
    }


    private float getSpeed(Direction direction) {
        float speed = blockEntity.getSpeed();

        if (speed != 0 && sourceFacing != null) {
            if (sourceFacing.getAxis() == direction.getAxis())
                speed *= 1;
            else if (sourceFacing.getAxisDirection() == direction.getAxisDirection())
                speed *= 1;
        }
        return speed;
    }

    protected void updateSourceFacing() {
        if (blockEntity.hasSource()) {
            assert blockEntity.source != null;
            BlockPos source = blockEntity.source.subtract(pos);
            sourceFacing = Direction.getNearest(source.getX(), source.getY(), source.getZ());
        } else {
            sourceFacing = null;
        }
    }

    @Override
    public void update() {
        updateSourceFacing();
        for (Map.Entry<Direction, RotatingData> key : keys.entrySet()) {
            Direction direction = key.getKey();
            Direction.Axis axis = direction.getAxis();

            updateRotation(key.getValue(), axis, getSpeed(direction));
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
