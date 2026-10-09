package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.GatlingPea;
import keletu.pvzmod.models.GatlingPeaModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GatlingPeaRender<T extends GatlingPea> extends MobRenderer<GatlingPea, GatlingPeaModel> {
    public GatlingPeaRender(EntityRendererProvider.Context pContext) {
        super(pContext, new GatlingPeaModel(pContext.bakeLayer(GatlingPeaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(GatlingPea pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/gatling_pea_new.png");
    }

    @Override
    protected RenderType getRenderType(GatlingPea entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }
}
