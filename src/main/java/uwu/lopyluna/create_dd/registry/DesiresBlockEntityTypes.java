package uwu.lopyluna.create_dd.registry;


import com.tterrag.registrate.util.entry.BlockEntityEntry;
import uwu.lopyluna.create_dd.content.blocks.kinetics.IndustrialFanBlock.IndustrialFanBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.IndustrialFanBlock.IndustrialFanInstance;
import uwu.lopyluna.create_dd.content.blocks.kinetics.IndustrialFanBlock.IndustrialFanRemderer;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankInstance;
import uwu.lopyluna.create_dd.content.blocks.kinetics.cog_crank.CogCrankRenderer;
import uwu.lopyluna.create_dd.content.blocks.logistics.fluid_keg.FluidKegBlockEntity;
import uwu.lopyluna.create_dd.content.blocks.logistics.item_stockpile.ItemStockpileBlockEntity;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

public class DesiresBlockEntityTypes {

	public static final BlockEntityEntry<IndustrialFanBlockEntity> INDUSTRIAL_FAN = REGISTRATE
			.blockEntity("industrial_fan", IndustrialFanBlockEntity::new)
			.instance(() -> IndustrialFanInstance::new, false)
			.validBlocks(DesiresBlocks.INDUSTRIAL_FAN)
			.renderer(() -> IndustrialFanRemderer::new)
			.register();

	public static final BlockEntityEntry<CogCrankBlockEntity> COG_CRANK = REGISTRATE
			.blockEntity("cog_crank", CogCrankBlockEntity::new)
			.instance(() -> CogCrankInstance::new)
			.validBlocks(DesiresBlocks.COG_CRANK)
			.renderer(() -> CogCrankRenderer::new)
			.register();

	public static final BlockEntityEntry<ItemStockpileBlockEntity> ITEM_STOCKPILE = REGISTRATE
			.blockEntity("item_stockpile", ItemStockpileBlockEntity::new)
			.validBlocks(DesiresBlocks.ITEM_STOCKPILE)
			.register();

	public static final BlockEntityEntry<FluidKegBlockEntity> FLUID_KEG = REGISTRATE
			.blockEntity("fluid_keg", FluidKegBlockEntity::new)
			.validBlocks(DesiresBlocks.FLUID_KEG)
			.register();


	public static void register() {}
}
