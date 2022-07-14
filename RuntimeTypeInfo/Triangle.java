package com.company.RTI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Triangle extends Figure
{
    private double a,b,c;
    private ArrayList<Triangle> triangles = new ArrayList<>();
    public Triangle(double a, double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public Triangle()
    {

    }
    private Triangle(String path,boolean read, boolean readCheck)
    {
        if(!(read && readCheck))
            return;

        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            List<String> validLines = reader.lines().map(String::trim).filter(str -> str.matches("[.0-9]+-[.0-9]+-[.0-9]+")).collect(Collectors.toList());

            Function<String,Double[]> function = new Function<String, Double[]>() {
                @Override
                public Double[] apply(String s) {
                    String[] arr = s.split("-");
                    Double[] result = {
                            Double.valueOf(arr[0]),
                            Double.valueOf(arr[1]),
                            Double.valueOf(arr[2])
                    };
                    return result;
                }
            };
            validLines.stream().map(function).forEach(doubles -> triangles.add(new Triangle(doubles[0],doubles[1],doubles[2])));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(triangles);
    }
    private boolean isRightAngled()
    {
        List<Double> edges = new ArrayList<>();
        edges.add(a);
        edges.add(b);
        edges.add(c);
        Collections.sort(edges);
        return (Math.pow(edges.get(0),2) + Math.pow(edges.get(1),2)) == Math.pow(edges.get(2),2);

    }
    @Override
    public double area() {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public String toString() {
        return "Triangle{" + a +
                ", " + b +
                ", " + c +
                '}';
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
