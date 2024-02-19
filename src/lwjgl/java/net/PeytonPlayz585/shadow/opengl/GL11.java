package net.PeytonPlayz585.shadow.opengl;

public class GL11 {
    public static int glGetTexLevelParameteri(int target, int level, int pname) {
        return org.lwjgl.opengles.GLES31.glGetTexLevelParameteri(target, level, pname);
    }
}