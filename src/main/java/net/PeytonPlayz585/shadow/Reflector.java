package net.PeytonPlayz585.shadow;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.client.resources.DefaultResourcePack;

public class Reflector {

  public static ReflectorClass ResourcePackRepository = new ReflectorClass(net.minecraft.client.resources.ResourcePackRepository.class);
  public static ReflectorField ResourcePackRepository_repositoryEntries = new ReflectorField(ResourcePackRepository, List.class, 1);
  public static ReflectorClass Minecraft = new ReflectorClass(net.minecraft.client.Minecraft.class);
  public static ReflectorField Minecraft_defaultResourcePack = new ReflectorField(Minecraft, DefaultResourcePack.class);

  public static Object getFieldValue(ReflectorField p_getFieldValue_0_) {
    return getFieldValue((Object) null, p_getFieldValue_0_);
  }

  public static Object getFieldValue(Object p_getFieldValue_0_, ReflectorField p_getFieldValue_1_) {
    try {
      Field field = p_getFieldValue_1_.getTargetField();

      if (field == null) {
        return null;
      } else {
        Object object = field.get(p_getFieldValue_0_);
        return object;
      }
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return null;
    }
  }

  public static Object getFieldValue(ReflectorFields p_getFieldValue_0_, int p_getFieldValue_1_) {
    ReflectorField reflectorfield = p_getFieldValue_0_.getReflectorField(p_getFieldValue_1_);
    return reflectorfield == null ? null : getFieldValue(reflectorfield);
  }

  public static Object getFieldValue(Object p_getFieldValue_0_, ReflectorFields p_getFieldValue_1_, int p_getFieldValue_2_) {
    ReflectorField reflectorfield = p_getFieldValue_1_.getReflectorField(p_getFieldValue_2_);
    return reflectorfield == null ? null : getFieldValue(p_getFieldValue_0_, reflectorfield);
  }

  public static float getFieldValueFloat(Object p_getFieldValueFloat_0_, ReflectorField p_getFieldValueFloat_1_, float p_getFieldValueFloat_2_) {
    Object object = getFieldValue(p_getFieldValueFloat_0_, p_getFieldValueFloat_1_);

    if (!(object instanceof Float)) {
      return p_getFieldValueFloat_2_;
    } else {
      Float f = (Float) object;
      return f.floatValue();
    }
  }

  public static boolean setFieldValue(ReflectorField p_setFieldValue_0_, Object p_setFieldValue_1_) {
    return setFieldValue((Object) null, p_setFieldValue_0_, p_setFieldValue_1_);
  }

  public static boolean setFieldValue(Object p_setFieldValue_0_, ReflectorField p_setFieldValue_1_, Object p_setFieldValue_2_) {
    try {
      Field field = p_setFieldValue_1_.getTargetField();

      if (field == null) {
        return false;
      } else {
        field.set(p_setFieldValue_0_, p_setFieldValue_2_);
        return true;
      }
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return false;
    }
  }

  public static boolean matchesTypes(Class[] p_matchesTypes_0_, Class[] p_matchesTypes_1_) {
      if (p_matchesTypes_0_.length != p_matchesTypes_1_.length) {
          return false;
      } else {
          for (int i = 0; i < p_matchesTypes_1_.length; ++i) {
              Class<?> oclass = p_matchesTypes_0_[i];
              Class<?> oclass1 = p_matchesTypes_1_[i];

              if (oclass != oclass1) {
                  return false;
              }
          }

          return true;
      }
  }

}