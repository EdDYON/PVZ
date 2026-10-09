package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import keletu.pvzmod.items.ItemLootChest;

public class GeoLootChestItemRenderer extends PVZGeoItemRenderer<ItemLootChest> {
    public GeoLootChestItemRenderer() {
        super();
    }

    @Override
    protected void applyGuiTransform(ItemLootChest item, PoseStack poseStack) {
        poseStack.scale(0.68F, 0.68F, 0.68F);
        this.translateToChestCenter(poseStack);
    }

    @Override
    protected void applyFirstPersonTransform(ItemLootChest item, PoseStack poseStack, boolean leftHand) {
        poseStack.translate(leftHand ? -0.20D : 0.20D, -0.16D, -0.10D);
        poseStack.scale(0.42F, 0.42F, 0.42F);
        poseStack.mulPose(Axis.XP.rotationDegrees(-8.0F));
        poseStack.mulPose(Axis.YP.rotationDegrees(leftHand ? -28.0F : 28.0F));
        this.translateToChestCenter(poseStack);
    }

    @Override
    protected void applyGroundTransform(ItemLootChest item, PoseStack poseStack) {
        poseStack.translate(0.0D, 0.08D, 0.0D);
        poseStack.scale(0.36F, 0.36F, 0.36F);
        this.translateToChestCenter(poseStack);
    }

    @Override
    protected void applyFixedTransform(ItemLootChest item, PoseStack poseStack) {
        poseStack.scale(0.44F, 0.44F, 0.44F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        this.translateToChestCenter(poseStack);
    }

    private void translateToChestCenter(PoseStack poseStack) {
        poseStack.translate(0.0D, -5.5D / 16.0D, 0.5D / 16.0D);
    }
}
