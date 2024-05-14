package uwu.lopyluna.create_dd.registry;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.entities.inert_blazeling.InertBlazeModel;
import uwu.lopyluna.create_dd.content.entities.inert_blazeling.InertBlazeRenderer;

@Mod.EventBusSubscriber(modid = DesiresCreate.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DesiresRegistry {


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DesiresEntityTypes.INERT_BLAZELING.get(), InertBlazeRenderer::new);

    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(InertBlazeModel.LAYER_LOCATION, InertBlazeModel::createBodyLayer);

    }
}
