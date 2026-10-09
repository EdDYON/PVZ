package keletu.pvzmod.models.gecko;

import net.minecraft.resources.ResourceLocation;

public interface PVZGeoResourceProvider {
    ResourceLocation getGeoModelResource();

    ResourceLocation getGeoTextureResource();

    ResourceLocation getGeoAnimationResource();

    String getGeoAnimationName();
}
