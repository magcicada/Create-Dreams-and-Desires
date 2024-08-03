package uwu.lopyluna.create_dd.registry;

import com.simibubi.create.content.contraptions.ContraptionType;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.blocks.contraptions.contraption_block.BoatContraption;

public class DesiresContraptionType {
    public static final ContraptionType BOAT = ContraptionType.register(DesiresCreate.asResource("boat").toString(), BoatContraption::new);
}
