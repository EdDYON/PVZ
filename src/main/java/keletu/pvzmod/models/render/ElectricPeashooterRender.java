package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.ElectricPeashooter;
import keletu.pvzmod.models.ElectricPeashooterLayer;
import keletu.pvzmod.models.ElectricPeashooterModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ElectricPeashooterRender<T extends ElectricPeashooter> extends MobRenderer<ElectricPeashooter, ElectricPeashooterModel> {
    public ElectricPeashooterRender(EntityRendererProvider.Context pContext) {
        super(pContext, new ElectricPeashooterModel(pContext.bakeLayer(ElectricPeashooterModel.LAYER_LOCATION)), 0.5F);
        this.addLayer(new ElectricPeashooterLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ElectricPeashooter pEntity) {
        return ElectricPeashooterModel.LAYER_LOCATION.getModel();
    }

    @Override
    protected RenderType getRenderType(ElectricPeashooter entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.itemEntityTranslucentCull(getTextureLocation(entity));
    }
}
