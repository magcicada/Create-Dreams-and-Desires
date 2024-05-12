package uwu.lopyluna.create_dd.infrastructure.data;

import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.infrastructure.data.recipe.DesireProcessingRecipeGen;
import uwu.lopyluna.create_dd.infrastructure.data.recipe.MechanicalCraftingRecipeGen;
import uwu.lopyluna.create_dd.infrastructure.data.recipe.SequencedAssemblyRecipeGen;
import uwu.lopyluna.create_dd.infrastructure.ponder.DesirePonderTags;
import uwu.lopyluna.create_dd.infrastructure.ponder.DesiresPonderIndex;
import uwu.lopyluna.create_dd.registry.DesiresLangPartial;

import java.util.function.BiConsumer;

public class DesiresDatagen {
	public static void gatherData(GatherDataEvent event) {
		addExtraRegistrateData();

		DataGenerator generator = event.getGenerator();

		if (event.includeServer()) {

			generator.addProvider(true, new SequencedAssemblyRecipeGen(generator));
			generator.addProvider(true, new MechanicalCraftingRecipeGen(generator));
			DesireProcessingRecipeGen.registerAll(generator);
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
