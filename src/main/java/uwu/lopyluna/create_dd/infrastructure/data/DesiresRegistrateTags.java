package uwu.lopyluna.create_dd.infrastructure.data;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;
import uwu.lopyluna.create_dd.registry.DesiresTags;

public class DesiresRegistrateTags {

	public static void addGenerators() {
		DesiresCreate.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, DesiresRegistrateTags::genBlockTags);
		//DesiresCreate.REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, DesiresRegistrateTags::genItemTags);
		//DesiresCreate.REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, DesiresRegistrateTags::genFluidTags);
		//DesiresCreate.REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, DesiresRegistrateTags::genEntityTags);
	}

	private static void genBlockTags(RegistrateTagsProvider<Block> prov) {

		prov.tag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SANDING.tag)
				.add(Blocks.SAND)
				.add(Blocks.RED_SAND);

		prov.tag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_FREEZING.tag)
				.add(Blocks.POWDER_SNOW);

		prov.tag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SEETHING.tag)
				.add(AllBlocks.BLAZE_BURNER.get());

		prov.tag(DesiresTags.AllBlockTags.INDUSTRIAL_FAN_HEATER.tag)
				.add(Blocks.LAVA)
				.add(DesiresBlocks.SEETHING_SAIL.get())
				.add(AllBlocks.BLAZE_BURNER.get());

		prov.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
				.addTag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SANDING.tag)
				.addTag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_FREEZING.tag)
				.addTag(DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SEETHING.tag);

		prov.tag(DesiresTags.AllBlockTags.INDUSTRIAL_FAN_TRANSPARENT.tag)
				.addTag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag);

	}
}
