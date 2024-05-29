package uwu.lopyluna.create_dd.registry;

import com.tterrag.registrate.util.entry.ItemEntry;

import static uwu.lopyluna.create_dd.registry.helper.ItemTransformer.itemConversion;

@SuppressWarnings({"removal", "unused", "all"})
@Deprecated(forRemoval=true)
public class DesiresClassicStuffPorting {
//FOR PREV UPDATES OF DNDESIRE TO KEEP OLD ITEMS & CONVERT THEM INTO THE NEW ITEMS
    public static final ItemEntry<uwu.lopyluna.create_dd.content.items.port.ConversionItem>
        REVERSED_GEARSHIFT = itemConversion("reversed_gearshift", DesiresBlocks.INVERSE_BOX, true),
        inductive_mechanism = itemConversion("inductive_mechanism", DesiresItems.KINETIC_MECHANISM, false),
        incomplete_inductive_mechanism = itemConversion("incomplete_inductive_mechanism", DesiresItems.INCOMPLETE_KINETIC_MECHANISM, false),
        LAPIS_ALLOY = itemConversion("lapis_alloy", DesiresItems.BURY_BLEND, false);


    public static void register() {}
}
