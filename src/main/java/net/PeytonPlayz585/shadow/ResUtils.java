package net.PeytonPlayz585.shadow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;

import net.lax1dude.eaglercraft.v1_8.internal.vfs.*;

public class ResUtils {

  private static final VirtualFilesystem VFS = SYS.VFS;

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
      return collectFilesFolder(file1, "", p_collectFiles_1_, p_collectFiles_2_);
    }
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

  private static String[] collectFilesFolder(String p_collectFilesFolder_0_, String p_collectFilesFolder_1_, String[] p_collectFilesFolder_2_, String[] p_collectFilesFolder_3_) {
    List list = new ArrayList();
    String s = "resourcepacks/" + p_collectFilesFolder_0_ + "assets/minecraft/";
    String[] afile = VFS.listFiles(s).toArray(new String[0]);

    for (int i = 0; i < afile.length; ++i) {
      String file1 = afile[i];

      if (!file1.endsWith("/")) {
        String[] fileName = file1.split("/");
        int yee = fileName.length;
        String s3 = p_collectFilesFolder_1_ + fileName[yee - 2];

        if (s3.startsWith(s)) {
          s3 = s3.substring(s.length());

          if (StrUtils.startsWith(s3, p_collectFilesFolder_2_) && StrUtils.endsWith(s3, p_collectFilesFolder_3_)) {
            list.add(s3);
          }
        }
      } else if (file1.endsWith("/")) {
        String[] fileName = file1.split("/");
        int yee = fileName.length;
        String s1 = p_collectFilesFolder_1_ + fileName[yee - 1];
        String[] astring = collectFilesFolder(file1, s1, p_collectFilesFolder_2_, p_collectFilesFolder_3_);

        for (int j = 0; j < astring.length; ++j) {
          String s2 = astring[j];
          list.add(s2);
        }
      }
    }

    String[] astring1 = (String[])((String[]) list.toArray(new String[list.size()]));
    return astring1;
  }
}