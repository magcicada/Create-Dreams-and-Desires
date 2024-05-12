package uwu.lopyluna.create_dd.infrastructure.data.recipe;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import uwu.lopyluna.create_dd.registry.DesiresFluids;
import uwu.lopyluna.create_dd.registry.DesiresItems;

@SuppressWarnings({"all"})
public class MixingRecipeGen extends DesireProcessingRecipeGen {

    GeneratedRecipe
            RAW_RUBBER = create("raw_rubber", b -> b
            .require(DesiresFluids.SAP.get(), 500)
            .output(DesiresItems.RAW_RUBBER.get(), 1)),

            CALCITE = create("calcite", b -> b
            .require(Items.DIORITE)
            .require(Items.BONE_BLOCK)
            .output(Items.CALCITE, 2)
            .requiresHeat(HeatCondition.HEATED)),

            BURY_BLEND = create("bury_blend", b -> b
            .require(AllItems.CRUSHED_IRON.get())
            .require(Items.LAPIS_LAZULI)
            .output(DesiresItems.BURY_BLEND.get(), 1));

    public MixingRecipeGen(DataGenerator generator) {
        super(generator);
    }


    @Override
    protected AllRecipeTypes getRecipeType() {
        return AllRecipeTypes.MIXING;
    }
}
