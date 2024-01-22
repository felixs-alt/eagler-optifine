package net.PeytonPlayz585.shadow.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.PeytonPlayz585.shadow.Config;
import net.minecraft.client.resources.DefaultResourcePack;

public class Reflector {

  public static ReflectorClass ResourcePackRepository = new ReflectorClass(net.minecraft.client.resources.ResourcePackRepository.class);
  public static ReflectorField ResourcePackRepository_repositoryEntries = new ReflectorField(ResourcePackRepository, List.class, 1);
  public static ReflectorClass Minecraft = new ReflectorClass(net.minecraft.client.Minecraft.class);
  public static ReflectorField Minecraft_defaultResourcePack = new ReflectorField(Minecraft, DefaultResourcePack.class);
  public static ReflectorClass ForgeBiome = new ReflectorClass(BiomeGenBase.class);
  public static ReflectorMethod ForgeBiome_getWaterColorMultiplier = new ReflectorMethod(ForgeBiome, "getWaterColorMultiplier");

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
  
  public static int callInt(ReflectorMethod p_callInt_0_, Object... p_callInt_1_)
  {
      try {
          Method method = p_callInt_0_.getTargetMethod();

          if (method == null) {
              return 0;
          } else {
              Integer integer = (Integer)method.invoke((Object)null, p_callInt_1_);
              return integer.intValue();
          }
      } catch (Throwable throwable) {
          handleException(throwable, (Object)null, p_callInt_0_, p_callInt_1_);
          return 0;
      }
  }
  
  public static int callInt(Object p_callInt_0_, ReflectorMethod p_callInt_1_, Object... p_callInt_2_) {
      try {
          Method method = p_callInt_1_.getTargetMethod();

          if (method == null) {
              return 0;
          } else {
              Integer integer = (Integer)method.invoke(p_callInt_0_, p_callInt_2_);
              return integer.intValue();
          }
      } catch (Throwable throwable) {
          handleException(throwable, p_callInt_0_, p_callInt_1_, p_callInt_2_);
          return 0;
      }
  }
  
  private static void handleException(Throwable p_handleException_0_, Object p_handleException_1_, ReflectorMethod p_handleException_2_, Object[] p_handleException_3_) {
      if (p_handleException_0_ instanceof InvocationTargetException) {
          Throwable throwable = p_handleException_0_.getCause();

          if (throwable instanceof RuntimeException) {
              RuntimeException runtimeexception = (RuntimeException)throwable;
              throw runtimeexception;
          } else {
              p_handleException_0_.printStackTrace();
          }
      } else {
          if (p_handleException_0_ instanceof IllegalArgumentException) {
              Config.warn("*** IllegalArgumentException ***");
              Config.warn("Method: " + p_handleException_2_.getTargetMethod());
              Config.warn("Object: " + p_handleException_1_);
              Config.warn("Parameter classes: " + Config.arrayToString(getClasses(p_handleException_3_)));
              Config.warn("Parameters: " + Config.arrayToString(p_handleException_3_));
          }

          Config.warn("*** Exception outside of method ***");
          Config.warn("Method deactivated: " + p_handleException_2_.getTargetMethod());
          p_handleException_2_.deactivate();
          p_handleException_0_.printStackTrace();
      }
  }

  private static void handleException(Throwable p_handleException_0_, ReflectorConstructor p_handleException_1_, Object[] p_handleException_2_) {
      if (p_handleException_0_ instanceof InvocationTargetException) {
          p_handleException_0_.printStackTrace();
      } else {
          if (p_handleException_0_ instanceof IllegalArgumentException) {
              Config.warn("*** IllegalArgumentException ***");
              Config.warn("Constructor: " + p_handleException_1_.getTargetConstructor());
              Config.warn("Parameter classes: " + Config.arrayToString(getClasses(p_handleException_2_)));
              Config.warn("Parameters: " + Config.arrayToString(p_handleException_2_));
          }

          Config.warn("*** Exception outside of constructor ***");
          Config.warn("Constructor deactivated: " + p_handleException_1_.getTargetConstructor());
          p_handleException_1_.deactivate();
          p_handleException_0_.printStackTrace();
      }
  }
  
  private static Object[] getClasses(Object[] p_getClasses_0_) {
      if (p_getClasses_0_ == null) {
          return new Class[0];
      } else {
          Class[] aclass = new Class[p_getClasses_0_.length];

          for (int i = 0; i < aclass.length; ++i) {
              Object object = p_getClasses_0_[i];

              if (object != null) {
                  aclass[i] = object.getClass();
              }
          }

          return aclass;
      }
  }

}