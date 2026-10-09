package keletu.pvzmod.entities;

import keletu.pvzmod.entities.projectile.PrimalPeaProjectile;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;

public class SuperPrimalGatlingPea extends SuperGatlingPea {
    public SuperPrimalGatlingPea(EntityType<? extends SuperPrimalGatlingPea> type, Level level) {
        super(type, level);
    }

    @Override
    public ThrowableProjectile entitySelect(Level world) {
        PrimalPeaProjectile ent = new PrimalPeaProjectile(
                world,
                this,
                this.plantStatFloat(PlantStatManager.PROJECTILE_DAMAGE, 3.0D),
                this.random.nextFloat() * 100.0F < this.plantStatFloat("special_chance_percent", 15.0D) ? 1 : 0
        );
        return ent;
    }
}
