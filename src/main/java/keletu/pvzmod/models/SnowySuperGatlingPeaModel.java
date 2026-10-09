package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.SuperSnowGatlingPea;
import keletu.pvzmod.models.anim.SGPAnimation;
import keletu.pvzmod.models.anim.SnowyGatlingPeaModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SnowySuperGatlingPeaModel extends HierarchicalModel<SuperSnowGatlingPea> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/super_snowy_gatling_pea.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart head;
    private final ModelPart eye;
    private final ModelPart mouth;
    private final ModelPart mouth1;
    private final ModelPart qiang;
    private final ModelPart ye;
    private final ModelPart hat;
    private final ModelPart snow;
    private final ModelPart face;
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

    public SnowySuperGatlingPeaModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.head = this.all.getChild("head");
        this.eye = this.head.getChild("eye");
        this.mouth = this.head.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
        this.qiang = this.mouth.getChild("qiang");
        this.ye = this.head.getChild("ye");
        this.hat = this.head.getChild("hat");
        this.snow = this.hat.getChild("snow");
        this.face = this.head.getChild("face");
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

        PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).mirror().addBox(-4.0F, -4.0F, -4.0F, 9.0F, 8.0F, 8.0F, new CubeDeformation(0F)).mirror(false)
                .texOffs(25, 66).addBox(0.0141F, 1.4167F, -4.0009F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(25, 66).addBox(0.0141F, 0.2667F, -4.0009F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(24, 76).addBox(0.0141F, 1.4167F, 4.0241F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(24, 76).addBox(0.0141F, 0.2667F, 4.0241F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(-1.0F, -14.0F, 0.0F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(26, 67).addBox(-1.75F, -1.25F, -4.0438F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(34, 33).addBox(-0.25F, -0.75F, -4.0688F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(34, 34).addBox(-0.25F, -0.75F, 4.0812F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(26, 62).addBox(-1.75F, -1.25F, 4.0313F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(1.75F, -1.75F, -0.0063F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(34, 61).addBox(-2.6167F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(7.6167F, 0.0F, 0.0F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(0, 53).addBox(-0.325F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(58, 45).addBox(1.325F, -3.0F, -3.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0F)), PartPose.offset(-0.1917F, 0.0F, 0.0F));

        PartDefinition qiang = mouth.addOrReplaceChild("qiang", CubeListBuilder.create(), PartPose.offset(2.0974F, 0.0167F, -0.0009F));

        PartDefinition cube_r1 = qiang.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 113).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0F, -0.25F, -0.25F)), PartPose.offsetAndRotation(0.0F, 1.0035F, 0.0F, -2.3562F, 0.0F, 0.1309F));

        PartDefinition cube_r2 = qiang.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(9, 101).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0F, -0.25F, -0.25F)), PartPose.offsetAndRotation(0.0F, -1.0036F, 0.0F, -2.3562F, 0.0F, -0.1309F));

        PartDefinition cube_r3 = qiang.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(9, 101).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0F, -0.25F, -0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.3536F, -1.5708F, 0.1309F, 0.0F));

        PartDefinition cube_r4 = qiang.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(9, 109).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0F, -0.25F, -0.25F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.3536F, -1.5708F, -0.1309F, 0.0F));

        PartDefinition ye = head.addOrReplaceChild("ye", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.8348F, 3.4827F, -0.0009F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r5 = ye.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(121, 35).addBox(-1.3839F, 0.3839F, -0.4991F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F).extend(0.2081F, 0.2081F, 0.001F)), PartPose.offsetAndRotation(-0.7302F, 1.314F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r6 = ye.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(116, 46).mirror().addBox(-1.25F, -1.0F, -0.4991F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0F)).mirror(false), PartPose.offsetAndRotation(-0.7302F, 1.314F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r7 = ye.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(120, 26).addBox(-1.211F, -0.6437F, -1.7263F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0F, 0F, -0.25F)), PartPose.offsetAndRotation(1.4665F, 0.6687F, 4.25F, -0.1261F, 0.4292F, -0.8255F));

        PartDefinition cube_r8 = ye.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(120, 34).addBox(-1.1044F, 0.6083F, -1.7263F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F).extend(0.2081F, 0.2081F, -0.249F)), PartPose.offsetAndRotation(1.4665F, 0.6687F, 4.25F, 0.2323F, 0.3845F, 0.0328F));

        PartDefinition cube_r9 = ye.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(114, 34).addBox(-0.6879F, -0.2311F, -0.6459F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F).extend(0.2081F, 0.2081F, -0.249F)), PartPose.offsetAndRotation(1.0048F, 1.5557F, -4.25F, -0.2347F, -0.4269F, 0.0438F));

        PartDefinition cube_r10 = ye.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(120, 30).mirror().addBox(-0.323F, -0.9428F, -0.6459F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0F, 0F, -0.25F)).mirror(false), PartPose.offsetAndRotation(1.0048F, 1.5557F, -4.25F, 0.1602F, -0.4584F, -0.8302F));

        PartDefinition cube_r11 = ye.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(120, 10).addBox(-1.8283F, -1.7966F, -1.3864F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.0617F, 0.0617F, 0.001F)), PartPose.offsetAndRotation(-1.2514F, -0.1991F, 2.7F, -0.2223F, 0.1395F, -1.0192F));

        PartDefinition cube_r12 = ye.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(114, 15).addBox(-1.1489F, -1.4776F, -1.3864F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.2514F, -0.1991F, 2.7F, -0.0579F, 0.2555F, -0.2256F));

        PartDefinition cube_r13 = ye.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(120, 37).addBox(-1.8283F, -1.7966F, -0.6136F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.0617F, 0.0617F, 0.001F)), PartPose.offsetAndRotation(-1.2514F, -0.1991F, -2.7F, 0.2223F, -0.1395F, -1.0192F));

        PartDefinition cube_r14 = ye.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(114, 41).addBox(-1.1489F, -1.4776F, -0.6136F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.2514F, -0.1991F, -2.7F, 0.0579F, -0.2555F, -0.2256F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5574F, -4.3774F, -4.0919F, 12.0F, 9.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(34, 18).addBox(-5.3074F, -0.3774F, -5.0919F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(34, 33).addBox(0.4426F, -0.3774F, -5.0919F, 7.0F, 1.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(45, 101).addBox(0.5926F, -0.3774F, -5.5919F, 7.0F, 1.0F, 12.0F, new CubeDeformation(0.0F).extend(0.2F, 0.1F, -0.15F))
                .texOffs(35, 33).addBox(-7.6574F, 3.6226F, -5.0919F, 5.0F, 1.0F, 11.0F, new CubeDeformation(0.0F).extend(-0.1F, 0F, 0F))
                .texOffs(85, 75).addBox(-7.7824F, 3.6226F, -5.5919F, 5.0F, 1.0F, 12.0F, new CubeDeformation(0.0F).extend(0.125F, 0.1F, -0.15F)), PartPose.offsetAndRotation(-0.093F, -3.2724F, -0.409F, 0.0F, 0.0F, -0.1745F));

        PartDefinition star5_r1 = hat.addOrReplaceChild("star5_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-239.8375F, -240.8284F, -5.5F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-4.0695F, -1.4993F, 10.409F, 0.0F, 0.0F, -0.192F));

        PartDefinition star6_r1 = hat.addOrReplaceChild("star6_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.8375F, -241.0984F, -6.5F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(0.9305F, -1.4993F, 11.409F, 0.0F, 0.0F, -0.192F));

        PartDefinition star4_r1 = hat.addOrReplaceChild("star4_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.1F, -240.5016F, 0.4F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-1.907F, -4.796F, 2.509F, -1.5708F, 0.4363F, 0.0F));

        PartDefinition star3_r1 = hat.addOrReplaceChild("star3_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-239.9F, -240.5016F, 0.4F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-1.907F, -4.796F, -2.491F, -1.5708F, -0.1745F, 0.0F));

        PartDefinition star2_r1 = hat.addOrReplaceChild("star2_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.25F, -240.2F, -0.002F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(0.343F, -2.2276F, -4.091F, 0.0F, 0.0F, -0.192F));

        PartDefinition star1_r1 = hat.addOrReplaceChild("star1_r1", CubeListBuilder.create().texOffs(141, 0).addBox(-240.0283F, -239.8467F, -0.0011F, 480.0F, 480.0F, 0.0F, new CubeDeformation(0.0F).extend(-238.2F, -238.2F, 0F)), PartPose.offsetAndRotation(-4.0695F, -2.4993F, -4.0919F, 0.0F, 0.0F, -0.192F));

        PartDefinition cube_r15 = hat.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(90, 101).addBox(-3.896F, -1.0758F, -3.6828F, 6.0F, 1.0F, 12.0F, new CubeDeformation(0.0F).extend(-0.0628F, 0.1222F, -0.16F))
                .texOffs(34, 33).addBox(-3.9129F, -1.0943F, -3.1828F, 6.0F, 1.0F, 11.0F, new CubeDeformation(0.0F).extend(-0.0759F, 0.0094F, 0F)), PartPose.offsetAndRotation(-0.1025F, 1.851F, -1.9091F, 0.0F, 0.0F, -0.829F));

        PartDefinition cube_r16 = hat.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(74, 30).addBox(-2.9503F, 1.8971F, -2.9328F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(-0.25F, 0F, -0.25F))
                .texOffs(38, 74).addBox(-2.9503F, 5.9471F, -0.6828F, 3.0F, 2.0F, 6.0F, new CubeDeformation(-0.25F))
                .texOffs(94, 12).addBox(-2.4503F, -0.4529F, -2.6828F, 2.0F, 8.0F, 10.0F, new CubeDeformation(0.0F).extend(0F, -0.25F, -0.25F)), PartPose.offsetAndRotation(-0.1025F, 1.851F, -1.9091F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r17 = hat.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(66, 72).addBox(-2.4503F, 3.2745F, -1.3651F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F).extend(0F, -0.25F, -0.125F)), PartPose.offsetAndRotation(-0.1025F, 1.851F, -1.9091F, -0.48F, 0.0F, -0.3054F));

        PartDefinition cube_r18 = hat.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(28, 67).addBox(6.2406F, 3.5056F, -2.7692F, 0.0F, 1.25F, 3.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-2.9525F, 1.851F, -1.9091F, 0.6545F, 0.0F, -1.5708F));

        PartDefinition cube_r19 = hat.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(28, 67).mirror().addBox(6.2406F, 0.6843F, 3.4459F, 0.0F, 1.25F, 3.0F, new CubeDeformation(0.0001F)).mirror(false), PartPose.offsetAndRotation(-2.9525F, 1.851F, -1.9091F, -0.6545F, 0.0F, -1.5708F));

        PartDefinition cube_r20 = hat.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(28, 67).addBox(6.2406F, 3.5056F, -2.9692F, 0.0F, 1.2F, 3.2F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.2011F, 1.851F, -1.7917F, 0.6109F, 0.0F, -1.5708F));

        PartDefinition cube_r21 = hat.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(28, 67).addBox(0.0F, -0.625F, -1.5F, 0.0F, 1.2F, 3.2F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(3.9105F, -4.3896F, 1.2784F, -0.6109F, 0.0F, -1.5708F));

        PartDefinition cube_r22 = hat.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(28, 67).addBox(0.0F, -0.5F, -1.75F, 0.0F, 1.0F, 3.25F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(5.5107F, -2.8049F, 1.5915F, -0.5847F, 0.0F, 0.0F));

        PartDefinition cube_r23 = hat.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(28, 67).addBox(5.6132F, -3.3803F, 2.2645F, 0.0F, 1.0F, 3.25F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.1025F, 1.6747F, -2.3247F, 0.5847F, 0.0F, 0.0F));

        PartDefinition snow = hat.addOrReplaceChild("snow", CubeListBuilder.create().texOffs(75, 58).mirror().addBox(2.6458F, -12.8872F, 2.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0F)).mirror(false)
                .texOffs(75, 58).addBox(5.6458F, -12.1872F, 2.6F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(74, 57).mirror().addBox(1.9458F, -13.1872F, 4.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0F)).mirror(false)
                .texOffs(75, 59).mirror().addBox(1.6458F, -11.8872F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(-7.7032F, 7.1097F, -3.0919F));

        PartDefinition cube_r24 = snow.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(108, 0).addBox(-0.5F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 1.5708F, 0.1309F));

        PartDefinition cube_r25 = snow.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(108, 0).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(108, 3).mirror().addBox(-1.0F, -3.0F, -2.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0F)).mirror(false), PartPose.offsetAndRotation(0.1958F, -1.4872F, 0.5F, 0.0F, 0.0F, 0.1309F));

        PartDefinition face = head.addOrReplaceChild("face", CubeListBuilder.create().texOffs(98, 0).addBox(-1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(98, 5).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(3.5F, 1.5F, 0.0F));

        PartDefinition jin = all.addOrReplaceChild("jin", CubeListBuilder.create(), PartPose.offset(-0.2455F, -0.8291F, -0.0501F));

        PartDefinition bone = jin.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(58, 57).addBox(-2.5F, -1.5F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(-0.2545F, -8.1709F, 0.0501F));

        PartDefinition jin1 = jin.addOrReplaceChild("jin1", CubeListBuilder.create().texOffs(0, 65).addBox(-2.0045F, -8.6709F, -0.9499F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(0.25F, 0.5F, 0.0F));

        PartDefinition cube_r26 = jin1.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(64, 20).addBox(-1.4371F, 2.3655F, -0.9499F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, -8.25F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition bone2 = jin1.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(66, 12).addBox(-0.9331F, -0.3885F, -0.9901F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-1.0714F, -7.2824F, 0.0402F));

        PartDefinition bone3 = jin1.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(66, 12).addBox(-0.9331F, -0.3885F, -0.9901F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-1.0714F, -7.2824F, 0.0402F));

        PartDefinition bone4 = jin1.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(66, 12).addBox(-0.9331F, -0.3885F, -0.9901F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-1.0714F, -7.2824F, 0.0402F));

        PartDefinition bone5 = jin1.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(66, 12).addBox(-0.9331F, -0.3885F, -0.9901F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-1.0714F, -7.2824F, 0.0402F));

        PartDefinition leave5 = all.addOrReplaceChild("leave5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.2943F, -0.0783F, 0.4009F, 0.0F, 0.7854F, 0.0F));

        PartDefinition leave1 = leave5.addOrReplaceChild("leave1", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r27 = leave1.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(78, 8).addBox(-2.4082F, 7.864F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r28 = leave1.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 46).addBox(7.7096F, 2.2198F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.113F, -8.4618F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition leave2 = leave5.addOrReplaceChild("leave2", CubeListBuilder.create(), PartPose.offset(-0.8F, 0.5F, -0.25F));

        PartDefinition cube_r29 = leave2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(58, 0).addBox(-2.4309F, 7.5642F, -2.5069F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r30 = leave2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(42, 12).addBox(-2.4309F, 2.7938F, -12.1885F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, -0.9063F, 0.5672F, 0.0F, 0.0F));

        PartDefinition leave3 = leave5.addOrReplaceChild("leave3", CubeListBuilder.create(), PartPose.offset(0.3F, -0.5F, 0.25F));

        PartDefinition cube_r31 = leave3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(90, 61).addBox(-0.5545F, 6.8196F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition cube_r32 = leave3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(-4, 78).addBox(-10.8425F, 3.5446F, -3.9499F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.866F, -8.5F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition leave4 = leave5.addOrReplaceChild("leave4", CubeListBuilder.create(), PartPose.offset(0.2F, 0.5F, -0.25F));

        PartDefinition cube_r33 = leave4.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(70, 48).addBox(-3.4309F, 2.0957F, 7.484F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.4226F, 0.9063F, -0.6545F, 0.0F, 0.0F));

        PartDefinition cube_r34 = leave4.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(92, 42).addBox(-3.4309F, 7.6066F, -2.4023F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 1600, 1600);
    }

    @Override
    public void setupAnim(SuperSnowGatlingPea entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, SnowyGatlingPeaModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, SnowyGatlingPeaModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, SnowyGatlingPeaModelAnimation.shoot, ageInTicks, 1.0F);
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