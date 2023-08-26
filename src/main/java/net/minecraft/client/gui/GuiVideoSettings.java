package net.minecraft.client.gui;

import java.io.IOException;

import net.PeytonPlayz585.shadow.GuiAnimations;
import net.PeytonPlayz585.shadow.GuiShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public class GuiVideoSettings extends GuiScreen {
	private GuiScreen parentGuiScreen;
	protected String screenTitle = "Video Settings";
	private GameSettings guiGameSettings;
	private GuiListExtended optionsRowList;
	/**+
	 * An array of all of GameSettings.Options's video options.
	 */
//	private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {
//			GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION,
//			GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.ANAGLYPH, GameSettings.Options.VIEW_BOBBING,
//			GameSettings.Options.GUI_SCALE, GameSettings.Options.GAMMA, GameSettings.Options.RENDER_CLOUDS,
//			GameSettings.Options.PARTICLES, GameSettings.Options.FXAA, GameSettings.Options.MIPMAP_LEVELS,
//			GameSettings.Options.BLOCK_ALTERNATIVES, GameSettings.Options.ENTITY_SHADOWS, GameSettings.Options.FOG,
//			GameSettings.Options.FULLSCREEN, GameSettings.Options.HUD_FPS, GameSettings.Options.HUD_COORDS,
//			GameSettings.Options.HUD_PLAYER, GameSettings.Options.HUD_STATS, GameSettings.Options.HUD_WORLD,
//			GameSettings.Options.HUD_24H, GameSettings.Options.CHUNK_FIX, GameSettings.Options.FAST_MATH };
	
	/**+
	 * An array of all of GameSettings.Options's video options.
	 */
	private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {
			GameSettings.Options.GRAPHICS,
			GameSettings.Options.RENDER_DISTANCE,
			GameSettings.Options.AMBIENT_OCCLUSION,
			GameSettings.Options.FRAMERATE_LIMIT,
			GameSettings.Options.AO_LEVEL,
			GameSettings.Options.VIEW_BOBBING,
			GameSettings.Options.GUI_SCALE,
			GameSettings.Options.USE_VBO
	};

	public GuiVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
		this.parentGuiScreen = parentScreenIn;
		this.guiGameSettings = gameSettingsIn;
	}

	/**+
	 * Adds the buttons (and other controls) to the screen in
	 * question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		this.screenTitle = I18n.format("options.videoTitle", new Object[0]);
        this.buttonList.clear();

        for (int i = 0; i < videoOptions.length; ++i) {
            GameSettings.Options gamesettings$options = videoOptions[i];

            if (gamesettings$options != null) {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 12;

                if (gamesettings$options.getEnumFloat()) {
                    this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
                } else {
                    this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options)));
                }
            }
        }

        int l = this.height / 6 + 21 * (videoOptions.length / 2) - 12;
        int i1 = 0;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(231, i1, l, I18n.format("Shaders...")));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(202, i1, l, I18n.format("Quality...")));
        l = l + 21;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(201, i1, l, I18n.format("Details...")));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(212, i1, l, I18n.format("Performance...")));
        l = l + 21;
        i1 = this.width / 2 - 155 + 0;
        this.buttonList.add(new GuiOptionButton(211, i1, l, I18n.format("Animations...")));
        i1 = this.width / 2 - 155 + 160;
        this.buttonList.add(new GuiOptionButton(222, i1, l, I18n.format("Other...")));
        l = l + 21;
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
	}

	/**+
	 * Called by the controls from the buttonList when activated.
	 * (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
            int i = this.guiGameSettings.guiScale;

            if (parGuiButton.id < 200 && parGuiButton instanceof GuiOptionButton) {
                this.guiGameSettings.setOptionValue(((GuiOptionButton)parGuiButton).returnEnumOptions(), 1);
                parGuiButton.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(parGuiButton.id));
            }

            if (parGuiButton.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            
            if(parGuiButton.id == 211) {
            	this.mc.displayGuiScreen(new GuiAnimations(this));
            }
            
            if(parGuiButton.id == 231) {
            	this.mc.displayGuiScreen(new GuiShaders(this));
            }

            if (this.guiGameSettings.guiScale != i) {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int j = scaledresolution.getScaledWidth();
                int k = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, j, k);
            }
            
            this.mc.gameSettings.saveOptions();
        }
	}

	/**+
	 * Called when the mouse is clicked. Args : mouseX, mouseY,
	 * clickedButton
	 */
	protected void mouseClicked(int parInt1, int parInt2, int parInt3) {
		int i = this.guiGameSettings.guiScale;
		super.mouseClicked(parInt1, parInt2, parInt3);
		if (this.guiGameSettings.guiScale != i) {
			ScaledResolution scaledresolution = new ScaledResolution(this.mc);
			int j = scaledresolution.getScaledWidth();
			int k = scaledresolution.getScaledHeight();
			this.setWorldAndResolution(this.mc, j, k);
		}

	}

	/**+
	 * Draws the screen and all the components in it. Args : mouseX,
	 * mouseY, renderPartialTicks
	 */
	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 15, 16777215);
        String s = "Shadow Client 3.0";
        this.drawString(this.fontRendererObj, s, 2, this.height - 10, 8421504);
        String s2 = "EaglercraftX 1.8.8";
        int i1 = this.fontRendererObj.getStringWidth(s2);
        this.drawString(this.fontRendererObj, s2, this.width - i1 - 2, this.height - 10, 8421504);
        super.drawScreen(i, j, f);
	}
	
	public static void drawGradientRect(GuiScreen guiScreenIn, int parInt1, int parInt2, int parInt3, int parInt4, int parInt5, int parInt6) {
    	guiScreenIn.drawGradientRect(parInt1, parInt2, parInt3, parInt4, parInt5, parInt6);
    }
}