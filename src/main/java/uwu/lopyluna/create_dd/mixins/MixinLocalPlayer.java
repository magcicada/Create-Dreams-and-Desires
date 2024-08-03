package uwu.lopyluna.create_dd.mixins;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import uwu.lopyluna.create_dd.content.blocks.contraptions.contraption_block.BoatContraptionEntity;

@Mixin(value = LocalPlayer.class)
public class MixinLocalPlayer extends AbstractClientPlayer {

    @Shadow public Input input;
    @Shadow private boolean handsBusy;

    public MixinLocalPlayer(ClientLevel pClientLevel, GameProfile pGameProfile, @Nullable ProfilePublicKey pProfilePublicKey) {
        super(pClientLevel, pGameProfile, pProfilePublicKey);
    }

    /**
     * @author u
     * @reason u
     */
    @Overwrite
    public void rideTick() {
        super.rideTick();
        if (this.wantsToStopRiding() && this.isPassenger()) this.input.shiftKeyDown = false;
        this.handsBusy = false;
        if (this.getVehicle() instanceof Boat boat) {
            boat.setInput(this.input.left, this.input.right, this.input.up, this.input.down);
            this.handsBusy |= this.input.left || this.input.right || this.input.up || this.input.down;
        }
        if (this.getVehicle() instanceof BoatContraptionEntity boat) {
            boat.setInput(this.input.left, this.input.right);
            this.handsBusy |= this.input.left || this.input.right;
        }

    }
}
