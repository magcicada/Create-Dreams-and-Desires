package uwu.lopyluna.create_dd.infrastructure.data.recipe;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;
import uwu.lopyluna.create_dd.registry.DesiresItems;

public class ItemApplicationRecipeGen extends DesireProcessingRecipeGen {
    public ItemApplicationRecipeGen(DataGenerator generator) {
        super(generator);
    }

    GeneratedRecipe OVERBURDEN = create("overburden_casing", b -> b
            .require(AllBlocks.ANDESITE_CASING.get())
            .require(DesiresItems.BURY_BLEND.get())
            .output(DesiresBlocks.OVERBURDEN_CASING.get()));

    GeneratedRecipe INDUSTRIAL = create("industrial_casing", b -> b
            .require(AllBlocks.INDUSTRIAL_IRON_BLOCK.get())
            .require(AllItems.ZINC_INGOT.get())
            .output(DesiresBlocks.INDUSTRIAL_CASING.get()));

    GeneratedRecipe HYDRAULIC = create("hydraulic_casing", b -> b
            .require(AllBlocks.COPPER_CASING.get())
            .require(Items.COPPER_INGOT)
            .output(DesiresBlocks.HYDRAULIC_CASING.get()));

    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.ITEM_APPLICATION;
    }
}
