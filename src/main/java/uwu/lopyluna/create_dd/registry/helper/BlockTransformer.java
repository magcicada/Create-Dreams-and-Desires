package uwu.lopyluna.create_dd.registry.helper;

import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.registry.DesiresPaletteBlocks;
import uwu.lopyluna.create_dd.registry.DesiresSoundEvents;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.getItemName;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.DesiresTags.optionalTag;

@SuppressWarnings({"unused", "deprecation"})
public class BlockTransformer {

    public static BlockEntry<Block> rubber_decor(String colorId, MapColor mapColor, Item dye) {
        SoundType rubberSoundType = new ForgeSoundType(0.9f, .6f, () -> DesiresSoundEvents.RUBBER_BREAK.get(),
                () -> SoundEvents.STEM_STEP, () -> DesiresSoundEvents.RUBBER_PLACE.get(),
                () -> SoundEvents.STEM_HIT, () -> SoundEvents.STEM_FALL);
        TagKey<Item> dyeRubberDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", colorId + "_rubber_decor"));
        TagKey<Item> rubberDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "rubber_decors"));
        TagKey<Block> stairsBlockTag = optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation("minecraft", "stairs"));
        TagKey<Item> stairsItemTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("minecraft", "stairs"));
        TagKey<Block> slabsBlockTag = optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation("minecraft", "slabs"));
        TagKey<Item> slabsItemTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("minecraft", "slabs"));

        REGISTRATE.block(colorId + "_padded_tiled_rubber", Block::new)
                .properties(p -> p.mapColor(mapColor)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(dyeRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
                .item()
                .tag(dyeRubberDecorTag)
                .build()
                .register();
        REGISTRATE.block(colorId + "_padded_rubber", Block::new)
                .properties(p -> p.mapColor(mapColor)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(dyeRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
                .recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 1)
                        .pattern("CCC")
                        .pattern("CDC")
                        .pattern("CCC")
                        .define('C', rubberDecorTag)
                        .define('D', dye)
                        .unlockedBy("has_" + c.getName(), has(c.get()))
                        .save(p, DesiresCreate.asResource("crafting/decor/" + c.getName() + "_from_" + c.getName())))
                .item()
                .tag(dyeRubberDecorTag, rubberDecorTag)
                .build()
                .register();

        REGISTRATE.block(colorId + "_padded_rubber_stairs", p -> new StairBlock(DesiresPaletteBlocks.PADDED_RUBBER.getDefaultState(), p))
                .properties(p -> p.mapColor(mapColor)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
                .tag(stairsBlockTag)
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(dyeRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
                .blockstate((c, p) -> p.stairsBlock(c.get(), DesiresCreate.asResource("block/" + colorId + "_padded_tiled_rubber")))
                .item()
                .tag(dyeRubberDecorTag, stairsItemTag)
                .build()
                .register();
        REGISTRATE.block(colorId + "_padded_rubber_slab", SlabBlock::new)
                .properties(p -> p.mapColor(mapColor)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
                .tag(slabsBlockTag)
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(dyeRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 2))
                .blockstate((c, p) -> p.slabBlock(c.get(), DesiresCreate.asResource("block/" + colorId + "_padded_rubber"),
                        DesiresCreate.asResource("block/" + colorId + "_padded_rubber_slab"),
                        DesiresCreate.asResource("block/" + colorId + "_padded_rubber"),
                        DesiresCreate.asResource("block/" + colorId + "_padded_rubber")))
                .item()
                .tag(slabsItemTag)
                .build()
                .register();
        return REGISTRATE.block(colorId + "_padded_mosaic_rubber", Block::new)
                .properties(p -> p.mapColor(mapColor)).properties(p -> p.sound(rubberSoundType)).properties(p -> p.strength(0.5f,1.5f))
                .recipe((c, p) -> p.stonecutting(DataIngredient.tag(dyeRubberDecorTag), RecipeCategory.BUILDING_BLOCKS, c, 1))
                .item()
                .tag(dyeRubberDecorTag)
                .build()
                .register();
    }

    public static BlockEntry<Block> blueprintBlocks( String colorId, String colorLang, Item dye, CTSpriteShiftEntry connectedTexture, MapColor mapColor ) {
        TagKey<Item> blueprintDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "blueprints"));
        TagKey<Block> blueprintDecorBlockTag = optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation("create_dd", "blueprints"));
        return REGISTRATE.block(colorId + "_blueprint_block", Block::new)
                .transform(BlockTransformer.block(() -> connectedTexture))
                .initialProperties(() -> Blocks.HAY_BLOCK)
                .tag(blueprintDecorBlockTag)
                .recipe((c, p) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 1)
                            .pattern("CCC")
                            .pattern("CDC")
                            .pattern("CCC")
                            .define('C', blueprintDecorTag)
                            .define('D', dye)
                            .unlockedBy("has_dyed_item", has(dye))
                            .save(p, DesiresCreate.asResource("crafting/decor/" + c.getName() + "_from_" + getItemName(dye)));
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
                            .pattern("DPD")
                            .pattern("PWP")
                            .pattern("DPD")
                            .define('D', dye)
                            .define('W', ItemTags.WOOL)
                            .define('P', Items.PAPER)
                            .unlockedBy("has_dyed_item", has(dye))
                            .save(p, DesiresCreate.asResource("crafting/decor/" + c.getName()));
                })
                .properties(p -> p.mapColor(mapColor))
                .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                        () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                        () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
                .properties(p -> p.strength(0.025f,0.25f))
                .lang(colorLang + " Blueprint Block")
                .item()
                .tag(blueprintDecorTag)
                .build()
                .register();
    }

    public static BlockEntry<Block> blueprintBlocks( String colorId, String colorLang, Item dye, CTSpriteShiftEntry connectedTexture, MapColor mapColor, String no_underscore ) {
        TagKey<Item> blueprintDecorTag = optionalTag(ForgeRegistries.ITEMS, new ResourceLocation("create_dd", "blueprints"));
        TagKey<Block> blueprintDecorBlockTag = optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation("create_dd", "blueprints"));
        return REGISTRATE.block(colorId + "blueprint_block", Block::new)
                .transform(BlockTransformer.block(() -> connectedTexture))
                .initialProperties(() -> Blocks.HAY_BLOCK)
                .tag(blueprintDecorBlockTag)
                .recipe((c, p) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 1)
                            .pattern("CCC")
                            .pattern("CDC")
                            .pattern("CCC")
                            .define('C', blueprintDecorTag)
                            .define('D', dye)
                            .unlockedBy("has_dyed_item", has(dye))
                            .save(p, DesiresCreate.asResource("crafting/decor/" + c.getName() + "_from_" + getItemName(dye)));
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, c.get(), 4)
                            .pattern("DPD")
                            .pattern("PWP")
                            .pattern("DPD")
                            .define('D', dye)
                            .define('W', ItemTags.WOOL)
                            .define('P', Items.PAPER)
                            .unlockedBy("has_dyed_item", has(dye))
                            .save(p, DesiresCreate.asResource("crafting/decor/" + c.getName()));
                })
                .properties(p -> p.mapColor(mapColor))
                .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                        () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                        () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
                .properties(p -> p.strength(0.025f,0.25f))
                .lang(colorLang + "Blueprint Block")
                .item()
                .tag(blueprintDecorTag)
                .build()
                .register();
    }


    public static <B extends Block> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> block( Supplier<CTSpriteShiftEntry> ct ) {
        return b -> b.initialProperties(SharedProperties::stone)
                .blockstate((c, p) -> p.simpleBlock(c.get()))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .item()
                .build();
    }

    public static <B extends Block> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> blockV2( Supplier<CTSpriteShiftEntry> ct, Supplier<CTSpriteShiftEntry> ct2 ) {
        return b -> b.initialProperties(SharedProperties::stone)
                .blockstate((c, p) -> p.simpleBlock(c.get(), p.models()
                        .cubeColumn(c.getName(), ct.get()
                                        .getOriginalResourceLocation(),
                                ct2.get()
                                        .getOriginalResourceLocation())))
                .onRegister(connectedTextures(() -> new HorizontalCTBehaviour(ct.get(), ct2.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .item()
                .build();
    }
}
