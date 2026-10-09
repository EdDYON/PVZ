package keletu.pvzmod.models;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.SunFlower;
import keletu.pvzmod.models.anim.SunFlowerModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SunFlowerModel extends HierarchicalModel<SunFlower> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/sun_flower.png"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart jin;
    private final ModelPart ye2;
    private final ModelPart ye1;
    private final ModelPart leaf;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart face;
    private final ModelPart mouth;
    private final ModelPart eye;

    public SunFlowerModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.jin = this.all.getChild("jin");
        this.ye2 = this.jin.getChild("ye2");
        this.ye1 = this.jin.getChild("ye1");
        this.leaf = this.all.getChild("leaf");
        this.bone = this.leaf.getChild("bone");
        this.bone2 = this.leaf.getChild("bone2");
        this.bone3 = this.leaf.getChild("bone3");
        this.bone4 = this.leaf.getChild("bone4");
        this.face = this.all.getChild("face");
        this.mouth = this.face.getChild("mouth");
        this.eye = this.face.getChild("eye");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.3595F, 11.3416F, -0.837F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create().texOffs(9, 18).addBox(-1.0F, -12.639F, 0.2424F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(-0.3595F, 11.6584F, 1.337F));

        PartDefinition cube_r1 = jin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 15).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -9.439F, 0.2424F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r2 = jin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 26).addBox(-1.5F, -2.0F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.4F, -11.139F, 1.0424F, -0.0944F, -0.3911F, 0.0361F));

        PartDefinition cube_r3 = jin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 19).addBox(-1.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, -14.439F, 1.1424F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r4 = jin.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(56, 23).addBox(-2.5F, -2.0F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.4F, -11.139F, 1.0424F, -0.0944F, 0.3911F, -0.0361F));

        PartDefinition cube_r5 = jin.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(9, 28).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.5F, -0.2618F, 0.0F, 0.0F));

        PartDefinition ye2 = jin.addOrReplaceChild("ye2", CubeListBuilder.create(), PartPose.offset(0.75F, -2.7923F, 1.0777F));

        PartDefinition cube_r6 = ye2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(8, 47).addBox(-0.0781F, -2.4355F, -0.2279F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, -0.48F));

        PartDefinition ye1 = jin.addOrReplaceChild("ye1", CubeListBuilder.create(), PartPose.offset(-0.75F, -2.2923F, 1.0777F));

        PartDefinition cube_r7 = ye1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(8, 53).addBox(-5.9028F, -2.1139F, -0.3427F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.48F));

        PartDefinition leaf = all.addOrReplaceChild("leaf", CubeListBuilder.create(), PartPose.offset(-0.3595F, 12.0041F, 1.3259F));

        PartDefinition bone = leaf.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.124F, 0.2378F));

        PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(17, 18).addBox(-2.5F, -0.1319F, 0.7588F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone2 = leaf.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.124F, -0.2599F));

        PartDefinition cube_r9 = bone2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(17, 28).addBox(-2.5F, -0.1319F, -6.7588F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition bone3 = leaf.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-0.2571F, -0.1241F, 0.0111F));

        PartDefinition cube_r10 = bone3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 6).addBox(-6.8149F, -0.2972F, -2.5F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition bone4 = leaf.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(0.2571F, -0.1241F, 0.0111F));

        PartDefinition cube_r11 = bone4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(17, 37).addBox(0.8149F, -0.2972F, -2.5F, 6.0F, 3.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition face = all.addOrReplaceChild("face", CubeListBuilder.create().texOffs(0, 8).addBox(-5.3595F, -4.3416F, -0.363F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r12 = face.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 42).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-5.1524F, 3.4512F, 0.137F, 0.0F, -0.2618F, -0.7854F));

        PartDefinition cube_r13 = face.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 4).addBox(-7.0F, -2.0F, 0.0F, 13.0F, 4.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3595F, 5.0584F, 0.262F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r14 = face.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 38).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.4334F, 3.4512F, 0.137F, 0.0F, 0.2618F, 0.7854F));

        PartDefinition cube_r15 = face.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5405F, -0.3416F, 0.137F, 0.0F, 0.2618F, 0.0F));

        PartDefinition cube_r16 = face.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 52).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.1405F, -4.8416F, -0.163F, 0.2618F, 0.0F, 0.7854F));

        PartDefinition cube_r17 = face.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 18).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.2595F, -0.3416F, 0.137F, 0.0F, -0.2618F, 0.0F));

        PartDefinition cube_r18 = face.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 46).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.8595F, -4.8416F, -0.163F, 0.2618F, 0.0F, -0.7854F));

        PartDefinition cube_r19 = face.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -2.0F, 0.0F, 11.0F, 4.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.3595F, -6.2416F, 0.137F, 0.2618F, 0.0F, 0.0F));

        PartDefinition mouth = face.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(29, 0).addBox(-1.5F, -0.0611F, 0.0F, 3.0F, 0.5F, 0.0F, new CubeDeformation(0F)), PartPose.offset(-0.3595F, 0.9695F, -0.413F));

        PartDefinition cube_r20 = mouth.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(29, 0).addBox(-0.25F, -0.5F, 0.0F, 0.5F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.8468F, -0.1223F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r21 = mouth.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(29, 0).addBox(-0.25F, -0.5F, 0.0F, 0.5F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.8468F, -0.1223F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r22 = mouth.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(29, 0).addBox(-1.0F, -0.25F, 0.0F, 2.5F, 0.5F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.9545F, -0.0048F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r23 = mouth.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(29, 0).addBox(-1.5F, -0.25F, 0.0F, 2.5F, 0.5F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.9545F, -0.0048F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition eye = face.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(27, 5).addBox(1.75F, -0.6875F, -0.0125F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(28, 0).addBox(1.5F, -1.1875F, 0.0125F, 1.5F, 2.75F, 0.0F, new CubeDeformation(0F))
                .texOffs(28, 0).addBox(-3.0F, -1.1875F, 0.0125F, 1.5F, 2.75F, 0.0F, new CubeDeformation(0F))
                .texOffs(27, 6).addBox(-2.75F, -0.6875F, -0.0125F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(-0.3595F, -2.1541F, -0.4005F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SunFlower entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, SunFlowerModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, SunFlowerModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.generateSunAnimation, SunFlowerModelAnimation.product, ageInTicks, 1.0F);

        this.all.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.all.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}