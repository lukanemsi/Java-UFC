package com.company.Threads;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Rectangle extends Figure implements Runnable
{

    private final double width,length;
    private static LinkedList<Rectangle> rectangles = new LinkedList<>();
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }

    public static LinkedList<Rectangle> readRectangles(String path)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            reader.lines()
                    .map(String::trim)
                    .filter(str -> str.matches("[.0-9]+-[.0-9]+"))
                    .map(str -> str.split("-"))
                    .map(strings -> new Double[]{Double.valueOf(strings[0]),Double.valueOf(strings[1])})
                    .forEach(d -> rectangles.add(new Rectangle(d[0],d[1])));
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        return rectangles;
    }
    @Override
    public double perimeter() {
        return (width + length) * 2;
    }

    @Override
    public double area() {
        return width * length;
    }

    @Override
    public String toString() {
        return "Rectangle[" + width +
                ", " + length +
                ']';
    }
    public double[] getEdges()
    {
        return new double[]{width, length};
    }


    public static Runnable fillRectangle(String path)
    {
        Runnable fillRec = () ->
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
            {
                for (int i = 0; i < 70_000; i++) {
                    Random random = new Random();
                    writer.write(random.nextInt(10_000) + "-" + random.nextInt(10_000) + '\n');
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        };
        return fillRec;
    }
    @Override
    public void run()
    {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Rectangle_out.dat")))
        {
            rectangles.stream()
                    .map(Rectangle::getEdges)
                    .map(ed -> ed[0] + "#" + ed[1] + "\n")
                    .forEach(str -> {
                        try {
                            writer.write(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}

