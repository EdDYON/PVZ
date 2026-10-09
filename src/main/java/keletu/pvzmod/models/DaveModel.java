package keletu.pvzmod.models;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.dave.Dave;
import keletu.pvzmod.models.anim.DaveModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class DaveModel extends HierarchicalModel<Dave> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "texutres/entity/npc/dave.png"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart up;
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart meimao;
    private final ModelPart eye;
    private final ModelPart hair1;
    private final ModelPart mouth;
    private final ModelPart mouth1;
    private final ModelPart mouth2;
    private final ModelPart mouth3;
    private final ModelPart mouth4;
    private final ModelPart mouth5;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart left_hand;
    private final ModelPart right_arm;
    private final ModelPart right_hand;
    private final ModelPart down;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public DaveModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.up = this.all.getChild("up");
        this.head = this.up.getChild("head");
        this.hat = this.head.getChild("hat");
        this.meimao = this.head.getChild("meimao");
        this.eye = this.head.getChild("eye");
        this.hair1 = this.head.getChild("hair1");
        this.mouth = this.head.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
        this.mouth2 = this.mouth.getChild("mouth2");
        this.mouth3 = this.mouth.getChild("mouth3");
        this.mouth4 = this.mouth.getChild("mouth4");
        this.mouth5 = this.mouth.getChild("mouth5");
        this.body = this.up.getChild("body");
        this.left_arm = this.up.getChild("left_arm");
        this.left_hand = this.left_arm.getChild("left_hand");
        this.right_arm = this.up.getChild("right_arm");
        this.right_hand = this.right_arm.getChild("right_hand");
        this.down = this.all.getChild("down");
        this.left_leg = this.down.getChild("left_leg");
        this.right_leg = this.down.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition up = all.addOrReplaceChild("up", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = up.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0F))
                .texOffs(32, 15).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 11).addBox(-4.0F, 0.0F, -3.5F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -1.7F, 4.85F, -1.2654F, 0.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(52, 11).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -3.7F, -0.2182F, 0.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -2.5F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(36, 0).addBox(-4.5F, 2.0F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F).extend(0.2F, -0.2F, 0.2F))
                .texOffs(72, 0).addBox(-1.0F, 0.5F, -9.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(72, 6).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -9.7F, 0.6F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r3 = hat.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(68, 42).addBox(-1.0F, -0.5F, 0.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.1751F, -11.1782F, -0.3491F, 0.0F, 0.0F));

        PartDefinition meimao = head.addOrReplaceChild("meimao", CubeListBuilder.create(), PartPose.offset(-2.5F, -5.05F, -3.125F));

        PartDefinition cube_r4 = meimao.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -1.0F, 1.3F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r5 = meimao.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -1.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.2255F, -0.0521F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition cube_r6 = meimao.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -1.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(5.2255F, -0.0521F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition cube_r7 = meimao.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-0.3F, -1.5F, -1.0F, 1.3F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition eye = head.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(38, 81).addBox(1.0F, -1.5F, -0.775F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F).extend(-0.3F, -0.5F, -0.3F))
                .texOffs(26, 80).addBox(-4.0F, -1.5F, -1.175F, 3.0F, 3.0F, 2.0F, new CubeDeformation(-0.7F)), PartPose.offset(0.0F, -4.5F, -3.55F));

        PartDefinition hair1 = head.addOrReplaceChild("hair1", CubeListBuilder.create().texOffs(64, 16).addBox(-4.2F, -5.439F, 1.9424F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offset(0.2F, 2.939F, -5.9424F));

        PartDefinition cube_r8 = hair1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(36, 11).addBox(-4.0F, -1.5F, 0.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F).extend(0.2F, 0F, 0F)), PartPose.offsetAndRotation(-0.2F, -1.7901F, 1.3541F, -0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r9 = hair1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(26, 72).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.3772F, -3.139F, 1.4819F, 0.0F, 0.1745F, 0.0F));

        PartDefinition cube_r10 = hair1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(20, 72).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.7772F, -3.139F, 1.4819F, 0.0F, -0.1745F, 0.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, -1.25F, -4.225F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(4, 82).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F).extend(-3F, -1.5F, 0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition mouth2 = mouth.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(4, 87).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F).extend(-3F, -1.5F, 0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition mouth3 = mouth.addOrReplaceChild("mouth3", CubeListBuilder.create().texOffs(4, 92).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F).extend(-3F, -1.5F, 0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition mouth4 = mouth.addOrReplaceChild("mouth4", CubeListBuilder.create().texOffs(4, 97).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F).extend(-3F, -1.5F, 0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition mouth5 = mouth.addOrReplaceChild("mouth5", CubeListBuilder.create().texOffs(4, 102).addBox(-5.0F, -2.5F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F).extend(-3F, -1.5F, 0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = up.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 45).addBox(-5.0F, -7.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(32, 45).addBox(-5.0F, -7.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.2F))
                .texOffs(0, 31).addBox(-5.0F, -2.0F, -4.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0F))
                .texOffs(34, 31).addBox(-5.0F, -2.0F, -4.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 7.0F, 0.0F));

        PartDefinition left_arm = up.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(64, 57).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.001F))
                .texOffs(68, 24).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(6.0F, 2.0F, 0.0F));

        PartDefinition left_hand = left_arm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(64, 45).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition right_arm = up.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(64, 66).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.001F))
                .texOffs(68, 33).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-6.0F, 2.0F, 0.0F));

        PartDefinition right_hand = right_arm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(64, 45).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

        PartDefinition down = all.addOrReplaceChild("down", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition left_leg = down.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 56).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(32, 56).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_leg = down.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 56).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(48, 56).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(-3.8F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Dave entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.stand, DaveModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.speak, DaveModelAnimation.speak1, ageInTicks, 2.0F);
        this.animate(entity.speak2, DaveModelAnimation.speak2, ageInTicks, 2.0F);
        this.animate(entity.longSpeak, DaveModelAnimation.speak_long, ageInTicks, 2.0F);
        this.animate(entity.run, DaveModelAnimation.run, ageInTicks, 1.0F);
        this.animate(entity.blink, DaveModelAnimation.eye, ageInTicks, 1.0F);
        this.animate(entity.walk, DaveModelAnimation.walk, ageInTicks, 1.0F);

        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
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