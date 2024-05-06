package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;

import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.DesiresTags.optionalTag;
import static uwu.lopyluna.create_dd.registry.helper.BlockTransformer.blueprintBlocks;
import static uwu.lopyluna.create_dd.registry.helper.BlockTransformer.rubber_decor;

@SuppressWarnings({"unused"})
public class DesiresPaletteBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.PALETTES_CREATIVE_TAB);
	}
	public static TagKey<Item> rubberDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "rubber_decor"));
	public static TagKey<Item> rawRubberDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "raw_rubber_decor"));

	public static final BlockEntry<Block> PADDED_RUBBER = REGISTRATE.block("padded_rubber", Block::new)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
			.properties(p -> p.sound(new ForgeSoundType(0.9f, .6f, () -> DesiresSoundEvents.RUBBER_BREAK.get(),
					() -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(),
					() -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
			.properties(p -> p.strength(0.5f,1.5f))
			.recipe((c, p) -> ShapedRecipeBuilder.shaped(c.get(), 1)
					.pattern("CC")
					.pattern("CC")
					.define('C', DesiresBlocks.RUBBER_BLOCK.get())
					.unlockedBy("has_" + c.getName(), has(c.get()))
					.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName())))
			.item()
			.tag(rubberDecorTag, optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "rubber_decors")))
			.build()
			.register();

	public static final BlockEntry<Block> RAW_PADDED_RUBBER = REGISTRATE.block("raw_padded_rubber", Block::new)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_WHITE))
			.properties(p -> p.sound(new ForgeSoundType(0.9f, .75f, () -> DesiresSoundEvents.RUBBER_BREAK.get(),
					() -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(),
					() -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL)))
			.properties(p -> p.strength(0.5f,1.5f))
			.recipe((c, p) -> ShapedRecipeBuilder.shaped(c.get(), 1)
					.pattern("CC")
					.pattern("CC")
					.define('C', DesiresBlocks.RAW_RUBBER_BLOCK.get())
					.unlockedBy("has_" + c.getName(), has(c.get()))
					.save(p, DesiresCreate.asResource("crafting/" + c.getName() + "_from_" + c.getName())))
			.item()
			.tag(rawRubberDecorTag)
			.build()
			.register();

	public static final BlockEntry<Block>
			BLACK_RUBBER_BLOCKS = rubber_decor("black", MaterialColor.COLOR_BLACK, Items.BLACK_DYE),
			WHITE_RUBBER_BLOCKS = rubber_decor("white", MaterialColor.SNOW, Items.WHITE_DYE),
			BLUE_RUBBER_BLOCKS = rubber_decor("blue", MaterialColor.COLOR_BLUE, Items.BLUE_DYE),
			LIGHT_BLUE_RUBBER_BLOCKS = rubber_decor("light_blue", MaterialColor.COLOR_LIGHT_BLUE, Items.LIGHT_BLUE_DYE),
			RED_RUBBER_BLOCKS = rubber_decor("red", MaterialColor.COLOR_RED, Items.RED_DYE),
			GREEN_RUBBER_BLOCKS = rubber_decor("green", MaterialColor.COLOR_GREEN, Items.GREEN_DYE),
			LIME_RUBBER_BLOCKS = rubber_decor("lime", MaterialColor.COLOR_LIGHT_GREEN, Items.LIME_DYE),
			PINK_RUBBER_BLOCKS = rubber_decor("pink", MaterialColor.COLOR_PINK, Items.PINK_DYE),
			MAGENTA_RUBBER_BLOCKS = rubber_decor("magenta", MaterialColor.COLOR_MAGENTA, Items.MAGENTA_DYE),
			YELLOW_RUBBER_BLOCKS = rubber_decor("yellow", MaterialColor.COLOR_YELLOW, Items.YELLOW_DYE),
			GRAY_RUBBER_BLOCKS = rubber_decor("gray", MaterialColor.COLOR_GRAY, Items.GRAY_DYE),
			LIGHT_GRAY_RUBBER_BLOCKS = rubber_decor("light_gray", MaterialColor.COLOR_LIGHT_GRAY, Items.LIGHT_GRAY_DYE),
			BROWN_RUBBER_BLOCKS = rubber_decor("brown", MaterialColor.COLOR_BROWN, Items.BROWN_DYE),
			CYAN_RUBBER_BLOCKS = rubber_decor("cyan", MaterialColor.COLOR_CYAN, Items.CYAN_DYE),
			PURPLE_RUBBER_BLOCKS = rubber_decor("purple", MaterialColor.COLOR_PURPLE, Items.PURPLE_DYE),
			ORANGE_RUBBER_BLOCKS = rubber_decor("orange", MaterialColor.COLOR_ORANGE, Items.ORANGE_DYE);

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
