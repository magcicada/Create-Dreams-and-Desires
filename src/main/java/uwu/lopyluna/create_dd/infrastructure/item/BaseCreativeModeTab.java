package uwu.lopyluna.create_dd.infrastructure.item;

import com.simibubi.create.AllItems;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BaseCreativeModeTab extends CreateCreativeModeTab {
	public BaseCreativeModeTab() {
		super("base");
	}

	@Override
	@NotNull
	public ItemStack makeIcon() {
		return AllItems.PRECISION_MECHANISM.asStack();
	}
}
