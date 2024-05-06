package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import uwu.lopyluna.create_dd.DesiresCreate;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.helper.BlockTransformer.blueprintBlocks;

@SuppressWarnings({"unused"})
public class DesiresPaletteBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB);
	}

	public static final BlockEntry<Block>
			BLACK_BLUEPRINT_BLOCK = blueprintBlocks("black", "Black", DesiresSpriteShifts.BLACK_BLUEPRINT_BLOCK, MaterialColor.COLOR_BLACK),
			WHITE_BLUEPRINT_BLOCK = blueprintBlocks("white", "White", DesiresSpriteShifts.WHITE_BLUEPRINT_BLOCK, MaterialColor.SNOW),
			BLUE_BLUEPRINT_BLOCK = blueprintBlocks("", "", DesiresSpriteShifts.BLUE_BLUEPRINT_BLOCK, MaterialColor.COLOR_BLUE, ""),
			LIGHT_BLUE_BLUEPRINT_BLOCK = blueprintBlocks("light", "Light", DesiresSpriteShifts.LIGHT_BLUE_BLUEPRINT_BLOCK, MaterialColor.COLOR_LIGHT_BLUE),
			RED_BLUEPRINT_BLOCK = blueprintBlocks("red", "Red", DesiresSpriteShifts.RED_BLUEPRINT_BLOCK, MaterialColor.COLOR_RED),
			GREEN_BLUEPRINT_BLOCK = blueprintBlocks("green", "Green", DesiresSpriteShifts.GREEN_BLUEPRINT_BLOCK, MaterialColor.COLOR_GREEN),
			LIME_BLUEPRINT_BLOCK = blueprintBlocks("lime", "Lime", DesiresSpriteShifts.LIME_BLUEPRINT_BLOCK, MaterialColor.COLOR_LIGHT_GREEN),
			PINK_BLUEPRINT_BLOCK = blueprintBlocks("pink", "Pink", DesiresSpriteShifts.PINK_BLUEPRINT_BLOCK, MaterialColor.COLOR_PINK),
			MAGENTA_BLUEPRINT_BLOCK = blueprintBlocks("magenta", "Magenta", DesiresSpriteShifts.MAGENTA_BLUEPRINT_BLOCK, MaterialColor.COLOR_MAGENTA),
			YELLOW_BLUEPRINT_BLOCK = blueprintBlocks("yellow", "Yellow", DesiresSpriteShifts.YELLOW_BLUEPRINT_BLOCK, MaterialColor.COLOR_YELLOW),
			GRAY_BLUEPRINT_BLOCK = blueprintBlocks("gray", "Gray", DesiresSpriteShifts.GRAY_BLUEPRINT_BLOCK, MaterialColor.COLOR_GRAY),
			LIGHT_GRAY_BLUEPRINT_BLOCK = blueprintBlocks("light_gray", "Light Gray", DesiresSpriteShifts.LIGHT_GRAY_BLUEPRINT_BLOCK, MaterialColor.COLOR_LIGHT_GRAY),
			BROWN_BLUEPRINT_BLOCK = blueprintBlocks("brown", "Brown", DesiresSpriteShifts.BROWN_BLUEPRINT_BLOCK, MaterialColor.COLOR_BROWN),
			CYAN_BLUEPRINT_BLOCK = blueprintBlocks("cyan", "Cyan", DesiresSpriteShifts.CYAN_BLUEPRINT_BLOCK, MaterialColor.COLOR_CYAN),
			PURPLE_BLUEPRINT_BLOCK = blueprintBlocks("purple", "Purple", DesiresSpriteShifts.PURPLE_BLUEPRINT_BLOCK, MaterialColor.COLOR_PURPLE),
			ORANGE_BLUEPRINT_BLOCK = blueprintBlocks("orange", "Orange", DesiresSpriteShifts.ORANGE_BLUEPRINT_BLOCK, MaterialColor.COLOR_ORANGE)
	;


	public static final int COLORED_BLOCKS = generateColorBlocks();

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
							.speedFactor(0.001F)
							.jumpFactor(1.25F)
							.friction(0.35F)
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
