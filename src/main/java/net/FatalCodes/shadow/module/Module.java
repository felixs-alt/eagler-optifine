package net.FatalCodes.shadow.module;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.Hero.settings.Setting;
import net.FatalCodes.shadow.Shadow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.Packet;

public class Module {

    protected Minecraft mc = Minecraft.getMinecraft();
    public String name;
    private int key;
    public boolean toggled;
    //if jesus no work change above to private again
    Category category;
    public boolean blatant;
    public Module(String nm, int k, Category c) {
        name = nm;
        key = k;
        category = c;
        toggled = false;
    }
    public Module(String nm, int k, Category c, boolean blatant) {
        if(blatant) {
			this.blatant = true;
			name = "⚠ " + nm;
		} else
        name = nm;
        key = k;
        category = c;
        toggled = false;
    }

    public void toggle() {
        toggled = !toggled;
        if(toggled) {
            onEnable();
        }else {
            onDisable();
        }
    }

    public void onEnable() { }
    public void onDisable() { }
    public void onUpdate() { }
    public void onRender() { }

    public void addAll(Setting... settings) {
        for(Setting s : settings)
        Shadow.setmgr.rSetting(s);
    }

    public Minecraft getMc() {
        return mc;
    }

    public void setMc(Minecraft mc) {
        this.mc = mc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    protected EntityPlayerSP player() {
		return mc.thePlayer;
	}
	
	protected PlayerControllerMP playerController() {
		return mc.playerController;
	}
	
	protected WorldClient world() {
		return mc.theWorld;
	}
	
	@SuppressWarnings("rawtypes")
	protected void sendPacket(Packet p) {
		player().sendQueue.addToSendQueue(p);
	}
	
}