package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.SuperSnowGatlingPea;
import keletu.pvzmod.models.SnowySuperGatlingPeaModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SuperSnowGatlingPeaRender<T extends SuperSnowGatlingPea> extends MobRenderer<SuperSnowGatlingPea, SnowySuperGatlingPeaModel> {
    public SuperSnowGatlingPeaRender(EntityRendererProvider.Context pContext) {
        super(pContext, new SnowySuperGatlingPeaModel(pContext.bakeLayer(SnowySuperGatlingPeaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SuperSnowGatlingPea pEntity) {
        return SnowySuperGatlingPeaModel.LAYER_LOCATION.getModel();
    }
}
