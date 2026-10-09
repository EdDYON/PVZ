package keletu.pvzmod.models;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.Walnut;
import keletu.pvzmod.models.anim.WallnutNew0Animation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class WalnutModel0 extends HierarchicalModel<Walnut> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/walnut_new_0"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart face;
    private final ModelPart bone2;
    private final ModelPart mouth;
    private final ModelPart eye;
    private final ModelPart bone;

    public WalnutModel0(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.face = this.all.getChild("face");
        this.bone2 = this.face.getChild("bone2");
        this.mouth = this.face.getChild("mouth");
        this.eye = this.face.getChild("eye");
        this.bone = this.eye.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -20.0F, -6.0F, 14.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-6.0F, -19.0F, -5.0F, 12.0F, 18.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(4, 60).mirror().addBox(-7.0F, -20.0F, -2.5F, 14.0F, 20.0F, 5.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition face = all.addOrReplaceChild("face", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0185F, -6.025F));

        PartDefinition bone2 = face.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-4.803F, -6.5821F, 0.0F));

        PartDefinition cube_r1 = bone2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(57, 19).addBox(-0.803F, -0.3994F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.2217F));

        PartDefinition cube_r2 = bone2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(57, 19).addBox(-0.197F, -0.3994F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition cube_r3 = bone2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(57, 19).addBox(-0.803F, -0.3994F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(9.606F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r4 = bone2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(57, 19).addBox(-0.197F, -0.3994F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(9.606F, 0.0F, 0.0F, 0.0F, 0.0F, 1.2217F));

        PartDefinition mouth = face.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(57, 19).addBox(-0.5F, 0.0185F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(57, 19).addBox(-0.25F, 1.0185F, 0.0F, 0.75F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = mouth.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(57, 19).addBox(0.025F, -0.475F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(1.5F, 0.2685F, 0.0F, 0.0F, 0.0F, -2.1817F));

        PartDefinition cube_r6 = mouth.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(57, 19).addBox(-0.0704F, -0.1825F, 0.0F, 0.75F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.4296F, 1.201F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r7 = mouth.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(57, 19).addBox(-1.0F, -0.25F, 0.0F, 0.5F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(1.3007F, -0.1307F, 0.0F, 0.0F, 0.0F, -0.4363F));

        PartDefinition cube_r8 = mouth.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(57, 19).addBox(0.5F, -0.25F, 0.0F, 0.5F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.3007F, -0.1307F, 0.0F, 0.0F, 0.0F, 0.4363F));

        PartDefinition cube_r9 = mouth.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(57, 19).addBox(-1.025F, -0.475F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.5F, 0.2685F, 0.0F, 0.0F, 0.0F, 2.1817F));

        PartDefinition eye = face.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(58, 4).addBox(-5.0F, -1.8333F, 0.0083F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(66, 4).addBox(1.0F, -1.8333F, 0.0083F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, -3.1482F, -0.0083F));

        PartDefinition bone = eye.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(57, 12).addBox(2.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(57, 12).addBox(-3.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, -0.3333F, -0.0167F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Walnut entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation0, WallnutNew0Animation.animation, ageInTicks, 1.0F);
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