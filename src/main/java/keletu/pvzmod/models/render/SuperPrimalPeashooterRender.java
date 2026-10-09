package keletu.pvzmod.models.render;

import keletu.pvzmod.entities.SuperPrimalGatlingPea;
import keletu.pvzmod.models.PrimalSuperGatlingPeaModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SuperPrimalPeashooterRender<T extends SuperPrimalGatlingPea> extends MobRenderer<SuperPrimalGatlingPea, PrimalSuperGatlingPeaModel> {
    public SuperPrimalPeashooterRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PrimalSuperGatlingPeaModel(pContext.bakeLayer(PrimalSuperGatlingPeaModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(SuperPrimalGatlingPea pEntity) {
        return PrimalSuperGatlingPeaModel.LAYER_LOCATION.getModel();
    }
}
