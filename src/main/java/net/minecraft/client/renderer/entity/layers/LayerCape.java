package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.profile.EaglerProfile;
import net.lax1dude.eaglercraft.v1_8.profile.GuiScreenEditProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

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
public class LayerCape implements LayerRenderer<AbstractClientPlayer> {
	private final RenderPlayer playerRenderer;
	
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

	public LayerCape(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}

	public void doRenderLayer(AbstractClientPlayer abstractclientplayer, float var2, float var3, float f, float var5,
			float var6, float var7, float var8) {
		if (abstractclientplayer.hasPlayerInfo() && !abstractclientplayer.isInvisible()
				&& abstractclientplayer.isWearing(EnumPlayerModelParts.CAPE)
				&& abstractclientplayer.getLocationCape() != null
				&& this.playerRenderer.getMainModel() instanceof ModelPlayer) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			if(GuiScreenEditProfile.capes.containsKey(abstractclientplayer.getName())) {
				this.playerRenderer.bindTexture(defaultVanillaCapes[GuiScreenEditProfile.capes.get(abstractclientplayer.getName())]);
			}
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.0F, 0.125F);
			double d0 = abstractclientplayer.prevChasingPosX
					+ (abstractclientplayer.chasingPosX - abstractclientplayer.prevChasingPosX) * (double) f
					- (abstractclientplayer.prevPosX
							+ (abstractclientplayer.posX - abstractclientplayer.prevPosX) * (double) f);
			double d1 = abstractclientplayer.prevChasingPosY
					+ (abstractclientplayer.chasingPosY - abstractclientplayer.prevChasingPosY) * (double) f
					- (abstractclientplayer.prevPosY
							+ (abstractclientplayer.posY - abstractclientplayer.prevPosY) * (double) f);
			double d2 = abstractclientplayer.prevChasingPosZ
					+ (abstractclientplayer.chasingPosZ - abstractclientplayer.prevChasingPosZ) * (double) f
					- (abstractclientplayer.prevPosZ
							+ (abstractclientplayer.posZ - abstractclientplayer.prevPosZ) * (double) f);
			float f1 = abstractclientplayer.prevRenderYawOffset
					+ (abstractclientplayer.renderYawOffset - abstractclientplayer.prevRenderYawOffset) * f;
			double d3 = (double) MathHelper.sin(f1 * 3.1415927F / 180.0F);
			double d4 = (double) (-MathHelper.cos(f1 * 3.1415927F / 180.0F));
			float f2 = (float) d1 * 10.0F;
			f2 = MathHelper.clamp_float(f2, -6.0F, 32.0F);
			float f3 = (float) (d0 * d3 + d2 * d4) * 100.0F;
			float f4 = (float) (d0 * d4 - d2 * d3) * 100.0F;
			if (f3 < 0.0F) {
				f3 = 0.0F;
			}

			float f5 = abstractclientplayer.prevCameraYaw
					+ (abstractclientplayer.cameraYaw - abstractclientplayer.prevCameraYaw) * f;
			f2 = f2 + MathHelper.sin((abstractclientplayer.prevDistanceWalkedModified
					+ (abstractclientplayer.distanceWalkedModified - abstractclientplayer.prevDistanceWalkedModified)
							* f)
					* 6.0F) * 32.0F * f5;
			if (abstractclientplayer.isSneaking()) {
				f2 += 25.0F;
			}

			GlStateManager.rotate(6.0F + f3 / 2.0F + f2, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(f4 / 2.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(-f4 / 2.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			((ModelPlayer) this.playerRenderer.getMainModel()).renderCape(0.0625F);
			GlStateManager.popMatrix();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}