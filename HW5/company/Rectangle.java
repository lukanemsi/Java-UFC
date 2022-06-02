package com.company;

import com.company.ufc.Square;

public class Rectangle extends Figure
{
    private float length;
    private float width;

    public Rectangle(float length,float width)
    {
        super("Rectangle");
        setLength(length);
        setWidth(width);
    }
    public Rectangle()
    {
        this(3,4);
    }

    @Override
    protected float perimeter() {
        return 2*(length + width);
    }

    @Override
    protected float area() {
        return length * width;
    }

    // Methods overLoading დამატებითი პარამეტრების დახმარებით
    public float perimeter(float length,float width) { return 2*(length + width); }
    public float area(float length,float width) {return length * width;}

    @Override
    public String toString()
    {
        return "Rectangle [length=" + length + ", width=" + width + "]";
    }
    // public void sayHello(){} -> compilation error რადგან sayHello მეთოდი Final არის, შესაბამისად Ovverride ვერ მოხდება

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
