package uwu.lopyluna.create_dd.registry;

import java.util.function.BiConsumer;

@SuppressWarnings({"unused"})
public class DesiresLangPartial {
    public static void provideLang(BiConsumer<String, String> consumer) {
        consume(consumer, "create_dd.recipe.fan_sanding.fan", "Fan behind Sand");
        consume(consumer, "create_dd.recipe.fan_freezing.fan", "Fan behind Powdered Snow");
        consume(consumer, "create_dd.recipe.fan_seething.fan", "Fan behind Super Heated Blaze Burner");
        consume(consumer, "create_dd.recipe.fan_sanding", "Bulk Sanding");
        consume(consumer, "create_dd.recipe.fan_freezing", "Bulk Freezing");
        consume(consumer, "create_dd.recipe.fan_seething", "Bulk Seething");
        consume(consumer, "itemGroup.create_dd.base", "Create: Dreams n' Desires");
        consume(consumer, "itemGroup.create_dd.palettes", "Dreams n' Desires Building Blocks");
        consume(consumer, "itemGroup.create_dd.beta", "Dreams n' Desires Beta Items");

    }

    private static void consume(BiConsumer<String, String> consumer, String key, String enUS) {
        consumer.accept(key, enUS);
    }
}
