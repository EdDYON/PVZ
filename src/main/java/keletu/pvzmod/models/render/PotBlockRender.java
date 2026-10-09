package keletu.pvzmod.models.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.blocks.Pot;
import keletu.pvzmod.blocks.PotBlockEntity;
import keletu.pvzmod.models.PotBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class PotBlockRender implements BlockEntityRenderer<PotBlockEntity> {
    private final PotBlockModel model;
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(PVZMod.MODID, "textures/block/pot.png");

    public PotBlockRender(BlockEntityRendererProvider.Context context) {
        this.model = new PotBlockModel(context.bakeLayer(PotBlockModel.LAYER_LOCATION));
    }

    @Override
    public void render(PotBlockEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        poseStack.translate(0.5D, 1.5D, 0.5D);

        Direction facing = entity.getBlockState().getValue(Pot.FACING);
        poseStack.mulPose(Axis.YP.rotationDegrees(
                switch (facing) {
                    case NORTH -> 0.0F;
                    case SOUTH -> 180.0F;
                    case WEST -> 90.0F;
                    case EAST -> -90.0F;
                    default -> 0.0F;
                }
        ));

        poseStack.scale(1.0F, -1.0F, -1.0F);

        float ageInTicks = entity.getLevel() != null
                ? entity.getLevel().getGameTime() + partialTick
                : 0.0F;

        model.setupAnim(ageInTicks);

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
        model.renderToBuffer(poseStack, consumer, packedLight, packedOverlay,
                1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose();
    }
}