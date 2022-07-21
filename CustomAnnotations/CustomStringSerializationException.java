package com.company.Annotations;

public class CustomStringSerializationException extends RuntimeException
{
    public CustomStringSerializationException(String string)
    {
        super(string);
    }
}
