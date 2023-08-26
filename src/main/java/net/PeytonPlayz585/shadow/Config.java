package net.PeytonPlayz585.shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class Config {
	
	public static boolean isAnimatedWater() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedWater != 2;
    }
	
	public static boolean isAnimatedPortal() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedPortal;
    }

    public static boolean isAnimatedLava() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedLava != 2;
    }
    
    public static boolean isAnimatedFire() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedFire;
    }

    public static boolean isAnimatedRedstone() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedRedstone;
    }

    public static boolean isAnimatedExplosion() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedExplosion;
    }

    public static boolean isAnimatedFlame() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedFlame;
    }

    public static boolean isAnimatedSmoke() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedSmoke;
    }

    public static boolean isVoidParticles() {
        return Minecraft.getMinecraft().gameSettings.ofVoidParticles;
    }

    public static boolean isWaterParticles() {
        return Minecraft.getMinecraft().gameSettings.ofWaterParticles;
    }
    
    public static boolean isPortalParticles() {
        return Minecraft.getMinecraft().gameSettings.ofPortalParticles;
    }

    public static boolean isPotionParticles() {
        return Minecraft.getMinecraft().gameSettings.ofPotionParticles;
    }

    public static boolean isFireworkParticles() {
        return Minecraft.getMinecraft().gameSettings.ofFireworkParticles;
    }
    
    public static boolean isAnimatedTerrain() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedTerrain;
    }

    public static boolean isAnimatedTextures() {
        return Minecraft.getMinecraft().gameSettings.ofAnimatedTextures;
    }
    
    public static boolean isDrippingWaterLava() {
        return Minecraft.getMinecraft().gameSettings.ofDrippingWaterLava;
    }
    
    public static float getAmbientOcclusionLevel() {
        return isShaders() ? 1.0F : Minecraft.getMinecraft().gameSettings.ofAoLevel;
    }

	public static int limit(int p_limit_0_, int p_limit_1_, int p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static float limit(float p_limit_0_, float p_limit_1_, float p_limit_2_) {
        return p_limit_0_ < p_limit_1_ ? p_limit_1_ : (p_limit_0_ > p_limit_2_ ? p_limit_2_ : p_limit_0_);
    }

    public static double limit(double p_limit_0_, double p_limit_2_, double p_limit_4_) {
        return p_limit_0_ < p_limit_2_ ? p_limit_2_ : (p_limit_0_ > p_limit_4_ ? p_limit_4_ : p_limit_0_);
    }

    public static float limitTo1(float p_limitTo1_0_) {
        return p_limitTo1_0_ < 0.0F ? 0.0F : (p_limitTo1_0_ > 1.0F ? 1.0F : p_limitTo1_0_);
    }
    
    public static boolean isShaders() {
    	return Minecraft.getMinecraft().gameSettings.shaders;
    }
	
}
