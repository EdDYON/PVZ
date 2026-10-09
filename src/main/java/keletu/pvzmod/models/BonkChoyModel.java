package keletu.pvzmod.models;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.BonkChoy;
import keletu.pvzmod.models.anim.BonkChoyModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class BonkChoyModel extends HierarchicalModel<BonkChoy> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/bonkchoy.png"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart body;
    private final ModelPart leaf;
    private final ModelPart bone9;
    private final ModelPart mouth;
    private final ModelPart eye;
    private final ModelPart right;
    private final ModelPart bone3;
    private final ModelPart bone6;
    private final ModelPart eyer;
    private final ModelPart left;
    private final ModelPart bone5;
    private final ModelPart bone4;
    private final ModelPart bone10;
    private final ModelPart eyel;
    private final ModelPart left_hand;
    private final ModelPart bone13;
    private final ModelPart bone12;
    private final ModelPart bone2;
    private final ModelPart right_hand;
    private final ModelPart bone8;
    private final ModelPart bone;
    private final ModelPart bone11;
    private final ModelPart bone7;

    public BonkChoyModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.body = this.all.getChild("body");
        this.leaf = this.body.getChild("leaf");
        this.bone9 = this.body.getChild("bone9");
        this.mouth = this.body.getChild("mouth");
        this.eye = this.body.getChild("eye");
        this.right = this.eye.getChild("right");
        this.bone3 = this.right.getChild("bone3");
        this.bone6 = this.right.getChild("bone6");
        this.eyer = this.right.getChild("eyer");
        this.left = this.eye.getChild("left");
        this.bone5 = this.left.getChild("bone5");
        this.bone4 = this.left.getChild("bone4");
        this.bone10 = this.left.getChild("bone10");
        this.eyel = this.left.getChild("eyel");
        this.left_hand = this.all.getChild("left_hand");
        this.bone13 = this.left_hand.getChild("bone13");
        this.bone12 = this.bone13.getChild("bone12");
        this.bone2 = this.bone13.getChild("bone2");
        this.right_hand = this.all.getChild("right_hand");
        this.bone8 = this.right_hand.getChild("bone8");
        this.bone = this.right_hand.getChild("bone");
        this.bone11 = this.bone.getChild("bone11");
        this.bone7 = this.bone.getChild("bone7");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0108F, 23.6452F, -0.0331F));

        PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-0.0108F, 1.2074F, -0.0662F));

        PartDefinition leaf = body.addOrReplaceChild("leaf", CubeListBuilder.create(), PartPose.offsetAndRotation(0.2979F, -13.385F, -0.1483F, 0.1484F, 0.0F, 0.0F));

        PartDefinition cube_r1 = leaf.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 55).addBox(4.9688F, -3.5752F, -3.7636F, 0.0F, 5.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.2969F, -4.6938F, 1.9542F, -0.4483F, 0.1776F, 0.3521F));

        PartDefinition cube_r2 = leaf.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 55).addBox(-6.0F, -9.6139F, -2.0828F, 12.0F, 4.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2969F, -1.0285F, -3.65F, -0.6632F, 0.0F, 0.0F));

        PartDefinition cube_r3 = leaf.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 35).addBox(-9.0F, -4.4932F, 0.2092F, 18.0F, 4.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2969F, -5.7797F, 2.7863F, -0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r4 = leaf.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(54, 16).addBox(-7.0F, -4.1754F, -0.4277F, 14.0F, 4.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2969F, -3.274F, 7.7597F, -1.0472F, 0.0F, 0.0F));

        PartDefinition cube_r5 = leaf.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(20, 50).addBox(-7.0F, -0.3095F, -0.3422F, 14.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2969F, -3.274F, 7.7597F, -0.6981F, 0.0F, 0.0F));

        PartDefinition cube_r6 = leaf.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 10).addBox(-9.0F, -0.4475F, 0.2915F, 18.0F, 6.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2969F, -5.7797F, 2.7863F, -0.5236F, 0.0F, 0.0F));

        PartDefinition cube_r7 = leaf.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 39).addBox(-7.0F, -5.9861F, -0.0374F, 14.0F, 6.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2969F, -1.0285F, -3.65F, -0.3142F, 0.0F, 0.0F));

        PartDefinition cube_r8 = leaf.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(20, 55).addBox(-5.0F, -3.5F, -4.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-0.2969F, -4.6938F, 1.9542F, -0.4483F, -0.1776F, -0.3521F));

        PartDefinition bone9 = body.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(0, 0).addBox(-3.6258F, -5.5194F, 0.3726F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(-0.3742F, -8.3332F, -4.2733F));

        PartDefinition cube_r9 = bone9.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 45).addBox(-3.6258F, -5.0083F, -0.3453F, 8.0F, 12.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r10 = bone9.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(36, 35).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 12.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3742F, 2.3806F, 6.4726F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r11 = bone9.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(32, 16).mirror().addBox(-3.0F, -11.0F, -4.0F, 3.0F, 11.0F, 8.0F, new CubeDeformation(0F)).mirror(false), PartPose.offsetAndRotation(-3.5758F, 7.1806F, 4.3726F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_r12 = bone9.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 16).addBox(0.0F, -11.0F, -4.0F, 3.0F, 11.0F, 8.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.3242F, 7.1806F, 4.3726F, 0.0F, 0.0F, -0.2618F));

        PartDefinition cube_r13 = bone9.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, 0.5F, -3.2F, 8.0F, 4.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.3742F, -10.2185F, 5.5225F, -0.4363F, 0.0F, 0.0F));

        PartDefinition cube_r14 = bone9.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 3.5F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.3742F, -10.243F, 5.267F, -0.1745F, 0.0F, 0.0F));

        PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(-0.6127F, -5.9973F, -6.4959F));

        PartDefinition cube_r15 = mouth.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(4, 34).addBox(-0.4758F, 1.8695F, -0.3606F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(0F, 0.125F, 0F))
                .texOffs(0, 2).addBox(-1.1258F, 1.2445F, -0.3606F, 1.75F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.2385F, -2.3359F, 2.2226F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r16 = mouth.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 2).addBox(1.0917F, 0.8641F, -0.3606F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0002F)), PartPose.offsetAndRotation(0.2385F, -2.3359F, 2.2226F, -0.1585F, 0.0735F, 0.4305F));

        PartDefinition cube_r17 = mouth.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 2).addBox(-3.3861F, 0.3737F, -0.3606F, 1.75F, 0.5F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2385F, -2.3359F, 2.2226F, -0.1434F, -0.0998F, -0.6037F));

        PartDefinition eye = body.addOrReplaceChild("eye", CubeListBuilder.create(), PartPose.offset(-0.0249F, -9.4504F, -6.0117F));

        PartDefinition right = eye.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(-0.3493F, 1.1172F, 1.9384F));

        PartDefinition bone3 = right.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(2, 81).addBox(-1.0F, -1.2592F, -0.4231F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(0.25F, 0.25F, 0F)), PartPose.offsetAndRotation(-0.9758F, -0.2367F, -0.1833F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone6 = right.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.9758F, -1.2367F, -0.1833F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r18 = bone6.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 4).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(0.125F, -0.375F, 0F)), PartPose.offsetAndRotation(-0.2012F, -0.525F, -0.2589F, 0.0F, 0.0F, 0.0436F));

        PartDefinition eyer = right.addOrReplaceChild("eyer", CubeListBuilder.create().texOffs(14, 88).addBox(-1.65F, -1.2489F, -0.4475F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(2, 76).addBox(-1.0F, -1.7516F, -0.4599F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(0.25F, 0.25F, 0F))
                .texOffs(41, 76).addBox(-0.5F, -2.1016F, -0.4849F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.9758F, 0.2633F, -0.1833F, -0.1745F, 0.0F, 0.0F));

        PartDefinition left = eye.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(-0.3493F, 1.1172F, 1.9384F));

        PartDefinition bone5 = left.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(30, 79).addBox(-1.0F, -1.1414F, 0.0134F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(0.125F, 0.125F, 0F)), PartPose.offsetAndRotation(1.7992F, 0.2379F, -0.7056F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone4 = left.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(30, 79).addBox(-1.0F, 0.3877F, 0.0042F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(0.125F, 0.375F, 0F)), PartPose.offsetAndRotation(1.7992F, -2.7436F, -0.1705F, -0.1745F, 0.0F, 0.0F));

        PartDefinition bone10 = left.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(60, 73).addBox(-1.0F, -0.875F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(-0.125F, -0.125F, 0F))
                .texOffs(60, 72).addBox(-1.0F, -0.125F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F).extend(-0.125F, -0.125F, 0F)), PartPose.offsetAndRotation(1.2992F, -2.3234F, -0.1642F, -0.1745F, 0.0F, 0.0F));

        PartDefinition eyel = left.addOrReplaceChild("eyel", CubeListBuilder.create().texOffs(6, 87).addBox(-1.3624F, -2.0692F, 0.0041F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(30, 75).addBox(-0.9374F, -3.7676F, -0.0082F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F).extend(0.125F, 0F, 0F))
                .texOffs(48, 71).addBox(-0.5624F, -2.9176F, -0.0332F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.7366F, 0.9901F, -0.7655F, -0.1745F, 0.0F, 0.0F));

        PartDefinition left_hand = all.addOrReplaceChild("left_hand", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1641F, -0.5793F, 0.0331F, 0.4796F, -0.0201F, 0.3441F));

        PartDefinition cube_r19 = left_hand.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(53, 35).addBox(-4.0F, 0.0F, -2.5F, 10.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.0566F, 0.0818F, 0.0F, 0.0F, 0.0F, -0.5672F));

        PartDefinition bone13 = left_hand.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(5.0116F, -3.1711F, 0.0F));

        PartDefinition bone12 = bone13.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(54, 30).addBox(0.0253F, 0.0115F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.025F, 0.05F, 0.0F, 0.0F, 0.0F, -1.309F));

        PartDefinition bone2 = bone13.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(1.8046F, -6.7255F, 0.0F));

        PartDefinition cube_r20 = bone2.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(58, 40).addBox(0.0F, -3.0F, -2.5F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.925F, 0.375F, 0.0F, 0.0F, 0.0F, -1.9635F));

        PartDefinition cube_r21 = bone2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(54, 20).addBox(-2.3935F, -3.1424F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.6333F, -1.5081F, 0.0F, 0.0F, 0.0F, -1.9635F));

        PartDefinition right_hand = all.addOrReplaceChild("right_hand", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.1533F, 0.3719F, 0.0331F, 0.1742F, -0.0076F, -0.1749F));

        PartDefinition bone8 = right_hand.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(45, 50).addBox(-9.0F, 0.0F, -2.5F, 12.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.1407F, -0.0315F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition bone = right_hand.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-8.2079F, -3.4854F, 0.0F));

        PartDefinition bone11 = bone.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(36, 55).addBox(-4.9564F, -3.999F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0331F, -0.0348F, 0.0F, 0.0F, 0.0F, 1.5272F));

        PartDefinition bone7 = bone.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(0, 59).addBox(-4.0F, -2.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.0331F, -0.0348F, 0.0F, 0.0F, 0.0F, 1.6581F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }


    @Override
    public void setupAnim(BonkChoy entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.stand, BonkChoyModelAnimation.standplus, ageInTicks, 1.0F);
        //this.animate(entity.blink, BokChoyModelAnimation.eye, ageInTicks, 1.0F);
        this.animate(entity.hit, BonkChoyModelAnimation.attack, ageInTicks, 1.0F);
        this.animate(entity.hit2, BonkChoyModelAnimation.attack2, ageInTicks, 1.0F);

        this.all.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.all.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}