package uwu.lopyluna.create_dd.content.items.equipment;

import com.simibubi.create.content.equipment.armor.BacktankUtil;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings({"all"})
public class BackTankPickaxeItem extends PickaxeItem {

    int getUses;

    public BackTankPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        getUses = pTier.getUses();
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!BacktankUtil.canAbsorbDamage(pAttacker, getUses))
            pStack.hurtAndBreak(2, pAttacker, p -> {
                p.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!BacktankUtil.canAbsorbDamage(pEntityLiving, getUses) && !pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(1, pEntityLiving, (p_40992_) -> {
                p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.VANISHING_CURSE)
            return true;
        if (enchantment == Enchantments.UNBREAKING)
            return false;
        if (enchantment == Enchantments.MENDING)
            return false;
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }
}