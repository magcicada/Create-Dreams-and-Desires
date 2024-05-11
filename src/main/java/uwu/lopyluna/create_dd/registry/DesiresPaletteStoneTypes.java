package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import uwu.lopyluna.create_dd.registry.helper.Lang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.registry.helper.palettes.DPaletteBlockPattern;
import uwu.lopyluna.create_dd.registry.helper.palettes.DPalettesVariantEntry;

import java.util.function.Function;

import static uwu.lopyluna.create_dd.registry.helper.palettes.DPaletteBlockPattern.STANDARD_RANGE;
import static uwu.lopyluna.create_dd.registry.helper.palettes.DPaletteBlockPattern.VANILLA_RANGE;

public enum DesiresPaletteStoneTypes {

    STONE(VANILLA_RANGE, r -> () -> Blocks.STONE),
    PACKED_MUD(VANILLA_RANGE, r -> () -> Blocks.PACKED_MUD),
    AMETHYST_BLOCK(VANILLA_RANGE, r -> () -> Blocks.AMETHYST_BLOCK),
    NETHERRACK(VANILLA_RANGE, r -> () -> Blocks.NETHERRACK),
    BASALT(VANILLA_RANGE, r -> () -> Blocks.BASALT),
    BLACKSTONE(VANILLA_RANGE, r -> () -> Blocks.BLACKSTONE),

    WEATHERED_LIMESTONE(STANDARD_RANGE, r -> r.paletteStoneBlock("weathered_limestone", () -> Blocks.SANDSTONE, true, false)
            .properties(p -> p.color(MaterialColor.COLOR_LIGHT_GRAY))
            .register()),

    GABBRO(STANDARD_RANGE, r -> r.paletteStoneBlock("gabbro", () -> Blocks.POLISHED_DEEPSLATE, true, false)
            .properties(p -> p.destroyTime(1.25f)
            .color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
            .register()),

    DOLOMITE(STANDARD_RANGE, r -> r.paletteStoneBlock("dolomite", () -> Blocks.TUFF, true, false)
            .properties(p -> p.destroyTime(1.25f)
            .color(MaterialColor.TERRACOTTA_WHITE))
            .register())
    ;

    private Function<CreateRegistrate, NonNullSupplier<Block>> factory;
    private DPalettesVariantEntry variants;

    public NonNullSupplier<Block> baseBlock;
    public DPaletteBlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    private DesiresPaletteStoneTypes(DPaletteBlockPattern[] variantTypes,
                                     Function<CreateRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public NonNullSupplier<Block> getBaseBlock() {
        return baseBlock;
    }

    public DPalettesVariantEntry getVariants() {
        return variants;
    }

    public static void register(CreateRegistrate registrate) {
        for (DesiresPaletteStoneTypes paletteStoneVariants : values()) {
            NonNullSupplier<Block> baseBlock = paletteStoneVariants.factory.apply(registrate);
            paletteStoneVariants.baseBlock = baseBlock;
            String id = Lang.asId(paletteStoneVariants.name());
            paletteStoneVariants.materialTag =
                    DesiresTags.optionalTag(ForgeRegistries.ITEMS, DesiresCreate.asResource("stone_types/" + id));
            paletteStoneVariants.variants = new DPalettesVariantEntry(id, paletteStoneVariants);
        }
    }
}
