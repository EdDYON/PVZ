package keletu.pvzmod.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class SnowEffect extends MobEffect {
    public static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("FF9DBC2A-EE3F-ACCD-9DF7-F7F1EE4015A9");
    public static final double FROST_MULTIPLIER = -0.10D;

    public SnowEffect() {
        super(MobEffectCategory.HARMFUL, 0X48AAA9);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, SnowEffect.MOVEMENT_SPEED_MODIFIER_UUID.toString(), FROST_MULTIPLIER, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(LivingEntity living, int amplifier) {
        living.setIsInPowderSnow(true);
        if (amplifier > 0 && living.canFreeze()) {
            living.setTicksFrozen(Math.min(living.getTicksRequiredToFreeze(), living.getTicksFrozen() + amplifier));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}