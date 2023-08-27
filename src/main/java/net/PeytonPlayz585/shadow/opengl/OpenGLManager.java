package net.PeytonPlayz585.shadow.opengl;

import net.lax1dude.eaglercraft.v1_8.internal.IBufferGL;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;

public class OpenGLManager {

	public static void setClientActiveTexture(int lightmaptexunit) {
		PlatformOpenGL._wglActiveTexture(lightmaptexunit);
	}

	public static void glBindBuffer(int glArrayBuffer, IBufferGL vbo) {
		PlatformOpenGL._wglBindBuffer(glArrayBuffer, vbo);
	}

	public static void glBufferData(int glArrayBuffer, ByteBuffer p_181722_1_, int i) {
		PlatformOpenGL._wglBufferData(glArrayBuffer, p_181722_1_, i);
	}

	public static void glDrawArrays(int glTriangles, int i, int j) {
		PlatformOpenGL._wglDrawArrays(RealOpenGLEnums.GL_TRIANGLES, 0, 6);
	}

	public static void glEnableClientState(int i) {
		//PlatformOpenGL._wglEnableVertexAttribArray(i);
	}

}
