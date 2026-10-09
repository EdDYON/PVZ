package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.Repeater;
import keletu.pvzmod.models.RepeaterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RepeaterRender<T extends Repeater> extends MobRenderer<Repeater, RepeaterModel> {
    public RepeaterRender(EntityRendererProvider.Context pContext) {
        super(pContext, new RepeaterModel(pContext.bakeLayer(RepeaterModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Repeater pEntity) {
        return RepeaterModel.LAYER_LOCATION.getModel();
    }
}
