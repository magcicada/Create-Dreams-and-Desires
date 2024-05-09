package uwu.lopyluna.create_dd.registry;

import java.util.function.BiConsumer;

@SuppressWarnings({"unused"})
public class DesiresLangPartial {
    public static void provideLang(BiConsumer<String, String> consumer) {
        consume(consumer, "itemGroup.create_dd.base", "Create: Dreams n' Desires");
        consume(consumer, "itemGroup.create_dd.palettes", "Dreams n' Desires Building Blocks");

    }

    private static void consume(BiConsumer<String, String> consumer, String key, String enUS) {
        consumer.accept(key, enUS);
    }
}
