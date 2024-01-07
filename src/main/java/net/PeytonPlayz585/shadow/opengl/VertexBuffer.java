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

	private ByteBuffer buffer;

	public VertexBuffer() {
    }

    public void func_181722_a(WorldRenderer parWorldRenderer) {
        int count = parWorldRenderer.getVertexCount();

        if (count > 0) {
            VertexFormat format = parWorldRenderer.getVertexFormat();
			buffer = parWorldRenderer.getByteBuffer();
			buffer.clear();
            buffer.limit(count * format.attribStride);

            EaglercraftGPU.renderBuffer(
                buffer,
                format.eaglercraftAttribBits,
                parWorldRenderer.getDrawMode(),
                count
            );

            parWorldRenderer.reset();
        }
	}
}