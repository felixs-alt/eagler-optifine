package net.PeytonPlayz585.shadow.opengl;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.GL_COMPILE;

import net.lax1dude.eaglercraft.v1_8.internal.IBufferGL;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;
import net.lax1dude.eaglercraft.v1_8.opengl.VertexFormat;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;

public class VertexBuffer {
    //private IBufferGL vbo = null;

    public void bindBuffer(IBufferGL vbo) {
        OpenGLManager.glBindBuffer(RealOpenGLEnums.GL_ARRAY_BUFFER, vbo);
    }

    public void func_181722_a(WorldRenderer p_181722_1_, int parInt1) {
    	if(p_181722_1_.getVertexCount() > 0) {
    		EaglercraftGPU.glNewList(parInt1, GL_COMPILE);
			VertexFormat fmt = p_181722_1_.getVertexFormat();
			ByteBuffer buf = p_181722_1_.getByteBuffer();
			EaglercraftGPU.renderBuffer(buf.position(0).compact().limit(p_181722_1_.getVertexCount() * fmt.attribStride), fmt.eaglercraftAttribBits, 7, p_181722_1_.getVertexCount());
			p_181722_1_.reset();
			EaglercraftGPU.glEndList();
    	}
    }

    public void unbindBuffer(IBufferGL vbo) {
        OpenGLManager.glBindBuffer(RealOpenGLEnums.GL_ARRAY_BUFFER, vbo);
    }
}