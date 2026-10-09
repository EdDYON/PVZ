package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import keletu.pvzmod.entities.Walnut;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.Arrow;

import java.util.List;

public class PlantStuckArrowLayer<T extends Walnut, M extends HierarchicalModel<T>> extends RenderLayer<T, M> {

    private final EntityRenderDispatcher dispatcher;

    public PlantStuckArrowLayer(EntityRendererProvider.Context context, RenderLayerParent<T, M> parent) {
        super(parent);
        this.dispatcher = context.getEntityRenderDispatcher();
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        List<Walnut.StuckArrowRecord> arrows = entity.getStuckArrowRecords();
        if (arrows.isEmpty()) {
            return;
        }

        ModelPart body = getArrowAnchorPart();
        if (body.isEmpty()) {
            return;
        }

        for (Walnut.StuckArrowRecord arrow : arrows) {
            poseStack.pushPose();
            body.translateAndRotate(poseStack);
            poseStack.translate(-arrow.x / 16.0F, arrow.y / 16.0F, arrow.z / 16.0F);

            renderArrow(poseStack, buffer, packedLight, entity, -arrow.dirX, arrow.dirY, arrow.dirZ, partialTick);

            poseStack.popPose();
        }
    }

    private ModelPart getArrowAnchorPart() {
        ModelPart root = this.getParentModel().root();
        return root.hasChild("all") ? root.getChild("all") : root;
    }

    private void renderArrow(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Walnut entity, float arrowX, float arrowY, float arrowZ, float partialTick) {
        float horizontal = Mth.sqrt(arrowX * arrowX + arrowZ * arrowZ);
        Arrow arrow = new Arrow(entity.level(), entity.getX(), entity.getY(), entity.getZ());
        arrow.setYRot((float) (Math.atan2(arrowX, arrowZ) * 180.0D / Math.PI));
        arrow.setXRot((float) (Math.atan2(arrowY, horizontal) * 180.0D / Math.PI));
        arrow.yRotO = arrow.getYRot();
        arrow.xRotO = arrow.getXRot();
        this.dispatcher.render(arrow, 0.0D, 0.0D, 0.0D, 0.0F, partialTick, poseStack, buffer, packedLight);
    }
}
