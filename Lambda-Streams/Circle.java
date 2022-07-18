package com.company.lambdaStreams;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Circle extends Figure
{
    private double radius;
    private static ArrayList<Circle> circleArrayList = new ArrayList<>();
    public static ArrayList<Circle> readCircles(String path)
    {
        BufferedReader reader;
        try {
        reader = new BufferedReader(new FileReader(path));
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
        try(reader)
        {
            // validate
            List<String> validLines = reader.lines().map(String::trim).filter(string -> string.matches("[.0-9]+")).collect(Collectors.toList());

            // convert and add
            validLines.stream().map(Double::valueOf).forEach(doubleVal -> circleArrayList.add(new Circle(doubleVal)));
        }catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        return circleArrayList;
    }
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