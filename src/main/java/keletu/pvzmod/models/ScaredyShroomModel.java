package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.ScaredyShroom;
import keletu.pvzmod.models.anim.ScaredyShroomAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ScaredyShroomModel extends HierarchicalModel<ScaredyShroom> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/scaredy_shroom.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart body1;
    private final ModelPart face;
    private final ModelPart mm;
    private final ModelPart eye;
    private final ModelPart eye1;
    private final ModelPart mouth;
    private final ModelPart mouth1;

    public ScaredyShroomModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.hat = this.all.getChild("hat");
        this.body = this.all.getChild("body");
        this.body1 = this.body.getChild("body1");
        this.face = this.body1.getChild("face");
        this.mm = this.body.getChild("mm");
        this.eye = this.body.getChild("eye");
        this.eye1 = this.eye.getChild("eye1");
        this.mouth = this.body.getChild("mouth");
        this.mouth1 = this.mouth.getChild("mouth1");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition hat = all.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, -15.5733F, 0.6934F));

        PartDefinition cube_r1 = hat.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-10, 17).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 0.0F, 10.0F, new CubeDeformation(-0.001F))
                .texOffs(0, 0).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 3.1234F, -0.1934F, -0.2618F, 0.0F, 0.0F));

        PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(1.466F, -6.1795F, 0.071F));

        PartDefinition body1 = body.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(24, 17).mirror().addBox(-2.0F, -16.0F, 0.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(-1.466F, 6.1795F, -2.071F));

        PartDefinition face = body1.addOrReplaceChild("face", CubeListBuilder.create().texOffs(0, 30).addBox(-2.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F))
                .texOffs(0, 30).mirror().addBox(1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)).mirror(false), PartPose.offset(0.0F, -6.0F, 2.0F));

        PartDefinition mm = body.addOrReplaceChild("mm", CubeListBuilder.create(), PartPose.offset(-1.466F, -4.0204F, -2.121F));

        PartDefinition cube_r2 = mm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 37).addBox(-0.179F, -0.2582F, 0.0F, 1.0F, 0.75F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(1.364F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r3 = mm.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 38).addBox(-0.4918F, -0.821F, 0.0F, 0.75F, 1.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(1.364F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

        PartDefinition cube_r4 = mm.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 38).addBox(-0.2582F, -0.821F, 0.0F, 0.75F, 1.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.364F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6545F));

        PartDefinition cube_r5 = mm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 37).addBox(-0.821F, -0.2582F, 0.0F, 1.0F, 0.75F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.364F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition eye = body.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 34).addBox(-1.75F, -0.6875F, -0.4714F, 1.25F, 2.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(0, 34).addBox(0.5F, -0.6875F, -0.4714F, 1.25F, 2.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(-1.466F, -2.383F, -1.6246F));

        PartDefinition eye1 = eye.addOrReplaceChild("eye1", CubeListBuilder.create().texOffs(0, 36).addBox(-1.5F, -0.375F, 0.0F, 0.75F, 0.75F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(0, 36).addBox(0.75F, -0.375F, 0.0F, 0.75F, 0.75F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, 0.1875F, -0.4964F));

        PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(24, 41).mirror().addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(-1.466F, 0.1795F, -2.571F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(24, 37).addBox(-1.5F, -1.5F, -1.2F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(0, 27).addBox(-1.5F, -1.5F, -1.1F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offset(0.0F, 0.0F, -0.3F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(ScaredyShroom entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimationState, ScaredyShroomAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.idleAnimationStateSpecial, ScaredyShroomAnimation.stand2, ageInTicks, 1.0F);
        this.animate(entity.shootAnimationState, ScaredyShroomAnimation.shoot, ageInTicks, 1.0F);
        this.animate(entity.hideAnimation, ScaredyShroomAnimation.duo, ageInTicks, 1.0F);
        this.animate(entity.hiddenAnimation, ScaredyShroomAnimation.duo_stand, ageInTicks, 1.0F);
        this.animate(entity.standAnimation, ScaredyShroomAnimation.out, ageInTicks, 1.0F);

        this.all.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.all.xRot = headPitch * ((float) Math.PI / 180F);
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