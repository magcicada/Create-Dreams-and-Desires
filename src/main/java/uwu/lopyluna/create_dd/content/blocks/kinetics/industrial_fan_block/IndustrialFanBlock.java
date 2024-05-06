package uwu.lopyluna.create_dd.content.blocks.kinetics.industrial_fan_block;

import com.simibubi.create.content.kinetics.fan.EncasedFanBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresBlockEntityTypes;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class IndustrialFanBlock extends EncasedFanBlock implements ICogWheel {
    public IndustrialFanBlock(Properties properties) {
        super(properties);
    }


    @Override
    public boolean showCapacityWithAnnotation() {
        return true;
    }

    @Override
    public BlockState updateAfterWrenched(BlockState newState, UseOnContext context) {
        BlockEntity be = context.getLevel().getBlockEntity(context.getClickedPos());
        if (be instanceof IndustrialFanBlockEntity) {
            ((IndustrialFanBlockEntity) be).reActivateSource = true;
        }
        blockUpdate(newState, context.getLevel(), context.getClickedPos());
        return newState;
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        BlockEntity be = context.getLevel().getBlockEntity(context.getClickedPos());
        if (be instanceof IndustrialFanBlockEntity) {
            ((IndustrialFanBlockEntity) be).reActivateSource = true;
        }
        return super.onWrenched(state, context);
    }

    @Override
    public BlockEntityType<? extends IndustrialFanBlockEntity> getBlockEntityType() {
        return DesiresBlockEntityTypes.INDUSTRIAL_FAN.get();
    }
}
