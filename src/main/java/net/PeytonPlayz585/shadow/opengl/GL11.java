package net.PeytonPlayz585.shadow.opengl;

import org.teavm.jso.core.JSNumber;
import org.teavm.jso.JSObject;
import org.teavm.jso.webgl.*;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL;
import net.lax1dude.eaglercraft.v1_8.internal.teavm.WebGL2RenderingContext;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;

public class GL11 {
    public static int glGetTexLevelParameteri(int target, int level, int pname) {
        WebGL2RenderingContext gl = PlatformOpenGL.ctx;
        WebGLTexture texture = gl.createTexture();
        gl.bindTexture(target, texture);
        JSObject parameterObj = gl.getTexParameter(target, pname);
        JSNumber parameterNumber = parameterObj.cast();
        int parameter = parameterNumber.intValue();
        gl.bindTexture(target, null);
        return parameter;
    }
}