package net.PeytonPlayz585.shadow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformAssets;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;

public class ResUtils {
  public static String[] collectFiles(String p_collectFiles_0_, String p_collectFiles_1_) {
    return collectFiles(new String[] {
      p_collectFiles_0_
    }, new String[] {
      p_collectFiles_1_
    });
  }

  public static String[] collectFiles(String[] p_collectFiles_0_, String[] p_collectFiles_1_) {
    Set < String > set = new LinkedHashSet();
    IResourcePack[] airesourcepack = Config.getResourcePacks();

    for (int i = 0; i < airesourcepack.length; ++i) {
      IResourcePack iresourcepack = airesourcepack[i];
      String[] astring = collectFiles(iresourcepack, (String[]) p_collectFiles_0_, (String[]) p_collectFiles_1_, (String[]) null);
      set.addAll(Arrays. < String > asList(astring));
    }

    String[] astring1 = (String[]) set.toArray(new String[set.size()]);
    return astring1;
  }

  public static String[] collectFiles(IResourcePack p_collectFiles_0_, String p_collectFiles_1_, String p_collectFiles_2_, String[] p_collectFiles_3_) {
    return collectFiles(p_collectFiles_0_, new String[] {
      p_collectFiles_1_
    }, new String[] {
      p_collectFiles_2_
    }, p_collectFiles_3_);
  }

  public static String[] collectFiles(IResourcePack p_collectFiles_0_, String[] p_collectFiles_1_, String[] p_collectFiles_2_) {
    return collectFiles(p_collectFiles_0_, (String[]) p_collectFiles_1_, (String[]) p_collectFiles_2_, (String[]) null);
  }

  public static String[] collectFiles(IResourcePack p_collectFiles_0_, String[] p_collectFiles_1_, String[] p_collectFiles_2_, String[] p_collectFiles_3_) {
    if (p_collectFiles_0_ instanceof DefaultResourcePack) {
      return collectFilesFixed(p_collectFiles_0_, p_collectFiles_3_);
    } else if (!(p_collectFiles_0_ instanceof AbstractResourcePack)) {
      return new String[0];
    } else {
      AbstractResourcePack abstractresourcepack = (AbstractResourcePack) p_collectFiles_0_;
      String file1 = abstractresourcepack.resourcePackFile;
      return file1 == null || file1.isEmpty() || file1 == "" || file1 == " " ? new String[0] : (isDirectory(file1) ? collectFilesFolder(file1, p_collectFiles_1_, p_collectFiles_2_) : yee());
    }
  }
  
  public static String[] yee() {
	  System.err.println("[Fatal error] resourcePackFile is NOT empty but it's NOT a directory!");
	  return new String[0];
  }

  private static String[] collectFilesFixed(IResourcePack p_collectFilesFixed_0_, String[] p_collectFilesFixed_1_) {
    if (p_collectFilesFixed_1_ == null) {
      return new String[0];
    } else {
      List list = new ArrayList();

      for (int i = 0; i < p_collectFilesFixed_1_.length; ++i) {
        String s = p_collectFilesFixed_1_[i];
        ResourceLocation resourcelocation = new ResourceLocation(s);

        if (p_collectFilesFixed_0_.resourceExists(resourcelocation)) {
          list.add(s);
        }
      }

      String[] astring = (String[])((String[]) list.toArray(new String[list.size()]));
      return astring;
    }
  }

  private static String[] collectFilesFolder(String p_collectFilesFolder_0_, String[] p_collectFilesFolder_1_, String[] p_collectFilesFolder_2_) {
	  return PlatformAssets.listFilesInDirectory(p_collectFilesFolder_0_, p_collectFilesFolder_1_, p_collectFilesFolder_2_);
  }
  
  public static boolean isFile(String path) {
      return path.matches(".+\\.[^.]+$");
  }

  public static boolean isDirectory(String path) {
      return path.matches(".+[\\\\/]$");
  }
}