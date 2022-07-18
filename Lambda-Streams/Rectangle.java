package com.company.lambdaStreams;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Rectangle extends Figure{
    private final double width;
    private final double length;
    private static ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
    public static ArrayList<Rectangle> readRectangles(String path)
    {
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
        try(reader)
        {
            List<String> validLines = reader.lines().map(String::trim).filter(string -> string.matches("[.0-9]+-[.0-9]+")).collect(Collectors.toList());
            validLines.stream().map(string -> string.split("-"))
                    .map(arr ->new Double[]{Double.valueOf(arr[0]),Double.valueOf(arr[1])})
                    .forEach(doubles -> rectangleArrayList.add(new Rectangle(doubles[0],doubles[1])));

        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        return rectangleArrayList;

    }
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }
    @Override
    public double perimeter() {
        return (width + length) * 2;
    }

    @Override
    public double area() {
        return width * length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Rectangle[" + width +
                ", " + length +
                ']';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.width, width) == 0 && Double.compare(rectangle.length, length) == 0;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(width) + Double.hashCode(length);
    }
}