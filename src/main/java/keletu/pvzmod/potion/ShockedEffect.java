package keletu.pvzmod.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.UUID;

public class ShockedEffect extends MobEffect {
    public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("FF9DBC2A-EE3F-ACCD-9DF7-F8F1EE4015AB");
    public static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("A7F35FBE-0B6E-6666-9B2F-2234567890AC");

    public ShockedEffect() {
        super(MobEffectCategory.HARMFUL, 0XDCC559);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ShockedEffect.ATTACK_DAMAGE_MODIFIER.toString(), -1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ShockedEffect.MOVEMENT_SPEED_MODIFIER.toString(), -1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.xxa = 0;
        entity.zza = 0;

        if (entity instanceof Mob mob) {
            entity.setXRot(30.0F);
            entity.xRotO = 30.0F;
            if (!mob.level().isClientSide) {
                mob.goalSelector.setControlFlag(Goal.Flag.MOVE, false);
                mob.goalSelector.setControlFlag(Goal.Flag.JUMP, false);
                mob.goalSelector.setControlFlag(Goal.Flag.LOOK, false);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}