package keletu.pvzmod.models;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SunItemModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/sun.png"), "main");
    private final ModelPart all;
    private final ModelPart bone2;
    private final ModelPart bone;

    public SunItemModel(ModelPart root) {
        this.all = root.getChild("all");
        this.bone2 = this.all.getChild("bone2");
        this.bone = this.all.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0, 0, 0));

        PartDefinition bone2 = all.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 17).addBox(4.0F, 4.0F, 6.225F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F).extend(-8F, -8F, -1.7F))
                .texOffs(0, 9).addBox(5.0F, 5.0F, 7.025F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(2, 19).addBox(4.0F, 4.0F, 8.025F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F).extend(-7.999F, -7.999F, 0.201F))
                .texOffs(0, 17).addBox(4.0F, 4.0F, 7.725F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F).extend(-8F, -8F, -1.7F)), PartPose.offset(0.0F, 0.0F, -0.025F));

        PartDefinition bone = all.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4999F, -11.0006F, 7.975F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(8.0001F, 7.9994F, 0.0F));
        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5018F, -10.9994F, 0.05F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, -2.5133F));
        PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5013F, -10.999F, 0.1F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, -1.885F));
        PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5007F, -10.999F, 0.05F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, -1.2566F));
        PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5002F, -10.9994F, 0.1F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, -0.6283F));
        PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-2.499F, -11.0F, 0.1F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, -3.1416F));
        PartDefinition cube_r6 = bone.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4982F, -10.9994F, 0.05F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, 2.5133F));
        PartDefinition cube_r7 = bone.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4987F, -10.999F, 0.1F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, 1.885F));
        PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4993F, -10.999F, 0.05F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, 1.2566F));
        PartDefinition cube_r9 = bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-2.4998F, -10.9994F, 0.1F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0001F, -0.0006F, 7.9F, 0.0F, 0.0F, 0.6283F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public void setupAnim(float ageInTicks) {
        this.bone.zRot = ageInTicks * 0.1F;
    }

    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, 1F, 1F, 1F, 1F);
    }
}