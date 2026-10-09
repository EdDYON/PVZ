package keletu.pvzmod.models.render;

import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.PotatoMine;
import keletu.pvzmod.models.PotatoMineModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PotatoMineRender<T extends PotatoMine> extends MobRenderer<PotatoMine, PotatoMineModel> {
    public PotatoMineRender(EntityRendererProvider.Context pContext) {
        super(pContext, new PotatoMineModel(pContext.bakeLayer(PotatoMineModel.LAYER_LOCATION)), 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(PotatoMine pEntity) {
        return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/potato_mine_new.png");
    }
}
