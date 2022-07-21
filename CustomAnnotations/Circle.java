package com.company.Annotations;

public class Circle extends Figure
{
    @Validator(min = 1,max = 10_000)
    private final double radius;
    @SkipSerialization
    private final double skip = 10;
    private final double doNotSkip = 100;


    public Circle(double radius)
    {
        this.radius = radius;
    }
    @Override
    public double perimeter()
    {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area()
    {
        return Math.PI * radius * radius;
    }
}
