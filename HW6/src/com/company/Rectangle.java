package com.company;

public class Rectangle extends Figure
{
    private float length;
    private float width;

    public Rectangle(float length,float width)
    {
        setLength(length);
        setWidth(width);
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }

    @Override
    public double area() {
        return length * width;
    }

    public double calculateDiagonal()
    {
        return Math.sqrt(Math.pow(length,2) + Math.pow(width,2));
    }
    @Override
    public String toString()
    {
        return "Rectangle [length=" + length + ", width=" + width + "]";
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Rectangle))
            return false;
        return this.area() == ((Rectangle) o).area();
    }

    private void setLength(float length)
    {
        if(length <= 0)
            this.length = 0;
        else
            this.length = length;
    }
    private void setWidth(float width)
    {
        if(width <= 0)
            this.width = 0;
        else
            this.width = width;
    }
    public float getLength() {return length;}
    public float getWidth() {return width;}
}
