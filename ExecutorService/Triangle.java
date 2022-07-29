package com.company.ExecutorService;


import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Callable;

public class Triangle extends Figure implements Runnable
{
    private final double a;
    private final double b;
    private final double c;

    private static LinkedList<Triangle> triangles = new LinkedList<>();

    public Triangle(double a,double b,double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Callable<LinkedList<Triangle>> readTriangles(String path)
    {
        return () ->
                {
                    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                    reader.lines()
                            .map(String::trim)
                            .filter(str -> str.matches("[.0-9]+-[.0-9]+-[.0-9]+"))
                            .map(str -> str.split("-"))
                            .map(str -> new Double[]{Double.valueOf(str[0]), Double.valueOf(str[1]), Double.valueOf(str[2])})
                            .forEach(doubles -> triangles.add(new Triangle(doubles[0], doubles[1], doubles[2])));
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    return triangles;
                };
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
        return "Triangle(" + a +
                ", " + b +
                ", " + c +
                ')';
    }


    public static Runnable fillTriangle(String path)
    {
        Runnable fillTriangle = () ->
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
            {
                for (int i = 0; i < 70_000; i++) {
                    Random random = new Random();
                    writer.write((random.nextInt(10_000) + 1) + "-" + (random.nextInt(10_000) + 1) + "-" + (random.nextInt(10_000) + 1) + '\n');
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        };
        return fillTriangle;
    }
    @Override
    public void run() {
        for (int i = 0; i < 70_000; i++)
        {
            Random random = new Random();

            triangles.add(new Triangle(
                    random.nextInt(10_000), random.nextInt(10_000), random.nextInt(10_000)
            ));
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Triangle_out.dat")))
        {
            triangles.stream()
                    .map(Triangle::getEdges)
                    .map(edges -> edges[0] + "#" + edges[1] + "#" + edges[2] + "\n")
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
    public double[] getEdges()
    {
        return new double[]{a,b,c};
    }


    public static LinkedList<Triangle> getTriangles() {
        return triangles;
    }
}
