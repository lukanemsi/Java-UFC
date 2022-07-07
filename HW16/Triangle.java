package com.company;

public class Triangle extends Figure
{
    private final double a;
    private final double b;
    private final double c;
    public Triangle(double a,double b,double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public double area() {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }

    @Override
    public String toString() {
        return "Triangle(" +
                a +
                ", " + b +
                ", " + c +
                ')';
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
