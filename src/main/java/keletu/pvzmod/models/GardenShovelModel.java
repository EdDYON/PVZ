package keletu.pvzmod.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class GardenShovelModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/item/garden_shovel.png"), "main");
    private final ModelPart all;

    public GardenShovelModel(ModelPart root) {
        this.all = root.getChild("all");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -11.4F, 0.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(8, 16).addBox(-1.0F, -1.0F, -2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.1F, 0.1F, -0.15F))
                .texOffs(8, 10).addBox(-1.0F, -15.4F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.1F))
                .texOffs(0, 21).mirror().addBox(-1.0F, -3.4F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)).mirror(false)
                .texOffs(16, 24).addBox(-1.0F, 1.2F, -2.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(0, 24).addBox(-1.0F, -2.2F, -2.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.1F))
                .texOffs(8, 20).addBox(-1.0F, -1.0F, 2.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F).extend(0.1F, 0.1F, -0.15F)), PartPose.offset(0.0F, 20.4F, -1.0F));

        PartDefinition cube_r1 = all.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, -10).addBox(0.005F, -5.0F, -5.0F, 0.0F, 10.0F, 10.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.325F, -17.4F, 1.0F, 0.0F, 0.0F, 0.1745F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, 1F, 1F, 1F, 1F);
    }
}
