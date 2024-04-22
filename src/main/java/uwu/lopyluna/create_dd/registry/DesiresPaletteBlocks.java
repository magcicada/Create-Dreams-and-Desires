package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import uwu.lopyluna.create_dd.DesiresCreate;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

public class DesiresPaletteBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB);
	}









	public static final int COLORED_BLOCKS = generateColorBlocks();

	//WALLPAPERS
	public static int generateColorBlocks(){
		String[] colors = {"black", "white", "blue", "light_blue", "red", "green", "lime", "pink", "magenta", "yellow", "gray", "light_gray", "brown", "cyan", "purple", "orange"};
		for (String color : colors) {
			String firstLetter = color.substring(0, 1).toUpperCase();
			String colorWithoutC = color.substring(1);

			String upColor = firstLetter + colorWithoutC;
			String light = "Light";
			if (upColor.contains(light)) {
				String nameWithoutLight = upColor.substring(6);

				String firstLetter2 = nameWithoutLight.substring(0, 1).toUpperCase();
				String colorWithoutC2 = nameWithoutLight.substring(1);

				upColor = light + " " + firstLetter2 + colorWithoutC2;
			}

			REGISTRATE.block(color + "_asphalt_block", Block::new)
					.initialProperties(SharedProperties::stone)
					.blockstate((c, p) -> p.simpleBlock(c.get(), p.models()
								.cubeAll(c.getName(), p.modLoc("block/asphalt/" + color))))
					.properties(p -> p.destroyTime(1.25f)
							.speedFactor(1.35F)
							.jumpFactor(1.25F)
							.friction(0.3F)
							.color(MaterialColor.COLOR_BLACK)
							.sound(SoundType.POLISHED_DEEPSLATE))
					.transform(pickaxeOnly())
					.item()
					.build()
					.lang(upColor + " Asphalt Block")
					.register();

		}
		return 0;
	}


	static {
		DesiresPaletteStoneTypes.register(DesiresCreate.REGISTRATE);
	}
	// Load this class
	public static void register() {}

}
