package net.lax1dude.eaglercraft.v1_8.opengl;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformAssets;

public class ImageData {

    public final int width;
    public final int height;
    public final IntBuffer pixels;
    public final boolean alpha;

    public ImageData(int width, int height, int[] pixels, boolean alpha) {
        this.width = width;
        this.height = height;
        this.pixels = ByteBuffer.allocateDirect(pixels.length * 4)
                .asIntBuffer()
                .put(pixels)
                .flip();
        this.alpha = alpha;
    }

    public ImageData(int width, int height, boolean alpha) {
        this.width = width;
        this.height = height;
        this.pixels = ByteBuffer.allocateDirect(width * height * 4)
                .asIntBuffer();
        this.alpha = alpha;
    }

    public ImageData fillAlpha() {
        for (int i = 0; i < pixels.limit(); i++) {
            pixels.put(i, pixels.get(i) | 0xFF000000);
        }
        return this;
    }

    public ImageData getSubImage(int x, int y, int pw, int ph) {
        int[] img = new int[pw * ph];
        for (int i = 0; i < ph; i++) {
            pixels.position((i + y) * this.width + x);
            pixels.get(img, i * pw, pw);
        }
        return new ImageData(pw, ph, img, alpha);
    }

    public static final ImageData loadImageFile(String path) {
        try (InputStream inputStream = EagRuntime.getResourceStream(path)) {
            if (inputStream != null) {
                return loadImageFile(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final ImageData loadImageFile(InputStream data) {
        return PlatformAssets.loadImageFile(data);
    }

    public static final ImageData loadImageFile(byte[] data) {
        return PlatformAssets.loadImageFile(data);
    }

    public void getRGB(int startX, int startY, int w, int h, int[] rgbArray, int offset, int scansize) {
        for (int y = 0; y < h; y++) {
            pixels.position(offset + (y + startY) * scansize + startX);
            pixels.get(rgbArray, y * w, w);
        }
    }

    public void copyPixelsFrom(ImageData input, int dx1, int dy1, int dx2, int dy2,
            int sx1, int sy1, int sx2, int sy2) {
        int cw = sx2 - sx1;
        int ch = sy2 - sy1;
        if (cw != dx2 - dx1 || ch != dy2 - dy1) {
            throw new IllegalArgumentException("Width and height of the copied region must match the width and height of the pasted region");
        }
        int[] srcPixels = input.getPixels();
        int[] dstPixels = getPixels();
        int srcOffset = sx1 + sy1 * input.width;
        int dstOffset = dx1 + dy1 * width;
        for (int y = 0; y < ch; y++) {
            System.arraycopy(srcPixels, srcOffset, dstPixels, dstOffset, cw);
            srcOffset += input.width;
            dstOffset += width;
        }
    }

    public void drawLayer(ImageData input, int dx1, int dy1, int dx2, int dy2,
            int sx1, int sy1, int sx2, int sy2) {
        int cw = sx2 - sx1;
        int ch = sy2 - sy1;
        if (cw != dx2 - dx1 || ch != dy2 - dy1) {
            throw new IllegalArgumentException("Width and height of the copied region must match the width and height of the pasted region");
        }
        int[] srcPixels = input.getPixels();
        int[] dstPixels = getPixels();
        int srcOffset = sx1 + sy1 * input.width;
        int dstOffset = dx1 + dy1 * width;
        for (int y = 0; y < ch; y++) {
            for (int x = 0; x < cw; x++) {
                int si = srcOffset + x;
                int di = dstOffset + x;
                int spx = srcPixels[si];
                int dpx = dstPixels[di];
                if ((spx & 0xFF000000) == 0xFF000000 || (dpx & 0xFF000000) == 0) {
                    dstPixels[di] = spx;
                } else {
                    int sa = (spx >> 24) & 0xFF;
                    int da = (dpx >> 24) & 0xFF;
                    int r = ((spx >> 16) & 0xFF) * sa / 255;
                    int g = ((spx >> 8) & 0xFF) * sa / 255;
                    int b = (spx & 0xFF) * sa / 255;
                    int aa = (255 - sa) * da;
                    r += ((dpx >> 16) & 0xFF) * aa / 65025;
                    g += ((dpx >> 8) & 0xFF) * aa / 65025;
                    b += (dpx & 0xFF) * aa / 65025;
                    sa += da;
                    if (sa > 0xFF) sa = 0xFF;
                    dstPixels[di] = (sa << 24) | (r << 16) | (g << 8) | b;
                }
            }
            srcOffset += input.width;
            dstOffset += width;
        }
    }

    public ImageData swapRB() {
        int[] dstPixels = getPixels();
        for (int i = 0; i < dstPixels.length; i++) {
            int j = dstPixels[i];
            dstPixels[i] = (j & 0xFF00FF00) | ((j & 0x00FF0000) >> 16) | ((j & 0x000000FF) << 16);
        }
        return this;
    }

    public static int swapRB(int c) {
        return (c & 0xFF00FF00) | ((c & 0x00FF0000) >> 16) | ((c & 0x000000FF) << 16);
    }

	HashMap<IntBuffer, int[]> pixelArray = new HashMap<IntBuffer, int[]>();

	public int[] getPixels() {
		if(pixelArray.containsKey(pixels)) {
			return pixelArray.get(pixels);
		}
        int[] dstPixels = new int[pixels.limit()];
        pixels.get(dstPixels);
		pixelArray.put(pixels, dstPixels);
        return dstPixels;
    }

}