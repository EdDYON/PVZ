package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.BonkChoy;
import keletu.pvzmod.models.BonkChoyModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BonkChoyRender<T extends BonkChoy> extends MobRenderer<BonkChoy, BonkChoyModel> {
    public BonkChoyRender(EntityRendererProvider.Context pContext) {
        super(pContext, new BonkChoyModel(pContext.bakeLayer(BonkChoyModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(BonkChoy pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/bonkchoy.png");
    }
}
