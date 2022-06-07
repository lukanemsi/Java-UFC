package com.company;

import java.util.Arrays;

public class Triangle extends Figure
{
    private double a;
    private double b;
    private double c;
    private double angle1;
    private double angle2;
    private double angle3;
    public Triangle(double a,double b,double c)
    {
            this.a = a;
            this.b = b;
            this.c = c;
    }
    @Override
    public void printFigureData()
    {
        System.out.println("Side A: " + a);
        System.out.println("Side B: " + b);
        System.out.println("Side C: " + c);
    }

    @Override
    public void sayHelloToFigure() {
        System.out.println("I'm Triangle!");
    }

    @Override
    public boolean validateFigure() {
        return triangleInequality(a,b,c);
    }
    private boolean triangleInequality(double a,double b,double c)
    {
        if(a <= 0 || b <= 0 || c <= 0)
            return false;
        return  a + b > c && a + c > b && c + b > a;
    }
    public void setAngles(double angle1,double angle2)
    {
        if(angle1 <= 0 || angle2 <= 0 || angle1 >= 180 || angle2 >= 180 || angle2 + angle1 >= 180)
        {
            System.out.println("No Such triangle exists with this type of angles");
            return;
        }
        this.angle1 = angle1;
        this.angle2 = angle2;
        this.angle3 = 180 - angle1 - angle2;
    }
    @Override
    public double perimeter()
    {
        return  a + b + c;
    }
    @Override
    public double area()
    {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }

    /**
     * ##
     * **/
    public int typeOfTriangle(double angle1,double angle2)
    {
        setAngles(angle1,angle2);
        if(angle1 == 90 || angle2 == 90 || angle1 + angle2 == 90)
            return 0;
        if(angle1 > 90 || angle2 > 90 || 180 - (angle1 + angle2) > 90)
            return -1;
        return 1;
    }
    public int typeOfTriangle()
    {
        double[] edges = {a,b,c};
        Arrays.sort(edges);
        if(Math.pow(edges[0],2) + Math.pow(edges[1],2) == Math.pow(edges[2],2))
            return 0;
        if(Math.pow(edges[0],2) + Math.pow(edges[1],2) > Math.pow(edges[2],2))
            return 1;
        return -1;
    }
    public double area(double height)
    {
        return height * a / 2;
    }
    @Override
    public String toString() {
        return "Rectangle: (" + a  + ", " + b + ", " +  c + ")";
    }

    @Override
    public boolean equals(Object o) {

        if(!(o instanceof  Triangle))
            return false;
        return this.perimeter() == ((Triangle) o).perimeter();
    }


}
