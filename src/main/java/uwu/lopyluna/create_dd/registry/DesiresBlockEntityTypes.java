package uwu.lopyluna.create_dd.registry;


import com.tterrag.registrate.util.entry.BlockEntityEntry;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlockEntity;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

public class DesiresBlockEntityTypes {

	public static final BlockEntityEntry<ItemStockpileBlockEntity> ITEM_STOCKPILE = REGISTRATE
			.blockEntity("item_stockpile", ItemStockpileBlockEntity::new)
			.validBlocks(DesiresBlocks.ITEM_STOCKPILE)
			.register();

	public static void register() {}
}
