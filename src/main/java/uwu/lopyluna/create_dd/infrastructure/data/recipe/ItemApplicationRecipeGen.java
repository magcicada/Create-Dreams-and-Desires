package uwu.lopyluna.create_dd.infrastructure.data.recipe;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;
import uwu.lopyluna.create_dd.registry.DesiresItems;

import static uwu.lopyluna.create_dd.registry.DesiresTags.forgeItemTag;

public class ItemApplicationRecipeGen extends DesireProcessingRecipeGen {
    public ItemApplicationRecipeGen(DataGenerator generator) {
        super(generator);
    }

    GeneratedRecipe OVERBURDEN = create("overburden_casing", b -> b
            .require(AllBlocks.ANDESITE_CASING.get()) //Block
            .require(DesiresItems.BURY_BLEND.get()) //Item to Apply
            .output(DesiresBlocks.OVERBURDEN_CASING.get())); //Output

    GeneratedRecipe INDUSTRIAL = create("industrial_casing", b -> b
            .require(AllBlocks.INDUSTRIAL_IRON_BLOCK.get()) //Block
            .require(AllItems.ZINC_INGOT.get()) //Item to Apply
            .output(DesiresBlocks.INDUSTRIAL_CASING.get())); //Output

    GeneratedRecipe HYDRAULIC = create("hydraulic_casing", b -> b
            .require(AllBlocks.COPPER_CASING.get()) //Block
            .require(AllItems.COPPER_SHEET.get()) //Item to Apply
            .output(DesiresBlocks.HYDRAULIC_CASING.get())); //Output

    GeneratedRecipe COGWHEEL = create("cogwheel", b -> b
            .require(AllBlocks.SHAFT.get()) //Block
            .require(ItemTags.PLANKS) //Item to Apply
            .output(AllBlocks.COGWHEEL.get())); //Output

    GeneratedRecipe LARGE_COGWHEEL = create("large_cogwheel", b -> b
            .require(AllBlocks.COGWHEEL.get()) //Block
            .require(ItemTags.PLANKS) //Item to Apply
            .output(AllBlocks.LARGE_COGWHEEL.get())); //Output

    GeneratedRecipe DOWNGRADE_COGWHEEL = create("downgrade_cogwheel", b -> b
            .require(AllBlocks.COGWHEEL.get()) //Block
            .require(forgeItemTag("nuggets/coal")) //Item to Apply
            .output(AllBlocks.SHAFT.get())); //Output

    GeneratedRecipe DOWNGRADE_LARGE_COGWHEEL = create("downgrade_large_cogwheel", b -> b
            .require(AllBlocks.LARGE_COGWHEEL.get()) //Block
            .require(forgeItemTag("nuggets/coal")) //Item to Apply
            .output(AllBlocks.COGWHEEL.get())); //Output


    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.ITEM_APPLICATION;
    }
}
