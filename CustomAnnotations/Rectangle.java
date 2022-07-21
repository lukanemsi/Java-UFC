package com.company.Annotations;


public class Rectangle extends Figure
{

    private final double width,length;
    private final double notSkipped = 10;
    @SkipSerialization
    private final double skipped = 100;
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }
    @Validator
    private boolean rectangleValidator()
    {
        return width > 0 && length > 0;
    }
    @Override
    public double perimeter() {
        return (width + length) * 2;
    }

    @Override
    public double area() {
        return width * length;
    }
}
