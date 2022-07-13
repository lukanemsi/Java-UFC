package com.company.InnerClasses;

import java.util.Arrays;
import java.util.TreeSet;

public class Main
{

    public static void main(String[] args)
    {
        Triangle triangle = new Triangle("Triangle_in.dat");
        triangle.printRightTriangles();
        System.out.println("----------------------");


        Tuple<TreeSet<Circle>, Result> circleTuple= Circle.circleTreeSet("Circle_in.dat");
        System.out.println(circleTuple.getFirst());
        System.out.println(circleTuple.getSecond());
        System.out.println("----------------------");
        try {
            Rectangle rectangle = new Rectangle("Rectangle_in.dat");
            System.out.println(Arrays.toString(rectangle.getRectangles()));
        } catch (FigureValidatorException e) {

            System.err.println(e.getMessage());
        }
    }
}




