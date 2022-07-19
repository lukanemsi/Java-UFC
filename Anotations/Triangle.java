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
 * @see {@link <a reef="https://en.wikipedia.org/wiki/Triangle">.triangle.</a>}
 *
 * */
public class Triangle extends Figure
{
    private final double a;
    private final double b;
    private final double c;
    /**
     * arrayList of read triangles
     * */
    private static ArrayList<Triangle> triangleArrayList = new ArrayList<>();
    /**
     * reads edges from file creates triangle and adds it to static field triangleArrayList
     *
     * @param path string value of File path, which the method reads from
     * @return  Arraylist of valid rectangles read from file, empty ArrayList if IO Exception was occurred
     * */
    public static ArrayList<Triangle> readTriangles(String path)
    {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
        try(reader)
        {
           List<String> validLines =  reader.lines().map(String::trim).filter(x -> x.matches("[.0-9]+-[.0-9]+-[.0-9]+")).collect(Collectors.toList());
           validLines.stream().map(string -> string.split("-"))
                   .map(arr -> new Double[]{Double.valueOf(arr[0]),Double.valueOf(arr[1]),Double.valueOf(arr[2])})
                   .map(doubles -> new Triangle(doubles[0],doubles[1],doubles[2])).forEach(tr -> triangleArrayList.add(tr));

        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return triangleArrayList;
    }

    /**
     * @param double values of triangle edges
     * */
     public Triangle(double a,double b,double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @return double value of triangle perimeter
     * */
    @Override
    public double perimeter() {
        return a + b + c;
    }
    /**
     * @return double value of triangle perimeter
     * */
    @Override
    public double area() {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }

    /**
     * @return double value of triangle perimeter
     * @deprecated since="1.8", the newest version is nonstatic method perimeter()
     * */
    @Deprecated(since = "1.8",forRemoval = false)
    public static double getLengthStatic(Triangle triangle)
    {
        return triangle.perimeter();
    }
    @Override
    public String toString() {
        return "Triangle(" +
                a +
                ", " + b +
                ", " + c +
                ')';
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.a, a) == 0 && Double.compare(triangle.b, b) == 0 && Double.compare(triangle.c, c) == 0;
    }
    @Override
    public int hashCode() {
        return Double.hashCode(a) + Double.hashCode(b) + Double.hashCode(c);
    }
}
