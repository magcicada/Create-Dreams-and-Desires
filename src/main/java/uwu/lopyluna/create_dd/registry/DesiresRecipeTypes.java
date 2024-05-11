package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.content.equipment.toolbox.ToolboxDyeingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeFactory;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import uwu.lopyluna.create_dd.registry.helper.Lang;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.recipes.FreezingRecipe;
import uwu.lopyluna.create_dd.content.recipes.SandingRecipe;
import uwu.lopyluna.create_dd.content.recipes.SeethingRecipe;

import java.util.Optional;
import java.util.function.Supplier;

public enum DesiresRecipeTypes implements IRecipeTypeInfo {

	SANDING(SandingRecipe::new),
	FREEZING(FreezingRecipe::new),
	SEETHING(SeethingRecipe::new),

	BACKPACK_DYEING(() -> new SimpleRecipeSerializer<>(ToolboxDyeingRecipe::new), () -> RecipeType.CRAFTING, false);



	private final ResourceLocation id;
	private final RegistryObject<RecipeSerializer<?>> serializerObject;
	@Nullable
	private final RegistryObject<RecipeType<?>> typeObject;
	private final Supplier<RecipeType<?>> type;

	DesiresRecipeTypes(Supplier<RecipeSerializer<?>> serializerSupplier, Supplier<RecipeType<?>> typeSupplier, boolean registerType) {
		String name = Lang.asId(name());
		id = DesiresCreate.asResource(name);
		serializerObject = Registers.SERIALIZER_REGISTER.register(name, serializerSupplier);
		if (registerType) {
			typeObject = Registers.TYPE_REGISTER.register(name, typeSupplier);
			type = typeObject;
		} else {
			typeObject = null;
			type = typeSupplier;
		}
	}

	DesiresRecipeTypes(Supplier<RecipeSerializer<?>> serializerSupplier) {
		String name = Lang.asId(name());
		id = DesiresCreate.asResource(name);
		serializerObject = Registers.SERIALIZER_REGISTER.register(name, serializerSupplier);
		typeObject = Registers.TYPE_REGISTER.register(name, () -> RecipeType.simple(id));
		type = typeObject;
	}

	DesiresRecipeTypes(ProcessingRecipeFactory<?> processingFactory) {
		this(() -> new ProcessingRecipeSerializer<>(processingFactory));
	}

	public static void register(IEventBus modEventBus) {
		ShapedRecipe.setCraftingSize(9, 9);
		Registers.SERIALIZER_REGISTER.register(modEventBus);
		Registers.TYPE_REGISTER.register(modEventBus);
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends RecipeSerializer<?>> T getSerializer() {
		return (T) serializerObject.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends RecipeType<?>> T getType() {
		return (T) type.get();
	}

	public <C extends Container, T extends Recipe<C>> Optional<T> find(C inv, Level world) {
		return world.getRecipeManager()
			.getRecipeFor(getType(), inv, world);
	}

	private static class Registers {
		private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DesiresCreate.MOD_ID);
		private static final DeferredRegister<RecipeType<?>> TYPE_REGISTER = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, DesiresCreate.MOD_ID);
	}

}
