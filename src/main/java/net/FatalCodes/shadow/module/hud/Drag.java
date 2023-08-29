package net.FatalCodes.shadow.module.hud;

import net.FatalCodes.shadow.module.Category;
import net.FatalCodes.shadow.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.FatalCodes.shadow.ui.DragScreen;

public class Drag extends Module {
    public DragScreen dragScreen;
    public Drag() {
        super("DragScreen", KeyboardConstants.KEY_O, Category.HUD);
    }

    public void onEnable() {
        if(this.dragScreen == null)
        this.dragScreen = new DragScreen();
        mc.displayGuiScreen(new DragScreen());
        super.onEnable();
    }
    public void onDisable() {
        super.onDisable();
        mc.displayGuiScreen((GuiScreen) null);
        mc.setIngameFocus();
    }
}
