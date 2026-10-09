package keletu.pvzmod.models;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.Pumpkin;
import keletu.pvzmod.models.anim.PumpkinModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PumpkinModel extends HierarchicalModel<Pumpkin> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/plant/pumpkin_0"), "main");
    private final ModelPart root;
    private final ModelPart all;

    public PumpkinModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 59).addBox(-11.5F, -12.0F, -9.5F, 2.0F, 12.0F, 19.0F, new CubeDeformation(0F))
                .texOffs(0, 0).addBox(-11.5F, -12.0F, -11.5F, 23.0F, 12.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(31, 0).addBox(-9.5F, -2.0F, -9.5F, 19.0F, 2.0F, 19.0F, new CubeDeformation(0F))
                .texOffs(0, 45).addBox(-11.5F, -12.0F, 9.5F, 23.0F, 12.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(0, 14).addBox(9.5F, -12.0F, -9.5F, 2.0F, 12.0F, 19.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Pumpkin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, PumpkinModelAnimation.stand, ageInTicks, 1.0F);

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