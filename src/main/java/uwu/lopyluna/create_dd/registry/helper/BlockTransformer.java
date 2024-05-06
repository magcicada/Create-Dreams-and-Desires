package uwu.lopyluna.create_dd.registry.helper;

import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.HorizontalCTBehaviour;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.util.ForgeSoundType;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.CreateRegistrate.casingConnectivity;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;

@SuppressWarnings({"unused"})
public class BlockTransformer {

    public static BlockEntry<Block> blueprintBlocks( String colorId, String colorLang, CTSpriteShiftEntry connectedTexture, MaterialColor mapColor ) {
        return REGISTRATE.block(colorId + "_blueprint_block", Block::new)
                .transform(BlockTransformer.block(() -> connectedTexture))
                .initialProperties(() -> Blocks.HAY_BLOCK)
                .properties(p -> p.color(mapColor))
                .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                        () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                        () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
                .properties(p -> p.strength(0.025f,0.25f))
                .lang(colorLang + " Blueprint Block")
                .register();
    }

    public static BlockEntry<Block> blueprintBlocks( String colorId, String colorLang, CTSpriteShiftEntry connectedTexture, MaterialColor mapColor, String no_underscore ) {
        return REGISTRATE.block(colorId + "blueprint_block", Block::new)
                .transform(BlockTransformer.block(() -> connectedTexture))
                .initialProperties(() -> Blocks.HAY_BLOCK)
                .properties(p -> p.color(mapColor))
                .properties(p -> p.sound(new ForgeSoundType(1, 0.85f, () -> SoundEvents.PAINTING_BREAK,
                        () -> SoundEvents.MOSS_STEP, () -> SoundEvents.PAINTING_PLACE,
                        () -> SoundEvents.BAMBOO_HIT, () -> SoundEvents.MOSS_STEP)))
                .properties(p -> p.strength(0.025f,0.25f))
                .lang(colorLang + "Blueprint Block")
                .register();
    }


    public static <B extends Block> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> block( Supplier<CTSpriteShiftEntry> ct ) {
        return b -> b.initialProperties(SharedProperties::stone)
                .blockstate((c, p) -> p.simpleBlock(c.get()))
                .onRegister(connectedTextures(() -> new EncasedCTBehaviour(ct.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .item()
                .build();
    }

    public static <B extends Block> NonNullUnaryOperator<BlockBuilder<B, CreateRegistrate>> blockV2( Supplier<CTSpriteShiftEntry> ct, Supplier<CTSpriteShiftEntry> ct2 ) {
        return b -> b.initialProperties(SharedProperties::stone)
                .blockstate((c, p) -> p.simpleBlock(c.get(), p.models()
                        .cubeColumn(c.getName(), ct.get()
                                        .getOriginalResourceLocation(),
                                ct2.get()
                                        .getOriginalResourceLocation())))
                .onRegister(connectedTextures(() -> new HorizontalCTBehaviour(ct.get(), ct2.get())))
                .onRegister(casingConnectivity((block, cc) -> cc.makeCasing(block, ct.get())))
                .item()
                .build();
    }
}
