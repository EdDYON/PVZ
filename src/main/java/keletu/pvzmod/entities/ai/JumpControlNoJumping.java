package keletu.pvzmod.entities.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.JumpControl;

public class JumpControlNoJumping extends JumpControl {
    public JumpControlNoJumping(Mob pMob) {
        super(pMob);
    }

    public void jump() {
    }
}
