package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.PuffShroom;
import keletu.pvzmod.models.PuffShroomModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PuffShroomRender<T extends PuffShroom> extends MobRenderer<PuffShroom, PuffShroomModel> {
    public PuffShroomRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PuffShroomModel(pContext.bakeLayer(PuffShroomModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PuffShroom pEntity) {
        return PuffShroomModel.LAYER_LOCATION.getModel();
    }
}
