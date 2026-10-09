package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.PrimalPeashooter;
import keletu.pvzmod.models.anim.PrimalPeaModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PrimalPeashooterModel extends HierarchicalModel<PrimalPeashooter> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/primal_peashooter.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart group;
    private final ModelPart face;
    private final ModelPart eye;
    private final ModelPart ye;
    private final ModelPart group2;
    private final ModelPart group4;
    private final ModelPart group3;
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

    public PrimalPeashooterModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.group = this.head.getChild("group");
        this.face = this.head.getChild("face");
        this.eye = this.head.getChild("eye");
        this.ye = this.head.getChild("ye");
        this.group2 = this.ye.getChild("group2");
        this.group4 = this.group2.getChild("group4");
        this.group3 = this.group2.getChild("group3");
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

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(61, 63).mirror().addBox(0.0F, -1.5F, -3.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.6F, 3.275F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition group = head.addOrReplaceChild("group", CubeListBuilder.create().texOffs(67, 0).addBox(-3.4861F, 0.6816F, -4.0F, 9.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5139F, -4.6816F, 0.0F));

        PartDefinition cube_r2 = group.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(67, 47).addBox(0.0F, -4.0F, -3.0F, 3.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

        PartDefinition cube_r3 = group.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(67, 40).mirror().addBox(-3.5F, -2.5F, -4.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(67, 28).addBox(-3.5F, -2.0F, -6.4F, 7.0F, 4.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(1.9499F, 0.1496F, 2.4F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r4 = group.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(73, 23).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9154F, 6.2816F, 4.2595F, 0.0F, 0.1745F, -0.3491F));

        PartDefinition cube_r5 = group.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(67, 23).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6512F, 6.7816F, -4.256F, 0.0F, -0.1745F, -0.3491F));

        PartDefinition cube_r6 = group.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(73, 19).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9604F, 5.1816F, 4.3416F, 0.0F, 0.2618F, -0.1745F));

        PartDefinition cube_r7 = group.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(67, 19).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9604F, 5.1816F, -4.3416F, 0.0F, -0.2618F, -0.1745F));

        PartDefinition cube_r8 = group.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(62, 16).addBox(-2.0F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5139F, 9.0414F, 4.633F, 0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r9 = group.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(67, 16).addBox(-2.0F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5139F, 9.041F, -4.6339F, -0.4363F, 0.0F, 0.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(67, 59).addBox(-1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(67, 64).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(3.5F, 1.5F, 0.0F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(83, 18).addBox(-1.75F, -1.25F, -4.0438F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(83, 17).addBox(-0.75F, -0.75F, -4.0688F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(83, 17).addBox(-0.75F, -0.75F, 4.0812F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(83, 18).addBox(-1.75F, -1.25F, 4.0313F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -1.75F, -0.0063F));

        PartDefinition ye = head.addOrReplaceChild("ye", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.9063F, -0.0486F, -0.0059F, 1.5708F, 1.0036F, 0.7418F));

        PartDefinition cube_r10 = ye.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 12).addBox(1.4626F, -1.0F, -1.0504F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4197F, -3.7981F, 0.5601F, 0.0F, 0.0F, 1.0036F));

        PartDefinition group2 = ye.addOrReplaceChild("group2", CubeListBuilder.create(), PartPose.offset(-1.7317F, -2.7181F, 0.5413F));

        PartDefinition group4 = group2.addOrReplaceChild("group4", CubeListBuilder.create(), PartPose.offset(0.0968F, 0.1519F, -0.0442F));

        PartDefinition cube_r11 = group4.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5374F, -1.0F, -0.5004F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7848F, -1.2319F, 0.0629F, 0.0F, -0.3054F, 1.0036F));

        PartDefinition cube_r12 = group4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 2).addBox(-3.0671F, -3.5F, -5.083F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5341F, -0.5469F, -0.0746F, 0.0F, -0.3927F, 1.0036F));

        PartDefinition cube_r13 = group4.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, -3).addBox(-0.2142F, -3.5F, -3.7021F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5341F, -0.5469F, -0.0746F, 0.0F, 0.5236F, 1.0036F));

        PartDefinition group3 = group2.addOrReplaceChild("group3", CubeListBuilder.create(), PartPose.offset(0.0968F, 0.1519F, -0.0441F));

        PartDefinition cube_r14 = group3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(5, 10).addBox(-0.5374F, -1.0F, 0.4996F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7848F, -1.2319F, 0.0629F, 0.0F, 0.3927F, 1.0036F));

        PartDefinition cube_r15 = group3.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(7, 2).addBox(-0.2147F, -3.5F, 0.7014F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5035F, -0.4989F, 0.2306F, 0.0F, -0.5236F, 1.0036F));

        PartDefinition cube_r16 = group3.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(7, -3).addBox(-3.0672F, -3.5F, 2.0829F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5035F, -0.4989F, 0.2306F, 0.0F, 0.3927F, 1.0036F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 16).addBox(-2.6167F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(7.6167F, 0.0F, 0.0F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(0, 24).mirror().addBox(-0.325F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.1F)).mirror(false)
                .texOffs(13, -6).addBox(1.325F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1917F, 0.0F, 0.0F));

        PartDefinition cube_r17 = mouth1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(-3, 36).addBox(-1.0F, 0.0F, -2.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.575F, 3.4F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r18 = mouth1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(-5, 36).addBox(-1.0F, 0.0F, -2.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.475F, -3.35F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create(), PartPose.offset(-0.2455F, -0.8291F, -0.0501F));

        PartDefinition bone = jin.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(13, 6).addBox(-3.5F, -1.5F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2545F, -8.1709F, 0.0501F));

        PartDefinition cube_r19 = bone.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(13, 18).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -3.2F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r20 = bone.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(13, 16).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 3.2F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r21 = bone.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(13, 8).addBox(0.0F, -1.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r22 = bone.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(19, 8).addBox(0.0F, -1.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition jin1 = jin.addOrReplaceChild("jin1", CubeListBuilder.create().texOffs(16, 27).addBox(-2.5045F, -8.6709F, -1.4499F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 0.5F, 0.0F));

        PartDefinition cube_r23 = jin1.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(16, 33).addBox(-1.9371F, 2.3655F, -1.4499F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0075F, -8.3368F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone2 = jin1.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r24 = bone2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(12, 20).addBox(-1.872F, 4.6993F, -1.4499F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.7602F, -6.7572F, 0.041F, 0.0F, 0.0F, -0.1745F));

        PartDefinition leave5 = all.addOrReplaceChild("leave5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2943F, -0.0783F, 0.4009F, 0.0F, 0.7854F, 0.0F));

        PartDefinition leave1 = leave5.addOrReplaceChild("leave1", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.05F));

        PartDefinition cube_r25 = leave1.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(47, 2).mirror().addBox(-2.4082F, 5.864F, -0.4499F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(29, 35).addBox(-2.4082F, 7.864F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r26 = leave1.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(29, 28).addBox(7.7096F, 2.2198F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.113F, -8.4618F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition leave2 = leave5.addOrReplaceChild("leave2", CubeListBuilder.create(), PartPose.offset(-0.8F, 0.5F, -0.45F));

        PartDefinition cube_r27 = leave2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(41, -4).addBox(1.0691F, 5.5642F, -3.5069F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(11, 52).addBox(-2.4309F, 7.5642F, -2.5069F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r28 = leave2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(11, 57).addBox(-2.4309F, 2.7938F, -12.1885F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, -0.9063F, 0.5672F, 0.0F, 0.0F));

        PartDefinition leave3 = leave5.addOrReplaceChild("leave3", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.05F));

        PartDefinition cube_r29 = leave3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(41, 2).mirror().addBox(-1.5545F, 4.8196F, -0.4499F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(29, 49).addBox(-0.5545F, 6.8196F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r30 = leave3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(29, 42).addBox(-10.8425F, 3.5446F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.866F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition leave4 = leave5.addOrReplaceChild("leave4", CubeListBuilder.create(), PartPose.offset(0.2F, 0.5F, -0.45F));

        PartDefinition cube_r31 = leave4.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(11, 42).addBox(-3.4309F, 2.0957F, 7.484F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, 0.9063F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r32 = leave4.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(47, -4).addBox(0.0691F, 5.6066F, -2.4023F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(11, 46).addBox(-3.4309F, 7.6066F, -2.4023F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(PrimalPeashooter entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, PrimalPeaModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, PrimalPeaModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, PrimalPeaModelAnimation.shoot, ageInTicks, 1.0F);

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