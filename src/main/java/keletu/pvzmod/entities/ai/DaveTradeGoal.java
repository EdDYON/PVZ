package keletu.pvzmod.entities.ai;

import keletu.pvzmod.entities.dave.Dave;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class DaveTradeGoal extends Goal {
    private final Dave dave;

    public DaveTradeGoal(Dave dave) {
        this.dave = dave;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.dave.isAlive()) return false;
        else if (this.dave.isInWater()) return false;
        else if (!this.dave.onGround()) return false;
        else if (this.dave.hurtMarked) return false;
        Player player = this.dave.getTradingPlayer();

        if (player == null) return false;
        else return !(this.dave.distanceToSqr(player) > 16.0D);

    }

    @Override
    public void start() {
        this.dave.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.dave.setTradingPlayer(null);
    }
}