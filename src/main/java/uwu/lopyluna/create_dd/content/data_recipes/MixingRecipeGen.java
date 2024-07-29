package uwu.lopyluna.create_dd.content.data_recipes;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import uwu.lopyluna.create_dd.registry.DesiresFluids;
import uwu.lopyluna.create_dd.registry.DesiresItems;

@SuppressWarnings({"unused"})
public class MixingRecipeGen extends DesireProcessingRecipeGen {

    GeneratedRecipe
            RAW_RUBBER = create("raw_rubber", b -> b
            .require(DesiresFluids.SAP.get(), 1000)
            .output(DesiresItems.RAW_RUBBER.get(), 1)),

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
