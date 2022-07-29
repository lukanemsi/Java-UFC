package com.company.ExecutorService;


import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Callable;

public class Circle extends Figure implements Runnable
{
    private final double radius;
    private static LinkedList<Circle> circles = new LinkedList<>();
    public static Callable<LinkedList<Circle>> readCircles(String path)
    {
        return () -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                reader.lines()
                        .map(String::trim)
                        .filter(str -> str.matches("[.0-9]+-"))
                        .map(str -> str.replaceAll("-", ""))
                        .map(Double::valueOf)
                        .forEach(d -> circles.add(new Circle(d)));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return circles;
        };
    }

    public Circle(double radius)
    {
        this.radius = radius;
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
        return "Circle(" + radius +
                ')';
    }

    public static Runnable fillCircle(String path)
    {
        Runnable fillCircle = () ->
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
            {
                for (int i = 0; i < 70_000; i++) {
                    Random random = new Random();
                    writer.write((random.nextInt(10_000) + 1) + "-" + '\n');
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        };
        return fillCircle;
    }
    @Override
    public void run()
    {

        for (int i = 0; i < 70_000; i++)
        {
            Random random = new Random();

            circles.add(new Circle(
                    random.nextInt(10_000)
            ));
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Circle_out.dat")))
        {
            circles.stream().map(Circle::getRadius).forEach(rad -> {
                try {
                    writer.write(rad + "#\n");
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
    public double getRadius() {
        return radius;
    }
    public static LinkedList<Circle> getCircles() {
        return circles;
    }

}
