package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.Tallnut;
import keletu.pvzmod.models.PlantStuckArrowLayer;
import keletu.pvzmod.models.TallNutModel0;
import keletu.pvzmod.models.TallNutModel1;
import keletu.pvzmod.models.TallNutModel2;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TallnutRender extends MobRenderer<Tallnut, HierarchicalModel<Tallnut>> {

    private final TallNutModel0 modelHealthy;
    private final TallNutModel1 modelDamaged;
    private final TallNutModel2 modelCritical;

    public TallnutRender(EntityRendererProvider.Context pContext) {
        super(pContext, new TallNutModel0(pContext.bakeLayer(TallNutModel0.LAYER_LOCATION)), 0.5F);

        this.modelHealthy = new TallNutModel0(pContext.bakeLayer(TallNutModel0.LAYER_LOCATION));
        this.modelDamaged = new TallNutModel1(pContext.bakeLayer(TallNutModel1.LAYER_LOCATION));
        this.modelCritical = new TallNutModel2(pContext.bakeLayer(TallNutModel2.LAYER_LOCATION));
        this.addLayer(new PlantStuckArrowLayer<>(pContext, this));
        this.addLayer(new WalnutHelmetLayer(this, pContext));
    }

    @Override
    public void render(Tallnut entity, float entityYaw, float partialTick, PoseStack poseStack,
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
    public ResourceLocation getTextureLocation(Tallnut entity) {
        float percent = entity.getHealth() / entity.getMaxHealth();

        if (percent > 2.0F / 3.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/tallnut_new.png");
        } else if (percent > 1.0F / 3.0F) {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/tallnut_new_1.png");
        } else {
            return new ResourceLocation(PVZMod.MODID, "textures/entity/plant/tallnut_new_2.png");
        }
    }
}
