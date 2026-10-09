package keletu.pvzmod.entities.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class DavePanicGoal extends PanicGoal {
    public boolean isRunning;

    public DavePanicGoal(PathfinderMob pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
    }
}