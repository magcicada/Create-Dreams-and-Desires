package uwu.lopyluna.create_dd.mixins;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.infrastructure.worldgen.AllOreFeatureConfigEntries;
import com.simibubi.create.infrastructure.worldgen.OreFeatureConfigEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import uwu.lopyluna.create_dd.DesiresCreate;

@Mixin(AllOreFeatureConfigEntries.class)
public class MixinAllOreFeatureConfigEntries {

    @Final
    private static final OreFeatureConfigEntry TIN_ORE =
            createDesires("tin_ore", 6, 16, -32, 128)
                    .standardDatagenExt()
                    .withBlocks(Couple.create(AllBlocks.ZINC_BLOCK, AllBlocks.BRASS_BLOCK))
                    .biomeTag(BiomeTags.IS_OVERWORLD)
                    .parent();

    private static OreFeatureConfigEntry createDesires(String name, int clusterSize, float frequency, int minHeight, int maxHeight) {
        ResourceLocation id = DesiresCreate.asResource(name);
        OreFeatureConfigEntry configDrivenFeatureEntry = new OreFeatureConfigEntry(id, clusterSize, frequency, minHeight, maxHeight);
        return configDrivenFeatureEntry;
    }
}
