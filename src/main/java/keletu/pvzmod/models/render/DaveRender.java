package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.dave.Dave;
import keletu.pvzmod.models.DaveModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DaveRender<T extends Dave> extends MobRenderer<Dave, DaveModel> {
    public DaveRender(EntityRendererProvider.Context pContext) {
        super(pContext, new DaveModel(pContext.bakeLayer(DaveModel.LAYER_LOCATION)), 0.75F);
    }

    @Override
    public ResourceLocation getTextureLocation(Dave pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/npc/dave.png");
    }
}
