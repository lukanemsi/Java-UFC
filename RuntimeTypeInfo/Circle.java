package com.company.RTI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Circle extends Figure
{
    private double radius;
    private ArrayList<Circle> circles = new ArrayList<>();
    public Circle(double radius)
    {
        this.radius = radius;
    }
    public Circle()
    {

    }
    private Circle(String path)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            // validate
            List<String> validLines = reader.lines().map(String::trim).filter(this::validate).collect(Collectors.toList());

            // convert and add
            validLines.stream().map(Double::valueOf).forEach(doubleVal -> circles.add(new Circle(doubleVal)));
        }catch (IOException e)
        {
            System.err.println(e.getMessage());
        }

        System.out.println(circles);
    }
    private boolean validate(String input)
    {
        try
        {
            Double.valueOf(input);
        }catch (NumberFormatException ignored)
        {
            return false;
        }
        return true;
    }

    @Override
    public double area()
    {
        return Math.PI * radius * radius;
    }
    @Override
    public double perimeter()
    {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle(" +radius +
                ')';
    }

    public double getRadius() {
        return radius;
    }
}
