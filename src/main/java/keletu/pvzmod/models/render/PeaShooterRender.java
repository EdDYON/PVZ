package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.PeaShooter;
import keletu.pvzmod.models.PeaShooterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PeaShooterRender<T extends PeaShooter> extends MobRenderer<PeaShooter, PeaShooterModel> {
    public PeaShooterRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PeaShooterModel(pContext.bakeLayer(PeaShooterModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PeaShooter pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/peashooter_new.png");
    }
}
