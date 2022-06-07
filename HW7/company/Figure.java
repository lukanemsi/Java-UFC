package com.company;

public abstract class Figure implements LFigure, LFigureAnother
{
    public abstract double area();
    public abstract double perimeter();
    public void printFigureAreaAndLength()
    {
        System.out.println("Area: " + area());
        System.out.println("Perimeter: " + perimeter());
    }
}
