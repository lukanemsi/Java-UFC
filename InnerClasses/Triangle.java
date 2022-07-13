package com.company.InnerClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Triangle extends Figure
{
    private String path;
    private Triangle[] triangles;
    private double a,b,c;
    public Triangle(double a,double b ,double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;

    }
    public Triangle(String path)
    {
        this.path = path;
        readTriangles();
    }
    private void readTriangles()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            List<String> lines = reader.lines().collect(Collectors.toList());
            List<String> validTriangles = lines.stream().map(String::trim).filter(this::validate).collect(Collectors.toList());
            createTriangles(validTriangles);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    // for filter function
    private boolean validate(String triangle)
    {
        Pattern pattern = Pattern.compile("[.0-9]+-[.0-9]+-[.0-9]+");
        Matcher matcher = pattern.matcher(triangle);
        return matcher.matches();
    }
    private void createTriangles(List<String> validTriangles)
    {

        triangles = new Triangle[validTriangles.size()];
        for (int i = 0; i < triangles.length; i++)
        {
            List<Double> edges = Arrays.stream(validTriangles.get(i).split("-")).map(Double::parseDouble).collect(Collectors.toList());
            triangles[i] = new Triangle(edges.get(0),edges.get(1),edges.get(2));
        }

    }
    public Triangle[] getTriangles()
    {
        return triangles;
    }
    @Override
    public String toString() {
        return "Triangle(" + a + ", " + b + ", " + c + ")";
    }

    public void printRightTriangles()
    {
        Triangle.RightTriangleIterator iterator = this.new RightTriangleIterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }

    class RightTriangleIterator implements Iterator<Triangle>
    {

        private boolean isRight(Triangle tr)
        {
            List<Double> edges = new ArrayList<>();
            edges.add(tr.a);
            edges.add(tr.b);
            edges.add(tr.c);
            Collections.sort(edges);
            return (Math.pow(edges.get(0),2) + Math.pow(edges.get(1),2)) == Math.pow(edges.get(2),2);
        }
        int counter = 0;
        @Override
        public boolean hasNext() {
            return counter <= triangles.length - 1;
        }
        @Override
        public Triangle next()
        {
            counter++;
            if(isRight(triangles[counter-1]))
            {
                return triangles[counter-1];
            }
            return next();

        }
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public double area() {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }
}
