package net.lax1dude.eaglercraft.v1_8.profile;

import java.io.IOException;

import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiScreenEditCape extends GuiScreen {
	
	GuiScreen parent;
	private int skinToShow;
	
	private int mousex = 0;
	private int mousey = 0;
	private boolean dropDownOpen = false;
	private String[] dropDownOptions;
	private int slotsVisible = 0;
	private int selectedSlot = 0;
	private int scrollPos = -1;
	private int skinsHeight = 0;
	private boolean dragging = false;
	SkinModel model = null;
	
	public static final String[] defaultVanillaCapeNames = new String[] {
			"No Cape",
			"Minecon 2011",
			"Minecon 2012",
			"Minecon 2013",
			"Minecon 2015",
			"Minecon 2016",
			"Microsoft Account",
			"Realms Mapmaker",
			"Mojang Old",
			"Mojang New",
			"Jira Moderator",
			"Mojang Very Old",
			"Scrolls",
			"Cobalt",
			"Lang Translator",
			"Millionth Player",
			"Prismarine",
			"Snowman",
			"Spade",
			"Birthday",
			"dB"
	};
	
	public GuiScreenEditCape(GuiScreen parent, int skinToShow, SkinModel model) {
		this.parent = parent;
		this.skinToShow = skinToShow;
		this.dropDownOptions = defaultVanillaCapeNames;
		this.selectedSlot = EaglerProfile.presetCapeId;
		this.model = model;
	}
	
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, I18n.format("gui.done")));
	}
	
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}
	
	private static final ResourceLocation eaglerGui = new ResourceLocation("eagler:gui/eagler_gui.png");
	
	public void drawScreen(int mx, int my, float par3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Select Cape", this.width / 2, 15, 16777215);
		
		mousex = mx;
		mousey = my;
		
		int skinX = width / 2 - 120;
		int skinY = height / 6 + 8;
		int skinWidth = 80;
		int skinHeight = 130;
		
		drawRect(skinX, skinY, skinX + skinWidth, skinY + skinHeight, -6250336);
		drawRect(skinX + 1, skinY + 1, skinX + skinWidth - 1, skinY + skinHeight - 1, 0xff000015);
		
		if(dropDownOpen) {
			super.drawScreen(-1, -1, par3);
		} else {
			super.drawScreen(mx, my, par3);
		}
		
		skinX = width / 2 - 20;
		skinY = height / 6 + 53;
		skinWidth = 140;
		skinHeight = 22;
		
		drawRect(skinX, skinY, skinX + skinWidth, skinY + skinHeight, -6250336);
		drawRect(skinX + 1, skinY + 1, skinX + skinWidth - 21, skinY + skinHeight - 1, -16777216);
		drawRect(skinX + skinWidth - 20, skinY + 1, skinX + skinWidth - 1, skinY + skinHeight - 1, -16777216);
		
		GlStateManager.color(1f, 1f, 1f, 1f);
		mc.getTextureManager().bindTexture(eaglerGui);
		drawTexturedModalRect(skinX + skinWidth - 18, skinY + 3, 0, 240, 16, 16);
		
		this.fontRendererObj.drawStringWithShadow(dropDownOptions[selectedSlot], skinX + 5, skinY + 7, 14737632);
		
		EaglerProfile.presetCapeId = selectedSlot;
		
		skinX = width / 2 - 20;
		skinY = height / 6 + 74;
		skinWidth = 140;
		skinHeight = (height - skinY - 4);
		slotsVisible = (skinHeight / 10);
		if(slotsVisible > dropDownOptions.length) slotsVisible = dropDownOptions.length;
		skinHeight = slotsVisible * 10 + 7;
		skinsHeight = skinHeight;
		if(scrollPos == -1) {
			scrollPos = selectedSlot - 2;
		}
		if(scrollPos > (dropDownOptions.length - slotsVisible)) {
			scrollPos = (dropDownOptions.length - slotsVisible);
		}
		if(scrollPos < 0) {
			scrollPos = 0;
		}
		if(dropDownOpen) {
			drawRect(skinX, skinY, skinX + skinWidth, skinY + skinHeight, -6250336);
			drawRect(skinX + 1, skinY + 1, skinX + skinWidth - 1, skinY + skinHeight - 1, -16777216);
			for(int i = 0; i < slotsVisible; i++) {
				if(i + scrollPos < dropDownOptions.length) {
					int idx = i + scrollPos;
					if(selectedSlot == i + scrollPos) {
						drawRect(skinX + 1, skinY + i*10 + 4, skinX + skinWidth - 1, skinY + i*10 + 14, 0x77ffffff);
					}else if(mx >= skinX && mx < (skinX + skinWidth - 10) && my >= (skinY + i*10 + 5) && my < (skinY + i*10 + 15)) {
						drawRect(skinX + 1, skinY + i*10 + 4, skinX + skinWidth - 1, skinY + i*10 + 14, 0x55ffffff);
					}
					this.fontRendererObj.drawStringWithShadow(dropDownOptions[i + scrollPos], skinX + 5, skinY + 5 + i*10, 14737632);
				}
			}
			int scrollerSize = skinHeight * slotsVisible / dropDownOptions.length;
			int scrollerPos = skinHeight * scrollPos / dropDownOptions.length;
			drawRect(skinX + skinWidth - 4, skinY + scrollerPos + 1, skinX + skinWidth - 1, skinY + scrollerPos + scrollerSize, 0xff888888);
		}
		
		int xx = width / 2 - 80;
		int yy = height / 6 + 130;
		
		SkinPreviewRenderer.renderBiped(xx, yy, mx, my, model, true);
	}
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 200) {
			mc.displayGuiScreen((GuiScreen) parent);
		}
	}
	
	public void updateScreen() {
		if(dropDownOpen) {
			if(Mouse.isButtonDown(0)) {
				int skinX = this.width / 2 - 20;
				int skinY = this.height / 6 + 74;
				int skinWidth = 140;
				if(mousex >= (skinX + skinWidth - 10) && mousex < (skinX + skinWidth) && mousey >= skinY && mousey < (skinY + skinsHeight)) {
					dragging = true;
				}
				if(dragging) {
					int scrollerSize = skinsHeight * slotsVisible / dropDownOptions.length;
					scrollPos = (mousey - skinY - (scrollerSize / 2)) * dropDownOptions.length / skinsHeight;
				}
			} else {
				dragging = false;
			}
		} else {
			dragging = false;
		}
	}
	
	private int[] grabPiece(int[] input, int w, int h, int sw) {
		int[] ret = new int[w * h];
		for(int i = 0; i < h; ++i) {
			System.arraycopy(input, i * sw, ret, i * w, w);
		}
		return ret;
	}
	
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		if(dropDownOpen) {
			int var1 = Mouse.getEventDWheel();
			if(var1 < 0) {
				scrollPos += 3;
			}
			if(var1 > 0) {
				scrollPos -= 3;
				if(scrollPos < 0) {
					scrollPos = 0;
				}
			}
		}
	}

	protected void keyTyped(char par1, int par2) {
		if(par2 == 200 && selectedSlot > 0) {
			--selectedSlot;
			scrollPos = selectedSlot - 2;
		}
		if(par2 == 208 && selectedSlot < (dropDownOptions.length - 1)) {
			++selectedSlot;
			scrollPos = selectedSlot - 2;
		}
	}
	
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		
		if (par3 == 0) {
			int skinX = this.width / 2 + 140 - 40;
			int skinY = this.height / 6 + 53;
		
			if(par1 >= skinX && par1 < (skinX + 20) && par2 >= skinY && par2 < (skinY + 22)) {
				dropDownOpen = !dropDownOpen;
			}
			
			skinX = this.width / 2 - 20;
			skinY = this.height / 6 + 53;
			int skinWidth = 140;
			int skinHeight = skinsHeight;
			
			if(!(par1 >= skinX && par1 < (skinX + skinWidth) && par2 >= skinY && par2 < (skinY + skinHeight + 22))) {
				dropDownOpen = false;
				dragging = false;
			}
			
			skinY += 21;
			
			if(dropDownOpen && !dragging) {
				for(int i = 0; i < slotsVisible; i++) {
					if(i + scrollPos < dropDownOptions.length) {
						if(selectedSlot != i + scrollPos) {
							if(par1 >= skinX && par1 < (skinX + skinWidth - 10) && par2 >= (skinY + i*10 + 5) && par2 < (skinY + i*10 + 15) && selectedSlot != i + scrollPos) {
								selectedSlot = i + scrollPos;
								dropDownOpen = false;
								dragging = false;
							}
						}
					}
				}
			}
		}
	}

}
