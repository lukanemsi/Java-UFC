package com.company.Annotations;


public class Triangle extends Figure
{
    @Validator(max = 2000)
    private final double a;
    @Validator(max = 2000)
    private final double b;
    @Validator(max = 2000)
    private final double c;
    @SkipSerialization
    private final String skip = "skipped";
    private final String notSkipped = "Not";

    public Triangle(double a,double b,double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Validator
    private boolean triangleValidator()
    {
        return a + b > c && b + c > a && c + a > b;
    }


    @Override
    public double area() {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }
    @Override
    public double perimeter() {
        return a + b + c;
    }
}
