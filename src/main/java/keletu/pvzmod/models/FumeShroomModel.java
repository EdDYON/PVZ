package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.FumeShroom;
import keletu.pvzmod.models.anim.FumeShroomAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class FumeShroomModel extends HierarchicalModel<FumeShroom> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/fume_shroom_new.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart hat;
    private final ModelPart mouth2;
    private final ModelPart body;
    private final ModelPart bone;
    private final ModelPart mouth1;
    private final ModelPart eye;
    private final ModelPart bone2;

    public FumeShroomModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.hat = this.all.getChild("hat");
        this.mouth2 = this.hat.getChild("mouth2");
        this.body = this.all.getChild("body");
        this.bone = this.body.getChild("bone");
        this.mouth1 = this.bone.getChild("mouth1");
        this.eye = this.bone.getChild("eye");
        this.bone2 = this.bone.getChild("bone2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 14.95F, 0.0F));

        PartDefinition hat = all.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(2, 0).addBox(-8.0F, -8.05F, -8.5F, 16.0F, 8.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, 0.0F));

        PartDefinition mouth2 = hat.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -3.0F, -4.4167F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(23, 25).addBox(-3.0F, -3.0F, -4.1667F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0001F))
                .texOffs(0, 33).mirror().addBox(-2.0F, -2.0F, -2.4167F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.05F, -8.0833F));

        PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 25).addBox(-6.0F, -11.0512F, -5.9018F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 10.05F, 0.0F));

        PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(-2.4F, 1.6988F, -11.9268F));

        PartDefinition mouth1 = bone.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(14, 26).addBox(-1.6998F, -0.333F, 0.0F, 1.25F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(3.5683F, -5.5681F, 6.0F));

        PartDefinition cube_r1 = mouth1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 26).addBox(-0.499F, -0.25F, 0.0F, 1.498F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition cube_r2 = mouth1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 26).addBox(-1.5F, -0.5F, 0.0F, 0.5F, 0.75F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.5669F, -0.2524F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition cube_r3 = mouth1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(14, 26).addBox(-0.25F, -0.5F, 0.0F, 0.5F, 0.8F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.9303F, 0.487F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube_r4 = mouth1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(14, 26).addBox(-1.0F, -0.25F, 0.0F, 1.25F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.9044F, -0.0432F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition eye = bone.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(16, 27).addBox(-4.1F, -1.1643F, 0.4069F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.001F))
                .texOffs(18, 29).addBox(-3.6F, -0.9143F, 0.3819F, 0.75F, 0.75F, 0.0F, new CubeDeformation(0.001F))
                .texOffs(16, 27).addBox(2.1F, -1.1643F, 0.4069F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.001F))
                .texOffs(16, 29).addBox(2.85F, -0.9143F, 0.3819F, 0.75F, 0.75F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(2.4F, -7.8357F, 5.5931F));

        PartDefinition bone2 = bone.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(2.4F, -9.496F, 5.975F));

        PartDefinition cube_r5 = bone2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 25).addBox(0.1901F, -0.3947F, 0.0F, 1.0F, 0.6F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(3.305F, -0.2144F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition cube_r6 = bone2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(14, 25).addBox(-1.7017F, -0.3195F, 0.0F, 2.5F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.9831F, 0.0713F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition cube_r7 = bone2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(14, 25).addBox(-1.1901F, -0.3947F, 0.0F, 1.0F, 0.6F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-3.305F, -0.2144F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition cube_r8 = bone2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(14, 25).addBox(-0.7983F, -0.3195F, 0.0F, 2.5F, 0.5F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.9831F, 0.0713F, 0.0F, 0.0F, 0.0F, 0.48F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(FumeShroom entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimationState, FumeShroomAnimation.animation, ageInTicks, 1.0F);
        this.animate(entity.shootAnimationState, FumeShroomAnimation.shoot, ageInTicks, 1.0F);

        this.all.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.all.xRot = headPitch * ((float) Math.PI / 180F);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}