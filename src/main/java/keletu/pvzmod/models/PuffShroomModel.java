package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.PuffShroom;
import keletu.pvzmod.models.anim.PuffShroomModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PuffShroomModel extends HierarchicalModel<PuffShroom> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/puff_shroom_new.png"), "main");

    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart hat;
    private final ModelPart body;
    private final ModelPart bone;
    private final ModelPart eye;
    private final ModelPart mouth;

    public PuffShroomModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.hat = this.all.getChild("hat");
        this.body = this.all.getChild("body");
        this.bone = this.body.getChild("bone");
        this.eye = this.bone.getChild("eye");
        this.mouth = this.body.getChild("mouth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition hat = all.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -3.0F, -5.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

        PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -7.0F));

        PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -2.0F, 7.0F));

        PartDefinition eye = bone.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(14, 31).addBox(-2.4688F, -0.8875F, -0.0269F, 1.5F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 33).addBox(-2.1188F, -0.5875F, -0.0519F, 0.75F, 0.75F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 33).addBox(1.4312F, -0.4875F, -0.0519F, 0.75F, 0.75F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(14, 31).addBox(1.0312F, -0.8875F, -0.0269F, 1.5F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0312F, -2.3625F, -2.9981F));

        PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 28).addBox(-1.4649F, -1.7326F, -2.0202F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(14, 27).addBox(-1.4649F, -1.7326F, -1.9202F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(8, 28).addBox(-0.9649F, -1.2326F, -1.0202F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0351F, -2.2674F, 4.0202F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(PuffShroom entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, PuffShroomModelAnimation.stand, ageInTicks, 1.0F);
        this.animate(entity.blinkAnimation, PuffShroomModelAnimation.blink, ageInTicks, 1.0F);
        this.animate(entity.shootAnimation, PuffShroomModelAnimation.shoot, ageInTicks, 1.0F);

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