package uwu.lopyluna.create_dd.registry;

import com.tterrag.registrate.util.entry.ItemEntry;

import static uwu.lopyluna.create_dd.registry.helper.ItemTransformer.itemConversion;

@SuppressWarnings({"removal", "unused", "all"})
@Deprecated(forRemoval=true)
public class DesiresClassicItems {
//FOR PREV UPDATES OF DNDESIRE TO KEEP OLD ITEMS & CONVERT THEM INTO THE NEW ITEMS
    public static final ItemEntry<uwu.lopyluna.create_dd.content.items.port.ConversionItem>
        REVERSED_GEARSHIFT = itemConversion("reversed_gearshift", DesiresBlocks.INVERSE_BOX, true),
        LAPIS_ALLOY = itemConversion("lapis_alloy", DesiresItems.BURY_BLEND, false);


    public static void register() {}
}
