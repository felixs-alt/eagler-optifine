package net.lax1dude.eaglercraft.v1_8.profile;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

/**
 * Copyright (c) 2022-2023 LAX1DUDE. All Rights Reserved.
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
public class SkinPreviewRenderer {

	private static ModelPlayer playerModelSteve = null;
	private static ModelPlayer playerModelAlex = null;
	private static ModelZombie playerModelZombie = null;
	
	public static final ResourceLocation[] defaultVanillaCapes = new ResourceLocation[] {
			null,
			new ResourceLocation("/capes/c01.minecon_2011.png"),
			new ResourceLocation("/capes/c02.minecon_2012.png"),
			new ResourceLocation("/capes/c03.minecon_2013.png"),
			new ResourceLocation("/capes/c04.minecon_2015.png"),
			new ResourceLocation("/capes/c05.minecon_2016.png"),
			new ResourceLocation("/capes/c06.microsoft_account.png"),
			new ResourceLocation("/capes/c07.mapmaker.png"),
			new ResourceLocation("/capes/c08.mojang_old.png"),
			new ResourceLocation("/capes/c09.mojang_new.png"),
			new ResourceLocation("/capes/c10.jira_mod.png"),
			new ResourceLocation("/capes/c11.mojang_very_old.png"),
			new ResourceLocation("/capes/c12.scrolls.png"),
			new ResourceLocation("/capes/c13.cobalt.png"),
			new ResourceLocation("/capes/c14.translator.png"),
			new ResourceLocation("/capes/c15.millionth_account.png"),
			new ResourceLocation("/capes/c16.prismarine.png"),
			new ResourceLocation("/capes/c17.snowman.png"),
			new ResourceLocation("/capes/c18.spade.png"),
			new ResourceLocation("/capes/c19.birthday.png"),
			new ResourceLocation("/capes/c20.db.png")
	};
	
	public static void initialize() {
		playerModelSteve = new ModelPlayer(0.0f, false);
		playerModelSteve.isChild = false;
		playerModelAlex = new ModelPlayer(0.0f, true);
		playerModelAlex.isChild = false;
		playerModelZombie = new ModelZombie(0.0f, true);
		playerModelZombie.isChild = false;
	}

	public static void renderBiped(int x, int y, int mx, int my, SkinModel skinModel, boolean showCape) {
		ModelBiped model;
		switch(skinModel) {
		case STEVE:
		default:
			model = playerModelSteve;
			break;
		case ALEX:
			model = playerModelAlex;
			break;
		case ZOMBIE:
			model = playerModelZombie;
			break;
		}
		
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
		GlStateManager.disableCull();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 80.0f, 100.0f);
		GlStateManager.scale(50.0f, 50.0f, 50.0f);
		GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
		GlStateManager.scale(1.0f, -1.0f, 1.0f);
		
		RenderHelper.enableGUIStandardItemLighting();
		
		GlStateManager.translate(0.0f, 1.0f, 0.0f);
		if(showCape) {
			GlStateManager.rotate(140.0f, 0.0f, 1.0f, 0.0f);
			mx = x - (x - mx) - 20;
			GlStateManager.rotate(((y - my) * -0.02f), 1.0f, 0.0f, 0.0f);
		} else {
			GlStateManager.rotate(((y - my) * -0.06f), 1.0f, 0.0f, 0.0f);
		}
		GlStateManager.rotate(((x - mx) * 0.06f), 0.0f, 1.0f, 0.0f);
		GlStateManager.translate(0.0f, -1.0f, 0.0f);
		
		model.render(null, 0.0f, 0.0f, (float)(System.currentTimeMillis() % 2000000) / 50f, ((x - mx) * 0.06f), ((y - my) * -0.1f), 0.0625f);
		
		if(showCape && !(EaglerProfile.presetCapeId >= 0 && defaultVanillaCapes[EaglerProfile.presetCapeId] == null)) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.0F, 0.150F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-6.0F, 1.0F, 0.0F, 0.0F);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(defaultVanillaCapes[EaglerProfile.presetCapeId]);

			new ModelBiped(0.0F, 0.0F, 64, 32).bipedCloak.render(0.0625F);
			
			if(EaglerProfile.presetCapeId < 0) {
				GlStateManager.matrixMode(RealOpenGLEnums.GL_TEXTURE);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(RealOpenGLEnums.GL_MODELVIEW);
			}
			
			GlStateManager.popMatrix();
		}
		
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		
		GlStateManager.popMatrix();
		GlStateManager.disableLighting();
	}

}
