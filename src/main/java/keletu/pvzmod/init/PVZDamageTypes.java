package keletu.pvzmod.init;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PVZDamageTypes {

    static class IndirectDamageSource extends DamageSource {

        public IndirectDamageSource(Holder<DamageType> pType, Entity pDirectEntity, Entity pCausingEntity) {
            super(pType, pDirectEntity, pCausingEntity);
        }
    }

    public static IndirectDamageSource causeElectricPeaProjectileDamage(Level level, @Nullable Entity source, @Nullable Entity indirectEntityIn) {
        Holder<DamageType> holder = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.LIGHTNING_BOLT);
        return new IndirectDamageSource(holder, source, indirectEntityIn);
    }
}