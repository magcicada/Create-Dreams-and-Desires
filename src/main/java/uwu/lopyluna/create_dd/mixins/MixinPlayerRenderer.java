package uwu.lopyluna.create_dd.mixins;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import uwu.lopyluna.create_dd.content.items.equipment.excavation_drill.ExcavationDrillItem;

@Mixin(PlayerRenderer.class)
public class MixinPlayerRenderer {
    @Inject(method = "getArmPose(Lnet/minecraft/client/player/AbstractClientPlayer;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
            at = @At(value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/player/AbstractClientPlayer;getItemInHand(Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void create$onGetArmPose(AbstractClientPlayer pPlayer, InteractionHand pHand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        ItemStack itemstackM = pPlayer.getItemInHand(pHand);
        if (!pPlayer.swinging && itemstackM.getItem() instanceof ExcavationDrillItem) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
