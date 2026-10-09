package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.Walnut;
import keletu.pvzmod.models.PlantStuckArrowLayer;
import keletu.pvzmod.models.WalnutModel0;
import keletu.pvzmod.models.WalnutModel1;
import keletu.pvzmod.models.WalnutModel2;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WalnutRender extends MobRenderer<Walnut, HierarchicalModel<Walnut>> {

    private final WalnutModel0 modelHealthy;
    private final WalnutModel1 modelDamaged;
    private final WalnutModel2 modelCritical;

    public WalnutRender(EntityRendererProvider.Context pContext) {
        super(pContext, new WalnutModel0(pContext.bakeLayer(WalnutModel0.LAYER_LOCATION)), 0.5F);

        this.modelHealthy = new WalnutModel0(pContext.bakeLayer(WalnutModel0.LAYER_LOCATION));
        this.modelDamaged = new WalnutModel1(pContext.bakeLayer(WalnutModel1.LAYER_LOCATION));
        this.modelCritical = new WalnutModel2(pContext.bakeLayer(WalnutModel2.LAYER_LOCATION));
        this.addLayer(new PlantStuckArrowLayer<>(pContext, this));
        this.addLayer(new WalnutHelmetLayer(this, pContext));
    }

    @Override
    public void render(Walnut entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        float percent = entity.getHealth() / entity.getMaxHealth();

        if (percent > 2.0F / 3.0F) {
            this.model = this.modelHealthy;
        } else if (percent >= 1.0F / 3.0F) {
            this.model = this.modelDamaged;
        } else {
            this.model = this.modelCritical;
        }

        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Walnut entity) {
        float percent = entity.getHealth() / entity.getMaxHealth();

        if (percent > 2.0F / 3.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/walnut_new_0.png");
        } else if (percent > 1.0F / 3.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/walnut_new_1.png");
        } else {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/walnut_new_2.png");
        }
    }
}
