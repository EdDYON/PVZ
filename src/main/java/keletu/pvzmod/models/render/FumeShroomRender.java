package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.FumeShroom;
import keletu.pvzmod.models.FumeShroomModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FumeShroomRender<T extends FumeShroom> extends MobRenderer<FumeShroom, FumeShroomModel> {
    public FumeShroomRender(EntityRendererProvider.Context pContext) {
        super(pContext, new FumeShroomModel(pContext.bakeLayer(FumeShroomModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(FumeShroom pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/fume_shroom_new.png");
    }
}
