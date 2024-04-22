package uwu.lopyluna.create_dd.registry.helper;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;


public class ItemTransformer {
    public static ItemEntry<Item> itemEntry(String name) {return REGISTRATE.item(name, Item::new)
            .register();
    }

    @SafeVarargs
    public static ItemEntry<Item> itemEntryTagged(String name, TagKey<Item>... tags) {return REGISTRATE.item(name, Item::new)
            .tag(tags)
            .register();
    }
}
