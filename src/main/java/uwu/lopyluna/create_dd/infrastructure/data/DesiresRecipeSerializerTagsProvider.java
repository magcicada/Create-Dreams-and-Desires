package uwu.lopyluna.create_dd.infrastructure.data;

//import com.simibubi.create.AllTags.AllRecipeSerializerTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.create_dd.DesiresCreate;

public class DesiresRecipeSerializerTagsProvider extends TagsProvider<RecipeSerializer<?>> {
	public DesiresRecipeSerializerTagsProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
		super(generator, Registry.RECIPE_SERIALIZER, DesiresCreate.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		// VALIDATE

		//for (AllRecipeSerializerTags tag : AllRecipeSerializerTags.values()) {
		//	if (tag.alwaysDatagen) {
		//		getOrCreateRawBuilder(tag.tag);
		//	}
		//}
	}

	@Override
	public String getName() {
		return "Create: Dreams n' Desire's Recipe Serializer Tags";
	}
}
