package net.PeytonPlayz585.shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiAnimations extends GuiScreen {
    private GuiScreen prevScreen;
    protected String title;

    private static GameSettings.Options[] enumOptions = new GameSettings.Options[] {
    		GameSettings.Options.ANIMATED_WATER, 
    		GameSettings.Options.ANIMATED_LAVA, 
    		GameSettings.Options.ANIMATED_FIRE, 
    		GameSettings.Options.ANIMATED_PORTAL, 
    		GameSettings.Options.ANIMATED_REDSTONE, 
    		GameSettings.Options.ANIMATED_EXPLOSION, 
    		GameSettings.Options.ANIMATED_FLAME, 
    		GameSettings.Options.ANIMATED_SMOKE, 
    		GameSettings.Options.VOID_PARTICLES, 
    		GameSettings.Options.WATER_PARTICLES, 
    		//GameSettings.Options.RAIN_SPLASH, 
    		GameSettings.Options.PORTAL_PARTICLES, 
    		GameSettings.Options.POTION_PARTICLES, 
    		GameSettings.Options.DRIPPING_WATER_LAVA, 
    		GameSettings.Options.ANIMATED_TERRAIN, 
    		GameSettings.Options.ANIMATED_TEXTURES, 
    		GameSettings.Options.FIREWORK_PARTICLES, 
    		GameSettings.Options.PARTICLES
    		};

    public GuiAnimations(GuiScreen p_i46_1_) {
        this.prevScreen = p_i46_1_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui() {
        this.title = I18n.format("Animation Settings", new Object[0]);
        this.buttonList.clear();

        for (int i = 0; i < enumOptions.length; ++i) {
            GameSettings.Options gamesettings$options = enumOptions[i];
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;

            if (gamesettings$options.getEnumFloat()) {
                this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
            } else {
                this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, Minecraft.getMinecraft().gameSettings.getKeyBinding(gamesettings$options)));
            }
        }

        this.buttonList.add(new GuiButton(210, this.width / 2 - 155, this.height / 6 + 168 + 11, 70, 20, "ALL ON"));
        this.buttonList.add(new GuiButton(211, this.width / 2 - 155 + 80, this.height / 6 + 168 + 11, 70, 20, "ALL OFF"));
        this.buttonList.add(new GuiOptionButton(200, this.width / 2 + 5, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id < 200 && button instanceof GuiOptionButton) {
                Minecraft.getMinecraft().gameSettings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = Minecraft.getMinecraft().gameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.prevScreen);
            }

            if (button.id == 210) {
                this.mc.gameSettings.setAllAnimations(true);
            }

            if (button.id == 211) {
                this.mc.gameSettings.setAllAnimations(false);
            }

            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            this.setWorldAndResolution(this.mc, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}