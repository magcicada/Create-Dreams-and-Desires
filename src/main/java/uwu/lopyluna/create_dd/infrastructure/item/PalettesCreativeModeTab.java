package uwu.lopyluna.create_dd.infrastructure.item;

import com.simibubi.create.AllBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PalettesCreativeModeTab extends CreateCreativeModeTab {
	public PalettesCreativeModeTab() {
		super("palettes");
	}

	@Override
	public void addItems(NonNullList<ItemStack> items, boolean specialItems) {
	}

	@Override
	@NotNull
	public ItemStack makeIcon() {
		return AllBlocks.EXPERIENCE_BLOCK.asStack();
	}
}
