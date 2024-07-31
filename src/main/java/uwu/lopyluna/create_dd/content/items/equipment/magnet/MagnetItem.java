package uwu.lopyluna.create_dd.content.items.equipment.magnet;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@SuppressWarnings({"removal", "unused", "all"})
@Deprecated(forRemoval=true)
public class MagnetItem extends Item {
    public MagnetItem(Properties pProperties) {
        super(pProperties.stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (pEntity instanceof Player pPlayer && pPlayer.isAlive() && pPlayer.getOffhandItem() == pStack && !(pPlayer.isDeadOrDying() || pPlayer.isSpectator())) {
            Vec3 playerPos = pPlayer.position();
            int Range = 24;
            AABB aabb = new AABB(playerPos.add(-Range, -Range, -Range), playerPos.add(Range, Range, Range));
            List<ItemEntity> getItemEntities = pLevel.getEntitiesOfClass(ItemEntity.class, aabb, itemEntity -> true);
            if (!getItemEntities.isEmpty()) {
                for (ItemEntity itemEntity : getItemEntities) {
                    if (!itemEntity.isAlive() || DesiresTags.AllItemTags.MAGNET_IGNORE.matches(itemEntity.getItem()))
                        continue;

                    double distanceX = pPlayer.getX() - itemEntity.getX();
                    double distanceY = pPlayer.getY() + (double)pPlayer.getEyeHeight() / 2.0D - itemEntity.getY();
                    double distanceZ = pPlayer.getZ() - itemEntity.getZ();
                    double factor = 1.25;
                    Vec3 vec3 = new Vec3(distanceX * factor, (distanceY * factor) + 0.25, distanceZ * factor);
                    double d0 = vec3.lengthSqr();
                    double d1 = 1.0D - Math.sqrt(d0) / 1.5D;
                    itemEntity.move(MoverType.SELF, itemEntity.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * 0.1D)));
                }
            }
        }
    }
}
