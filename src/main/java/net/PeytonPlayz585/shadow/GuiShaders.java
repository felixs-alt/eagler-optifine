package net.PeytonPlayz585.shadow;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;

public class GuiShaders extends GuiScreen {
	protected GuiScreen parentScreen;
	private GuiShaders.List list;
	GuiButton shaderOptions = null;

	public GuiShaders(GuiScreen screen) {
		this.parentScreen = screen;
	}

	public void initGui() {
		this.buttonList.clear();
		int i = 120;
        int j = 20;
        int k = this.width - i - 10;
        int j1 = this.width - i - 20;
        int k1 = Math.min(150, j1 / 2 - 10);
        GuiButton button;
		this.buttonList.add(new GuiButton(6, j1 / 4 * 3 - k1 / 2, this.height - 25, k1, j, I18n.format("gui.done", new Object[0])));
		this.buttonList.add(button = new GuiButton(201, j1 / 4 - k1 / 2, this.height - 25, k1, j, "Shaders Folder"));
		button.enabled = false;
		this.buttonList.add(shaderOptions = new GuiButton(203, k, this.height - 25, i, j, "Shader Options"));
		shaderOptions.enabled = Minecraft.getMinecraft().gameSettings.shaders;
		this.list = new GuiShaders.List(this.mc);
		this.list.registerScrollButtons(7, 8);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.list.handleMouseInput();
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if(parGuiButton.enabled) {
			if(parGuiButton.id == 6) {
				Minecraft.getMinecraft().displayGuiScreen(parentScreen);
			}
		}
	}

	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
        this.list.drawScreen(i, j, f);
        
        this.drawCenteredString(this.fontRendererObj, "Shaders", this.width / 2, 15, 16777215);
        
        super.drawScreen(i, j, f);
	}

	class List extends GuiSlot {
	    java.util.Map<Integer, String> languageMap = new java.util.HashMap<>();

		public List(Minecraft mcIn) {
			super(mcIn, GuiShaders.this.width - 120 - 20, GuiShaders.this.height, 30, GuiShaders.this.height - 50, 16);
			
			languageMap.put(1, "OFF");
			languageMap.put(2, "High Performance PBR");
		}
		
		public int getListWidth() {
	        return this.width - 20;
	    }

		protected int getSize() {
			return 2;
		}

		protected void elementClicked(int i, boolean var2, int var3, int var4) {
			if(i == 0 && !Minecraft.getMinecraft().gameSettings.shaders) {
				return;
			} else if(i == 0 && Minecraft.getMinecraft().gameSettings.shaders) {
				Minecraft.getMinecraft().gameSettings.shaders = false;
				Minecraft.getMinecraft().loadingScreen.eaglerShowRefreshResources();
				Minecraft.getMinecraft().refreshResources();
				Minecraft.getMinecraft().gameSettings.saveOptions();
				shaderOptions.enabled = false;
				GuiShaders.this.updateScreen();
			} else if(i == 1 && Minecraft.getMinecraft().gameSettings.shaders) {
				return;
			} else {
				Minecraft.getMinecraft().gameSettings.shaders = true;
			    Minecraft.getMinecraft().loadingScreen.eaglerShowRefreshResources();
				Minecraft.getMinecraft().refreshResources();
				Minecraft.getMinecraft().gameSettings.saveOptions();
				shaderOptions.enabled = true;
				GuiShaders.this.updateScreen();
			}
		}

		protected boolean isSelected(int i) {
			if(i == 1 && Minecraft.getMinecraft().gameSettings.shaders) {
				return true;
			} else if(i == 1 && !Minecraft.getMinecraft().gameSettings.shaders) {
				return false;
			} else if(i == 0 && Minecraft.getMinecraft().gameSettings.shaders == false) {
				return true;
			} else {
				return false;
			}
		}
		
		protected int getScrollBarX() {
	        return this.width - 6;
	    }

		protected int getContentHeight() {
			return this.getSize() * 18;
		}

		protected void drawBackground() {
			//yee...
		}

		protected void drawSlot(int i, int var2, int j, int var4, int var5, int var6) {
			String s = this.languageMap.get(i + 1);
			GuiShaders.this.drawCenteredString(GuiShaders.this.fontRendererObj, s, this.width / 2, j + 1, 16777215);
		}
	}
}