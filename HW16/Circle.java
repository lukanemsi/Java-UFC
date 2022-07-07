package com.company;

import java.util.Objects;

public class Circle extends Figure
{
    private double radius;
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

    @Override
    public String toString() {
        return "Circle(" +
                  radius +
                ')';
    }
    public double getRadius()
    {
        return radius;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Circle)
        {
            Circle c = (Circle) other;
            return Double.compare(this.radius,c.radius) == 0;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}
