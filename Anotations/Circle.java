package Annotation;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * implementation of Figure abstract class
 * @author Me
 * @see {@link <a reef="https://en.wikipedia.org/wiki/Circle">.circle.</a>}
 * */
public class Circle extends Figure
{
    private double radius;
    /**
     * arrayList of read circles
     * */
    private static ArrayList<Circle> circleArrayList = new ArrayList<>();

    /**
     * reads radius from file creates Circle and adds it to static field circleArrayList
     *
     * @param path string value of File path, which the method reads from
     * @return  Arraylist of valid circles read from file, empty ArrayList if IO Exception was occurred
     * */
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

    /**
     * @param double value of Circle radius
     * */
    public Circle(double radius)
    {
        this.radius = radius;
    }
    /**
     * @return double value of circle perimeter
     *
     * */
    @Override
    public double perimeter()
    {
        return 2 * Math.PI * radius;
    }

    /**
     * @return double value of circle area
     * */
    @Override
    public double area()
    {
        return Math.PI * radius * radius;
    }


    /**
     * @return double value of circle perimeter
     * @deprecated since="1.8", will Remove soon, the newest version is nonstatic method perimeter()
     * */
    @Deprecated(since = "1.8",forRemoval = true)
    public static double getLengthStatic(Circle circle)
    {
        return circle.perimeter();
    }
    public double getRadius()
    {
        return radius;
    }
    @Override
    public String toString() {
        return "Circle(" +
                radius +
                ')';
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