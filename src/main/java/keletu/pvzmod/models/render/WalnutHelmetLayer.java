package keletu.pvzmod.models.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import keletu.pvzmod.PVZConfig;
import keletu.pvzmod.entities.Tallnut;
import keletu.pvzmod.entities.Walnut;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Map;

public class WalnutHelmetLayer<T extends Walnut, M extends HierarchicalModel<T>> extends RenderLayer<T, M> {

    private static final Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();

    private final HumanoidModel<?> defaultBipedModel;
    private final ItemRenderer itemRenderer;

    public WalnutHelmetLayer(RenderLayerParent<T, M> parent,
                             EntityRendererProvider.Context context) {
        super(parent);
        this.defaultBipedModel = new HumanoidModel<>(context.bakeLayer(ModelLayers.ARMOR_STAND_OUTER_ARMOR));
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight,
                       T entity, float limbSwing, float limbSwingAmount,
                       float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (itemstack.isEmpty()) {
            return;
        }

        poseStack.pushPose();

        translateToTop(itemstack, poseStack, entity);

        if (itemstack.getItem() instanceof ArmorItem armorItem
                && armorItem.getType() == ArmorItem.Type.HELMET) {

            HumanoidModel<?> armorModel = getArmorModelHook(entity, itemstack, EquipmentSlot.HEAD, defaultBipedModel);
            boolean glint = itemstack.hasFoil();

            poseStack.pushPose();
            poseStack.scale(0.65F, 0.65F, 0.65F);

            if (armorItem instanceof net.minecraft.world.item.DyeableLeatherItem dyeable) {
                int color = dyeable.getColor(itemstack);
                float r = (float) (color >> 16 & 255) / 255.0F;
                float g = (float) (color >> 8 & 255) / 255.0F;
                float b = (float) (color & 255) / 255.0F;

                renderHelmetModel(entity, poseStack, buffer, packedLight, glint, armorModel, r, g, b,
                        getArmorResource(entity, itemstack, EquipmentSlot.HEAD, null));

                renderHelmetModel(entity, poseStack, buffer, packedLight, glint, armorModel, 1.0F, 1.0F, 1.0F,
                        getArmorResource(entity, itemstack, EquipmentSlot.HEAD, "overlay"));
            } else {
                renderHelmetModel(entity, poseStack, buffer, packedLight, glint, armorModel, 1.0F, 1.0F, 1.0F,
                        getArmorResource(entity, itemstack, EquipmentSlot.HEAD, null));
            }

            poseStack.popPose();
        } else {
            poseStack.pushPose();

            poseStack.scale(0.8F, 0.8F, 0.8F);
            poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

            itemRenderer.renderStatic(
                    itemstack,
                    ItemDisplayContext.HEAD,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    buffer,
                    entity.level(),
                    entity.getId()
            );

            poseStack.popPose();
        }

        poseStack.popPose();
    }

    private void translateToTop(ItemStack stack, PoseStack poseStack, T entity) {
        for (String entry : PVZConfig.HELEMET.get()) {
            String registryName = entry.split(";")[0];
            if (stack.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation(registryName.split(":")[0], registryName.split(":")[1])))) {
                poseStack.scale(Float.parseFloat(entry.split(";")[1]), Float.parseFloat(entry.split(";")[2]), Float.parseFloat(entry.split(";")[3]));
                if (entity instanceof Tallnut) {
                    poseStack.translate(Float.parseFloat(entry.split(";")[7]), Float.parseFloat(entry.split(";")[8]), Float.parseFloat(entry.split(";")[9]));
                } else {
                    poseStack.translate(Float.parseFloat(entry.split(";")[4]), Float.parseFloat(entry.split(";")[5]), Float.parseFloat(entry.split(";")[6]));
                }
                return;
            }
        }
        poseStack.scale(3.0F, 3.0F, 2.8F);
        if (entity instanceof Tallnut) {
            poseStack.translate(0.0F, -0.36F, -0.01F);
        } else {
            poseStack.translate(0.0F, -0.16F, 0.0F);
        }
    }

    private void renderHelmetModel(T entity, PoseStack poseStack, MultiBufferSource buffer,
                                   int packedLight, boolean glint, HumanoidModel<?> model,
                                   float red, float green, float blue, ResourceLocation armorResource) {

        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(
                buffer,
                RenderType.entityCutoutNoCull(armorResource),
                false,
                glint
        );

        setModelVisible(model, EquipmentSlot.HEAD);

        model.head.xRot = 0.0F;
        model.head.yRot = 0.0F;
        model.head.zRot = 0.0F;
        model.hat.xRot = 0.0F;
        model.hat.yRot = 0.0F;
        model.hat.zRot = 0.0F;

        model.head.x = 0.0F;
        model.head.y = 0.0F;
        model.head.z = 0.0F;
        model.hat.x = 0.0F;
        model.hat.y = 0.0F;
        model.hat.z = 0.0F;

        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
    }

    private void setModelVisible(HumanoidModel<?> model, EquipmentSlot slot) {
        model.setAllVisible(false);
        if (slot == EquipmentSlot.HEAD) {
            model.head.visible = true;
            model.hat.visible = true;
        }
    }

    protected HumanoidModel<?> getArmorModelHook(LivingEntity entity, ItemStack itemStack,
                                                 EquipmentSlot slot, HumanoidModel<?> model) {
        Model hookModel = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entity, itemStack, slot, model);
        return hookModel instanceof HumanoidModel<?> humanoidModel ? humanoidModel : model;
    }

    public static ResourceLocation getArmorResource(Entity entity, ItemStack stack,
                                                    EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem) stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "minecraft";
        int idx = texture.indexOf(':');

        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }

        String path = String.format(
                "%s:textures/models/armor/%s_layer_%d%s.png",
                domain,
                texture,
                1,
                type == null ? "" : "_" + type
        );

        path = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, path, slot, type);

        return ARMOR_TEXTURE_RES_MAP.computeIfAbsent(path, ResourceLocation::new);
    }
}
