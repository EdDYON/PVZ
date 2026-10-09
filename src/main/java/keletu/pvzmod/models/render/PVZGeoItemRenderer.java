package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import keletu.pvzmod.models.gecko.PVZGeoModel;
import keletu.pvzmod.models.gecko.PVZGeoResourceProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public abstract class PVZGeoItemRenderer<T extends Item & GeoAnimatable & PVZGeoResourceProvider> extends GeoItemRenderer<T> {
    protected PVZGeoItemRenderer() {
        super(new PVZGeoModel<>());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack,
                             MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        try {
            this.applyContextTransform((T) stack.getItem(), context, poseStack);
            super.renderByItem(stack, context, poseStack, buffer, packedLight, packedOverlay);
        } finally {
            poseStack.popPose();
        }
    }

    protected void applyContextTransform(T item, ItemDisplayContext context, PoseStack poseStack) {
        switch (context) {
            case GUI -> this.applyGuiTransform(item, poseStack);
            case FIRST_PERSON_RIGHT_HAND -> this.applyFirstPersonTransform(item, poseStack, false);
            case FIRST_PERSON_LEFT_HAND -> this.applyFirstPersonTransform(item, poseStack, true);
            case THIRD_PERSON_RIGHT_HAND -> this.applyThirdPersonTransform(item, poseStack, false);
            case THIRD_PERSON_LEFT_HAND -> this.applyThirdPersonTransform(item, poseStack, true);
            case GROUND -> this.applyGroundTransform(item, poseStack);
            case FIXED -> this.applyFixedTransform(item, poseStack);
            default -> {
            }
        }
    }

    protected void applyGuiTransform(T item, PoseStack poseStack) {
        poseStack.scale(0.82F, 0.82F, 0.82F);
        poseStack.mulPose(Axis.XP.rotationDegrees(22.5F));
        poseStack.mulPose(Axis.YP.rotationDegrees(-35.0F));
    }

    protected void applyFirstPersonTransform(T item, PoseStack poseStack, boolean leftHand) {
        poseStack.translate(leftHand ? -0.26D : 0.26D, -0.18D, -0.10D);
        poseStack.scale(0.52F, 0.52F, 0.52F);
        poseStack.mulPose(Axis.XP.rotationDegrees(-8.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(leftHand ? -28.0F : 28.0F));
    }

    protected void applyThirdPersonTransform(T item, PoseStack poseStack, boolean leftHand) {
        poseStack.translate(leftHand ? -0.08D : 0.08D, 0.10D, 0.0D);
        poseStack.scale(0.44F, 0.44F, 0.44F);
        poseStack.mulPose(Axis.YP.rotationDegrees(leftHand ? -20.0F : 20.0F));
    }

    protected void applyGroundTransform(T item, PoseStack poseStack) {
        poseStack.translate(0.0D, 0.08D, 0.0D);
        poseStack.scale(0.36F, 0.36F, 0.36F);
    }

    protected void applyFixedTransform(T item, PoseStack poseStack) {
        poseStack.translate(0.0D, -0.10D, 0.0D);
        poseStack.scale(0.55F, 0.55F, 0.55F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
    }
}
