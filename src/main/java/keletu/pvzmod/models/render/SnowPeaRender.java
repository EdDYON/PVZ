package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.SnowPea;
import keletu.pvzmod.models.SnowPeaModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnowPeaRender<T extends SnowPea> extends MobRenderer<SnowPea, SnowPeaModel> {
    public SnowPeaRender(EntityRendererProvider.Context pContext) {
        super(pContext, new SnowPeaModel(pContext.bakeLayer(SnowPeaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SnowPea pEntity) {
        return SnowPeaModel.LAYER_LOCATION.getModel();
    }
}
