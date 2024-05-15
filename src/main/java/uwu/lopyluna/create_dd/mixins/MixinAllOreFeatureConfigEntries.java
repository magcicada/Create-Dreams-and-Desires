package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.Create;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.infrastructure.worldgen.AllOreFeatureConfigEntries;
import com.simibubi.create.infrastructure.worldgen.OreFeatureConfigEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uwu.lopyluna.create_dd.registry.DesiresPaletteStoneTypes;

@SuppressWarnings({"all"})
@Mixin(value = AllOreFeatureConfigEntries.class, remap = false)
public class MixinAllOreFeatureConfigEntries {

    @Final
    @Shadow
    public static final OreFeatureConfigEntry ZINC_ORE =
            create("gabbro_blob", 64, 0.85F, -64, 0)
                    .standardDatagenExt()
                    .withBlocks(Couple.create(AllPaletteStoneTypes.GRANITE.baseBlock, DesiresPaletteStoneTypes.GABBRO.baseBlock))
                    .biomeTag(BiomeTags.IS_OVERWORLD)
                    .parent();

    @Final
    @Shadow
    public static final OreFeatureConfigEntry STRIATED_ORES_OVERWORLD =
            create("dolomite_blob", 64, 0.85F, -64, 0)
                    .standardDatagenExt()
                    .withBlocks(Couple.create(AllPaletteStoneTypes.DIORITE.baseBlock, DesiresPaletteStoneTypes.DOLOMITE.baseBlock))
                    .biomeTag(BiomeTags.IS_OVERWORLD)
                    .parent();

    private static OreFeatureConfigEntry create(String name, int clusterSize, float frequency, int minHeight, int maxHeight) {
        ResourceLocation id = Create.asResource(name);
        OreFeatureConfigEntry configDrivenFeatureEntry = new OreFeatureConfigEntry(id, clusterSize, frequency, minHeight, maxHeight);
        return configDrivenFeatureEntry;
    }
}
