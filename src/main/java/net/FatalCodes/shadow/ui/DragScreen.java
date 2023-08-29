package net.FatalCodes.shadow.ui;

import net.FatalCodes.shadow.Shadow;
import net.FatalCodes.shadow.module.ModuleManager;
import net.FatalCodes.shadow.module.RenderModule;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.FatalCodes.shadow.module.Module;

public class DragScreen extends GuiScreen {

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(1, width/2-100, height/6+148, "Back"));
        super.initGui();
    }

    @Override
    public void drawScreen(int i, int j, float var3) {
        for(Module m : ModuleManager.mods) {
            if(m.isToggled() && m instanceof RenderModule) {
                ((RenderModule)m).renderLayout(i, j);
            }
        }
        super.drawScreen(i, j, var3);
    }

    @Override
    protected void actionPerformed(GuiButton parGuiButton) {
        if(parGuiButton.id == 1) {
            mc.displayGuiScreen(Shadow.getClickgui());
        }
        super.actionPerformed(parGuiButton);
    }
    
}
