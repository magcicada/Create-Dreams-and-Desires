package uwu.lopyluna.create_dd.registry;

import com.tterrag.registrate.util.entry.ItemEntry;

import static uwu.lopyluna.create_dd.registry.helper.ItemTransformer.itemConversion;

@SuppressWarnings({"removal", "unused", "all"})
@Deprecated(forRemoval=true)
public class DesiresClassicStuffPorting {
//FOR PREV UPDATES OF DNDESIRE TO KEEP OLD ITEMS & CONVERT THEM INTO THE NEW ITEMS
    public static final ItemEntry<uwu.lopyluna.create_dd.content.items.port.ConversionItem>
        REVERSED_GEARSHIFT = itemConversion("reversed_gearshift", DesiresBlocks.INVERSE_BOX, true),
        steel_block = itemConversion("steel_block", DesiresPaletteBlocks.DARK_METAL_BLOCK, true),
        steel_casing = itemConversion("steel_casing", DesiresPaletteBlocks.DARK_METAL_PLATING, true),
        steel_polished_block = itemConversion("steel_polished_block", DesiresPaletteBlocks.DARK_METAL_BLOCK, true),
        steel_polished_stairs = itemConversion("steel_polished_stairs", DesiresPaletteBlocks.DARK_METAL_STAIRS, true),
        steel_polished_slab = itemConversion("steel_polished_slab", DesiresPaletteBlocks.DARK_METAL_SLAB, true),
        steel_tiled_block = itemConversion("steel_tiled_block", DesiresPaletteBlocks.DARK_METAL_BRICKS, true),
        steel_tiled_stairs = itemConversion("steel_tiled_stairs", DesiresPaletteBlocks.DARK_METAL_BRICK_STAIRS, true),
        steel_tiled_slab = itemConversion("steel_tiled_slab", DesiresPaletteBlocks.DARK_METAL_BRICK_STAIRS, true),
        horizontal_hazard_block = itemConversion("horizontal_hazard_block", DesiresPaletteBlocks.HAZARD_BLOCK, true),
        hazard_block_r = itemConversion("hazard_block_r", DesiresPaletteBlocks.HAZARD_BLOCK, true),
        horizontal_hazard_block_r = itemConversion("horizontal_hazard_block_r", DesiresPaletteBlocks.HAZARD_BLOCK, true),
        inductive_mechanism = itemConversion("inductive_mechanism", DesiresItems.KINETIC_MECHANISM, false),
        incomplete_inductive_mechanism = itemConversion("incomplete_inductive_mechanism", DesiresItems.INCOMPLETE_KINETIC_MECHANISM, false),
        LAPIS_ALLOY = itemConversion("lapis_alloy", DesiresItems.BURY_BLEND, false);


    public static void register() {}
}
