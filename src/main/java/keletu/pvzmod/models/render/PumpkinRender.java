package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.Pumpkin;
import keletu.pvzmod.models.PumpkinModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PumpkinRender extends MobRenderer<Pumpkin, HierarchicalModel<Pumpkin>> {

    public PumpkinRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PumpkinModel(pContext.bakeLayer(PumpkinModel.LAYER_LOCATION)), 0.5F);
        //this.addLayer(new PlantStuckArrowLayer<>(pContext, this));
    }

    @Override
    public ResourceLocation getTextureLocation(Pumpkin entity) {
        float percent = entity.getHealth() / entity.getMaxHealth();

        if (percent > 3.0F / 4.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/pumpkin.png");
        } else if (percent > 1.0F / 2.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/pumpkin_1.png");
        } else if (percent > 1.0F / 4.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/pumpkin_2.png");
        } else {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/pumpkin_3.png");
        }
    }
}
