package uwu.lopyluna.create_dd.infrastructure.data.recipe;

import java.util.function.Supplier;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.foundation.data.recipe.CompatMetals;
import com.simibubi.create.foundation.data.recipe.Mods;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import uwu.lopyluna.create_dd.registry.DesiresPaletteStoneTypes;

@SuppressWarnings({"unused"})
public class WashingRecipeGen extends DesireProcessingRecipeGen {

	GeneratedRecipe

			WEATHER_LIMESTONE = convert(AllPaletteStoneTypes.LIMESTONE.getBaseBlock().get(), DesiresPaletteStoneTypes.WEATHERED_LIMESTONE.getBaseBlock().get()),

			EXPOSED_COPPER = convert(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER),
			WEATHERED_COPPER = convert(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER),
			OXIDIZED_COPPER = convert(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER),

			EXPOSED_CUT_COPPER = convert(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
			WEATHERED_CUT_COPPER = convert(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
			OXIDIZED_CUT_COPPER = convert(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER),

			EXPOSED_CUT_COPPER_SLAB = convert(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
			WEATHERED_CUT_COPPER_SLAB = convert(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
			OXIDIZED_CUT_COPPER_SLAB = convert(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

			EXPOSED_CUT_COPPER_STAIRS = convert(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
			WEATHERED_CUT_COPPER_STAIRS = convert(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
			OXIDIZED_CUT_COPPER_STAIRS = convert(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS);

	public GeneratedRecipe convert(Block block, Block result) {
		return create(() -> block, b -> b.output(result));
	}

	public GeneratedRecipe crushedOre(ItemEntry<Item> crushed, Supplier<ItemLike> nugget, Supplier<ItemLike> secondary,
		float secondaryChance) {
		return create(crushed::get, b -> b.output(nugget.get(), 9)
			.output(secondaryChance, secondary.get(), 1));
	}

	public GeneratedRecipe moddedCrushedOre(ItemEntry<? extends Item> crushed, CompatMetals metal) {
		String metalName = metal.getName();
		for (Mods mod : metal.getMods()) {
			ResourceLocation nugget = mod.nuggetOf(metalName);
			create(mod.getId() + "/" + crushed.getId()
				.getPath(),
				b -> b.withItemIngredients(Ingredient.of(crushed::get))
					.output(1, nugget, 9)
					.whenModLoaded(mod.getId()));
		}
		return null;
	}

	public WashingRecipeGen(DataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.SPLASHING;
	}

}
