package net.FatalCodes.shadow;

import de.Hero.clickgui.ClickGui;
import de.Hero.settings.SettingsManager;
import net.FatalCodes.shadow.event.Event;
import net.FatalCodes.shadow.module.ModuleManager;

public class Shadow {
    public static ModuleManager moduleManager;
    public static SettingsManager setmgr;
    public static ClickGui clickGui;

    
    public static void ShadowClientStartup() {
        moduleManager = new ModuleManager();
        setmgr = new SettingsManager();
        clickGui = new ClickGui();

    }
    public final static ClickGui getClickgui() { return clickGui; }
}
