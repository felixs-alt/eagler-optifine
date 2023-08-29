package net.FatalCodes.shadow;

import net.FatalCodes.shadow.module.ModuleManager;

public class Shadow {
    public static ModuleManager moduleManager;

    
    public static void ShadowClientStartup() {
        moduleManager = new ModuleManager();
    }
}
