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
 * @see {@link <a reef="https://en.wikipedia.org/wiki/Rectangle">.rectangle.</a>}
 * */
public class Rectangle extends Figure{
    private final double width;
    private final double length;

    /**
     * arrayList of read rectangles
     * */
    private static ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();

    /**
     * reads edges from file creates rectangle and adds it to static field rectangleArrayList
     *
     * @param path string value of File path, which the method reads from
     * @return  Arraylist of valid rectangles read from file, empty ArrayList if IO Exception was occurred
     * */
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

    /**
     * @param width and length values of rectangle
     * */
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }

    /**
     * @return double value of rectangle perimeter
     *
     * */
    @Override
    public double perimeter()
    {
        return (width + length) * 2;
    }
    /**
     * @return double value of rectangle area
     *
     * */
    @Override
    public double area()
    {
        return width * length;
    }

    /**
     * @return double value of rectangle perimeter
     * @deprecated since="1.8", will Remove soon, the newest version is nonstatic method perimeter()
     * */
    @Deprecated(since = "1.8",forRemoval = true)
    public static double getLengthStatic(Rectangle rectangle)
    {
        return rectangle.perimeter();
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