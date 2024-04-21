package uwu.lopyluna.create_dd.block.BlockPalette;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Lang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.ForgeRegistries;
import uwu.lopyluna.create_dd.DDCreate;
import uwu.lopyluna.create_dd.block.BlockPalette.gen.DDPaletteBlockPattern;
import uwu.lopyluna.create_dd.block.BlockPalette.gen.DDPalettesVariant;
import uwu.lopyluna.create_dd.sounds.DDSoundEvents;

import java.util.function.Function;

import static uwu.lopyluna.create_dd.block.BlockPalette.gen.DDPaletteBlockPattern.STANDARD_RANGE;

public enum DDPaletteStoneTypes {

    weathered_limestone(STANDARD_RANGE, r -> r.paletteStoneBlock("weathered_limestone", () -> Blocks.SANDSTONE, true, false)
            .properties(p -> p.color(MaterialColor.COLOR_LIGHT_GRAY))
            .register()),

    gabbro(STANDARD_RANGE, r -> r.paletteStoneBlock("gabbro", () -> Blocks.TUFF, true, true)
            .properties(p -> p.destroyTime(1.25f)
                    .color(MaterialColor.TERRACOTTA_LIGHT_GRAY))
            .register()),

    dolomite(STANDARD_RANGE, r -> r.paletteStoneBlock("dolomite", () -> Blocks.TUFF, true, true)
            .properties(p -> p.destroyTime(1.25f)
            .color(MaterialColor.TERRACOTTA_WHITE))
            .register())

    ;
    private final Function<CreateRegistrate, NonNullSupplier<Block>> factory;
    private DDPalettesVariant variants;

    public NonNullSupplier<Block> baseBlock;
    public final DDPaletteBlockPattern[] variantTypes;
    public TagKey<Item> materialTag;

    DDPaletteStoneTypes(DDPaletteBlockPattern[] variantTypes,
                        Function<CreateRegistrate, NonNullSupplier<Block>> factory) {
        this.factory = factory;
        this.variantTypes = variantTypes;
    }

    public NonNullSupplier<Block> getBaseBlock() {
        return baseBlock;
    }

    public DDPalettesVariant getVariants() {
        return variants;
    }

    public static void register(CreateRegistrate registrate) {
        for (DDPaletteStoneTypes paletteStoneVariants : values()) {
            NonNullSupplier<Block> baseBlock = paletteStoneVariants.factory.apply(registrate);
            paletteStoneVariants.baseBlock = baseBlock;
            String id = Lang.asId(paletteStoneVariants.name());
            paletteStoneVariants.materialTag =
                    AllTags.optionalTag(ForgeRegistries.ITEMS, DDCreate.asResource("stone_types/" + id));
            paletteStoneVariants.variants = new DDPalettesVariant(id, paletteStoneVariants);
        }
    }
}
