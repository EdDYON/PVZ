package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.SuperGatlingPea;
import keletu.pvzmod.models.SuperGatlingPeaModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SGPShooterRender<T extends SuperGatlingPea> extends MobRenderer<SuperGatlingPea, SuperGatlingPeaModel> {
    public SGPShooterRender(EntityRendererProvider.Context pContext) {
        super(pContext, new SuperGatlingPeaModel(pContext.bakeLayer(SuperGatlingPeaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SuperGatlingPea pEntity) {
        return SuperGatlingPeaModel.LAYER_LOCATION.getModel();
    }
}
