package uwu.lopyluna.create_dd.content.items.equipment.deforester_saw;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import uwu.lopyluna.create_dd.content.items.equipment.BackTankAxeItem;
import uwu.lopyluna.create_dd.registry.DesiresItems;

import javax.annotation.ParametersAreNonnullByDefault;

import java.util.function.Consumer;

import static com.simibubi.create.foundation.utility.TreeCutter.findTree;
import static uwu.lopyluna.create_dd.registry.DesireTiers.Deforester;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DeforesterSawItem extends BackTankAxeItem {
    private static boolean deforesting = false; // required as to not run into "recursions" over forge events on tree cutting
    public DeforesterSawItem(Properties pProperties) {
        super(Deforester, 6.0F, -3.2F, pProperties);
    }

    // Moved away from Item#onBlockDestroyed as it does not get called in Creative
    public static void destroyTree(Level pLevel, BlockState state, BlockPos pos,
                                   Player player) {

        if (deforesting || !(state.is(BlockTags.LOGS) || AllTags.AllBlockTags.SLIMY_LOGS.matches(state)) || !player.isCrouching())
            return;
        Vec3 vec = player.getLookAngle();

        deforesting = true;
        findTree(pLevel, pos).destroyBlocks(pLevel, player, (dropPos, item) -> dropItemFromCutTree(pLevel, pos, vec, dropPos, item));
        deforesting = false;
    }

    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        ItemStack heldItemMainhand = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);

        if (!DesiresItems.DEFORESTER_SAW.isIn(heldItemMainhand))
            return;
        destroyTree((Level) event.getLevel(), event.getState(), event.getPos(), event.getPlayer());
    }

    public static void dropItemFromCutTree(Level world, BlockPos breakingPos, Vec3 fallDirection, BlockPos pos,
                                           ItemStack stack) {
        float distance = (float) Math.sqrt(pos.distSqr(breakingPos));
        Vec3 dropPos = VecHelper.getCenterOf(pos);
        ItemEntity entity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
        entity.setDeltaMovement(fallDirection.scale(distance / 16f));
        world.addFreshEntity(entity);
    }

    //@Override
    //@OnlyIn(Dist.CLIENT)
    //public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    //    consumer.accept(SimpleCustomRenderer.create(this, new DeforesterSawRenderer()));
    //}


    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return BacktankUtil.isBarVisible(stack, maxUses());
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        return BacktankUtil.getBarWidth(stack, maxUses());
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return BacktankUtil.getBarColor(stack, maxUses());
    }


    public static int maxUses() {
        return Deforester.getUses();
    }

}
