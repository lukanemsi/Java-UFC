package com.company.InnerClasses;

public abstract class FigureValidator
{
    public abstract void validate(String figureRegex) throws FigureValidatorException;
}
class FigureValidatorException extends Exception
{
    public FigureValidatorException(String text)
    {
        super(text);
    }
}
