package uwu.lopyluna.create_dd.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.ForgeRegistries;

import static uwu.lopyluna.create_dd.DesiresCreate.MOD_ID;
import static uwu.lopyluna.create_dd.registry.DesiresTags.optionalTag;

@SuppressWarnings({"unchecked"})
public class DTags {
    public static <T> TagKey<T> tag(String type, String id) {
        return ("item".equalsIgnoreCase(type))  ? (TagKey<T>) optionalTag(ForgeRegistries.ITEMS,  new ResourceLocation(MOD_ID, id.toLowerCase())) :
                ("block".equalsIgnoreCase(type))  ? (TagKey<T>) optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation(MOD_ID, id.toLowerCase())) :
                        ("fluid".equalsIgnoreCase(type))  ? (TagKey<T>) optionalTag(ForgeRegistries.FLUIDS, new ResourceLocation(MOD_ID, id.toLowerCase())) :
                                null;
    }
    public static <T> TagKey<T> tag(String type, String mod_id, String id) {
        return ("item".equalsIgnoreCase(type))  ? (TagKey<T>) optionalTag(ForgeRegistries.ITEMS,  new ResourceLocation(mod_id.toLowerCase(), id.toLowerCase())) :
                ("block".equalsIgnoreCase(type))  ? (TagKey<T>) optionalTag(ForgeRegistries.BLOCKS, new ResourceLocation(mod_id.toLowerCase(), id.toLowerCase())) :
                        ("fluid".equalsIgnoreCase(type))  ? (TagKey<T>) optionalTag(ForgeRegistries.FLUIDS, new ResourceLocation(mod_id.toLowerCase(), id.toLowerCase())) :
                                null;
    }
}
