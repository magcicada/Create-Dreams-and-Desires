package uwu.lopyluna.create_dd.content.data_recipes;

import com.google.common.base.Supplier;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;
import uwu.lopyluna.create_dd.registry.DesiresItems;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.UnaryOperator;

@ParametersAreNonnullByDefault
@SuppressWarnings({"unused", "all"})
public class AdvanceCraftingRecipeGen extends CreateRecipeProvider {

    GeneratedRecipe
            //SEQUENCED ASSEMBLY RECIPE
            KINETIC_MECHANISM = createSequencedAssembly("kinetic_mechanism", b -> b.require(AllItems.IRON_SHEET.get())
            .transitionTo(DesiresItems.INCOMPLETE_KINETIC_MECHANISM.get())
            .addOutput(DesiresItems.KINETIC_MECHANISM.get(), 480)
            .addOutput(AllItems.ANDESITE_ALLOY.get(), 16)
            .addOutput(AllItems.IRON_SHEET.get(), 8)
            .addOutput(AllBlocks.COGWHEEL.get(), 5)
            .addOutput(AllItems.SUPER_GLUE.asStack(), 4)
            .addOutput(AllItems.ZINC_NUGGET.get(), 3)
            .addOutput(AllBlocks.SHAFT.get(), 2)
            .addOutput(AllItems.CRUSHED_IRON.get(), 2)
            .addOutput(Items.IRON_INGOT, 1)
            .loops(2)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(AllItems.ANDESITE_ALLOY.get()))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(AllBlocks.COGWHEEL.get()))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(Items.SLIME_BALL))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(AllItems.ZINC_NUGGET.get()))
    ),


    //MECHANICAL CRAFTING RECIPE
    FURNACE_ENGINE = createMechanicalCrafting(DesiresBlocks.FURNACE_ENGINE::get).returns(1)
            .recipe(b -> b
                    .key('S', AllTags.forgeItemTag("plates/brass"))
                    .key('O', AllTags.forgeItemTag("dusts/obsidian"))
                    .key('N', AllTags.forgeItemTag("nuggets/zinc"))
                    .key('B', AllTags.forgeItemTag("storage_blocks/zinc"))
                    .key('C', DesiresBlocks.INDUSTRIAL_CASING.get())
                    .key('A', AllItems.ANDESITE_ALLOY.get())
                    .patternLine(" S S ")
                    .patternLine(" ACA ")
                    .patternLine("NOBON")
            ),

    HYDRAULIC_PRESS = createMechanicalCrafting(DesiresBlocks.HYDRAULIC_PRESS::get).returns(1)
            .recipe(b -> b
                    .key('P', AllBlocks.FLUID_PIPE.get())
                    .key('M', AllBlocks.MECHANICAL_PRESS.get())
                    .key('H', DesiresBlocks.HYDRAULIC_CASING.get())
                    .key('C', Items.COPPER_BLOCK)
                    .patternLine(" P ")
                    .patternLine(" H ")
                    .patternLine("CMC")
            ),

    EXCAVATION_DRILL = createMechanicalCrafting(DesiresItems.EXCAVATION_DRILL::get).returns(1)
            .recipe(b -> b
                    .key('#', AllTags.forgeItemTag("ingots/brass"))
                    .key('P', AllItems.PRECISION_MECHANISM.get())
                    .key('A', AllItems.ANDESITE_ALLOY.get())
                    .key('B', DesiresItems.BURY_BLEND.get())
                    .key('I', Items.IRON_INGOT)
                    .patternLine(" I   ")
                    .patternLine("IABP ")
                    .patternLine(" IB##")
            ),

    GILDED_ROSE_SWORD = createMechanicalCrafting(DesiresItems.GILDED_ROSE_SWORD::get).returns(1)
            .recipe(b -> b
                    .key('R', AllItems.ROSE_QUARTZ.get())
                    .key('G', AllTags.forgeItemTag("plates/gold"))
                    .key('Z', AllTags.forgeItemTag("ingots/zinc"))
                    .key('E', AllItems.EXP_NUGGET.get())
                    .key('T', Items.DIAMOND_SWORD)
                    .patternLine(" R ")
                    .patternLine(" R ")
                    .patternLine("RER")
                    .patternLine("ZTZ")
                    .patternLine(" G ")
            ),
            GILDED_ROSE_PICKAXE = createMechanicalCrafting(DesiresItems.GILDED_ROSE_PICKAXE::get).returns(1)
                    .recipe(b -> b
                            .key('R', AllItems.ROSE_QUARTZ.get())
                            .key('G', AllTags.forgeItemTag("plates/gold"))
                            .key('Z', AllTags.forgeItemTag("ingots/zinc"))
                            .key('E', AllItems.EXP_NUGGET.get())
                            .key('T', Items.DIAMOND_PICKAXE)
                            .patternLine(" E ")
                            .patternLine("RGR")
                            .patternLine("RTR")
                            .patternLine(" Z ")
                            .patternLine(" Z ")
                    ),
            GILDED_ROSE_AXE = createMechanicalCrafting(DesiresItems.GILDED_ROSE_AXE::get).returns(1)
                    .recipe(b -> b
                            .key('R', AllItems.ROSE_QUARTZ.get())
                            .key('G', AllTags.forgeItemTag("plates/gold"))
                            .key('Z', AllTags.forgeItemTag("ingots/zinc"))
                            .key('E', AllItems.EXP_NUGGET.get())
                            .key('T', Items.DIAMOND_AXE)
                            .patternLine("RE ")
                            .patternLine("RGR")
                            .patternLine("RT ")
                            .patternLine(" Z ")
                            .patternLine(" Z ")
                    ),
            GILDED_ROSE_SHOVEL = createMechanicalCrafting(DesiresItems.GILDED_ROSE_SHOVEL::get).returns(1)
                    .recipe(b -> b
                            .key('R', AllItems.ROSE_QUARTZ.get())
                            .key('G', AllTags.forgeItemTag("plates/gold"))
                            .key('Z', AllTags.forgeItemTag("ingots/zinc"))
                            .key('E', AllItems.EXP_NUGGET.get())
                            .key('T', Items.DIAMOND_SHOVEL)
                            .patternLine(" R ")
                            .patternLine("RER")
                            .patternLine("GTG")
                            .patternLine(" Z ")
                            .patternLine(" Z ")
                    ),
            GILDED_ROSE_HOE = createMechanicalCrafting(DesiresItems.GILDED_ROSE_HOE::get).returns(1)
                    .recipe(b -> b
                            .key('R', AllItems.ROSE_QUARTZ.get())
                            .key('G', AllTags.forgeItemTag("plates/gold"))
                            .key('Z', AllTags.forgeItemTag("ingots/zinc"))
                            .key('E', AllItems.EXP_NUGGET.get())
                            .key('T', Items.DIAMOND_HOE)
                            .patternLine(" E ")
                            .patternLine("RRG")
                            .patternLine(" T ")
                            .patternLine(" Z ")
                            .patternLine(" Z ")
                    )

    ;

    public AdvanceCraftingRecipeGen(PackOutput p_i48262_1_) {
        super(p_i48262_1_);
    }

    protected GeneratedRecipe createSequencedAssembly(String name, UnaryOperator<SequencedAssemblyRecipeBuilder> transform) {
        GeneratedRecipe generatedRecipe =
                c -> transform.apply(new SequencedAssemblyRecipeBuilder(DesiresCreate.asResource(name)))
                        .build(c);
        all.add(generatedRecipe);
        return generatedRecipe;
    }

    GeneratedRecipeBuilder createMechanicalCrafting(Supplier<ItemLike> result) {
        return new GeneratedRecipeBuilder(result);
    }

    class GeneratedRecipeBuilder {

        private String suffix;
        private Supplier<ItemLike> result;
        private int amount;

        public GeneratedRecipeBuilder(Supplier<ItemLike> result) {
            this.suffix = "";
            this.result = result;
            this.amount = 1;
        }

        GeneratedRecipeBuilder returns(int amount) {
            this.amount = amount;
            return this;
        }

        GeneratedRecipeBuilder withSuffix(String suffix) {
            this.suffix = suffix;
            return this;
        }

        GeneratedRecipe recipe(UnaryOperator<MechanicalCraftingRecipeBuilder> builder) {
            return register(consumer -> {
                MechanicalCraftingRecipeBuilder b =
                        builder.apply(MechanicalCraftingRecipeBuilder.shapedRecipe(result.get(), amount));
                ResourceLocation location = DesiresCreate.asResource("mechanical_crafting/" + RegisteredObjects.getKeyOrThrow(result.get()
                                .asItem())
                        .getPath() + suffix);
                b.build(consumer, location);
            });
        }
    }
    
}
