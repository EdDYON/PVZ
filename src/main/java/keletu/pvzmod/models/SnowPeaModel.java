package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.SnowPea;
import keletu.pvzmod.models.anim.SnowPeaModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SnowPeaModel extends HierarchicalModel<SnowPea> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/snowpea_new.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart group;
    private final ModelPart eye;
    private final ModelPart mouth;
    private final ModelPart mouth1;
    private final ModelPart face;
    private final ModelPart jin;
    private final ModelPart bone;
    private final ModelPart jin1;
    private final ModelPart bone2;
    private final ModelPart leave5;
    private final ModelPart leave1;
    private final ModelPart leave2;
    private final ModelPart leave3;
    private final ModelPart leave4;

    public SnowPeaModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.group = this.head.getChild("group");
        this.eye = this.head.getChild("eye");
        this.mouth = this.head.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
        this.face = this.head.getChild("face");
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

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 9.0F, 8.0F, 8.0F, new CubeDeformation(0F)), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition group = head.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -1.0F));

        PartDefinition cube_r1 = group.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(57, 41).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F).extend(0.2081F, 0.2081F, 0.001F)), PartPose.offsetAndRotation(-2.5328F, 2.9368F, 0.9991F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r2 = group.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(34, 16).addBox(-3.0F, -1.5F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.4301F, 2.2507F, 0.9991F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r3 = group.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(45, 34).addBox(-0.5F, -1.0F, -1.05F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1047F, 3.5872F, 3.2491F, -0.195F, 0.1758F, -0.8463F));

        PartDefinition cube_r4 = group.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(45, 30).addBox(-1.0607F, -0.3536F, -1.05F, 1.4142F, 1.4142F, 2.0F, new CubeDeformation(0.0F).extend(0F, 0F, 0.001F)), PartPose.offsetAndRotation(-0.1047F, 3.5872F, 3.2491F, -0.0117F, 0.2615F, -0.0452F));

        PartDefinition cube_r5 = group.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(45, 38).addBox(-1.0607F, -0.3536F, -1.05F, 1.4142F, 1.4142F, 2.0F, new CubeDeformation(0.0F).extend(0.001F, 0.001F, 0.002F)), PartPose.offsetAndRotation(-0.1047F, 3.5872F, -1.2509F, 0.0138F, -0.3051F, -0.0457F));

        PartDefinition cube_r6 = group.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(53, 34).addBox(-0.5F, -1.0F, -1.05F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.1047F, 3.5872F, -1.2509F, 0.2284F, -0.2046F, -0.8526F));

        PartDefinition cube_r7 = group.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(54, 15).addBox(-1.2374F, -1.2374F, -0.5F, 1.4142F, 1.4142F, 1.0F, new CubeDeformation(0.0F).extend(0F, 0F, 0.001F)), PartPose.offsetAndRotation(-0.9787F, -3.5614F, 0.9991F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r8 = group.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(45, 27).addBox(-0.75F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.9787F, -3.5614F, 0.9991F, 0.0F, 0.0F, 1.0472F));

        PartDefinition cube_r9 = group.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 45).addBox(-1.8232F, -1.8232F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.06F, 0.06F, 0.001F)), PartPose.offsetAndRotation(-2.8651F, 0.5013F, 4.0278F, -0.2978F, 0.1848F, -1.0314F));

        PartDefinition cube_r10 = group.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(34, 16).addBox(-1.25F, -1.5F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.7862F, 0.4837F, 3.9991F, -0.0786F, 0.3405F, -0.2317F));

        PartDefinition cube_r11 = group.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(45, 42).addBox(-1.8232F, -1.8232F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.06F, 0.06F, 0.001F)), PartPose.offsetAndRotation(-2.8638F, 0.5013F, -2.033F, 0.3361F, -0.2071F, -1.0388F));

        PartDefinition cube_r12 = group.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(42, 59).addBox(-1.25F, -1.5F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.7862F, 0.4837F, -2.0009F, 0.0894F, -0.3829F, -0.2355F));

        PartDefinition cube_r13 = group.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(42, 53).addBox(-1.3845F, -1.4224F, -1.58F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6049F, -2.5095F, -0.0009F, -0.1332F, -0.2261F, 0.5387F));

        PartDefinition cube_r14 = group.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(45, 42).addBox(-1.9732F, -1.8634F, -1.579F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.06F, 0.06F, 0.001F)), PartPose.offsetAndRotation(-2.6758F, -2.5532F, -0.0215F, 0.0692F, -0.2527F, -0.2706F));

        PartDefinition cube_r15 = group.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(45, 47).addBox(-1.9732F, -1.8634F, -0.4204F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.06F, 0.06F, 0.001F)), PartPose.offsetAndRotation(-2.6762F, -2.5532F, 2.0182F, -0.0692F, 0.2527F, -0.2706F));

        PartDefinition cube_r16 = group.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(34, 16).addBox(-1.3845F, -1.4224F, -0.4204F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6049F, -2.5095F, 1.9991F, 0.1332F, 0.2261F, 0.5387F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(35, 3).addBox(-1.75F, -1.25F, -4.0438F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(35, 2).addBox(-0.25F, -0.75F, -4.0688F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(35, 2).addBox(-0.25F, -0.75F, 4.0812F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(35, 3).addBox(-1.75F, -1.25F, 4.0313F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(1.75F, -1.75F, -0.0063F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 28).mirror().addBox(-2.6167F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(7.6167F, 0.0F, 0.0F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(0, 16).addBox(-0.325F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(16, 10).addBox(1.325F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0F)), PartPose.offset(-0.1917F, 0.0F, 0.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(35, 23).addBox(-1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(35, 28).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(3.5F, 1.5F, 0.0F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create(), PartPose.offset(-0.2455F, -0.8291F, -0.0501F));

        PartDefinition bone = jin.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(15, 28).mirror().addBox(-2.5F, -1.5F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(-0.2545F, -8.1709F, 0.0501F));

        PartDefinition jin1 = jin.addOrReplaceChild("jin1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0045F, -8.6709F, -0.9499F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(0.25F, 0.5F, 0.0F));

        PartDefinition cube_r17 = jin1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 42).addBox(-1.4371F, 2.3655F, -0.9499F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, -8.25F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone2 = jin1.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(35, 33).addBox(-0.9331F, -0.3885F, -0.9901F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-1.0714F, -7.2824F, 0.0402F));

        PartDefinition leave5 = all.addOrReplaceChild("leave5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2943F, -0.0783F, 0.4009F, 0.0F, 0.7854F, 0.0F));

        PartDefinition leave1 = leave5.addOrReplaceChild("leave1", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r18 = leave1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(18, 45).addBox(-2.4082F, 7.864F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r19 = leave1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(21, 38).addBox(7.7096F, 2.2198F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.113F, -8.4618F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition leave2 = leave5.addOrReplaceChild("leave2", CubeListBuilder.create(), PartPose.offset(-0.8F, 0.5F, -0.25F));

        PartDefinition cube_r20 = leave2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(19, 52).addBox(-2.4309F, 7.5642F, -2.5069F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r21 = leave2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(19, 57).addBox(-2.4309F, 2.7938F, -12.1885F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, -0.9063F, 0.5672F, 0.0F, 0.0F));

        PartDefinition leave3 = leave5.addOrReplaceChild("leave3", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r22 = leave3.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(7, 57).addBox(-0.5545F, 6.8196F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r23 = leave3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(7, 50).addBox(-10.8425F, 3.5446F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.866F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition leave4 = leave5.addOrReplaceChild("leave4", CubeListBuilder.create(), PartPose.offset(0.2F, 0.5F, -0.25F));

        PartDefinition cube_r24 = leave4.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(-5, 50).addBox(-3.4309F, 2.0957F, 7.484F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, 0.9063F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r25 = leave4.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(-5, 55).addBox(-3.4309F, 7.6066F, -2.4023F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SnowPea entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, SnowPeaModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, SnowPeaModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, SnowPeaModelAnimation.shoot, ageInTicks, 1.0F);

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