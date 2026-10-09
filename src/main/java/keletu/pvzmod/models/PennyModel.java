package keletu.pvzmod.models;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import keletu.pvzmod.PVZMod;
import keletu.pvzmod.entities.dave.Penny;
import keletu.pvzmod.models.anim.PennyModelAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class PennyModel extends HierarchicalModel<Penny> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(PVZMod.MODID, "textures/entity/npc/penny.png"), "main");
    private final ModelPart root;
    private final ModelPart all;
    private final ModelPart luntai;
    private final ModelPart bone4;
    private final ModelPart bone58;
    private final ModelPart bone32;
    private final ModelPart bone71;
    private final ModelPart bone;
    private final ModelPart bone75;
    private final ModelPart bone35;
    private final ModelPart bone8;
    private final ModelPart bone7;
    private final ModelPart bone24;
    private final ModelPart bone60;
    private final ModelPart bone61;
    private final ModelPart bone31;
    private final ModelPart bone27;
    private final ModelPart bone63;
    private final ModelPart bone74;
    private final ModelPart bone10;
    private final ModelPart bone77;
    private final ModelPart bone9;
    private final ModelPart bone68;
    private final ModelPart bone66;
    private final ModelPart bone67;
    private final ModelPart bone5;
    private final ModelPart bone64;
    private final ModelPart bone62;
    private final ModelPart bone36;
    private final ModelPart bone2;
    private final ModelPart bone18;
    private final ModelPart bone3;
    private final ModelPart bone41;
    private final ModelPart bone55;
    private final ModelPart cabine;
    private final ModelPart bone52;
    private final ModelPart bone43;
    private final ModelPart bone44;
    private final ModelPart bone45;
    private final ModelPart bone6;
    private final ModelPart bone73;
    private final ModelPart bone56;
    private final ModelPart bone16;
    private final ModelPart bone15;
    private final ModelPart bone14;
    private final ModelPart bone11;
    private final ModelPart cube_cut;
    private final ModelPart bone49;
    private final ModelPart bone26;
    private final ModelPart bone21;
    private final ModelPart bone25;
    private final ModelPart bone65;
    private final ModelPart bone76;
    private final ModelPart bone28;
    private final ModelPart bone69;
    private final ModelPart bone70;
    private final ModelPart bone42;
    private final ModelPart bone47;
    private final ModelPart bone38;
    private final ModelPart bone30;
    private final ModelPart bone40;
    private final ModelPart bone46;
    private final ModelPart bone37;
    private final ModelPart bone29;
    private final ModelPart bone39;
    private final ModelPart bone48;
    private final ModelPart bone17;
    private final ModelPart bone13;
    private final ModelPart bone53;
    private final ModelPart bone51;
    private final ModelPart bone50;
    private final ModelPart bone57;
    private final ModelPart bone54;
    private final ModelPart bone59;
    private final ModelPart bone34;
    private final ModelPart bone72;
    private final ModelPart bone33;

    public PennyModel(ModelPart root) {
        this.root = root;
        this.all = root.getChild("all");
        this.luntai = this.all.getChild("luntai");
        this.bone4 = this.all.getChild("bone4");
        this.bone58 = this.all.getChild("bone58");
        this.bone32 = this.bone58.getChild("bone32");
        this.bone71 = this.bone32.getChild("bone71");
        this.bone = this.bone71.getChild("bone");
        this.bone75 = this.bone32.getChild("bone75");
        this.bone35 = this.bone75.getChild("bone35");
        this.bone8 = this.bone35.getChild("bone8");
        this.bone7 = this.bone35.getChild("bone7");
        this.bone24 = this.bone35.getChild("bone24");
        this.bone60 = this.bone24.getChild("bone60");
        this.bone61 = this.bone24.getChild("bone61");
        this.bone31 = this.bone32.getChild("bone31");
        this.bone27 = this.bone58.getChild("bone27");
        this.bone63 = this.bone27.getChild("bone63");
        this.bone74 = this.bone63.getChild("bone74");
        this.bone10 = this.bone63.getChild("bone10");
        this.bone77 = this.bone63.getChild("bone77");
        this.bone9 = this.bone63.getChild("bone9");
        this.bone68 = this.bone63.getChild("bone68");
        this.bone66 = this.bone68.getChild("bone66");
        this.bone67 = this.bone68.getChild("bone67");
        this.bone5 = this.bone63.getChild("bone5");
        this.bone64 = this.bone5.getChild("bone64");
        this.bone62 = this.bone5.getChild("bone62");
        this.bone36 = this.bone63.getChild("bone36");
        this.bone2 = this.bone63.getChild("bone2");
        this.bone18 = this.bone63.getChild("bone18");
        this.bone3 = this.bone63.getChild("bone3");
        this.bone41 = this.bone63.getChild("bone41");
        this.bone55 = this.bone63.getChild("bone55");
        this.cabine = this.bone27.getChild("cabine");
        this.bone52 = this.cabine.getChild("bone52");
        this.bone43 = this.bone52.getChild("bone43");
        this.bone44 = this.bone52.getChild("bone44");
        this.bone45 = this.bone52.getChild("bone45");
        this.bone6 = this.cabine.getChild("bone6");
        this.bone73 = this.cabine.getChild("bone73");
        this.bone56 = this.bone27.getChild("bone56");
        this.bone16 = this.bone56.getChild("bone16");
        this.bone15 = this.bone56.getChild("bone15");
        this.bone14 = this.bone56.getChild("bone14");
        this.bone11 = this.bone56.getChild("bone11");
        this.cube_cut = this.bone58.getChild("cube_cut");
        this.bone49 = this.cube_cut.getChild("bone49");
        this.bone26 = this.cube_cut.getChild("bone26");
        this.bone21 = this.cube_cut.getChild("bone21");
        this.bone25 = this.cube_cut.getChild("bone25");
        this.bone65 = this.bone25.getChild("bone65");
        this.bone76 = this.cube_cut.getChild("bone76");
        this.bone28 = this.cube_cut.getChild("bone28");
        this.bone69 = this.bone28.getChild("bone69");
        this.bone70 = this.bone69.getChild("bone70");
        this.bone42 = this.cube_cut.getChild("bone42");
        this.bone47 = this.cube_cut.getChild("bone47");
        this.bone38 = this.bone47.getChild("bone38");
        this.bone30 = this.bone47.getChild("bone30");
        this.bone40 = this.bone47.getChild("bone40");
        this.bone46 = this.cube_cut.getChild("bone46");
        this.bone37 = this.bone46.getChild("bone37");
        this.bone29 = this.bone46.getChild("bone29");
        this.bone39 = this.bone46.getChild("bone39");
        this.bone48 = this.cube_cut.getChild("bone48");
        this.bone17 = this.cube_cut.getChild("bone17");
        this.bone13 = this.cube_cut.getChild("bone13");
        this.bone53 = this.cube_cut.getChild("bone53");
        this.bone51 = this.cube_cut.getChild("bone51");
        this.bone50 = this.bone51.getChild("bone50");
        this.bone57 = this.cube_cut.getChild("bone57");
        this.bone54 = this.cube_cut.getChild("bone54");
        this.bone59 = this.all.getChild("bone59");
        this.bone34 = this.bone59.getChild("bone34");
        this.bone72 = this.bone34.getChild("bone72");
        this.bone33 = this.bone59.getChild("bone33");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition luntai = all.addOrReplaceChild("luntai", CubeListBuilder.create().texOffs(334, 388).addBox(25.0F, -12.0F, -6.0F, 7.0F, 14.0F, 14.0F, new CubeDeformation(0F))
                .texOffs(376, 388).addBox(25.0F, -12.0F, 66.0F, 7.0F, 14.0F, 14.0F, new CubeDeformation(0F))
                .texOffs(170, 392).addBox(-4.0F, -12.0F, 66.0F, 7.0F, 14.0F, 14.0F, new CubeDeformation(0F))
                .texOffs(48, 396).addBox(-4.0F, -12.0F, -6.0F, 7.0F, 14.0F, 14.0F, new CubeDeformation(0F)), PartPose.offset(-14.0F, 24.0F, -49.0F));

        PartDefinition bone4 = all.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-0.01F, -19.872F, -34.3432F));

        PartDefinition bone58 = all.addOrReplaceChild("bone58", CubeListBuilder.create(), PartPose.offset(-0.25F, 2.2056F, -48.5824F));

        PartDefinition bone32 = bone58.addOrReplaceChild("bone32", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone71 = bone32.addOrReplaceChild("bone71", CubeListBuilder.create().texOffs(192, 306).addBox(-10.75F, 3.7944F, -15.4176F, 22.0F, 11.0F, 24.0F, new CubeDeformation(0F))
                .texOffs(62, 317).addBox(-6.75F, 4.7944F, -15.9176F, 14.0F, 7.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bone71.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(428, 269).addBox(-1.5F, -3.5F, -0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.5375F, 8.5964F, -15.4176F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r2 = bone71.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(170, 361).addBox(-0.5F, -3.5F, -0.5F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(7.0375F, 8.5964F, -15.4176F, 0.0F, 0.0F, -0.3491F));

        PartDefinition cube_r3 = bone71.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(328, 0).addBox(-10.75F, 0.5067F, -15.8734F, 22.0F, 5.0F, 26.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -0.1328F, -0.0419F, 0.2182F, 0.0F, 0.0F));

        PartDefinition bone = bone71.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r4 = bone.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(246, 364).addBox(-8.75F, -0.4007F, -11.8734F, 17.0F, 1.0F, 21.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, -0.1328F, -0.0419F, 0.3054F, 0.0F, 0.0F));

        PartDefinition cube_r5 = bone.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(442, 229).addBox(-5.25F, -3.146F, -3.5289F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.3F))
                .texOffs(406, 143).addBox(-5.25F, -3.146F, -3.5289F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(284, 208).addBox(-4.75F, -2.4717F, 0.4635F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5F, -0.1328F, -0.0419F, 0.1745F, 0.0F, 0.0F));

        PartDefinition bone75 = bone32.addOrReplaceChild("bone75", CubeListBuilder.create().texOffs(204, 0).addBox(-37.0F, -28.0F, 1.0F, 38.0F, 32.0F, 24.0F, new CubeDeformation(0F)), PartPose.offset(18.25F, 11.7944F, 7.5824F));

        PartDefinition bone35 = bone75.addOrReplaceChild("bone35", CubeListBuilder.create().texOffs(354, 329).addBox(-37.0F, -13.0F, -23.0F, 8.0F, 11.0F, 24.0F, new CubeDeformation(0F))
                .texOffs(424, 16).addBox(-37.0F, -2.0F, -23.0F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(102, 463).addBox(-37.0F, -2.0F, 0.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(72, 361).addBox(-7.0F, -13.0F, -23.0F, 8.0F, 11.0F, 24.0F, new CubeDeformation(0F))
                .texOffs(430, 306).addBox(-7.0F, -2.0F, -23.0F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(120, 463).addBox(-7.0F, -2.0F, 0.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone8 = bone35.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(278, 473).addBox(-0.8571F, -0.6098F, -12.1818F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(464, 156).addBox(-0.8571F, -0.1098F, -11.6818F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(442, 193).addBox(-0.8571F, -3.2256F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.002F))
                .texOffs(466, 193).addBox(-0.8571F, -0.1098F, 6.6819F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(284, 473).addBox(-0.8571F, -0.6098F, 10.1819F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(1.3571F, -7.3902F, -9.8182F));

        PartDefinition cube_r6 = bone8.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(466, 98).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.1429F, -0.6677F, 5.8409F, -0.8727F, 0.0F, 0.0F));

        PartDefinition cube_r7 = bone8.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(466, 91).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.1429F, -0.6677F, -5.8409F, 0.8727F, 0.0F, 0.0F));

        PartDefinition bone7 = bone35.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(468, 356).addBox(-11.0F, -1.5F, -27.8637F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(290, 473).addBox(-10.0F, -2.0F, -28.3637F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(442, 217).addBox(-11.0F, -4.6158F, -21.1819F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.002F))
                .texOffs(468, 442).addBox(-11.0F, -1.5F, -9.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(296, 473).addBox(-10.0F, -2.0F, -6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(-27.5F, -6.0F, 6.3637F));

        PartDefinition cube_r8 = bone7.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(468, 435).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-10.0F, -2.0579F, -10.3409F, -0.8727F, 0.0F, 0.0F));

        PartDefinition cube_r9 = bone7.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(468, 363).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-10.0F, -2.0579F, -22.0228F, 0.8727F, 0.0F, 0.0F));

        PartDefinition bone24 = bone35.addOrReplaceChild("bone24", CubeListBuilder.create().texOffs(116, 470).addBox(-17.0F, -14.0F, -12.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(266, 361).addBox(14.0F, -7.0F, -12.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(198, 429).addBox(-16.0F, -7.0F, -12.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(-19.0F, 2.0F, -11.5F));

        PartDefinition bone60 = bone24.addOrReplaceChild("bone60", CubeListBuilder.create(), PartPose.offset(-14.0F, -9.75F, -14.75F));

        PartDefinition cube_r10 = bone60.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(470, 163).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition cube_r11 = bone60.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(278, 443).addBox(-0.5F, -0.5F, 0.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone61 = bone24.addOrReplaceChild("bone61", CubeListBuilder.create().texOffs(164, 470).addBox(-3.0F, -3.0F, -0.5F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(16.0F, -11.0F, -12.0F));

        PartDefinition bone31 = bone32.addOrReplaceChild("bone31", CubeListBuilder.create().texOffs(328, 85).addBox(-13.2125F, 1.4481F, -1.0F, 40.0F, 6.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(252, 410).addBox(-6.2125F, -0.5519F, -2.0F, 5.0F, 10.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(422, 441).addBox(14.7875F, -0.5519F, -2.0F, 5.0F, 10.0F, 6.0F, new CubeDeformation(0F)), PartPose.offset(-6.5375F, 8.5964F, -15.4176F));

        PartDefinition bone27 = bone58.addOrReplaceChild("bone27", CubeListBuilder.create(), PartPose.offset(-17.75F, 16.7944F, 7.5824F));

        PartDefinition bone63 = bone27.addOrReplaceChild("bone63", CubeListBuilder.create(), PartPose.offset(19.1786F, -37.3173F, 4.3385F));

        PartDefinition bone74 = bone63.addOrReplaceChild("bone74", CubeListBuilder.create().texOffs(232, 432).addBox(-19.0F, -10.2249F, -10.5F, 0.0F, 21.0F, 10.0F, new CubeDeformation(0F))
                .texOffs(68, 424).addBox(19.0F, -10.2249F, -10.5F, 0.0F, 21.0F, 10.0F, new CubeDeformation(0F))
                .texOffs(204, 56).addBox(-19.0F, -10.2249F, -0.5F, 38.0F, 20.0F, 24.0F, new CubeDeformation(0F)), PartPose.offset(-1.1786F, -5.4578F, -2.8385F));

        PartDefinition cube_r12 = bone74.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(192, 278).addBox(-19.0F, -14.0F, -8.0F, 38.0F, 14.0F, 14.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 3.9173F, 5.1568F, 0.7854F, 0.0F, 0.0F));

        PartDefinition bone10 = bone63.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offset(-7.6786F, -16.0363F, -7.2275F));

        PartDefinition cube_r13 = bone10.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(246, 361).addBox(-3.5F, 0.5F, -1.5F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(352, 96).addBox(9.5F, 0.5F, -1.5F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r14 = bone10.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(462, 415).addBox(-2.0F, -17.0F, -6.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(116, 453).addBox(-3.0F, -18.0F, -1.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(6.5F, 14.4957F, 9.5459F, 0.7854F, 0.0F, 0.0F));

        PartDefinition bone77 = bone63.addOrReplaceChild("bone77", CubeListBuilder.create().texOffs(284, 201).addBox(16.0F, -18.5F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0F)), PartPose.offset(1.8214F, 18.3173F, 3.6615F));

        PartDefinition bone9 = bone63.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(400, 472).addBox(-1.0F, -2.4F, -7.8F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(276, 361).addBox(-1.0F, -3.9F, -13.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.2F))
                .texOffs(428, 53).addBox(-1.0F, -3.8F, -20.5F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.001F))
                .texOffs(372, 452).addBox(0.0F, -5.8F, -31.0F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0F))
                .texOffs(92, 323).addBox(-0.5F, -3.3F, -21.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(18.8214F, 0.1173F, 17.1615F));

        PartDefinition cube_r15 = bone9.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(252, 458).addBox(0.0F, -0.5F, -7.0F, 0.0F, 1.0F, 10.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, -2.3F, -24.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r16 = bone9.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(402, 433).addBox(2.873F, -6.0F, -8.8934F, 0.0F, 10.0F, 10.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0782F, 3.2F, -14.1572F, 0.0F, 0.1963F, 0.0F));

        PartDefinition cube_r17 = bone9.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(392, 462).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.3439F, -0.7221F, -0.3927F, 0.0F, 0.0F));

        PartDefinition bone68 = bone63.addOrReplaceChild("bone68", CubeListBuilder.create(), PartPose.offset(-18.6786F, -1.6827F, -9.8385F));

        PartDefinition bone66 = bone68.addOrReplaceChild("bone66", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -3.0F));

        PartDefinition cube_r18 = bone66.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(246, 469).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-0.01F, 1.059F, 0.9347F, 2.0944F, 0.0F, 0.0F));

        PartDefinition cube_r19 = bone66.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(470, 236).addBox(-2.4F, -3.7F, 0.1F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(272, 443).addBox(-0.9F, -1.2F, -0.6F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 3.0F, -0.132F, 0.1298F, -0.0172F));

        PartDefinition bone67 = bone68.addOrReplaceChild("bone67", CubeListBuilder.create(), PartPose.offset(-0.01F, 1.3533F, 0.0683F));

        PartDefinition cube_r20 = bone67.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(102, 470).addBox(-0.5F, -1.3F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8727F, 0.0F, 0.0F));

        PartDefinition cube_r21 = bone67.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(68, 390).addBox(-18.5F, -10.0F, -8.05F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(17.5F, -1.2253F, 12.0886F, 0.7854F, 0.0F, 0.0F));

        PartDefinition bone5 = bone63.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(16.3214F, -1.6827F, -9.8385F));

        PartDefinition bone64 = bone5.addOrReplaceChild("bone64", CubeListBuilder.create(), PartPose.offset(0.01F, 1.3533F, 0.0683F));

        PartDefinition cube_r22 = bone64.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(468, 449).addBox(-0.5F, -1.3F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8727F, 0.0F, 0.0F));

        PartDefinition cube_r23 = bone64.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(68, 387).addBox(16.5F, -10.0F, -8.05F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-17.5F, -1.2253F, 12.0886F, 0.7854F, 0.0F, 0.0F));

        PartDefinition bone62 = bone5.addOrReplaceChild("bone62", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -3.0F));

        PartDefinition cube_r24 = bone62.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(188, 469).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.01F, 1.059F, 0.9347F, 2.0944F, 0.0F, 0.0F));

        PartDefinition cube_r25 = bone62.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(470, 228).addBox(-2.6F, -3.7F, 0.1F, 5.0F, 7.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(442, 266).addBox(-1.1F, -1.2F, -0.6F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 3.0F, -0.132F, -0.1298F, 0.0172F));

        PartDefinition bone36 = bone63.addOrReplaceChild("bone36", CubeListBuilder.create().texOffs(214, 459).addBox(16.0F, -30.5F, 5.5F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.3F))
                .texOffs(140, 462).addBox(16.0F, -30.5F, 5.5F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0F)), PartPose.offset(1.8214F, 19.3173F, 3.6615F));

        PartDefinition bone2 = bone63.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(462, 271).addBox(-0.5F, -3.5F, -3.5F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0F))
                .texOffs(352, 462).addBox(-0.5F, -3.5F, -3.5F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.offset(-20.6786F, -7.6827F, 12.6615F));

        PartDefinition bone18 = bone63.addOrReplaceChild("bone18", CubeListBuilder.create(), PartPose.offset(-1.1886F, -1.5547F, 2.3184F));

        PartDefinition cube_r26 = bone18.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(426, 473).addBox(1.5F, -5.0F, 40.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.3F))
                .texOffs(230, 472).addBox(3.5F, -3.1F, 39.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(468, 456).addBox(1.0F, -5.0F, 40.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(96, 278).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 46.0F, new CubeDeformation(0F))
                .texOffs(332, 472).addBox(-1.0F, -5.0F, 40.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-20.5F, -11.9079F, -1.3507F, -0.0436F, 0.0F, 0.0F));

        PartDefinition cube_r27 = bone18.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(110, 454).addBox(-19.0F, -14.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(470, 299).addBox(-19.5F, -17.0F, -4.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(320, 217).addBox(-21.5F, -13.0F, -0.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(332, 462).addBox(-21.5F, -13.0F, -2.5F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0F))
                .texOffs(344, 452).addBox(-21.5F, -17.0F, -4.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(90, 472).addBox(-17.0F, -15.0F, -5.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition bone3 = bone63.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(-1.1686F, -1.5547F, 2.3184F));

        PartDefinition cube_r28 = bone3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(78, 472).addBox(14.0F, -15.0F, -5.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(116, 434).addBox(14.5F, -17.0F, -4.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(170, 369).addBox(19.0F, -14.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(0, 472).addBox(17.5F, -13.0F, -0.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(462, 251).addBox(19.5F, -13.0F, -2.5F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.001F))
                .texOffs(222, 449).addBox(19.5F, -17.0F, -4.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r29 = bone3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(462, 471).addBox(-6.5F, -3.1F, 39.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(414, 473).addBox(-2.5F, -5.0F, 40.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.3F))
                .texOffs(32, 420).addBox(-6.0F, -5.0F, 40.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(0, 252).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 46.0F, new CubeDeformation(0F))
                .texOffs(78, 466).addBox(-1.0F, -5.0F, 40.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(20.5F, -11.9079F, -1.3507F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone41 = bone63.addOrReplaceChild("bone41", CubeListBuilder.create().texOffs(442, 205).addBox(-3.0F, -3.75F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(392, 453).addBox(-3.0F, -0.75F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.1786F, -17.9327F, 2.6615F));

        PartDefinition bone55 = bone63.addOrReplaceChild("bone55", CubeListBuilder.create(), PartPose.offset(-1.1686F, -0.2996F, -8.1291F));

        PartDefinition cube_r30 = bone55.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(374, 295).addBox(-16.0F, -13.0F, -8.05F, 32.0F, 11.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(438, 122).addBox(-3.0F, -7.0F, -9.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, -1.2551F, 10.4475F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cabine = bone27.addOrReplaceChild("cabine", CubeListBuilder.create(), PartPose.offset(16.7558F, -16.5901F, 9.1956F));

        PartDefinition bone52 = cabine.addOrReplaceChild("bone52", CubeListBuilder.create().texOffs(416, 453).addBox(4.5197F, 3.5187F, -8.5187F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(456, 49).addBox(4.5197F, 3.5187F, 6.9813F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(374, 278).addBox(-8.8093F, -1.3334F, -7.7687F, 14.0F, 1.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(-22.9465F, -0.0766F, 3.5731F));

        PartDefinition cube_r31 = bone52.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(328, 96).addBox(-6.0F, -0.5F, -6.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(284, 219).addBox(-6.0F, -0.5F, 9.5F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.1907F, 2.4166F, -2.0187F, 0.0F, 0.0F, 0.5672F));

        PartDefinition bone43 = bone52.addOrReplaceChild("bone43", CubeListBuilder.create(), PartPose.offset(-5.1447F, -5.3334F, 2.8025F));

        PartDefinition cube_r32 = bone43.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(454, 401).addBox(-3.0F, 2.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(418, 87).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3054F, 0.0F));

        PartDefinition cube_r33 = bone43.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(372, 202).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 1.6F, 0.0F, 0.0F, 0.9599F, 0.0F));

        PartDefinition bone44 = bone52.addOrReplaceChild("bone44", CubeListBuilder.create(), PartPose.offset(-5.1447F, -3.3334F, -4.1975F));

        PartDefinition cube_r34 = bone44.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(470, 177).addBox(-0.5F, -3.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(322, 429).addBox(-0.5F, -3.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition cube_r35 = bone44.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(138, 275).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.0187F, -5.2591F, 0.8072F, -0.3186F, -0.1451F, 1.1578F));

        PartDefinition cube_r36 = bone44.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(68, 383).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.6317F, -3.5F, 0.3023F, -0.0939F, -0.3367F, 0.2778F));

        PartDefinition bone45 = bone52.addOrReplaceChild("bone45", CubeListBuilder.create(), PartPose.offset(0.5214F, -3.8334F, 0.2384F));

        PartDefinition cube_r37 = bone45.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(470, 185).addBox(-1.5F, -2.7F, -2.5F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(416, 53).addBox(-0.45F, -4.5F, -2.5F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0076F, 0.043F, 0.1747F));

        PartDefinition cube_r38 = bone45.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(86, 463).addBox(-1.5F, -1.5F, -2.5F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0436F, 0.0F));

        PartDefinition bone6 = cabine.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(464, 312).addBox(2.0F, -6.3465F, -3.1292F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(438, 471).addBox(2.0855F, -6.3465F, -2.3641F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(214, 473).addBox(4.0F, -1.0F, 3.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(440, 383).addBox(1.0F, 1.0F, -1.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(470, 105).addBox(4.0F, 1.0F, 0.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(12, 472).addBox(-1.0F, -2.0F, -1.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(21.7024F, 0.4865F, -7.3774F, 0.0F, 0.3491F, 0.0F));

        PartDefinition cube_r39 = bone6.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(470, 285).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.0F, -4.05F, 4.325F, 0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r40 = bone6.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(402, 322).addBox(-2.0F, 1.0F, -2.5F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.0F, -3.9465F, 0.3708F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r41 = bone6.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(202, 469).addBox(1.0F, -1.0F, -2.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.5813F, -4.3465F, -0.1214F, 0.0F, -0.2182F, 0.0F));

        PartDefinition cube_r42 = bone6.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(462, 261).addBox(-2.0F, -1.0F, -2.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.0F, -4.8465F, 0.3708F, -0.2182F, 0.0F, 0.0F));

        PartDefinition cube_r43 = bone6.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(320, 208).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.001F))
                .texOffs(230, 463).addBox(-2.0F, -8.0F, -2.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -2.891F, 2.6833F, -1.0036F, 0.0F, 0.0F));

        PartDefinition cube_r44 = bone6.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(380, 466).addBox(1.0F, -1.0F, -1.85F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.3491F, -4.3465F, -0.1066F, 0.0F, 0.2182F, 0.0F));

        PartDefinition bone73 = cabine.addOrReplaceChild("bone73", CubeListBuilder.create(), PartPose.offset(3.2442F, 15.5901F, 2.8044F));

        PartDefinition bone56 = bone27.addOrReplaceChild("bone56", CubeListBuilder.create().texOffs(0, 300).addBox(-19.0F, -47.0F, -11.0F, 34.0F, 4.0F, 13.0F, new CubeDeformation(0F)), PartPose.offset(20.0F, -7.0F, 29.0F));

        PartDefinition bone16 = bone56.addOrReplaceChild("bone16", CubeListBuilder.create(), PartPose.offset(-12.0F, -49.0F, -9.0F));

        PartDefinition cube_r45 = bone16.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(450, 471).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(272, 465).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-5.0F, -0.5947F, -20.5481F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r46 = bone16.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(462, 407).addBox(-2.9971F, -0.0523F, -9.8348F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.0029F, -0.0536F, -10.728F, -0.3491F, 0.0F, 0.0F));

        PartDefinition cube_r47 = bone16.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(394, 364).addBox(-2.9971F, -1.1465F, -9.272F, 4.0F, 4.0F, 19.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-4.0029F, -0.0536F, -10.728F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r48 = bone16.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(462, 321).addBox(3.5443F, -1.1465F, 5.5423F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-4.0029F, -0.0536F, -10.728F, -0.1098F, -0.6516F, 0.0668F));

        PartDefinition bone15 = bone56.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offset(-10.5F, -47.0F, -4.5F));

        PartDefinition cube_r49 = bone15.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(428, 71).addBox(-4.5F, 3.75F, -4.5F, 9.0F, 7.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(440, 358).addBox(-3.5F, -1.25F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0F))
                .texOffs(392, 472).addBox(-1.0F, -4.25F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(288, 465).addBox(-2.0F, -8.25F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-2.0F, -8.75F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition bone14 = bone56.addOrReplaceChild("bone14", CubeListBuilder.create(), PartPose.offset(8.0F, -49.0F, -9.0F));

        PartDefinition cube_r50 = bone14.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(462, 329).addBox(-1.0029F, -0.0523F, -9.8348F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.0029F, -0.0536F, -10.728F, -0.3491F, 0.0F, 0.0F));

        PartDefinition cube_r51 = bone14.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(96, 252).addBox(-1.0029F, -1.1465F, -9.272F, 4.0F, 4.0F, 19.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(4.0029F, -0.0536F, -10.728F, -0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r52 = bone14.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(282, 100).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(464, 0).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(5.0F, -0.5947F, -20.5481F, 0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r53 = bone14.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(462, 59).addBox(-10.5443F, -1.1465F, 5.5423F, 7.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.0029F, -0.0536F, -10.728F, -0.1098F, 0.6516F, -0.0668F));

        PartDefinition bone11 = bone56.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(170, 420).addBox(3.0F, -13.0F, 1.0F, 0.0F, 16.0F, 14.0F, new CubeDeformation(0F))
                .texOffs(304, 465).addBox(1.5F, -9.0F, 7.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0F)), PartPose.offset(6.5F, -47.0F, -4.5F));

        PartDefinition cube_r54 = bone11.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(72, 349).addBox(-3.5F, -1.25F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0F))
                .texOffs(252, 427).addBox(-4.5F, 3.75F, -4.5F, 9.0F, 7.0F, 9.0F, new CubeDeformation(0F))
                .texOffs(340, 472).addBox(-1.0F, -4.25F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(464, 67).addBox(-2.0F, -8.25F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(2.0F, -8.75F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition cube_cut = bone58.addOrReplaceChild("cube_cut", CubeListBuilder.create().texOffs(0, 0).addBox(-20.7866F, -29.8158F, -35.8708F, 38.0F, 42.0F, 64.0F, new CubeDeformation(0F))
                .texOffs(322, 364).addBox(-11.7866F, 12.1842F, -2.8708F, 20.0F, 8.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(0, 326).addBox(-20.7866F, 12.1842F, 13.1292F, 38.0F, 8.0F, 15.0F, new CubeDeformation(0F))
                .texOffs(142, 180).addBox(-20.7866F, 12.1842F, -35.8708F, 38.0F, 8.0F, 33.0F, new CubeDeformation(0F))
                .texOffs(470, 112).addBox(-18.5366F, 6.6842F, 28.1292F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(466, 200).addBox(8.9634F, 6.6842F, 28.1292F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(296, 278).addBox(-20.7866F, -29.8158F, 27.6292F, 38.0F, 50.0F, 1.0F, new CubeDeformation(-0.01F))
                .texOffs(466, 83).addBox(-5.2866F, -22.3158F, 28.1792F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0F)), PartPose.offset(2.0366F, -4.3898F, 68.4531F));

        PartDefinition bone49 = cube_cut.addOrReplaceChild("bone49", CubeListBuilder.create().texOffs(236, 221).addBox(-1.4348F, -5.8422F, -27.125F, 4.0F, 14.0F, 43.0F, new CubeDeformation(0F))
                .texOffs(400, 225).addBox(0.5652F, -9.8422F, -3.125F, 2.0F, 4.0F, 19.0F, new CubeDeformation(0F))
                .texOffs(470, 170).addBox(-2.9348F, -2.3422F, 17.875F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(372, 433).addBox(0.5652F, 8.1578F, 2.875F, 2.0F, 6.0F, 13.0F, new CubeDeformation(0F))
                .texOffs(418, 387).addBox(0.5652F, 8.1578F, -27.125F, 2.0F, 6.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(176, 341).addBox(-3.4348F, -2.8422F, -13.125F, 4.0F, 4.0F, 31.0F, new CubeDeformation(0F))
                .texOffs(458, 25).addBox(-3.4348F, -0.8422F, -19.125F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0F)), PartPose.offset(-23.3517F, 6.0264F, 8.2542F));

        PartDefinition cube_r55 = bone49.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(296, 154).addBox(0.0F, -11.6F, -17.0F, 0.0F, 16.0F, 38.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.5652F, -9.8422F, 0.875F, 0.0F, 0.0F, -0.4363F));

        PartDefinition bone26 = cube_cut.addOrReplaceChild("bone26", CubeListBuilder.create().texOffs(400, 202).addBox(-2.5652F, -9.8422F, -3.125F, 2.0F, 4.0F, 19.0F, new CubeDeformation(0F))
                .texOffs(142, 221).addBox(-2.5652F, -5.8422F, -27.125F, 4.0F, 14.0F, 43.0F, new CubeDeformation(0F))
                .texOffs(284, 329).addBox(-0.5652F, -2.8422F, -13.125F, 4.0F, 4.0F, 31.0F, new CubeDeformation(0F))
                .texOffs(466, 370).addBox(-0.0652F, -2.3422F, 17.875F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(322, 433).addBox(-2.5652F, 8.1578F, 2.875F, 2.0F, 6.0F, 13.0F, new CubeDeformation(0F))
                .texOffs(418, 358).addBox(-0.5652F, -0.8422F, -19.125F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(134, 417).addBox(-2.5652F, 8.1578F, -27.125F, 2.0F, 6.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(19.7786F, 6.0264F, 8.2542F));

        PartDefinition cube_r56 = bone26.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(296, 100).addBox(0.0F, -11.6F, -17.0F, 0.0F, 16.0F, 38.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5652F, -9.8422F, 0.875F, 0.0F, 0.0F, 0.4363F));

        PartDefinition bone21 = cube_cut.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(328, 65).addBox(-17.0F, -5.3333F, -8.0F, 34.0F, 4.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(212, 410).addBox(13.0F, -1.3333F, -8.0F, 4.0F, 6.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(416, 31).addBox(-17.0F, -1.3333F, -8.0F, 4.0F, 6.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(-1.7866F, -34.4824F, 17.1292F));

        PartDefinition bone25 = cube_cut.addOrReplaceChild("bone25", CubeListBuilder.create().texOffs(20, 463).addBox(-0.5F, -11.5885F, -9.4989F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(48, 377).addBox(-2.0F, -6.5885F, -7.4989F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(308, 219).addBox(-1.9F, -3.0885F, -8.5989F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(312, 219).addBox(-1.9F, 4.9115F, -8.5989F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(19.2134F, -6.2273F, -8.3719F));

        PartDefinition cube_r57 = bone25.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(284, 327).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(316, 219).addBox(-0.5F, -8.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.4F, 5.4115F, -1.8989F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone65 = bone25.addOrReplaceChild("bone65", CubeListBuilder.create().texOffs(212, 392).addBox(0.5F, -11.5885F, -3.4989F, 0.0F, 11.0F, 5.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r58 = bone65.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(178, 470).addBox(-0.5F, -2.0F, -0.2F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bone76 = cube_cut.addOrReplaceChild("bone76", CubeListBuilder.create(), PartPose.offset(-15.0866F, 19.9842F, 12.9292F));

        PartDefinition cube_r59 = bone76.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(252, 443).addBox(-5.5F, -9.0F, -0.5F, 9.0F, 14.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(442, 251).addBox(23.1F, -9.0F, -0.5F, 9.0F, 14.0F, 1.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition bone28 = cube_cut.addOrReplaceChild("bone28", CubeListBuilder.create(), PartPose.offset(5.2134F, -43.8158F, -8.3708F));

        PartDefinition bone69 = bone28.addOrReplaceChild("bone69", CubeListBuilder.create().texOffs(372, 152).addBox(-6.0F, 11.0F, -2.5F, 16.0F, 4.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r60 = bone69.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(424, 466).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(410, 466).addBox(-2.0F, -2.0F, 9.5F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition cube_r61 = bone69.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(374, 306).addBox(-8.0F, -2.0F, -6.0F, 16.0F, 4.0F, 12.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 4.1F, 5.5F, 0.0F, 0.0F, -1.309F));

        PartDefinition cube_r62 = bone69.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(170, 376).addBox(-8.0F, -2.0F, -6.0F, 16.0F, 4.0F, 12.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(4.0F, 4.0F, 5.5F, 0.0F, 0.0F, 1.309F));

        PartDefinition bone70 = bone69.addOrReplaceChild("bone70", CubeListBuilder.create(), PartPose.offset(4.0F, -3.0F, 5.5F));

        PartDefinition cube_r63 = bone70.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(156, 462).addBox(-3.0F, -13.625F, -1.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(464, 8).addBox(-4.0F, -17.625F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(328, 31).addBox(-13.0F, -10.6F, -11.0F, 22.0F, 12.0F, 22.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.8051F, -0.2646F, 0.0F, 0.0F, 0.0F, 1.1781F));

        PartDefinition bone42 = cube_cut.addOrReplaceChild("bone42", CubeListBuilder.create().texOffs(32, 401).addBox(-1.5F, -16.1F, -1.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(400, 269).addBox(-3.5F, -0.1F, -3.5F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0F))
                .texOffs(48, 440).addBox(-2.5F, -28.1F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0F))
                .texOffs(100, 349).addBox(-0.5F, -37.1F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(462, 285).addBox(-1.0F, -39.1F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(-13.2866F, -31.7158F, -21.3708F));

        PartDefinition bone47 = cube_cut.addOrReplaceChild("bone47", CubeListBuilder.create(), PartPose.offset(-3.7866F, 15.1842F, -48.8708F));

        PartDefinition bone38 = bone47.addOrReplaceChild("bone38", CubeListBuilder.create().texOffs(68, 377).addBox(-19.0F, -19.0F, 27.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(372, 96).addBox(-18.0F, -35.0F, 13.0F, 1.0F, 40.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(222, 473).addBox(-18.0F, -33.0F, 12.8F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(272, 473).addBox(-18.0F, -4.0F, 12.8F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(378, 416).addBox(-24.0F, 2.0F, 13.0F, 6.0F, 1.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone30 = bone47.addOrReplaceChild("bone30", CubeListBuilder.create().texOffs(406, 96).addBox(0.0F, -7.0F, -8.0F, 0.0F, 16.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(50, 466).addBox(-2.0F, -2.0F, 3.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(438, 178).addBox(-2.0F, -5.0F, -6.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0F))
                .texOffs(64, 466).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(438, 289).addBox(-2.0F, 4.0F, -6.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0F)), PartPose.offset(-20.0F, -7.0F, 21.0F));

        PartDefinition bone40 = bone47.addOrReplaceChild("bone40", CubeListBuilder.create().texOffs(32, 424).addBox(-4.0F, -1.0F, -8.0F, 2.0F, 0.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(-13.9991F, -38.665F, 21.0F));

        PartDefinition cube_r64 = bone40.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(90, 418).addBox(-1.5F, -1.0F, -8.0F, 6.0F, 0.0F, 16.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-6.6415F, 2.7782F, 0.0F, 0.0F, 0.0F, -0.7418F));

        PartDefinition cube_r65 = bone40.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(422, 409).addBox(0.5F, -1.0F, -8.0F, 4.0F, 0.0F, 16.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-7.4227F, 7.5535F, 0.0F, 0.0F, 0.0F, -1.5708F));

        PartDefinition bone46 = cube_cut.addOrReplaceChild("bone46", CubeListBuilder.create(), PartPose.offset(17.7134F, 0.1842F, -27.8708F));

        PartDefinition bone37 = bone46.addOrReplaceChild("bone37", CubeListBuilder.create().texOffs(188, 460).addBox(-0.5F, -18.0F, -8.2F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(92, 317).addBox(0.5F, -4.0F, 6.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(246, 463).addBox(-0.5F, 12.0F, -8.2F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.2F))
                .texOffs(136, 361).addBox(-0.5F, -20.0F, -8.0F, 1.0F, 40.0F, 16.0F, new CubeDeformation(0F))
                .texOffs(334, 416).addBox(0.5F, 17.0F, -8.0F, 6.0F, 1.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone29 = bone46.addOrReplaceChild("bone29", CubeListBuilder.create().texOffs(198, 420).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(438, 107).addBox(-2.0F, -5.0F, -6.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0F))
                .texOffs(36, 466).addBox(-2.0F, -2.0F, 3.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0F))
                .texOffs(438, 163).addBox(-2.0F, 4.0F, -6.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0F))
                .texOffs(0, 401).addBox(0.0F, -7.0F, -8.0F, 0.0F, 16.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(2.5F, 8.0F, 0.0F));

        PartDefinition bone39 = bone46.addOrReplaceChild("bone39", CubeListBuilder.create().texOffs(422, 425).addBox(-5.1415F, -3.7782F, -8.0F, 2.0F, 0.0F, 16.0F, new CubeDeformation(0F)), PartPose.offset(3.6406F, -20.8869F, 0.0F));

        PartDefinition cube_r66 = bone39.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(418, 322).addBox(-4.5F, -1.0F, -8.0F, 6.0F, 0.0F, 16.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

        PartDefinition cube_r67 = bone39.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(424, 0).addBox(-4.5F, -1.0F, -8.0F, 4.0F, 0.0F, 16.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(0.2812F, 4.7754F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition bone48 = cube_cut.addOrReplaceChild("bone48", CubeListBuilder.create().texOffs(106, 326).addBox(-12.0F, -12.0F, -8.9286F, 24.0F, 24.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(274, 386).addBox(11.0F, -15.0F, 2.0714F, 4.0F, 30.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(90, 396).addBox(-8.0F, -8.0F, -14.9286F, 16.0F, 16.0F, 6.0F, new CubeDeformation(0F))
                .texOffs(400, 248).addBox(-10.0F, -10.0F, -15.4286F, 20.0F, 20.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(372, 172).addBox(-11.0F, -15.0F, 2.0714F, 22.0F, 4.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(372, 187).addBox(-11.0F, 11.0F, 2.0714F, 22.0F, 4.0F, 11.0F, new CubeDeformation(0F))
                .texOffs(304, 388).addBox(-15.0F, -15.0F, 2.0714F, 4.0F, 30.0F, 11.0F, new CubeDeformation(0F)), PartPose.offset(-1.7866F, -2.8158F, 43.0578F));

        PartDefinition bone17 = cube_cut.addOrReplaceChild("bone17", CubeListBuilder.create(), PartPose.offset(-14.7866F, -18.1888F, 29.2698F));

        PartDefinition cube_r68 = bone17.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(260, 469).addBox(-1.0F, 4.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-3.9131F, -46.2245F, 14.4419F, -0.9599F, -0.3054F, 0.0F));

        PartDefinition cube_r69 = bone17.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(284, 306).addBox(-1.0F, -43.0F, 7.0F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.001F))
                .texOffs(352, 433).addBox(-2.0F, -25.0F, 6.0F, 5.0F, 24.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.0F, 0.873F, -1.1406F, 0.0F, -0.3054F, 0.0F));

        PartDefinition cube_r70 = bone17.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(418, 338).addBox(-3.0F, -4.0F, -4.0F, 7.0F, 8.0F, 12.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(1.0F, -0.127F, -1.1406F, 0.2182F, -0.3054F, 0.0F));

        PartDefinition bone13 = cube_cut.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(11.2134F, -18.1888F, 29.2698F));

        PartDefinition cube_r71 = bone13.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(368, 466).addBox(-2.0F, 4.0F, -2.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(3.9131F, -46.2245F, 14.4419F, -0.9599F, 0.3054F, 0.0F));

        PartDefinition cube_r72 = bone13.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(284, 180).addBox(-2.0F, -44.0F, 7.0F, 3.0F, 18.0F, 3.0F, new CubeDeformation(0.001F))
                .texOffs(0, 433).addBox(-3.0F, -26.0F, 6.0F, 5.0F, 24.0F, 5.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-1.0F, 1.873F, -1.1406F, 0.0F, 0.3054F, 0.0F));

        PartDefinition cube_r73 = bone13.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(246, 341).addBox(-3.5F, -4.0F, -6.0F, 7.0F, 8.0F, 12.0F, new CubeDeformation(0F)), PartPose.offsetAndRotation(-0.8755F, -0.127F, 0.9172F, 0.2182F, 0.3054F, 0.0F));

        PartDefinition bone53 = cube_cut.addOrReplaceChild("bone53", CubeListBuilder.create().texOffs(466, 207).addBox(-16.5F, -12.0F, 77.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(130, 470).addBox(-32.0F, -12.0F, 77.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(466, 221).addBox(-22.5F, -12.0F, 77.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(466, 214).addBox(-10.5F, -12.0F, 76.9F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(458, 435).addBox(-2.0F, -12.0F, 77.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(13.2134F, -17.3158F, -48.4208F));

        PartDefinition bone51 = cube_cut.addOrReplaceChild("bone51", CubeListBuilder.create().texOffs(374, 322).addBox(5.6151F, 3.9375F, 8.9679F, 13.0F, 6.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(410, 462).addBox(6.1151F, 1.4375F, 7.9679F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(96, 275).addBox(-11.8849F, -0.5625F, 7.9679F, 20.0F, 2.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(408, 473).addBox(-11.8849F, 1.4375F, 7.9679F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(442, 237).addBox(-22.3849F, 3.9375F, 8.9679F, 13.0F, 6.0F, 1.0F, new CubeDeformation(0F))
                .texOffs(204, 100).addBox(-20.8849F, 4.4375F, 7.9679F, 38.0F, 5.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(0.0983F, 9.7467F, 20.1613F));

        PartDefinition bone50 = bone51.addOrReplaceChild("bone50", CubeListBuilder.create().texOffs(0, 317).addBox(-17.5F, -10.0F, 79.075F, 31.0F, 9.0F, 0.0F, new CubeDeformation(0F))
                .texOffs(442, 244).addBox(-8.5F, -8.5F, 78.0F, 13.0F, 6.0F, 1.0F, new CubeDeformation(0F)), PartPose.offset(0.1151F, 12.4375F, -69.0321F));

        PartDefinition bone57 = cube_cut.addOrReplaceChild("bone57", CubeListBuilder.create().texOffs(176, 326).addBox(-3.0F, -9.5F, -1.0F, 6.0F, 12.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(438, 457).addBox(-0.5F, 2.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(11.9634F, 0.6842F, 29.1292F));

        PartDefinition bone54 = cube_cut.addOrReplaceChild("bone54", CubeListBuilder.create().texOffs(316, 462).addBox(-3.0F, -9.5F, -1.0F, 6.0F, 12.0F, 2.0F, new CubeDeformation(0F))
                .texOffs(420, 473).addBox(-0.5F, 2.5F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0F)), PartPose.offset(-15.5366F, 0.6842F, 29.1292F));

        PartDefinition bone59 = all.addOrReplaceChild("bone59", CubeListBuilder.create(), PartPose.offset(-15.1478F, 18.0F, -1.4556F));

        PartDefinition bone34 = bone59.addOrReplaceChild("bone34", CubeListBuilder.create().texOffs(148, 106).addBox(18.6959F, 0.5F, -31.9249F, 3.0F, 3.0F, 71.0F, new CubeDeformation(0F))
                .texOffs(148, 106).mirror().addBox(8.5996F, 0.5F, -31.9249F, 3.0F, 3.0F, 71.0F, new CubeDeformation(0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r74 = bone34.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(226, 386).addBox(0.0F, 0.5F, -3.5F, 3.0F, 3.0F, 21.0F, new CubeDeformation(0.001F))
                .texOffs(20, 440).addBox(0.0F, 0.5F, -0.5F, 3.0F, 3.0F, 11.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(22.0766F, 0.0F, 39.9809F, 0.0F, 1.309F, 0.0F));

        PartDefinition cube_r75 = bone34.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(0, 377).addBox(-3.0F, 0.5F, -3.5F, 3.0F, 3.0F, 21.0F, new CubeDeformation(0.001F))
                .texOffs(116, 439).addBox(-3.0F, 0.5F, -0.5F, 3.0F, 3.0F, 11.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(8.2189F, 0.0F, 39.9809F, 0.0F, -1.309F, 0.0F));

        PartDefinition bone72 = bone34.addOrReplaceChild("bone72", CubeListBuilder.create().texOffs(330, 208).addBox(14.8109F, -1.5F, 7.1714F, 3.0F, 3.0F, 32.0F, new CubeDeformation(0F))
                .texOffs(466, 377).addBox(14.8109F, -1.5F, 11.1714F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
                .texOffs(20, 433).addBox(14.8109F, -1.5F, 32.1714F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
                .texOffs(330, 243).addBox(-17.8109F, -1.5F, 7.1714F, 3.0F, 3.0F, 32.0F, new CubeDeformation(0F))
                .texOffs(470, 244).addBox(-17.8109F, -1.5F, 11.1714F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F))
                .texOffs(470, 293).addBox(-17.8109F, -1.5F, 32.1714F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offset(15.1478F, 2.0F, -39.567F));

        PartDefinition cube_r76 = bone72.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(440, 370).addBox(-3.0F, 0.5F, -3.5F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-17.6779F, -2.0F, 41.1789F, 0.0F, -0.9599F, 0.0F));

        PartDefinition cube_r77 = bone72.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(288, 429).addBox(-3.0F, 0.5F, -2.5F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-6.5F, -2.0F, 3.5226F, 0.0F, -1.0036F, 0.0F));

        PartDefinition cube_r78 = bone72.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(144, 439).addBox(0.0F, 0.5F, -3.5F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(17.6779F, -2.0F, 41.1789F, 0.0F, 0.9599F, 0.0F));

        PartDefinition cube_r79 = bone72.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(198, 432).addBox(0.0F, 0.5F, -2.5F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(6.5F, -2.0F, 3.5226F, 0.0F, 1.0036F, 0.0F));

        PartDefinition bone33 = bone59.addOrReplaceChild("bone33", CubeListBuilder.create().texOffs(0, 349).addBox(-14.0F, 2.0F, 0.0F, 16.0F, 8.0F, 20.0F, new CubeDeformation(0F))
                .texOffs(464, 75).addBox(2.0F, 5.0F, 3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(464, 140).addBox(-18.0F, 5.0F, 3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(0, 180).addBox(-9.0F, 2.0F, 8.0F, 6.0F, 7.0F, 65.0F, new CubeDeformation(0F))
                .texOffs(406, 128).addBox(-14.0F, 3.0F, 73.0F, 16.0F, 7.0F, 8.0F, new CubeDeformation(0F))
                .texOffs(464, 148).addBox(2.0F, 5.0F, 75.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F))
                .texOffs(464, 304).addBox(-18.0F, 5.0F, 75.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0F)), PartPose.offset(21.1478F, -6.0F, -51.5444F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(Penny entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(entity.idleAnimation, PennyModelAnimation.idle, ageInTicks, 1.0F);
    }
}