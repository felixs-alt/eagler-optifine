package net.FatalCodes.shadow.module.pvp;

import net.FatalCodes.shadow.module.Category;
import net.FatalCodes.shadow.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.network.Packet;


public class AutoWtap extends Module {
    public AutoWtap() {
		super("AutoWtap", KeyboardConstants.KEY_NONE, Category.PVP);
	}
	
	private Float coolDown = 0f;
    
    @Override
    public void onUpdate() {
        if(this.isToggled()) {
    	coolDown -= 1f;
    	mc.thePlayer.setSprinting(true);
        if (mc.thePlayer.isSwingInProgress)
        	if (coolDown < 0) {
        		mc.thePlayer.setSprinting(false);
        		coolDown = 3f;
        		
        	}
        }
    }
    
}

