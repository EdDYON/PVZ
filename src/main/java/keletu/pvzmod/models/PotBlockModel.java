package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PotBlockModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/block/pot.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart group;

    public PotBlockModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.group = this.all.getChild("group");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(4, 0).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(10, 10).addBox(-7.0F, -8.0F, -4.0F, 3.0F, 2.0F, 8.0F, new CubeDeformation(0F))
                .texOffs(10, 20).addBox(4.0F, -8.0F, -4.0F, 3.0F, 2.0F, 8.0F, new CubeDeformation(0F))
                .texOffs(4, 5).addBox(-7.0F, -8.0F, 4.0F, 14.0F, 2.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(0, 30).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = all.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 0).addBox(0.267F, 0.3479F, -0.025F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -3.2456F, -5.075F, 0.0F, 0.0F, 0.6545F));

        PartDefinition cube_r2 = all.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(38, 0).addBox(-1.267F, 0.3479F, -0.025F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -3.2456F, -5.075F, 0.0F, 0.0F, -0.6545F));

        PartDefinition cube_r3 = all.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 0).addBox(-4.1381F, -0.8521F, 0.025F, 3.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -3.2456F, -5.075F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r4 = all.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(38, 0).addBox(1.1381F, -0.8521F, 0.025F, 3.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -3.2456F, -5.075F, 0.0F, 0.0F, -0.1309F));

        PartDefinition group = all.addOrReplaceChild("group", CubeListBuilder.create().texOffs(39, 3).addBox(-6.0F, -2.0F, 4.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0001F)), PartPose.offset(4.0F, -8.0F, -3.0F));

        PartDefinition cube_r5 = group.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(38, -6).addBox(0.0F, -3.0F, -3.5F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-6.0F, -2.0F, 5.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r6 = group.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, -6).addBox(0.0F, -3.0F, -3.5F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0001F))
                .texOffs(39, 3).addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-6.0F, -2.0F, 5.0F, 0.0F, -1.5708F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void resetPose() {
        this.root.getAllParts().forEach(ModelPart::resetPose);
    }

    public void setupAnim(float ageInTicks) {
        resetPose();

        float t = (ageInTicks / 20.0F) % 2.0F;
        float scaleY;

        if (t <= 1.0F) {
            float progress = t / 1.0F;
            scaleY = 1.0F + (1.05F - 1.0F) * progress;
        } else {
            float progress = (t - 1.0F) / 1.0F;
            scaleY = 1.05F + (1.0F - 1.05F) * progress;
        }

        this.all.xScale = 1.0F;
        this.all.yScale = scaleY;
        this.all.zScale = 1.0F;
    }

    public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        root.render(poseStack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}