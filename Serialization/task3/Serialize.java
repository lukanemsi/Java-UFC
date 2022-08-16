package Serialization;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Serialize
{
    public static boolean isSerializable(Class<?> clazz) {

        if (!(clazz.isPrimitive() || clazz.isInterface() || Serializable.class.isAssignableFrom(clazz)))
            return false;

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isVolatile(mod) || Modifier.isTransient(mod) || Modifier.isStatic(mod))
                continue;
            if (!isSerializable(field.getType()))
                return false;
        }
        return true;
    }

}
