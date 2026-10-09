package keletu.pvzmod.models.gecko;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class PVZGeoModel<T extends GeoAnimatable & PVZGeoResourceProvider> extends GeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T animatable) {
        return animatable.getGeoModelResource();
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return animatable.getGeoTextureResource();
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return animatable.getGeoAnimationResource();
    }
}
