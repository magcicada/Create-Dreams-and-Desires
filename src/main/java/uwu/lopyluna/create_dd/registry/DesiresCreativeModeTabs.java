package uwu.lopyluna.create_dd.registry;

import net.minecraft.world.item.CreativeModeTab;
import uwu.lopyluna.create_dd.infrastructure.item.BaseCreativeModeTab;
import uwu.lopyluna.create_dd.infrastructure.item.BetaCreativeModeTab;
import uwu.lopyluna.create_dd.infrastructure.item.PalettesCreativeModeTab;

public class DesiresCreativeModeTabs {
	public static final CreativeModeTab BASE_CREATIVE_TAB = new BaseCreativeModeTab();
	public static final CreativeModeTab PALETTES_CREATIVE_TAB = new PalettesCreativeModeTab();
	public static final CreativeModeTab BETA_CREATIVE_TAB = new BetaCreativeModeTab();

	public static void init() {
	}
}
