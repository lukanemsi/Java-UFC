package com.company.RTI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Rectangle extends Figure
{
    private double width,length;
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }
    public Rectangle()
    {

    }
    private Rectangle(String path,boolean read)
    {
        if(!read)
            return;

        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            // validate
            List<String> validLines = reader.lines().map(String::trim).filter(this::validate).collect(Collectors.toList());

            //map according to function and add
            Function<String,Double[]> function = new Function<String, Double[]>() {
                @Override
                public Double[] apply(String s) {
                    String[] arr = s.split("-");
                    Double[] result = {Double.valueOf(arr[0]),Double.valueOf(arr[1])};
                    return result;
                }
            };
            validLines.stream().map(function).forEach(doubles -> rectangles.add(new Rectangle(doubles[0],doubles[1])));

        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }

        System.out.println(rectangles);
    }
    private boolean validate(String input)
    {
        Pattern pattern = Pattern.compile("[.0-9]+-[.0-9]+");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    private boolean isSquare()
    {
        return width == length;
    }
    @Override
    public double area() {
        return width * length;
    }

    @Override
    public double perimeter() {
        return 2 * (width + length);
    }

    @Override
    public String toString() {
        return "Rectangle[" +
                  width +
                ", " + length +
                ']';
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }
}
