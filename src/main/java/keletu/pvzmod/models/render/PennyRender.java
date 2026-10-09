package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.dave.Penny;
import keletu.pvzmod.models.PennyModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PennyRender<T extends Penny> extends MobRenderer<Penny, PennyModel> {
    public PennyRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PennyModel(pContext.bakeLayer(PennyModel.LAYER_LOCATION)), 2.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(Penny pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/npc/penny.png");
    }
}
