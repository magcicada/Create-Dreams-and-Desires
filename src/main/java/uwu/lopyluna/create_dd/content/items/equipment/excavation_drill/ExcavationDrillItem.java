package uwu.lopyluna.create_dd.content.items.equipment.excavation_drill;

import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.foundation.item.CustomArmPoseItem;
import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.create_dd.content.items.equipment.BackTankPickaxeItem;
import uwu.lopyluna.create_dd.infrastructure.utility.BoreMining;
import uwu.lopyluna.create_dd.registry.DesiresItems;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import javax.annotation.ParametersAreNonnullByDefault;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static uwu.lopyluna.create_dd.infrastructure.utility.VeinMining.findVein;
import static uwu.lopyluna.create_dd.registry.DesireTiers.Drill;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings({"all"})
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExcavationDrillItem extends BackTankPickaxeItem implements CustomArmPoseItem {
    private static final Set<BlockPos> hashedBlocks = new HashSet<>();
    private static boolean veinExcavating = false;
    public ExcavationDrillItem(Properties pProperties) {
        super(Drill, 1, -2.8F, pProperties);
    }

    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;

        return pState.is(pState.getBlock()) && player.isCrouching() ? pState.is(DesiresTags.forgeBlockTag("ores")) ? this.speed * 0.4F : this.speed * 0.6F :
                pState.is(pState.getBlock()) && !player.isCrouching() ? this.speed * 1.25F : 0.5F;
    }

    public static void destroyVein(Level pLevel, BlockState state, BlockPos pos,
                                   Player player) {

        if (veinExcavating || !(state.is(DesiresTags.forgeBlockTag("ores"))) || !player.isCrouching())
            return;
        Vec3 vec = player.getLookAngle();

        veinExcavating = true;
        findVein(pLevel, pos).destroyBlocks(pLevel, player, (dropPos, item) -> dropItemFromExcavatedVein(pLevel, pos, vec, dropPos, item));
        veinExcavating = false;
    }

    public static void dropItemFromExcavatedVein(Level world, BlockPos breakingPos, Vec3 fallDirection, BlockPos pos,
                                           ItemStack stack) {
        float distance = (float) Math.sqrt(pos.distSqr(breakingPos));
        Vec3 dropPos = VecHelper.getCenterOf(pos);
        ItemEntity entity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
        entity.setDeltaMovement(fallDirection.scale(distance / 16f));
        world.addFreshEntity(entity);
    }

    public static boolean validBlocks(BlockState pState, BlockEvent.BreakEvent event, BlockPos blockPos) {
        ItemStack heldItemMainhand = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
        return pState.getDestroySpeed(event.getLevel(), blockPos) != 0.0F ||
                    !pState.is(DesiresTags.forgeBlockTag("ores")) ||
                    DesiresItems.EXCAVATION_DRILL.get().isCorrectToolForDrops(heldItemMainhand, pState);
    }

    @SubscribeEvent
    public static void onBlockDestroyed(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = (Level) event.getLevel();
        ItemStack heldItemMainhand = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos blockPos = event.getPos();

        boolean validOres = event.getLevel().getBlockState(blockPos).is(DesiresTags.forgeBlockTag("ores"));

        if (DesiresItems.EXCAVATION_DRILL.isIn(heldItemMainhand) && validOres) {
            destroyVein((Level) event.getLevel(), event.getState(), event.getPos(), event.getPlayer());
        }

        if (DesiresItems.EXCAVATION_DRILL.isIn(heldItemMainhand) && player instanceof ServerPlayer serverPlayer && player.isCrouching() && !validOres) {
            BlockPos initalBlockPos = event.getPos();

            if (hashedBlocks.contains(initalBlockPos)) {
                return;
            }

            for (BlockPos pos : BoreMining.getBlocksToBeDestroyed(1, initalBlockPos, serverPlayer)) {
                if(pos == initalBlockPos || !(event.getLevel().getBlockState(pos).getDestroySpeed(event.getLevel(), pos) != 0.0F) ||
                        event.getLevel().getBlockState(pos).is(DesiresTags.forgeBlockTag("ores")) ||
                        !DesiresItems.EXCAVATION_DRILL.get().isCorrectToolForDrops(heldItemMainhand, event.getLevel().getBlockState(pos))) {
                    continue;
                }
                SoundType soundType = level.getBlockState(pos).getSoundType();
                if (!level.isClientSide()) {
                    level.playSound(player, pos, soundType.getBreakSound(), SoundSource.BLOCKS, soundType.getVolume() - 0.5F, soundType.getPitch() + 0.25F);
                }

                hashedBlocks.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                hashedBlocks.remove(pos);
            }
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    @Nullable
    public HumanoidModel.ArmPose getArmPose(ItemStack stack, AbstractClientPlayer player, InteractionHand hand) {
        if (!player.swinging) {
            return HumanoidModel.ArmPose.CROSSBOW_HOLD;
        }
        return null;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new ExcavationDrillRenderer()));
    }

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
        return Drill.getUses();
    }
}
