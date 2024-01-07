package net.PeytonPlayz585.shadow.gui;

import net.PeytonPlayz585.shadow.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiOther extends GuiScreen implements GuiYesNoCallback {
    private GuiScreen prevScreen;
    protected String title;
    private GameSettings settings;
    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {GameSettings.Options.LAGOMETER, GameSettings.Options.PROFILER, GameSettings.Options.FULLSCREEN, GameSettings.Options.HUD_FPS, GameSettings.Options.HUD_COORDS, GameSettings.Options.HUD_STATS, GameSettings.Options.HUD_WORLD, GameSettings.Options.HUD_PLAYER, GameSettings.Options.HUD_24H, GameSettings.Options.ANAGLYPH};

    public GuiOther(GuiScreen p_i51_1_) {
        this.prevScreen = p_i51_1_;
        this.settings = Minecraft.getMinecraft().gameSettings;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.title = I18n.format("Other Settings", new Object[0]);
        this.buttonList.clear();

        for (int i = 0; i < enumOptions.length; ++i) {
            GameSettings.Options gamesettings$options = enumOptions[i];
            int j = width / 2 - 155 + i % 2 * 160;
            int k = height / 6 + 21 * (i / 2) - 12;

            if (!gamesettings$options.getEnumFloat()) {
                this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
            }
        }

        this.buttonList.add(new GuiButton(210, width / 2 - 100, height / 6 + 168 + 11 - 44, I18n.format("Reset Video Settings", new Object[0])));
        this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id < 200 && button instanceof GuiOptionButton) {
                this.settings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.prevScreen);
            }

            if (button.id == 210) {
                this.mc.gameSettings.saveOptions();
                GuiYesNo guiyesno = new GuiYesNo(this, I18n.format("Reset all video settings to their default values?", new Object[0]), "", 9999);
                this.mc.displayGuiScreen(guiyesno);
            }
        }
    }

    public void confirmClicked(boolean result, int id) {
        if (result) {
            this.mc.gameSettings.resetSettings();
        }

        this.mc.displayGuiScreen(this);
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}