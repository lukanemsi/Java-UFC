package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Triangle extends  Figure
{
    private double a;
    private double b;
    private double c;
    private double angle1;
    private double angle2;
    private double angle3;
    public Triangle(double a,double b,double c)
    {
        if(triangleInequality(a,b,c))
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        else
            System.out.println("No such triangle exists with this type of edges: " + a + "," + b + "," + c );

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

    // სამკუთხედის ტიპის გამოთვლა
    public int typeOfTriangle(double angle1,double angle2)
    {
        setAngles(angle1,angle2);
        if(angle1 == 90 || angle2 == 90 || angle1 + angle2 == 90)
            return 0;
        if(angle1 > 90 || angle2 > 90 || 180 - (angle1 + angle2) > 90)
            return -1;
        return 1;
    }

    // სამკუთხედის ტიპის გამოთვლა უპარამეტროდ
    public int typeOfTriangle()
    {
        double[] edges = {a,b,c};
        // ზრდადობის მიხედვით დაწყობა (სორტირება) ფორმულისთვის
        Arrays.sort(edges);
        // პითაგორა
        if(Math.pow(edges[0],2) + Math.pow(edges[1],2) == Math.pow(edges[2],2))
            return 0;
        if(Math.pow(edges[0],2) + Math.pow(edges[1],2) > Math.pow(edges[2],2))
            return 1;
        return -1;
    }

    private boolean triangleInequality(double a,double b,double c)
    {
        if(a <= 0 || b <= 0 || c <= 0)
            return false;
        return  a + b > c && a + c > b && c + b > a;
    }

    @Override
    public double perimeter()
    {
        return  a + b + c;
    }
    //ჰერონის ფორმულით
    @Override
    public double area()
    {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }

    //  ნაგულისხმებია a გვერძე დაშვებული სიმღლე
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

