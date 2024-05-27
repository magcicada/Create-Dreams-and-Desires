package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.content.contraptions.MountedFluidStorage;
import com.simibubi.create.foundation.fluid.SmartFluidTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = MountedFluidStorage.class, remap = false)
public interface AccessorMountedFluidStorage {

    @Accessor("tank")
    SmartFluidTank create_dd$getTank();

    @Accessor("tank")
    void create_dd$setTank(SmartFluidTank tank);


}
