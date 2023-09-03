package net.PeytonPlayz585.shadow.gui;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.EaglercraftVersion;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.profile.GuiScreenEditProfile;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiSecretMainMenu extends GuiScreen {
	
	ResourceLocation mainMenuTexture = new ResourceLocation("textures/gui/title/background/secret/secret.png");
	private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
    
    public void initGui() {
    	int i = height / 4 + 48;
    	this.addSingleplayerMultiplayerButtons(i, 24);
    	this.buttonList.add(new GuiButton(0, width / 2 - 100, i + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
		this.buttonList.add(new GuiButton(4, width / 2 + 2, i + 72 + 12, 98, 20, I18n.format("menu.editProfile", new Object[0])));
		this.buttonList.add(new GuiButtonLanguage(5, width / 2 - 124, i + 72 + 12));
        super.initGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	GlStateManager.pushMatrix();
    	float f = ((float) (1 % 8) / (float) 80 - 0.5F) / 64.0F;
		float f1 = ((float) (1 / 8) / (float) 8 - 0.5F) / 64.0F;
		float f2 = 0.0F;
		short short1 = 274;
		int k = width / 2 - short1 / 2;
		byte b0 = 30;
		GlStateManager.translate(f, f1, f2);
        mc.getTextureManager().bindTexture(mainMenuTexture);
        GlStateManager.translate(f, f1, f2);
        drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        GlStateManager.popMatrix();
        
        drawGradientRect(0, 0, width, height, -2130706433, 16777215);
		drawGradientRect(0, 0, width, height, 0, Integer.MIN_VALUE);
		mc.getTextureManager().bindTexture(minecraftTitleTextures);
		//GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); //no lol...
		drawTexturedModalRect(k + 0, b0 + 0, 0, 0, 99, 44);
		drawTexturedModalRect(k + 99, b0 + 0, 129, 0, 27, 44);
		drawTexturedModalRect(k + 99 + 26, b0 + 0, 126, 0, 3, 44);
		drawTexturedModalRect(k + 99 + 26 + 3, b0 + 0, 99, 0, 26, 44);
		drawTexturedModalRect(k + 154, b0 + 0, 0, 45, 155, 44);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button) {
    	if (button.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}

		if (button.id == 5) {
			this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
		}

		if (button.id == 2) {
			this.mc.displayGuiScreen(new GuiMultiplayer(this));
		}

		if (button.id == 4) {
			this.mc.displayGuiScreen(new GuiScreenEditProfile(this));
		}

		if (button.id == 14) {
			EagRuntime.openLink(EaglercraftVersion.projectForkURL);
		}
		
        super.actionPerformed(button);
    }
    
    private void addSingleplayerMultiplayerButtons(int parInt1, int parInt2) {
    	GuiButton btn;
		this.buttonList.add(new GuiButton(2, width / 2 - 100, parInt1 + parInt2 * 0, I18n.format("menu.multiplayer", new Object[0])));
		this.buttonList.add(btn = new GuiButton(14, width / 2 - 100, parInt1 + parInt2 * 1, I18n.format("menu.forkOnGitlab", new Object[0])));
		btn.enabled = EaglercraftVersion.mainMenuEnableGithubButton;
	}
}