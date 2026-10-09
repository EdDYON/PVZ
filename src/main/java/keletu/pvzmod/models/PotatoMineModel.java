package keletu.pvzmod.models;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.PotatoMine;
import keletu.pvzmod.models.anim.PotatoMineAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PotatoMineModel extends HierarchicalModel<PotatoMine> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/potato_mine_new"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart bone4;
    private final ModelPart bone5;
    private final ModelPart bone6;
    private final ModelPart dirty1;
    private final ModelPart dirty;
    private final ModelPart body;
    private final ModelPart bone7;
    private final ModelPart eye;
    private final ModelPart mouth;

    public PotatoMineModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.bone = this.all.getChild("bone");
        this.bone2 = this.all.getChild("bone2");
        this.bone3 = this.all.getChild("bone3");
        this.bone4 = this.all.getChild("bone4");
        this.bone5 = this.all.getChild("bone5");
        this.bone6 = this.all.getChild("bone6");
        this.dirty1 = this.all.getChild("dirty1");
        this.dirty = this.all.getChild("dirty");
        this.body = this.all.getChild("body");
        this.bone7 = this.body.getChild("bone7");
        this.eye = this.body.getChild("eye");
        this.mouth = this.body.getChild("mouth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(-1.5F, 22.75F, -8.025F));

        PartDefinition bone = all.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-0.25F, 1.25F, 3.725F));

        PartDefinition cube_r1 = bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(53, 31).addBox(-2.0F, -1.5F, -1.0F, 2.0F, 1.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

        PartDefinition bone2 = all.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-2.5F, 1.25F, 4.025F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(53, 27).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition bone3 = all.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-2.5F, 1.25F, 12.025F));

        PartDefinition cube_r3 = bone3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(53, 13).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

        PartDefinition bone4 = all.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(5.5F, 1.25F, 12.025F));

        PartDefinition cube_r4 = bone4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

        PartDefinition bone5 = all.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(5.5F, 1.25F, 4.025F));

        PartDefinition cube_r5 = bone5.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(44, 18).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition bone6 = all.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(6.2F, 1.25F, 8.025F));

        PartDefinition cube_r6 = bone6.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(44, 25).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 1.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition dirty1 = all.addOrReplaceChild("dirty1", CubeListBuilder.create().texOffs(-16, 0).addBox(-8.0F, -0.1F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(-16, 35).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.25F, 8.025F));

        PartDefinition dirty = all.addOrReplaceChild("dirty", CubeListBuilder.create().texOffs(43, 10).mirror().addBox(-0.5F, -1.5F, -6.05F, 2.0F, 1.5F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 1.25F, 8.025F));

        PartDefinition cube_r7 = dirty.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(44, 43).addBox(-1.0F, -1.25F, -1.0F, 2.0F, 1.25F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.05F, 0.0F, 4.25F, 0.0F, -0.1309F, 0.0F));

        PartDefinition cube_r8 = dirty.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(44, 39).addBox(-1.1F, -1.5F, -0.6F, 2.0F, 1.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.0F, 4.0F, 0.0F, 0.2618F, 0.0F));

        PartDefinition cube_r9 = dirty.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(53, 10).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.3F, 0.0F, 4.0F, 0.0F, 0.9599F, 0.0F));

        PartDefinition cube_r10 = dirty.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(44, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.3F, 0.0F, 4.0F, 0.0F, -0.4363F, 0.0F));

        PartDefinition cube_r11 = dirty.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(44, 29).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 1.8F, 0.0F, 0.3927F, 0.0F));

        PartDefinition cube_r12 = dirty.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(44, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.7F, 0.0F, -2.1F, 0.0F, 0.2182F, 0.0F));

        PartDefinition cube_r13 = dirty.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(53, 17).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 1.8F, 0.0F, -0.3927F, 0.0F));

        PartDefinition cube_r14 = dirty.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(53, 20).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 1.5F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition cube_r15 = dirty.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(53, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.7F, 0.0F, -2.1F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r16 = dirty.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(44, 14).addBox(-2.0F, -1.25F, -1.0F, 3.0F, 1.25F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 0.0F, -4.2F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r17 = dirty.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(53, 35).addBox(-1.5F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.55F, -0.5F, -4.7F, 0.0F, 0.48F, 0.0F));

        PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 17).mirror().addBox(-4.0F, -3.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -0.75F, 8.025F));

        PartDefinition bone7 = body.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(43, 0).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(43, 6).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        PartDefinition eye = body.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(11, 33).addBox(-2.25F, -0.4387F, 0.1007F, 0.5F, 0.5F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(2, 32).addBox(-2.75F, -0.6887F, 0.1257F, 1.5F, 1.5F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(11, 33).addBox(1.75F, -0.4387F, 0.1007F, 0.5F, 0.5F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(2, 32).addBox(1.25F, -0.6887F, 0.1257F, 1.5F, 1.5F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5113F, -4.1507F));

        PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 34).addBox(0.587F, 0.2294F, 0.0F, 1.0F, 0.25F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(11, 33).addBox(0.587F, 0.4794F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.087F, -0.5794F, -4.025F));

        PartDefinition cube_r18 = mouth.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 34).addBox(0.125F, -0.25F, 0.0F, 0.5F, 0.25F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.4F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r19 = mouth.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 34).addBox(0.075F, 0.05F, 0.0F, 0.25F, 0.65F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r20 = mouth.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 34).addBox(-0.625F, -0.25F, 0.0F, 0.5F, 0.25F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.174F, 0.4F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r21 = mouth.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 34).addBox(-0.325F, 0.05F, 0.0F, 0.25F, 0.65F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.174F, -0.1F, 0.0F, 0.0F, 0.0F, -0.1309F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(PotatoMine entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimationState, PotatoMineAnimation.wait, ageInTicks, 1.0F);
        this.animate(entity.idleAnimationState2, PotatoMineAnimation.wait1, ageInTicks, 1.0F);
        this.animate(entity.growAnimation, PotatoMineAnimation.chutu, ageInTicks, 1.0F);
        this.animate(entity.boomAnimation, PotatoMineAnimation.boom, ageInTicks, 1.0F);

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