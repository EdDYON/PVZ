package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.SuperGatlingPea;
import keletu.pvzmod.models.anim.SGPAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SuperGatlingPeaModel extends HierarchicalModel<SuperGatlingPea> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/super_gatling_pea.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart face;
    private final ModelPart eye;
    private final ModelPart ye;
    private final ModelPart mouth;
    private final ModelPart mouth1;
    private final ModelPart qiang;
    private final ModelPart hat;
    private final ModelPart jin;
    private final ModelPart jin1;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone;
    private final ModelPart leave5;
    private final ModelPart leave1;
    private final ModelPart leave2;
    private final ModelPart leave3;
    private final ModelPart leave4;

    public SuperGatlingPeaModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.face = this.head.getChild("face");
        this.eye = this.head.getChild("eye");
        this.ye = this.head.getChild("ye");
        this.mouth = this.head.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
        this.qiang = this.mouth.getChild("qiang");
        this.hat = this.head.getChild("hat");
        this.jin = this.all.getChild("jin");
        this.jin1 = this.jin.getChild("jin1");
        this.bone2 = this.jin1.getChild("bone2");
        this.bone3 = this.jin1.getChild("bone3");
        this.bone4 = this.jin1.getChild("bone4");
        this.bone5 = this.jin1.getChild("bone5");
        this.bone = this.jin.getChild("bone");
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

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(42, 17).addBox(0.0141F, 1.4167F, 4.0241F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(0, 18).addBox(-4.0F, -4.0F, -4.0F, 9.0F, 8.0F, 8.0F, new CubeDeformation(0F))
                .texOffs(45, 17).addBox(0.0141F, 0.2667F, -4.0009F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(45, 17).addBox(0.0141F, 1.4167F, -4.0009F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(42, 17).addBox(0.0141F, 0.2667F, 4.0241F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(24, 46).addBox(-1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(98, 0).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(3.5F, 1.5F, 0.0F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(26, 67).addBox(-1.75F, -1.25F, -4.0438F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(34, 33).addBox(-0.25F, -0.75F, -4.0688F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(34, 33).addBox(-0.25F, -0.75F, 4.0812F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(26, 67).addBox(-1.75F, -1.25F, 4.0313F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(1.75F, -1.75F, -0.0063F));

        PartDefinition ye = head.addOrReplaceChild("ye", CubeListBuilder.create().texOffs(16, 53).addBox(-8.9064F, -1.6715F, -0.0079F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(64, 28).addBox(-7.9064F, 3.8285F, -1.5079F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(8, 65).addBox(-0.9144F, -1.684F, -1.5079F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(16, 53).addBox(-8.9064F, -1.6715F, -0.0079F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(-2.0186F, 1.2465F, 0.0079F));

        PartDefinition cube_r1 = ye.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 64).addBox(-1.5F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.6408F, 3.0224F, -0.0079F, 0.0F, 0.0F, -0.5672F));

        PartDefinition cube_r2 = ye.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 67).addBox(-4.0F, -1.0F, -1.5F, 0.0F, 2.5F, 3.0F, new CubeDeformation(0F))
                .texOffs(16, 61).addBox(-4.0F, 1.5F, -1.5F, 6.0F, 0.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(16, 53).addBox(-4.5F, -4.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.7185F, 1.4928F, 4.3421F, 0.0879F, 0.151F, -1.0405F));

        PartDefinition cube_r3 = ye.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 67).addBox(-4.0F, -1.0F, -1.5F, 0.0F, 2.5F, 3.0F, new CubeDeformation(0.0001F))
                .texOffs(16, 61).addBox(-4.0F, 1.5F, -1.5F, 6.0F, 0.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(16, 53).addBox(-4.5F, -4.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.7185F, 1.4928F, -4.3079F, -0.0879F, -0.151F, -1.0405F));

        PartDefinition cube_r4 = ye.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 63).addBox(-3.375F, -2.0612F, 0.0F, 7.0F, 5.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(14, 67).addBox(2.1441F, -2.0392F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4437F, 0.7934F, 1.9921F, 0.0F, 0.1745F, -0.1745F));

        PartDefinition cube_r5 = ye.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 17).addBox(-2.8848F, 2.0236F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4437F, 0.7934F, 1.9921F, 0.139F, 0.1059F, 0.7491F));

        PartDefinition cube_r6 = ye.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(64, 28).addBox(-1.9737F, 1.2006F, -1.5F, 3.75F, 0.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4437F, 0.7934F, 1.9921F, -0.101F, 0.1426F, -0.794F));

        PartDefinition cube_r7 = ye.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 67).addBox(2.1441F, -2.0392F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(60, 63).addBox(-3.375F, -2.0612F, 0.0F, 7.0F, 5.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4437F, 0.7934F, -2.0079F, 0.0F, -0.1745F, -0.1745F));

        PartDefinition cube_r8 = ye.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(64, 28).addBox(-1.9737F, 1.2006F, -1.5F, 3.75F, 0.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4437F, 0.7934F, -2.0079F, 0.101F, -0.1426F, -0.794F));

        PartDefinition cube_r9 = ye.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(64, 17).addBox(-2.8848F, 2.0236F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.4437F, 0.7934F, -2.0079F, -0.139F, -0.1059F, 0.7491F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(34, 61).addBox(-2.6167F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(7.6167F, 0.0F, 0.0F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.325F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(58, 45).addBox(1.325F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0F)), PartPose.offset(-0.1917F, 0.0F, 0.0F));

        PartDefinition qiang = mouth.addOrReplaceChild("qiang", CubeListBuilder.create(), PartPose.offset(2.0974F, 0.0167F, -0.0009F));

        PartDefinition cube_r10 = qiang.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(9, 113).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 1.0035F, 0.0F, -2.3562F, 0.0F, 0.1309F));

        PartDefinition cube_r11 = qiang.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(9, 109).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, -1.0036F, 0.0F, -2.3562F, 0.0F, -0.1309F));

        PartDefinition cube_r12 = qiang.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(9, 109).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.3536F, -1.5708F, 0.1309F, 0.0F));

        PartDefinition cube_r13 = qiang.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(9, 101).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.3536F, -1.5708F, -0.1309F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5574F, -4.3774F, -4.0919F, 12.0F, 9.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(34, 18).addBox(-5.3074F, -0.3774F, -5.0919F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(0, 34).addBox(0.4426F, -0.3774F, -5.0919F, 7.0F, 1.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(45, 101).addBox(0.4926F, -0.3774F, -5.5919F, 7.0F, 1.0F, 12.0F, new CubeDeformation(0.1F))
                .texOffs(36, 33).addBox(-7.5574F, 3.7826F, -5.0919F, 4.8F, 1.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(85, 75).addBox(-8.0139F, 3.8479F, -5.5919F, 5.05F, 1.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.093F, -3.2724F, -0.409F, 0.0F, 0.0F, -0.1745F));

        PartDefinition star4_r1 = hat.addOrReplaceChild("star4_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.1F, -240.5016F, 0.4F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-1.907F, -4.796F, 2.509F, -1.5708F, 0.4363F, 0.0F));

        PartDefinition star6_r1 = hat.addOrReplaceChild("star6_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.8375F, -241.0984F, -6.5F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(0.9305F, -1.4993F, 11.409F, 0.0F, 0.0F, -0.192F));

        PartDefinition star5_r1 = hat.addOrReplaceChild("star5_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-239.8375F, -240.8284F, -5.5F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-4.0695F, -1.4993F, 10.409F, 0.0F, 0.0F, -0.192F));

        PartDefinition star3_r1 = hat.addOrReplaceChild("star3_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-239.9F, -240.5016F, 0.4F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-1.907F, -4.796F, -2.491F, -1.5708F, -0.1745F, 0.0F));

        PartDefinition star1_r1 = hat.addOrReplaceChild("star1_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.0283F, -239.8467F, -0.0011F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-4.0695F, -2.4993F, -4.0919F, 0.0F, 0.0F, -0.192F));

        PartDefinition star2_r1 = hat.addOrReplaceChild("star2_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.25F, -240.2F, -0.002F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(0.343F, -2.2276F, -4.091F, 0.0F, 0.0F, -0.192F));

        PartDefinition cube_r14 = hat.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(1, 34).addBox(-3.837F, -1.1036F, -3.1828F, 5.8483F, 1.0187F, 11.0F, new CubeDeformation(0F))
                .texOffs(84, 101).addBox(-4.0488F, -1.1081F, -3.6828F, 6.0F, 1.0644F, 12.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-0.1025F, 1.851F, -1.9091F, 0.0F, 0.0F, -0.829F));

        PartDefinition cube_r15 = hat.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(74, 30).addBox(-2.9503F, 1.8971F, -2.9328F, 3.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offsetAndRotation(-0.1025F, 2.151F, -2.1591F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r16 = hat.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(66, 72).addBox(-2.4503F, 3.2745F, -1.2401F, 2.0F, 4.0F, 1.0F, new CubeDeformation(-0.1001F)), PartPose.offsetAndRotation(-0.1025F, 1.851F, -2.1591F, -0.48F, 0.0F, -0.3054F));

        PartDefinition cube_r17 = hat.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(38, 74).addBox(-3.0753F, 5.8221F, -0.6828F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.25F))
                .texOffs(94, 12).addBox(-2.4503F, -0.723F, -2.6828F, 2.0F, 8.0F, 10.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.1025F, 1.851F, -1.9091F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r18 = hat.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(23, 70).addBox(6.2406F, 3.5056F, -2.7692F, 0.0F, 1.25F, 3.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-2.9525F, 1.851F, -1.9091F, 0.6545F, 0.0F, -1.5708F));

        PartDefinition cube_r19 = hat.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(23, 70).addBox(6.2406F, 0.6843F, 3.4459F, 0.0F, 1.25F, 3.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-2.9525F, 1.851F, -1.9091F, -0.6545F, 0.0F, -1.5708F));

        PartDefinition cube_r20 = hat.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(23, 70).addBox(6.2406F, 3.5056F, -2.9692F, 0.0F, 1.2F, 3.2F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.2011F, 1.851F, -1.7917F, 0.6109F, 0.0F, -1.5708F));

        PartDefinition cube_r21 = hat.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(23, 70).addBox(0.0F, -0.625F, -1.5F, 0.0F, 1.2F, 3.2F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(3.9105F, -4.3896F, 1.2784F, -0.6109F, 0.0F, -1.5708F));

        PartDefinition cube_r22 = hat.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(23, 70).addBox(0.0F, -0.5F, -1.75F, 0.0F, 1.0F, 3.25F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(5.5107F, -2.8049F, 1.5915F, -0.5847F, 0.0F, 0.0F));

        PartDefinition cube_r23 = hat.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(25, 70).addBox(5.6132F, -3.3803F, 2.2645F, 0.0F, 1.0F, 3.25F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.1025F, 1.6747F, -2.3247F, 0.5847F, 0.0F, 0.0F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create(), PartPose.offset(-0.2455F, -0.8291F, -0.0501F));

        PartDefinition jin1 = jin.addOrReplaceChild("jin1", CubeListBuilder.create().texOffs(0, 65).addBox(-2.0045F, -8.6709F, -0.9499F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(0.25F, 0.5F, 0.0F));

        PartDefinition cube_r24 = jin1.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(64, 20).addBox(-1.4371F, 2.3655F, -0.9499F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, -8.25F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone2 = jin1.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r25 = bone2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(66, 12).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone3 = jin1.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r26 = bone3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(66, 12).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone4 = jin1.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r27 = bone4.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(66, 12).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone5 = jin1.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-0.5443F, -2.8827F, -0.042F));

        PartDefinition cube_r28 = bone5.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(66, 12).addBox(-1.4371F, 4.3655F, -0.9499F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.4557F, -5.3673F, 0.042F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone = jin.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(58, 57).addBox(-2.5F, -1.5F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(-0.2545F, -8.1709F, 0.0501F));

        PartDefinition leave5 = all.addOrReplaceChild("leave5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2943F, -0.0783F, 0.4009F, 0.0F, 0.7854F, 0.0F));

        PartDefinition leave1 = leave5.addOrReplaceChild("leave1", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r29 = leave1.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(78, 8).addBox(-2.4082F, 7.864F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r30 = leave1.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 46).addBox(7.7096F, 2.2198F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.113F, -8.4618F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition leave2 = leave5.addOrReplaceChild("leave2", CubeListBuilder.create(), PartPose.offset(-0.8F, 0.5F, -0.25F));

        PartDefinition cube_r31 = leave2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(58, 0).addBox(-2.4309F, 7.5642F, -2.5069F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r32 = leave2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(42, 12).addBox(-2.4309F, 2.7938F, -12.1885F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, -0.9063F, 0.5672F, 0.0F, 0.0F));

        PartDefinition leave3 = leave5.addOrReplaceChild("leave3", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r33 = leave3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(90, 61).addBox(-0.5545F, 6.8196F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r34 = leave3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(-4, 78).addBox(-10.8425F, 3.5446F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.866F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition leave4 = leave5.addOrReplaceChild("leave4", CubeListBuilder.create(), PartPose.offset(0.2F, 0.5F, -0.25F));

        PartDefinition cube_r35 = leave4.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(70, 48).addBox(-3.4309F, 2.0957F, 7.484F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, 0.9063F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r36 = leave4.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(92, 42).addBox(-3.4309F, 7.6066F, -2.4023F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 1600, 1600);
    }

    @Override
    public void setupAnim(SuperGatlingPea entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, SGPAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, SGPAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, SGPAnimation.shoot, ageInTicks, 1.0F);
        this.animate(entity.superAnimation, SGPAnimation.power2, ageInTicks, 1.0F);

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