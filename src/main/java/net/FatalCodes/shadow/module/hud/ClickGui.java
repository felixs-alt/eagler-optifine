package net.FatalCodes.shadow.module.hud;

import java.util.ArrayList;

import de.Hero.settings.Setting;
import net.FatalCodes.shadow.Shadow;
import net.FatalCodes.shadow.module.Category;
import net.FatalCodes.shadow.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends Module {
    public ClickGui clickgui;

    public ClickGui() {
        super("ClickGui", KeyboardConstants.KEY_RSHIFT, Category.HUD);
    }

    public void onEnable() {
    	if(this.clickgui == null)
    		this.clickgui = new ClickGui();
    	
        mc.displayGuiScreen(Shadow.getClickgui());
    	super.onEnable();
        }

    public void onDisable() {
        super.onDisable();
        mc.displayGuiScreen((GuiScreen) null);
        mc.setIngameFocus();
    }
}
