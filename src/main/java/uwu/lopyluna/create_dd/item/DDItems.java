package uwu.lopyluna.create_dd.item;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.item.CombustibleItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SimpleFoiledItem;
import uwu.lopyluna.create_dd.creative.DDItemTab;

import static com.simibubi.create.AllTags.forgeItemTag;
import static uwu.lopyluna.create_dd.DDCreate.REGISTRATE;

@SuppressWarnings({"all"})
public class DDItems {

    static {
        REGISTRATE.creativeModeTab(() -> DDItemTab.BASE_CREATIVE_TAB);
    }

    //YIPPEE ITEMS PIPEBOMB YUMMY ac
    public static final ItemEntry<Item>
            integrated_circuit = ingredient("integrated_circuit"),
            integrated_mechanism = ingredient("integrated_mechanism"),
            calculation_mechanism = ingredient("calculation_mechanism"),
            inductive_mechanism = ingredient("inductive_mechanism"),
            infernal_mechanism = ingredient("infernal_mechanism"),
            sealed_mechanism = ingredient("sealed_mechanism"),
            diamond_shard = taggedIngredient("diamond_shard", forgeItemTag("nuggets/diamond")),
            crystallized_sap = ingredient("crystallized_sap"),
            raw_rubber = ingredient("raw_rubber"),
            rubber = ingredient("rubber");

    public static final ItemEntry<SimpleFoiledItem>
            frozen_nugget = foilIngredient("frozen_nugget")
    ;

    public static final ItemEntry<SequencedAssemblyItem>
            incomplete_integrated_circuit = sequencedIngredient("incomplete_integrated_circuit"),
            incomplete_integrated_mechanism = sequencedIngredient("incomplete_integrated_mechanism"),
            incomplete_calculation_mechanism = sequencedIngredient("incomplete_calculation_mechanism"),
            incomplete_inductive_mechanism = sequencedIngredient("incomplete_inductive_mechanism"),
            incomplete_infernal_mechanism = sequencedIngredient("incomplete_infernal_mechanism"),
            incomplete_sealed_mechanism = sequencedIngredient("incomplete_sealed_mechanism");


    public static final ItemEntry<CombustibleItem> COAL_PIECE = REGISTRATE.item("coal_piece", CombustibleItem::new)
            .onRegister(i -> i.setBurnTime(200))
            .register();


    private static ItemEntry<Item> ingredient(String name) {
        return REGISTRATE.item(name, Item::new)
                .register();
    }

    private static ItemEntry<SimpleFoiledItem> foilIngredient(String name) {
        return REGISTRATE.item(name, SimpleFoiledItem::new)
                .register();
    }

    private static ItemEntry<SequencedAssemblyItem> sequencedIngredient(String name) {
        return REGISTRATE.item(name, SequencedAssemblyItem::new)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
        return REGISTRATE.item(name, Item::new)
                .tag(tags)
                .register();
    }

    public static void register() {}
}
