package net.FatalCodes.shadow.module.pvp;

import net.FatalCodes.shadow.module.Category;
import net.FatalCodes.shadow.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

public class NoHurtCam extends Module {
    public NoHurtCam() {
        super("NoHurtCam", KeyboardConstants.KEY_NONE, Category.PVP);
    }

    public void onUpdate() {
        if(this.isToggled()) {
            mc.thePlayer.maxHurtTime = 0;
        }
    }
    
}
