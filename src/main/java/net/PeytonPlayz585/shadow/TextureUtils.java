package net.PeytonPlayz585.shadow;

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
    
    static class TextureMap {
    	
    	public TextureMap() {
    		
    	}
    	
    	public String getSpriteSafe(String s) {
    		return s;
    	}
    }

}
