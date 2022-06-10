package com.company;

import org.w3c.dom.css.Rect;

import java.util.Objects;

public class Rectangle implements Comparable
{
    private double width;
    private double length;
    public Rectangle(double length,double width)
    {
        setLength(length);
        setWidth(width);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Rectangle)
        {
            Rectangle other = (Rectangle) o;
            return this.length == other.length && this.width == other.width;
        }
        return false;
    }


    public double area()
    {
        return width * length;
    }
    public double perimeter()
    {
        return 2 * (length + width);
    }



    public void setLength(double length) {
        if(length > 0)
            this.length = length;
    }
    public void setWidth(double width)
    {
        if(width > 0)
            this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Rectangle)
        {
            Rectangle other = (Rectangle) o;
            if(this.length == other.length && this.width == other.width)
                return 0;
            if(this.area() < other.area())
                return -1;
            if(this.area() >= other.area())
                return 1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Rec: (l=" + length + ", w=" + width + "" +
                ")";
    }

    @Override
    public int hashCode() {
        return Double.hashCode(length) + Double.hashCode(width);
        // მხოლოდ მაშინ გადავა Equal_ზე როდესაც this.length == o.length && this.width == o.width || this.length == o.width && this.width == o.length
    }
}
