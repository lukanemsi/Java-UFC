package com.company.lambdaStreams;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Triangle>  triangles = Triangle.readTriangles("Triangle_in.dat");
        ArrayList<Circle> circles = Circle.readCircles("Circle_in.dat");
        ArrayList<Rectangle> rectangles = Rectangle.readRectangles("Rectangle_in.dat");

        // task1
        List<Circle> filteredCircles =  circles.stream().filter(c -> c.getRadius() > 10).collect(Collectors.toList());

        // task2
        List<Double> rectangleAreas = rectangles.stream().map(Rectangle::area).filter(area -> area > 100).collect(Collectors.toList());

        // task3
        triangles.stream().map(rec -> new Double[]{rec.getA(),rec.getB(),rec.getC()})
                .map(arr -> Arrays.stream(arr).sorted().toArray(Double[]::new))
                .filter(sortedArr -> Math.pow(sortedArr[0], 2) + Math.pow(sortedArr[1],2 ) == Math.pow(sortedArr[2], 2))
                .map(arr -> arr[0]+arr[1]+arr[2]).forEach(System.out::println);
        System.out.println("-----------------");

        // task3 sxva varianti
        triangles.stream().filter(triangle -> {
            Double[] edges = {triangle.getA(), triangle.getB(), triangle.getC()};
            Arrays.sort(edges);
            return Math.pow(edges[0], 2) + Math.pow(edges[1],2 ) == Math.pow(edges[2], 2);
        }).map(Triangle::perimeter).forEach(System.out::println);


        System.out.println("-----------------");
        // task 4
        Set<Circle> circleSet = new HashSet<>(circles);
        Circle maximum = circleSet.stream().max((c, c1) -> Double.compare(c.area(), c1.area())).orElse(new Circle(0));
        Circle minimum = circleSet.stream().min((c,c1) -> Double.compare(c.perimeter(),c1.perimeter())).orElse(new Circle(0));

        // task 5
        Set<Rectangle> rectangleSet = new HashSet<>(rectangles);
        Set<Rectangle> filteredRecSet = rectangleSet.stream().filter(rec -> Math.pow(rec.getLength(),2) + Math.pow(rec.getWidth(),2) > 50).collect(Collectors.toSet());

        // task 6
        Set<Triangle> triangleSet = new HashSet<>(triangles);
        Set<Double> filteredTrianglePer = triangleSet.stream().map(Triangle::perimeter).filter(perimeter -> perimeter <= 29.5).collect(Collectors.toSet());

        // task 7
        Map<Double,Long> circleMap = circles.stream().collect(Collectors.groupingBy(Circle::getRadius,Collectors.counting()));

        // task 8
        Map<Double,Optional<Rectangle>> rectangleMap = rectangles.stream().collect(Collectors.groupingBy(Rectangle::perimeter,Collectors.minBy(Comparator.comparingDouble(Rectangle::getWidth))));

        // task 9
        Map<Double,DoubleSummaryStatistics> triangleMap = triangles.stream().collect(Collectors.groupingBy(Triangle::getA,Collectors.summarizingDouble(Triangle::perimeter)));


        // task 10
        List<Integer> primeNumbs = Stream.iterate(2 , i -> i <= 100, i -> i+1)
                .filter(
                        integer -> Stream.iterate(2, x -> x < integer, x -> x + 1).noneMatch(x -> integer % x == 0)
                ).collect(Collectors.toList());

        // task 11
        List<Integer> reversePrimeNumbs = primeNumbs.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

       // task 12
        task12(args);
    }
    public static void task12(String[] args)
    {

        if(args.length != 2)
        {
            System.err.println("args expected!");
            return;
        }
        File fileIn = new File(args[0]);
        File fileOut = new File(args[1]);
        if(fileIn.exists() && fileOut.exists())
        {
            BufferedReader reader;
            BufferedWriter writer;
            try
            {
                reader = new BufferedReader(new FileReader(fileIn));
                writer = new BufferedWriter(new FileWriter(fileOut));
            } catch (IOException e)
            {
                System.err.println(e.getMessage());
                return;
            }
            try(reader;writer)
            {
                reader.lines().map(str -> str.replaceAll("\\s","_")).map(String::toUpperCase)
                        .forEach(str -> {
                            try
                            {
                                writer.write(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (IOException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
}
