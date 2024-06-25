package uwu.lopyluna.create_dd.infrastructure.data;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.data_recipes.DesireProcessingRecipeGen;
import uwu.lopyluna.create_dd.content.data_recipes.AdvanceCraftingRecipeGen;
import uwu.lopyluna.create_dd.infrastructure.ponder.DesirePonderTags;
import uwu.lopyluna.create_dd.infrastructure.ponder.DesiresPonderIndex;
import uwu.lopyluna.create_dd.registry.DesiresLangPartial;

import java.util.function.BiConsumer;

public class DesiresDatagen {
	public static void gatherData(GatherDataEvent event) {
		addExtraRegistrateData();

		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		if (event.includeServer()) {

			generator.addProvider(true, new AdvanceCraftingRecipeGen(output));
			DesireProcessingRecipeGen.registerAll(generator, output);
		}
	}

	private static void addExtraRegistrateData() {
		DesiresRegistrateTags.addGenerators();

		DesiresCreate.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
			BiConsumer<String, String> langConsumer = provider::add;

			providePartialLang(langConsumer);
			providePonderLang();
		});
	}

	private static void providePartialLang(BiConsumer<String, String> consumer) {
		DesiresLangPartial.provideLang(consumer);
	}

	private static void providePonderLang() {
		DesirePonderTags.register();
		DesiresPonderIndex.register();
	}
}
