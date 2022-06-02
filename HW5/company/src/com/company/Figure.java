package com.company;

import com.company.ufc.Square;

public class Figure
{
    protected float perimeter()
    {
        return -1;
    }
    protected float area()
    {
        return -1;
    }
    public Figure(String figure)
    {
        // რადგან Square კლასს პირობის თანახმად სტრინგი არ გადაეცემა instanceof_ის დახმარებით შეგვიძლია მნიშვნელობის უფრო დაკონკრეტება
        if(this instanceof Square)
            figure += " as well as Square";

        System.out.println("this figure is: " + figure);
    }
    public final void sayHello()
    {
        System.out.println("Figure Welcome");
    }
}
