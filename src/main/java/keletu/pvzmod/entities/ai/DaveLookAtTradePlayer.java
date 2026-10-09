package keletu.pvzmod.entities.ai;

import keletu.pvzmod.entities.dave.Dave;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;

public class DaveLookAtTradePlayer extends LookAtPlayerGoal {
    private final Dave dave;

    public DaveLookAtTradePlayer(Dave dave) {
        super(dave, Player.class, 8.0F);
        this.dave = dave;
    }

    @Override
    public boolean canUse() {
        if (this.dave.isTrading()) {
            this.lookAt = this.dave.getTradingPlayer();
            return true;
        }

        return false;
    }
}