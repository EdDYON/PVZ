package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.SunFlower;
import keletu.pvzmod.models.SunFlowerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SunFlowerRender<T extends SunFlower> extends MobRenderer<SunFlower, SunFlowerModel> {
    public SunFlowerRender(EntityRendererProvider.Context pContext) {
        super(pContext, new SunFlowerModel(pContext.bakeLayer(SunFlowerModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SunFlower pEntity) {
        return SunFlowerModel.LAYER_LOCATION.getModel();
    }
}
