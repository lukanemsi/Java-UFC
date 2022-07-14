package com.company.RTI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**task 4*/
public enum FigureMethod
{
    GET_LENGTH("perimeter"), GET_AREA("area");

    /** დამჭირდა String command რადგან enum_ის სახელს და Figure ფუნქციას გასხვავებული სახელი აქვს*/
    private String command;
    private FigureMethod(String command)
    {
        this.command = command;
    }
    public String getCommand()
    {
        return command;
    }
    public static double method4(Figure f,FigureMethod m)
    {

        try {
            Method method = m.getClass().getDeclaredMethod("getCommand");
            String command = (String)method.invoke(m);
            return (double) f.getClass().getDeclaredMethod(command).invoke(f);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }
}
