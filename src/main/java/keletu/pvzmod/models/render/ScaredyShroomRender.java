package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.ScaredyShroom;
import keletu.pvzmod.models.ScaredyShroomModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ScaredyShroomRender<T extends ScaredyShroom> extends MobRenderer<ScaredyShroom, ScaredyShroomModel> {
    public ScaredyShroomRender(EntityRendererProvider.Context pContext) {
        super(pContext, new ScaredyShroomModel(pContext.bakeLayer(ScaredyShroomModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ScaredyShroom pEntity) {
        return ScaredyShroomModel.LAYER_LOCATION.getModel();
    }
}
