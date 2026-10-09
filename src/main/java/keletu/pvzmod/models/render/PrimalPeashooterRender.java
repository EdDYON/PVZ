package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.PrimalPeashooter;
import keletu.pvzmod.models.PrimalPeashooterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PrimalPeashooterRender<T extends PrimalPeashooter> extends MobRenderer<PrimalPeashooter, PrimalPeashooterModel> {
    public PrimalPeashooterRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PrimalPeashooterModel(pContext.bakeLayer(PrimalPeashooterModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PrimalPeashooter pEntity) {
        return PrimalPeashooterModel.LAYER_LOCATION.getModel();
    }
}
