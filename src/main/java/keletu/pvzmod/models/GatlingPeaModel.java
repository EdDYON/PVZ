package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.GatlingPea;
import keletu.pvzmod.models.anim.GatlingPeaModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class GatlingPeaModel extends HierarchicalModel<GatlingPea> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/gatling_pea_new.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart face;
    private final ModelPart eye;
    private final ModelPart mouth;
    private final ModelPart mouth1;
    private final ModelPart qiang;
    private final ModelPart hat;
    private final ModelPart jin;
    private final ModelPart bone;
    private final ModelPart jin1;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart leave5;
    private final ModelPart leave1;
    private final ModelPart leave2;
    private final ModelPart leave3;
    private final ModelPart leave4;

    public GatlingPeaModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.face = this.head.getChild("face");
        this.eye = this.head.getChild("eye");
        this.mouth = this.head.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
        this.qiang = this.mouth.getChild("qiang");
        this.hat = this.head.getChild("hat");
        this.jin = this.all.getChild("jin");
        this.bone = this.jin.getChild("bone");
        this.jin1 = this.jin.getChild("jin1");
        this.bone2 = this.jin1.getChild("bone2");
        this.bone3 = this.jin1.getChild("bone3");
        this.bone4 = this.jin1.getChild("bone4");
        this.bone5 = this.jin1.getChild("bone5");
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

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(91, 0).addBox(-4.0F, -4.0F, -4.0F, 9.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(72, 17).addBox(0.0141F, 0.2667F, -4.0009F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(72, 17).addBox(0.0141F, 0.2667F, 4.0241F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(40, 70).addBox(-1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(40, 75).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(3.5F, 1.5F, 0.0F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(72, 17).addBox(-1.75F, -1.25F, -4.0438F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(72, 16).addBox(-0.25F, -0.75F, -4.0688F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(72, 16).addBox(-0.25F, -0.75F, 4.0812F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(72, 17).addBox(-1.75F, -1.25F, 4.0313F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(1.75F, -1.75F, -0.0063F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(96, 28).addBox(-3.141F, -2.0042F, -1.9998F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.141F, 0.0042F, -0.0002F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(94, 16).mirror().addBox(-0.325F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.1F)).mirror(false)
                .texOffs(72, 14).addBox(1.325F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.716F, -0.0042F, 0.0002F));

        PartDefinition qiang = mouth.addOrReplaceChild("qiang", CubeListBuilder.create(), PartPose.offset(1.5731F, 0.0126F, -0.0007F));

        PartDefinition cube_r1 = qiang.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(72, 38).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, 1.0035F, 0.0F, -2.3562F, 0.0F, 0.0F));

        PartDefinition cube_r2 = qiang.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(72, 30).mirror().addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0036F, 0.0F, -2.3562F, 0.0F, 0.0F));

        PartDefinition cube_r3 = qiang.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(72, 34).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.3536F, -1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r4 = qiang.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(72, 26).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.3536F, -1.5708F, 0.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(89, 78).addBox(4.7722F, -0.0018F, -2.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(84, 54).addBox(-0.7278F, -1.0018F, -4.5F, 6.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0641F, -2.1333F, -0.0009F));

        PartDefinition cube_r5 = hat.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(84, 67).addBox(-0.75F, -1.5F, -5.0F, 8.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(80, 8).addBox(2.4F, -5.75F, 1.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(29, 69).addBox(1.4F, -5.75F, -1.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.002F))
                .texOffs(38, 86).addBox(2.4F, -3.25F, 2.65F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.125F))
                .texOffs(38, 89).addBox(2.4F, -3.25F, -4.775F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.125F))
                .texOffs(80, 8).addBox(4.5F, -3.25F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(80, 8).addBox(4.5F, -3.25F, 1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 80).addBox(4.5F, -4.25F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.001F))
                .texOffs(0, 78).addBox(-3.375F, -3.5842F, -5.0F, 5.0F, 3.0F, 10.0F, new CubeDeformation(-0.25F))
                .texOffs(58, 64).addBox(-7.75F, 0.5F, -5.5F, 2.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(51, 78).mirror().addBox(-5.75F, -1.5F, -5.5F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(44, 94).addBox(-5.5F, -1.25F, 4.35F, 5.0F, 5.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(50, 100).addBox(-5.75F, -1.5F, -5.5F, 5.0F, 5.0F, 1.0F, new CubeDeformation(-0.25F))
                .texOffs(85, 36).addBox(-6.75F, -5.5F, -4.5F, 12.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r6 = hat.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 98).mirror().addBox(-2.25F, -6.875F, -5.0F, 3.0F, 5.0F, 10.0F, new CubeDeformation(-0.2501F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r7 = hat.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(80, 8).addBox(-1.0F, -1.0F, -0.5F, 2.25F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6654F, -5.0888F, 2.6242F, 0.0F, 0.4363F, -0.0873F));

        PartDefinition cube_r8 = hat.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(29, 79).addBox(-1.0F, -1.125F, -0.5F, 2.8284F, 2.25F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.0482F, -4.8219F, -1.8536F, 0.0F, 0.7854F, -0.0873F));

        PartDefinition cube_r9 = hat.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(29, 74).mirror().addBox(-0.5F, -1.125F, -1.5F, 1.0F, 2.25F, 2.8284F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(2.4004F, -4.8527F, 2.2071F, 0.0F, 0.7854F, -0.0873F));

        PartDefinition cube_r10 = hat.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 8).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2738F, -5.6067F, 2.5F, 0.0F, 0.7854F, -0.0873F));

        PartDefinition cube_r11 = hat.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(72, 8).addBox(-0.5F, -0.396F, -0.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.3149F, -5.7106F, -2.25F, 0.0F, -0.7854F, -0.0873F));

        PartDefinition cube_r12 = hat.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(80, 8).addBox(-1.5F, -1.0F, -1.5F, 2.25F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6959F, -5.0915F, -1.5066F, 0.0F, -0.4363F, -0.0873F));

        PartDefinition cube_r13 = hat.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(80, 8).addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4712F, -5.0718F, -2.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r14 = hat.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(80, 8).addBox(-0.5F, -1.0F, 0.5F, 1.0F, 1.774F, 0.9235F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6677F, -4.016F, -2.4053F, -0.48F, 0.0F, -0.0873F));

        PartDefinition cube_r15 = hat.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(80, 8).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 1.774F, 0.9235F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.708F, -3.556F, -2.5182F, -0.48F, 0.0F, -0.0873F));

        PartDefinition cube_r16 = hat.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(80, 8).addBox(-0.5F, -1.0F, -1.4235F, 1.0F, 1.774F, 0.9235F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.6677F, -4.016F, 2.4053F, 0.48F, 0.0F, -0.0873F));

        PartDefinition cube_r17 = hat.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(80, 8).mirror().addBox(-0.5F, -1.0F, -0.4235F, 1.0F, 1.774F, 0.9235F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.708F, -3.556F, 2.5182F, 0.48F, 0.0F, -0.0873F));

        PartDefinition cube_r18 = hat.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(72, 8).addBox(-0.75F, -0.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.3248F, -0.7629F, 5.1F, 0.0F, 0.0F, -0.8727F));

        PartDefinition cube_r19 = hat.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(80, 8).mirror().addBox(-0.5F, -0.5F, 9.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(80, 8).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.55F, 2.5807F, -5.1F, 0.0F, 0.0F, 2.2689F));

        PartDefinition cube_r20 = hat.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(72, 8).mirror().addBox(0.0F, -0.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.3248F, -0.9629F, -5.1F, 0.0F, 0.0F, 2.2689F));

        PartDefinition cube_r21 = hat.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(72, 3).addBox(-1.0F, -0.925F, -3.125F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.25F))
                .texOffs(84, 85).addBox(-0.5F, -3.275F, -2.875F, 2.0F, 7.0F, 10.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.5736F, 5.4893F, -2.125F, 0.0F, 0.0F, -0.829F));

        PartDefinition cube_r22 = hat.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 92).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2479F, 2.3172F, 6.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition cube_r23 = hat.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 95).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3095F, 1.6128F, -6.0F, 0.0F, 0.0F, 0.6981F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create(), PartPose.offset(-0.2455F, -0.8291F, -0.0501F));

        PartDefinition bone = jin.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(44, 0).addBox(-1.8309F, -1.4361F, -1.9499F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.9237F, -8.2348F, 0.0F));

        PartDefinition jin1 = jin.addOrReplaceChild("jin1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0045F, -8.6709F, -0.9499F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.25F, 0.5F, 0.0F));

        PartDefinition cube_r24 = jin1.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 5).addBox(-1.4371F, 2.3655F, -0.9499F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, -8.25F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone2 = jin1.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r25 = bone2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(73, 42).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone3 = jin1.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r26 = bone3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(73, 42).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone4 = jin1.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r27 = bone4.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(73, 42).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone5 = jin1.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r28 = bone5.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(73, 42).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition leave5 = all.addOrReplaceChild("leave5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2943F, -0.0783F, 0.4009F, 0.0F, 0.7854F, 0.0F));

        PartDefinition leave1 = leave5.addOrReplaceChild("leave1", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r29 = leave1.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(18, 8).addBox(-2.4082F, 7.864F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r30 = leave1.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(21, 1).addBox(7.7096F, 2.2198F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.113F, -8.4618F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition leave2 = leave5.addOrReplaceChild("leave2", CubeListBuilder.create(), PartPose.offset(-0.8F, 0.5F, -0.25F));

        PartDefinition cube_r31 = leave2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(19, 15).addBox(-2.4309F, 7.5642F, -2.5069F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r32 = leave2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(19, 20).addBox(-2.4309F, 2.7938F, -12.1885F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.4226F, -0.9063F, 0.5672F, 0.0F, 0.0F));

        PartDefinition leave3 = leave5.addOrReplaceChild("leave3", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r33 = leave3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(7, 20).addBox(-0.5545F, 6.8196F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r34 = leave3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(7, 13).addBox(-10.8425F, 3.5446F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.866F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition leave4 = leave5.addOrReplaceChild("leave4", CubeListBuilder.create(), PartPose.offset(0.2F, 0.5F, -0.25F));

        PartDefinition cube_r35 = leave4.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(-5, 13).addBox(-3.4309F, 2.0957F, 7.484F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.4226F, 0.9063F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r36 = leave4.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(-5, 18).addBox(-3.4309F, 7.6066F, -2.4023F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(GatlingPea entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, GatlingPeaModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, GatlingPeaModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, GatlingPeaModelAnimation.shoot, ageInTicks, 1.0F);

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