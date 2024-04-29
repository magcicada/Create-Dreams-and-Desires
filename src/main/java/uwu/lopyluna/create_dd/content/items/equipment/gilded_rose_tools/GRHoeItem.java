package uwu.lopyluna.create_dd.content.items.equipment.gilded_rose_tools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@SuppressWarnings({"deprecation"})
public class GRHoeItem extends HoeItem {
    public GRHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    boolean isOnCooldown;
    boolean offHandPower;

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            if (pEntity instanceof Player pPlayer && pIsSelected) {
                isOnCooldown = pPlayer.getCooldowns().isOnCooldown(pPlayer.getMainHandItem().getItem());
                offHandPower = DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pPlayer.getOffhandItem().getItem());
            }
        }
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            return isOnCooldown ? 0.1F : this.speed;
        } else return super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !isOnCooldown;
    }

    @Override
    public float getAttackDamage() {
        return isOnCooldown ? 0.0F : super.getAttackDamage();
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            if (pAttacker instanceof Player pPlayer && !isOnCooldown) {
                resetCooldown(pStack, pPlayer);
                return true;
            }
            return false;
        } else return super.hurtEnemy(pStack, pTarget, pAttacker);
    }


    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            if (pEntityLiving instanceof Player pPlayer && !isOnCooldown) {
                resetCooldown(pStack, pPlayer);
                return true;
            }
            return false;
        } else return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }

    public void resetCooldown(ItemStack pStack, Player pPlayer) {
        if (!pPlayer.getCooldowns().isOnCooldown(pPlayer.getMainHandItem().getItem())) {
            pPlayer.getCooldowns().addCooldown(this, getUseDuration(pStack));
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(stack)) {
            if (enchantment == Enchantments.VANISHING_CURSE)
                return true;
            if (enchantment == Enchantments.BLOCK_EFFICIENCY)
                return true;
            if (enchantment == Enchantments.MOB_LOOTING)
                return true;
            return enchantment == Enchantments.BLOCK_FORTUNE;
        } else return super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(itemStack)) {
            return new ItemStack(this.getCraftingRemainingItem());
        } else return super.getCraftingRemainingItem(itemStack);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            return false;
        } else return super.isBarVisible(pStack);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(stack)) {
            return false;
        } else return super.isDamageable(stack);
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(stack)) {
            return false;
        } else return super.isRepairable(stack);
    }

    public float efficiencyDuration(ItemStack pStack) {
        int efficiencyLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, pStack);
        return efficiencyLevel == 0 ? 1 :
                efficiencyLevel == 1 ? 1.25F :
                        efficiencyLevel == 2 ? 1.5F :
                                efficiencyLevel == 3 ? 1.75F :
                                        efficiencyLevel == 4 ? 2 :
                                                efficiencyLevel == 5 ? 2.25F : 1;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pStack);
            int lootingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, pStack);
            if (offHandPower) {
                return (int) (((fortuneLevel + lootingLevel + 1) * 20) / efficiencyDuration(pStack)) + 20;
            } else {
                return (int) (((fortuneLevel + lootingLevel + 1) * 20) / efficiencyDuration(pStack)) + (2 * 20);
            }
        } else return super.getUseDuration(pStack);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            return false;
        } else return super.isFoil(pStack);
    }
    @Override
    public boolean isEnchantable(ItemStack pStack) {
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pStack)) {
            return false;
        } else return super.isEnchantable(pStack);
    }
}
