package net.PeytonPlayz585.shadow.shaders;

import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.EaglerDeferredConfig;
import net.minecraft.client.Minecraft;

public class Shaders {
	
	public static boolean shaderProfileChanged = false;
	
	public void updateShaderProfile(int i) {
		switch (i) {
			case 1:
				setShaderProfileToLow();
				break;
				
			case 2:
				setShaderProfileToMedium();
				break;
				
			case 3:
				setShaderProfileToHigh();
				break;
				
			case 4:
				setShaderProfileToUltra();
				break;
		}
		Shaders.shaderProfileChanged = true;
	}
	
	public void setShaderProfileToLow() {
		EaglerDeferredConfig conf = Minecraft.getMinecraft().gameSettings.deferredShaderConf;
		conf.wavingBlocks = false;
		conf.dynamicLights = false;
		conf.ssao = false;
		conf.shadowsSun = 16;
		conf.useEnvMap = false;
		conf.lensDistortion = false;
		conf.lensFlares = false;
		conf.fxaa = false;
		conf.shadowsColored = false;
		conf.shadowsSmoothed = false;
		conf.realisticWater = false;
		conf.bloom = false;
		conf.lightShafts = false;
		conf.raytracing = false;
	}
	
	public void setShaderProfileToMedium() {
		EaglerDeferredConfig conf = Minecraft.getMinecraft().gameSettings.deferredShaderConf;
		conf.wavingBlocks = true;
		conf.dynamicLights = false;
		conf.ssao = true;
		conf.shadowsSun = 32;
		conf.useEnvMap = false;
		conf.lensDistortion = false;
		conf.lensFlares = false;
		conf.fxaa = true;
		conf.shadowsColored = false;
		conf.shadowsSmoothed = true;
		conf.realisticWater = false;
		conf.bloom = false;
		conf.lightShafts = false;
		conf.raytracing = false;
	}
	
	public void setShaderProfileToHigh() {
		EaglerDeferredConfig conf = Minecraft.getMinecraft().gameSettings.deferredShaderConf;
		conf.wavingBlocks = true;
		conf.dynamicLights = true;
		conf.ssao = true;
		conf.shadowsSun = 64;
		conf.useEnvMap = true;
		conf.lensDistortion = true;
		conf.lensFlares = true;
		conf.fxaa = true;
		conf.shadowsColored = true;
		conf.shadowsSmoothed = true;
		conf.realisticWater = false;
		conf.bloom = false;
		conf.lightShafts = false;
		conf.raytracing = false;
	}
	
	public void setShaderProfileToUltra() {
		EaglerDeferredConfig conf = Minecraft.getMinecraft().gameSettings.deferredShaderConf;
		conf.wavingBlocks = true;
		conf.dynamicLights = true;
		conf.ssao = true;
		conf.shadowsSun = 128;
		conf.useEnvMap = true;
		conf.lensDistortion = true;
		conf.lensFlares = true;
		conf.fxaa = true;
		conf.shadowsColored = true;
		conf.shadowsSmoothed = true;
		conf.realisticWater = true;
		conf.bloom = true;
		conf.lightShafts = true;
		conf.raytracing = true;
	}
}
