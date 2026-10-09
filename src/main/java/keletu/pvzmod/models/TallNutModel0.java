package keletu.pvzmod.models;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.Tallnut;
import keletu.pvzmod.models.anim.TallNutAnimation0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class TallNutModel0 extends HierarchicalModel<Tallnut> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/tallnut_new"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart face;
    private final ModelPart mouth;
    private final ModelPart eye;
    private final ModelPart bone;

    public TallNutModel0(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.face = this.all.getChild("face");
        this.mouth = this.face.getChild("mouth");
        this.eye = this.face.getChild("eye");
        this.bone = this.eye.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -28.0F, -5.0F, 12.0F, 27.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(0, 71).addBox(-7.0F, -29.0F, -6.0F, 14.0F, 29.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(44, 9).addBox(-7.0F, -28.0F, -7.0F, 14.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 37).addBox(-7.0F, -29.0F, -2.5F, 14.0F, 29.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition face = all.addOrReplaceChild("face", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0185F, -6.025F));

        PartDefinition mouth = face.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, -11.7F, 2.975F));

        PartDefinition cube_r1 = mouth.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(45, 2).addBox(-1.2728F, 2.0819F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.45F, 3.4006F, -2.975F, 0.0F, 0.0F, -1.9548F));

        PartDefinition cube_r2 = mouth.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 2).addBox(-0.6986F, -0.3658F, 0.0F, 0.75F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 3.9736F, -2.975F, 0.0F, 0.0F, 3.002F));

        PartDefinition cube_r3 = mouth.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(45, 2).addBox(-0.0514F, -0.3658F, 0.0F, 0.75F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 3.9736F, -2.975F, 0.0F, 0.0F, -3.002F));

        PartDefinition cube_r4 = mouth.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(45, 2).addBox(-0.0691F, -0.4915F, 0.0F, 2.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 3.1006F, -2.975F, 0.0F, 0.0F, 0.1396F));

        PartDefinition cube_r5 = mouth.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(45, 2).addBox(0.2728F, 2.0819F, 0.0F, 1.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.45F, 3.4006F, -2.975F, 0.0F, 0.0F, 1.9548F));

        PartDefinition cube_r6 = mouth.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(45, 2).addBox(-1.9309F, -0.4915F, 0.0F, 2.0F, 0.5F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 3.1006F, -2.975F, 0.0F, 0.0F, -0.1396F));

        PartDefinition eye = face.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(67, 3).addBox(-4.9167F, -1.0833F, -2.0083F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(59, 3).addBox(1.0833F, -1.0833F, -2.0083F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(-0.0833F, -11.8982F, 2.0083F));

        PartDefinition bone = eye.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(45, 2).addBox(-3.25F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(45, 2).addBox(2.25F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0833F, -0.3333F, -2.0333F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Tallnut entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation0, TallNutAnimation0.animation, ageInTicks, 1.0F);

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