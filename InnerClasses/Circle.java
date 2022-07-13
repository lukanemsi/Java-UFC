package com.company.InnerClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Circle  extends Figure
{
    private double radius;
    public Circle(double radius)
    {
        this.radius = radius;
    }
    public static Tuple<TreeSet<Circle>,Result> circleTreeSet(String path)
    {
        Comparator<Circle> anonymousCircleComparator = new Comparator<Circle>() {
            @Override
            public int compare(Circle o1, Circle o2) {
                return Double.compare(o1.area(),o2.area());
            }
        };
        TreeSet<Circle> treeSet = new TreeSet<>(anonymousCircleComparator);
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            Stream<String> fileLines = reader.lines();
            String[] lines = fileLines.map(Objects::toString).toArray(String[]::new);
            for(String line: lines)
            {
                double radius;
                try
                {
                    radius = Double.parseDouble(line.trim());
                }catch (NumberFormatException ignored)
                {
                    continue;
                }
                treeSet.add(new Circle(radius));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return new Tuple<>(treeSet,Result.FILE_NOT_FOUND);
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
            return new Tuple<>(treeSet,Result.IO_ERROR);
        }
        return new Tuple<>(treeSet,Result.OK);
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
}
enum Result
{
    OK(0,"OK"),FILE_NOT_FOUND(1,"FileNotFound"),IO_ERROR(2,"IO Error");
    private String message;
    private int number;
    private Result(int number, String message)
    {
        this.number = number;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getNumber() {
        return number;
    }
}
