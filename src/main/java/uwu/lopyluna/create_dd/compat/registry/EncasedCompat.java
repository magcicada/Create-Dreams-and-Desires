package uwu.lopyluna.create_dd.compat.registry;


import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import uwu.lopyluna.create_dd.registry.DesiresBlocks;
import uwu.lopyluna.create_dd.registry.DesiresCreativeModeTabs;
import uwu.lopyluna.create_dd.registry.DesiresSpriteShifts;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

@SuppressWarnings({"unused"})
public class EncasedCompat {
    //ON HOLD FOR NOW DUE TO ISSUES

    static {
        REGISTRATE.setCreativeTab(DesiresCreativeModeTabs.BASE_CREATIVE_TAB);
    }

    //public static final BlockEntry<? extends Block>
    //CREATIVE_ENCASED_SHAFT = fr.iglee42.createcasing.api.CreateCasingApi.createEncasedShaft(
    //        REGISTRATE, "creative", DesiresBlocks.CREATIVE_CASING::get, DesiresSpriteShifts.CREATIVE_CASING),
    //CREATIVE_ENCASED_COGWHEEL = fr.iglee42.createcasing.api.CreateCasingApi.createEncasedCogwheel(
    //        REGISTRATE, "creative", DesiresBlocks.CREATIVE_CASING::get,
    //        DesiresSpriteShifts.CREATIVE_CASING, DesiresSpriteShifts.CREATIVE_CASING_COGWHEEL_SIDE, DesiresSpriteShifts.CREATIVE_CASING_COGWHEEL_OTHERSIDE),
    //CREATIVE_ENCASED_LARGE_COGWHEEL = fr.iglee42.createcasing.api.CreateCasingApi.createEncasedLargeCogwheel(
    //        REGISTRATE, "creative", DesiresBlocks.CREATIVE_CASING::get, DesiresSpriteShifts.CREATIVE_CASING),
    //CREATIVE_GEARBOX = fr.iglee42.createcasing.api.CreateCasingApi.createGearbox(
    //        REGISTRATE, "creative", DesiresSpriteShifts.CREATIVE_CASING, true),
    //CREATIVE_DEPOT = fr.iglee42.createcasing.api.CreateCasingApi.createDepot(
    //        REGISTRATE, "creative"),
    //CREATIVE_MIXER = fr.iglee42.createcasing.api.CreateCasingApi.createMixer(
    //        REGISTRATE, "creative"),
    //CREATIVE_PRESS = fr.iglee42.createcasing.api.CreateCasingApi.createPress(
    //        REGISTRATE, "creative");

    public static void register() {}
}
