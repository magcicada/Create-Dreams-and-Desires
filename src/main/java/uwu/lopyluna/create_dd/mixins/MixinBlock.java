package uwu.lopyluna.create_dd.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import static net.minecraft.world.level.block.Block.dropResources;

@Mixin(Block.class)
public class MixinBlock {


    //@Inject(at = @At("TAIL"), method = "playerDestroy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/item/ItemStack;)V")

    /**
     * @author _
     * @reason _
     */
    @SuppressWarnings({"deprecation"})
    @Overwrite
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, BlockEntity pBlockEntity, ItemStack pTool) {
        pPlayer.awardStat(Stats.BLOCK_MINED.get(pState.getBlock()));
        if (DesiresTags.AllItemTags.ADDITIONAL_DROPS_TOOL.matches(pTool)) {
            int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pTool);
            int lootingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, pTool);

            pTool.setTag(null);
            pTool.enchant(Enchantments.BLOCK_FORTUNE, fortuneLevel + fortuneLevel + lootingLevel + 1);
            pLevel.playSound(pPlayer, pPos, pState.getBlock().defaultBlockState().getSoundType().getPlaceSound(), SoundSource.BLOCKS, (pState.getBlock().defaultBlockState().getSoundType().getVolume() + 1.0F) / 2.0F, pState.getBlock().defaultBlockState().getSoundType().getPitch() * 1.2F);
            pPlayer.causeFoodExhaustion(0.01F);
            dropResources(pState, pLevel, pPos, pBlockEntity, pPlayer, pTool);
        } else {
            pPlayer.causeFoodExhaustion(0.005F);
            dropResources(pState, pLevel, pPos, pBlockEntity, pPlayer, pTool);
        }
    }


}
