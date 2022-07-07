package com.company;

import java.util.Objects;

public class Rectangle extends Figure{
    private final double width;
    private final double length;
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }
    @Override
    public double perimeter() {
        return (width + length) * 2;
    }

    @Override
    public double area() {
        return width * length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Rectangle[" +
                "W= " + width +
                ", L= " + length +
                ']';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.length, length) == 0;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(width) + Double.hashCode(length);
    }
}
