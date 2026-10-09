package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.ElectricPeashooter;
import keletu.pvzmod.models.anim.ElectricPeashooterModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ElectricPeashooterModel extends HierarchicalModel<ElectricPeashooter> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/electric_peashooter.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart face;
    private final ModelPart eye;
    private final ModelPart dian;
    private final ModelPart mouth;
    private final ModelPart mouth1;
    private final ModelPart jin;
    private final ModelPart bone;
    private final ModelPart jin1;
    private final ModelPart bone2;
    private final ModelPart leave5;
    private final ModelPart leave1;
    private final ModelPart leave2;
    private final ModelPart leave3;
    private final ModelPart leave4;

    public ElectricPeashooterModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.face = this.head.getChild("face");
        this.eye = this.head.getChild("eye");
        this.dian = this.head.getChild("dian");
        this.mouth = this.head.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
        this.jin = this.all.getChild("jin");
        this.bone = this.jin.getChild("bone");
        this.jin1 = this.jin.getChild("jin1");
        this.bone2 = this.jin1.getChild("bone2");
        this.leave5 = this.all.getChild("leave5");
        this.leave1 = this.leave5.getChild("leave1");
        this.leave2 = this.leave5.getChild("leave2");
        this.leave3 = this.leave5.getChild("leave3");
        this.leave4 = this.leave5.getChild("leave4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -4.5F, -4.5F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F).extend(-9.9F, -8.9F, -8.9F))
                .texOffs(0, 18).addBox(-4.0F, -4.0F, -4.0F, 9.0F, 8.0F, 8.0F, new CubeDeformation(0F)), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(52, 59).addBox(-1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(22, 60).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(3.5F, 1.5F, 0.0F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(28, 34).addBox(-1.5F, -1.5F, -4.0688F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(40, 63).addBox(-1.8F, -1.8F, -4.0438F, 3.6F, 3.6F, 0.0F, new CubeDeformation(0F))
                .texOffs(62, 63).addBox(-1.8F, -1.8F, 4.0313F, 3.6F, 3.6F, 0.0F, new CubeDeformation(0F))
                .texOffs(28, 37).addBox(-1.5F, -1.5F, 4.0812F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(1.5F, -1.5F, -0.0063F));

        PartDefinition dian = head.addOrReplaceChild("dian", CubeListBuilder.create(), PartPose.offset(-3.6848F, -0.6572F, 0.0F));

        PartDefinition cube_r1 = dian.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 17).addBox(-1.9827F, -0.5F, -0.0951F, 0.7028F, 1.0F, 0.1851F, new CubeDeformation(-0.0001F))
                .texOffs(24, 43).mirror().addBox(-1.6799F, -0.5F, -0.0951F, 0.4F, 1.0F, 1.0F, new CubeDeformation(0.003F)).mirror(false), PartPose.offsetAndRotation(-2.4021F, -2.2878F, 0.0F, 1.5708F, 0.0F, -0.7854F));

        PartDefinition cube_r2 = dian.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(54, 39).addBox(0.0554F, -0.5F, 0.2333F, 3.9392F, 1.0F, 0.6946F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.8264F, -1.8636F, 0.0F, 1.5708F, 0.0F, 0.9599F));

        PartDefinition cube_r3 = dian.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(60, 47).addBox(0.0951F, -0.5F, -0.0299F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F).extend(0.001F, 0.001F, -0.249F)), PartPose.offsetAndRotation(-2.8264F, -1.8636F, 0.0F, 1.5708F, 0.0F, 0.7854F));

        PartDefinition cube_outline_r1 = dian.addOrReplaceChild("cube_outline_r1", CubeListBuilder.create().texOffs(62, 22).addBox(-0.5299F, -1.0001F, -0.5951F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-1.9F)), PartPose.offsetAndRotation(-3.1191F, -1.5688F, -0.0099F, 1.5708F, 0.0F, -0.7854F));

        PartDefinition cube_outline_r2 = dian.addOrReplaceChild("cube_outline_r2", CubeListBuilder.create().texOffs(62, 10).addBox(0.5338F, -1.0001F, -0.2352F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F).extend(-4.015F, -1.9F, -0.856F)), PartPose.offsetAndRotation(-2.8362F, -1.8517F, -0.0099F, 1.5708F, 0.0F, 0.9599F));

        PartDefinition cube_outline_r3 = dian.addOrReplaceChild("cube_outline_r3", CubeListBuilder.create().texOffs(52, 55).addBox(0.5451F, -1.0001F, -0.0299F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(-4.05F, -1.9F, -2.15F)), PartPose.offsetAndRotation(-2.8362F, -1.8517F, -0.0099F, 1.5708F, 0.0F, 0.7854F));

        PartDefinition cube_outline_r4 = dian.addOrReplaceChild("cube_outline_r4", CubeListBuilder.create().texOffs(8, 63).addBox(-2.5828F, -1.0F, -0.6F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F).extend(-1.8F, -1.9F, -0.89F)), PartPose.offsetAndRotation(-2.4063F, -2.2743F, -0.01F, 1.5708F, 0.0F, -0.7854F));

        PartDefinition cube_r4 = dian.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 39).addBox(-0.1799F, -0.5F, -0.0951F, 1.9F, 1.0F, 1.0F, new CubeDeformation(0.003F))
                .texOffs(24, 43).addBox(-0.6799F, -0.5F, 0.9049F, 0.5F, 1.0F, 1.4F, new CubeDeformation(0.001F))
                .texOffs(24, 45).addBox(-0.8799F, -0.5F, -0.0951F, 0.7F, 1.0F, 1.0F, new CubeDeformation(0.002F))
                .texOffs(0, 63).addBox(-0.1799F, -0.5F, 0.9049F, 0.5F, 1.0F, 3.0F, new CubeDeformation(0.0002F)), PartPose.offsetAndRotation(-2.8264F, -1.8636F, 0.0F, 1.5708F, 0.0F, -0.7854F));

        PartDefinition cube_outline_r5 = dian.addOrReplaceChild("cube_outline_r5", CubeListBuilder.create().texOffs(14, 53).addBox(-10.2742F, -1.0F, 7.6705F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F).extend(-1.0856F, -1.9F, -4.11F)), PartPose.offsetAndRotation(8.384F, 1.1281F, -0.01F, 1.5708F, 0.0F, -0.48F));

        PartDefinition cube_r5 = dian.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(60, 50).addBox(-1.918F, -0.5F, -0.5103F, 0.5F, 1.0F, 4.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.4021F, -2.2878F, 0.0F, 1.5708F, 0.0F, -0.48F));

        PartDefinition cube_outline_r6 = dian.addOrReplaceChild("cube_outline_r6", CubeListBuilder.create().texOffs(62, 13).addBox(-0.4125F, -1.0019F, 1.3201F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F).extend(-1.118F, -1.9F, -3.0053F)), PartPose.offsetAndRotation(-2.8189F, -1.8549F, -0.008F, 1.5708F, 0.0F, -0.7854F));

        PartDefinition cube_outline_r7 = dian.addOrReplaceChild("cube_outline_r7", CubeListBuilder.create().texOffs(62, 18).addBox(0.4201F, -1.0001F, -0.5951F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(-1.7F, -1.9F, -1.9F)), PartPose.offsetAndRotation(-2.8362F, -1.8517F, -0.0099F, 1.5708F, 0.0F, -0.7854F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 53).addBox(-2.5667F, -2.5F, -2.5F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F).extend(-1.65F, -4.8F, -4.8F))
                .texOffs(40, 55).addBox(-2.6167F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(7.6167F, 0.0F, 0.0F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(24, 48).addBox(-0.325F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(34, 27).addBox(-0.825F, -3.5F, -3.5F, 3.0F, 7.0F, 7.0F, new CubeDeformation(0.0F).extend(-2.9F, -6.9F, -6.9F))
                .texOffs(54, 27).addBox(1.325F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0001F)), PartPose.offset(-0.1917F, 0.0F, 0.0F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create(), PartPose.offset(-0.2455F, -0.8291F, -0.0501F));

        PartDefinition bone = jin.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(52, 41).addBox(-2.5F, -1.5F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(40, 48).addBox(-3.0F, -1.45F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F).extend(-4.8F, -2.15F, -4.8F)), PartPose.offset(-0.2545F, -8.1709F, 0.0501F));

        PartDefinition jin1 = jin.addOrReplaceChild("jin1", CubeListBuilder.create().texOffs(32, 60).addBox(-1.7545F, -8.1709F, -0.9499F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = jin1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(14, 59).addBox(-1.4371F, 2.3655F, -0.9499F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.75F, -7.75F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone2 = jin1.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(62, 59).addBox(-0.9331F, -0.3885F, -0.9901F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.8214F, -6.7824F, 0.0402F));

        PartDefinition leave5 = all.addOrReplaceChild("leave5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2943F, -0.0783F, 0.4009F, 0.0F, 0.7854F, 0.0F));

        PartDefinition leave1 = leave5.addOrReplaceChild("leave1", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r7 = leave1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(38, 10).addBox(-2.4082F, 7.864F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r8 = leave1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 34).addBox(7.7096F, 2.2198F, -4.9499F, 5.0F, 0.0F, 9.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.113F, -8.4618F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition leave2 = leave5.addOrReplaceChild("leave2", CubeListBuilder.create(), PartPose.offset(-0.8F, 0.5F, -0.25F));

        PartDefinition cube_r9 = leave2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 43).addBox(-2.4309F, 7.5642F, -2.5069F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r10 = leave2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(38, 0).addBox(-3.4309F, 2.7938F, -12.1885F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.4226F, -0.9063F, 0.5672F, 0.0F, 0.0F));

        PartDefinition leave3 = leave5.addOrReplaceChild("leave3", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r11 = leave3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(28, 41).addBox(-0.5545F, 6.8196F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r12 = leave3.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(34, 18).addBox(-10.8425F, 3.5446F, -4.9499F, 5.0F, 0.0F, 9.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.866F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition leave4 = leave5.addOrReplaceChild("leave4", CubeListBuilder.create(), PartPose.offset(0.2F, 0.5F, -0.25F));

        PartDefinition cube_r13 = leave4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(38, 5).addBox(-4.4309F, 2.0957F, 7.484F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.4226F, 0.9063F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r14 = leave4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 48).addBox(-3.4309F, 7.6066F, -2.4023F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(ElectricPeashooter entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, ElectricPeashooterModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, ElectricPeashooterModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, ElectricPeashooterModelAnimation.shoot, ageInTicks, 1.0F);

        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}