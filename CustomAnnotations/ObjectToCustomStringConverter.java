package com.company.Annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.StringJoiner;

public class ObjectToCustomStringConverter
{
    public String convert(Object object)
    {
        CustomStringSerializable customStringSerializable = object.getClass().getAnnotation(CustomStringSerializable.class);
        if(Objects.isNull(customStringSerializable))
        {
            throw new CustomStringSerializationException("Class doesn't have proper annotation to convert");
        }

        StringJoiner result = new StringJoiner(",","[","]");
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method meth : methods)
        {
            Validator validator = meth.getAnnotation(Validator.class);
            if(!Objects.isNull(validator))
            {
                meth.setAccessible(true);
                try
                {
                    boolean valid = (boolean)meth.invoke(object);
                    if(!valid)
                    {
                        throw new CustomStringSerializationException("Not valid obj");
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field fi : fields)
        {
            Validator validator = fi.getAnnotation(Validator.class);
            if(validator != null)
            {
                double max = validator.max();
                double min = validator.min();
                fi.setAccessible(true);
                double value = 0;
                try {
                    value = (double) fi.get(object);
                } catch (IllegalAccessException ignored) {}

                if(max != -1 && min != -1)
                {
                    if (value > max || value < min)
                        throw new CustomStringSerializationException("Field is not Valid");
                }
                else if(max == -1)
                {
                    if(value < min)
                        throw new CustomStringSerializationException("Field is not Valid");
                }
                else {
                    if(value > max)
                        throw new CustomStringSerializationException("Field is not Valid");
                }
            }
            if(!Objects.isNull(fi.getAnnotation(SkipSerialization.class)))
                continue;
            fi.setAccessible(true);
            try
            {
                result.add(fi.getName() + "=" + fi.get(object));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }
}
