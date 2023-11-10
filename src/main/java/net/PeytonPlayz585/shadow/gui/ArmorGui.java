package net.PeytonPlayz585.shadow.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ArmorGui {
  public static void draw() {
    renderPotEffects();
    renderArmorDura();
    renderCPS();
  }

  public static void renderArmorDura() {
    GlStateManager.enableLighting();
    ItemStack boots = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[0];
    ItemStack legs = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[1];
    ItemStack chest = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[2];
    ItemStack helmet = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3];
    ItemStack hand = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();
    if (helmet != null) {
      ItemStack displayhelmet = helmet.copy();
      displayhelmet.stackSize = 1;
      GuiIngame.itemRenderer.renderItemAndEffectIntoGUI(displayhelmet, 5 + 3, 15 + 2);
      GuiIngame.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displayhelmet, 5 + 3, 15 + 2, "");
    }
    if (chest != null) {
      ItemStack displaychest = chest.copy();
      displaychest.stackSize = 1;
      GuiIngame.itemRenderer.renderItemAndEffectIntoGUI(displaychest, 5 + 3, 15 + 18);
      GuiIngame.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displaychest, 5 + 3, 15 + 18, "");
    }
    if (legs != null) {
      ItemStack displaylegs = legs.copy();
      displaylegs.stackSize = 1;
      GuiIngame.itemRenderer.renderItemAndEffectIntoGUI(displaylegs, 5 + 3, 10 + 34);
      GuiIngame.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displaylegs, 5 + 3, 15 + 34, "");
    }
    if (boots != null) {
      ItemStack displayboots = boots.copy();
      displayboots.stackSize = 1;
      GuiIngame.itemRenderer.renderItemAndEffectIntoGUI(displayboots, 5 + 3, 15 + 50);
      GuiIngame.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displayboots, 5 + 3, 15 + 50, "");
    }
    if (hand != null) {
      ItemStack displayhand = hand.copy();
      displayhand.stackSize = 1;
      GuiIngame.itemRenderer.renderItemAndEffectIntoGUI(displayhand, 5 + 3, 15 + 66);
      GuiIngame.itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, displayhand, 5 + 3, 15 + 66, "");
    }

    GlStateManager.disableLighting();
  }
  public static void renderPotEffects() {
    int i = 80;
    int i2 = 16;
    Collection < PotionEffect > collection = Minecraft.getMinecraft().thePlayer.getActivePotionEffects();
    if (!collection.isEmpty()) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
      GlStateManager.enableAlpha();
      int l = 33;
      if (collection.size() > 5) l = 132 / (collection.size() - 1);
      for (PotionEffect potioneffect: Minecraft.getMinecraft().thePlayer.getActivePotionEffects()) {
        Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if (potion.hasStatusIcon()) {
          Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
          int i3 = potion.getStatusIconIndex();
          GuiIngame guiIngame = new GuiIngame(Minecraft.getMinecraft());
          guiIngame.drawTexturedModalRect(4 + 21 - 20, 110 + i2 - 14, 0 + i3 % 8 * 18, 198 + i3 / 8 * 18, 18, 18);
        }
        String s1 = I18n.format(potion.getName(), new Object[0]);
        if (potioneffect.getAmplifier() == 1) {
          s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.2", new Object[0]);
        } else if (potioneffect.getAmplifier() == 2) {
          s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.3", new Object[0]);
        } else if (potioneffect.getAmplifier() == 3) {
          s1 = String.valueOf(String.valueOf(s1)) + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
        Minecraft.getMinecraft().fontRendererObj.drawString(s1, (4 + 21), (110 + i2 - 14), -1);
        String s2 = Potion.getDurationString(potioneffect);
        Minecraft.getMinecraft().fontRendererObj.drawString(s2, (4 + 21), (110 + i2 + 10 - 14), -1);
        i2 += l;
      }
    }
  }

  public static boolean wasPressed;
  public static long lastPressed;
  private static List < Long > clicks = new ArrayList < > ();

  public static void renderCPS() {
    boolean pressed = Mouse.isButtonDown(0) || Mouse.isButtonDown(1);

    if (pressed != wasPressed) {
      lastPressed = System.currentTimeMillis();
      wasPressed = pressed;
      if (pressed) {
        clicks.add(lastPressed);
      }
    }

    final long time = System.currentTimeMillis();
    FuncUtils.removeIf(clicks, sinceLast -> sinceLast + 1000 < time);

    Minecraft.getMinecraft().fontRendererObj.drawString("[CPS: " + clicks.size() + "]", 300 + 2, 5 + 2, -1);
  }

  public static void renderKeyStrokes() {
    Gui.drawRect(400 + 25 + 5, 40, 400 + 25 + 5 + 25, 40 + 25, !Minecraft.getMinecraft().gameSettings.keyBindForward.pressed ? 0x88101010 : 0x88888888);
    Gui.drawRect(400 + 25 + 5, 40 + 5 + 25, 400 + 25 + 5 + 25, 40 + 25 + 5 + 25, !Minecraft.getMinecraft().gameSettings.keyBindBack.pressed ? 0x88101010 : 0x88888888);
    Gui.drawRect(400 + 25 + 5 + 5 + 25, 40 + 5 + 25, 400 + 25 + 5 + 5 + 25 + 25, 40 + 25 + 5 + 25, !Minecraft.getMinecraft().gameSettings.keyBindRight.pressed ? 0x88101010 : 0x88888888);
    Gui.drawRect(400, 40 + 5 + 25, 400 + 25, 40 + 25 + 5 + 25, !Minecraft.getMinecraft().gameSettings.keyBindLeft.pressed ? 0x88101010 : 0x88888888);
    Gui.drawRect(400, 40 + 5 + 25 + 5 + 25, 400 + 40, 40 + 25 + 5 + 25 + 5 + 25, !Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed ? 0x88101010 : 0x88888888);
    Gui.drawRect(400 + 40 + 5, 40 + 5 + 25 + 5 + 25, 400 + 40 + 40 + 5, 40 + 25 + 5 + 25 + 5 + 25, !Minecraft.getMinecraft().gameSettings.keyBindUseItem.pressed ? 0x88101010 : 0x88888888);
    Minecraft.getMinecraft().fontRendererObj.drawString(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode), 400 + 25 + 5 + (25 / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode)) / 2), 40 + 25 / 2 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, Minecraft.getMinecraft().gameSettings.keyBindForward.pressed ? 0 : -1);
    Minecraft.getMinecraft().fontRendererObj.drawString(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindLeft.keyCode), 400 + (25 / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindLeft.keyCode)) / 2), (40 + 25 + (25 / 2)), Minecraft.getMinecraft().gameSettings.keyBindLeft.pressed ? 0 : -1);
    Minecraft.getMinecraft().fontRendererObj.drawString(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindRight.keyCode), 400 + 25 + 25 + 5 + 5 + (25 / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(Minecraft.getMinecraft().gameSettings.getKeyDisplayString(Minecraft.getMinecraft().gameSettings.keyBindRight.keyCode)) / 2), (40 + 25 + (25 / 2)), Minecraft.getMinecraft().gameSettings.keyBindRight.pressed ? 0 : -1);
    Minecraft.getMinecraft().fontRendererObj.drawString("LEFT", 400 + 40 / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("LEFT") / 2, (40 + 25 + 25 + 5 + 5 + 25 / 2) - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed ? 0 : -1);
    Minecraft.getMinecraft().fontRendererObj.drawString("RIGHT", 400 + 40 + 5 + 40 / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth("RIGHT") / 2, (40 + 25 + 25 + 5 + 5 + 25 / 2) - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2, Minecraft.getMinecraft().gameSettings.keyBindUseItem.pressed ? 0 : -1);
  }
}