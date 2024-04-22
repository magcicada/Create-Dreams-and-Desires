package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlock;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileCTBehaviour;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileItem;

import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

public class DesiresBlocks {

	static {
		REGISTRATE.creativeModeTab(() -> DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
	}
	//Horizontal Tank -> Fluid Keg
	//Vertical Vault -> Item Stockpile

	public static final BlockEntry<ItemStockpileBlock> ITEM_STOCKPILE = REGISTRATE.block("item_stockpile", ItemStockpileBlock::new)
			.initialProperties(SharedProperties::softMetal)
			.properties(p -> p.color(MaterialColor.TERRACOTTA_BLUE)
					.sound(SoundType.NETHERITE_BLOCK)
					.explosionResistance(1200))
			.transform(pickaxeOnly())
			.blockstate((c, p) -> p.getVariantBuilder(c.get())
					.forAllStates(s -> ConfiguredModel.builder()
							.modelFile(AssetLookup.standardModel(c, p))
							.build()))
			.onRegister(connectedTextures(ItemStockpileCTBehaviour::new))
			.item(ItemStockpileItem::new)
			.build()
			.register();

	// Load this class

	public static void register() {}

}
