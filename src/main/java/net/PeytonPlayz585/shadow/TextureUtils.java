package net.PeytonPlayz585.shadow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class TextureUtils {
	
	static TextureMap texturemap = new TextureMap();
	public static String s = "minecraft:blocks/";
	public static String iconGrassTop = texturemap.getSpriteSafe(s + "grass_top");
	public static String iconGrassSide = texturemap.getSpriteSafe(s + "grass_side");
	public static String iconGrassSideOverlay = texturemap.getSpriteSafe(s + "grass_side_overlay");
	public static String iconSnow = texturemap.getSpriteSafe(s + "snow");
	public static String iconGrassSideSnowed = texturemap.getSpriteSafe(s + "grass_side_snowed");
	public static String iconMyceliumSide = texturemap.getSpriteSafe(s + "mycelium_side");
	public static String iconMyceliumTop = texturemap.getSpriteSafe(s + "mycelium_top");
	public static String iconWaterStill = texturemap.getSpriteSafe(s + "water_still");
    public static String iconWaterFlow = texturemap.getSpriteSafe(s + "water_flow");
    public static String iconLavaStill = texturemap.getSpriteSafe(s + "lava_still");
    public static String iconLavaFlow = texturemap.getSpriteSafe(s + "lava_flow");
    public static String iconFireLayer0 = texturemap.getSpriteSafe(s + "fire_layer_0");
    public static String iconFireLayer1 = texturemap.getSpriteSafe(s + "fire_layer_1");
    public static String iconPortal = texturemap.getSpriteSafe(s + "portal");
    public static String iconGlass = texturemap.getSpriteSafe(s + "glass");
    public static String iconGlassPaneTop = texturemap.getSpriteSafe(s + "glass_pane_top");
    public static String s1 = "minecraft:items/";
    public static String iconCompass = texturemap.getSpriteSafe(s1 + "compass");
    public static String iconClock = texturemap.getSpriteSafe(s1 + "clock");
    
    public static String fixResourcePath(String p_fixResourcePath_0_, String p_fixResourcePath_1_) {
        String s = "assets/minecraft/";

        if (p_fixResourcePath_0_.startsWith(s)) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(s.length());
            return p_fixResourcePath_0_;
        } else if (p_fixResourcePath_0_.startsWith("./")) {
            p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);

            if (!p_fixResourcePath_1_.endsWith("/")) {
                p_fixResourcePath_1_ = p_fixResourcePath_1_ + "/";
            }

            p_fixResourcePath_0_ = p_fixResourcePath_1_ + p_fixResourcePath_0_;
            return p_fixResourcePath_0_;
        } else {
            if (p_fixResourcePath_0_.startsWith("/~")) {
                p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(1);
            }

            String s1 = "mcpatcher/";

            if (p_fixResourcePath_0_.startsWith("~/")) {
                p_fixResourcePath_0_ = p_fixResourcePath_0_.substring(2);
                p_fixResourcePath_0_ = s1 + p_fixResourcePath_0_;
                return p_fixResourcePath_0_;
            } else if (p_fixResourcePath_0_.startsWith("/")) {
                p_fixResourcePath_0_ = s1 + p_fixResourcePath_0_.substring(1);
                return p_fixResourcePath_0_;
            } else {
                return p_fixResourcePath_0_;
            }
        }
    }
    
    public static void update() {
        TextureMap texturemap = new TextureMap();

        if (texturemap != null) {
            String s = "minecraft:blocks/";
            iconGrassTop = texturemap.getSpriteSafe(s + "grass_top");
            iconGrassSide = texturemap.getSpriteSafe(s + "grass_side");
            iconGrassSideOverlay = texturemap.getSpriteSafe(s + "grass_side_overlay");
            iconSnow = texturemap.getSpriteSafe(s + "snow");
            iconGrassSideSnowed = texturemap.getSpriteSafe(s + "grass_side_snowed");
            iconMyceliumSide = texturemap.getSpriteSafe(s + "mycelium_side");
            iconMyceliumTop = texturemap.getSpriteSafe(s + "mycelium_top");
            iconWaterStill = texturemap.getSpriteSafe(s + "water_still");
            iconWaterFlow = texturemap.getSpriteSafe(s + "water_flow");
            iconLavaStill = texturemap.getSpriteSafe(s + "lava_still");
            iconLavaFlow = texturemap.getSpriteSafe(s + "lava_flow");
            iconFireLayer0 = texturemap.getSpriteSafe(s + "fire_layer_0");
            iconFireLayer1 = texturemap.getSpriteSafe(s + "fire_layer_1");
            iconPortal = texturemap.getSpriteSafe(s + "portal");
            iconGlass = texturemap.getSpriteSafe(s + "glass");
            iconGlassPaneTop = texturemap.getSpriteSafe(s + "glass_pane_top");
            String s1 = "minecraft:items/";
            iconCompass = texturemap.getSpriteSafe(s1 + "compass");
            iconClock = texturemap.getSpriteSafe(s1 + "clock");
        }
    }
    
    public static String getBasePath(String p_getBasePath_0_) {
        int i = p_getBasePath_0_.lastIndexOf(47);
        return i < 0 ? "" : p_getBasePath_0_.substring(0, i);
    }
    
    public static ITextureObject getTexture(ResourceLocation p_getTexture_0_) {
        ITextureObject itextureobject = Config.getTextureManager().getTexture(p_getTexture_0_);

        if (itextureobject != null) {
            return itextureobject;
        } else if (!Config.hasResource(p_getTexture_0_)) {
            return null;
        } else {
            SimpleTexture simpletexture = new SimpleTexture(p_getTexture_0_);
            Config.getTextureManager().loadTexture(p_getTexture_0_, simpletexture);
            return simpletexture;
        }
    }
    
    public static void registerResourceListener() {
        IResourceManager iresourcemanager = Minecraft.getMinecraft().getResourceManager();

        if (iresourcemanager instanceof IReloadableResourceManager) {
            IReloadableResourceManager ireloadableresourcemanager = (IReloadableResourceManager)iresourcemanager;
            IResourceManagerReloadListener iresourcemanagerreloadlistener = new IResourceManagerReloadListener() {
                public void onResourceManagerReload(IResourceManager resourceManager) {
                    TextureUtils.resourcesReloaded();
                }
            };
            ireloadableresourcemanager.registerReloadListener(iresourcemanagerreloadlistener);
        }
    }
    
    public static void resourcesReloaded() {
        if (getTextureMapBlocks() != null) {
            Config.dbg("*** Reloading custom textures ***");
            CustomSky.reset();
            update();
            BetterGrass.update();
            CustomSky.update();
            SmartLeaves.updateLeavesModels();
            Config.getTextureManager().tick();
        }
    }
    
    public static net.minecraft.client.renderer.texture.TextureMap getTextureMapBlocks() {
        return Minecraft.getMinecraft().getTextureMapBlocks();
    }
    
    static class TextureMap {
    	
    	public TextureMap() {
    		
    	}
    	
    	public String getSpriteSafe(String s) {
    		return s;
    	}
    }

}
