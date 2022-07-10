package com.company.com;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondMain {
    public static void main(String[] args)
    {
        //Containers
        FigureContainers<Circle> circleContainers = circles("com/Circle_in.dat");
        FigureContainers<Triangle> triangleContainers = triangles("com/Triangle_in.dat");
        FigureContainers<Rectangle> rectangleContainers = rectangles("com/Rectangle_in.dat");

        //Circles
        var circleArrayList = circleContainers.getFigureList();
        var circleTreeSet = circleContainers.getFigureTreeSet();
        var circleTreeMap = circleContainers.getFigureMapToLines();
		int line = getMaximumFiguresLine(circleTreeMap);
		
        //Triangles
        var triangleArrayList = triangleContainers.getFigureList();
        var triangleTreeSet = triangleContainers.getFigureTreeSet();
        var triangleTreeMap = triangleContainers.getFigureMapToLines();

        //Rectangles
        var rectangleArrayList = rectangleContainers.getFigureList();
        var rectangleTreeSet = rectangleContainers.getFigureTreeSet();
        var rectangleTreeMap = rectangleContainers.getFigureMapToLines();
    }


    public static <T extends Figure> MinAndMax<T> getMinMax(TreeSet<T> figureTreeSet) {
        Optional<T> max = figureTreeSet.stream().max(Figure::compareTo);
        Optional<T> min = figureTreeSet.stream().min(Figure::compareTo);
        if (max.isPresent() && min.isPresent())
            return new MinAndMax<T>(max.get(), min.get());

        System.err.println("Min and Max are nulls!");
        return new MinAndMax<T>(null, null);
    }

    public static <T extends Figure> MinAndMax<T> getMinMax(ArrayList<T> figureArrayList) {
        Optional<T> max = figureArrayList.stream().max(Figure::compareTo);
        Optional<T> min = figureArrayList.stream().min(Figure::compareTo);
        if (max.isPresent() && min.isPresent())
            return new MinAndMax<T>(max.get(), min.get());
        System.err.println("Min and Max are nulls!");
        return new MinAndMax<>(null, null);
    }

    public static FigureContainers<Circle> circles(String path) {
        ArrayList<Circle> circleArrayList = new ArrayList<>();
        TreeSet<Circle> circleTreeSet = new TreeSet<>();
        TreeMap<Circle,Integer> circleTreeMap = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Stream<String> fileLines = reader.lines();
            String[] lines = fileLines.map(Objects::toString).toArray(String[]::new);
            int lineCounter = 1;
            Circle circle;
            for (String line : lines)
            {
                double radius;
                try {
                    radius = Double.parseDouble(line.trim());
                } catch (NumberFormatException ignored)
                {
                    lineCounter++;
                    continue;
                }
                circle = new Circle(radius);
                circleArrayList.add(circle);
                circleTreeSet.add(circle);
                circleTreeMap.put(circle,lineCounter);
                lineCounter++;
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }

        FigureContainers<Circle> circleContainers = new FigureContainers<>();
        circleContainers.setFigureList(circleArrayList);
        circleContainers.setFigureTreeSet(circleTreeSet);
        circleContainers.setFigureMapToLines(circleTreeMap);
        return circleContainers;
    }

    public static FigureContainers<Triangle> triangles(String path) {
        TreeSet<Triangle> triangleTreeSet = new TreeSet<>();
        ArrayList<Triangle> triangleArrayList = new ArrayList<>();
        TreeMap<Triangle,Integer> triangleTreeMap = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            Pattern trianglePattern = Pattern.compile("[.0-9]+-[.0-9]+-[.0-9]+");
            Matcher matcher;
            Triangle triangle;
            int lineCounter = 1;
            for (String line : lines)
            {
                matcher = trianglePattern.matcher(line);
                if (matcher.find())
                {
                    String result = matcher.group();
                    String[] edges = result.split("-");
                    triangle = new Triangle(
                            Double.parseDouble(edges[0]),
                            Double.parseDouble(edges[1]),
                            Double.parseDouble(edges[2]));

                    triangleArrayList.add(triangle);
                    triangleTreeSet.add(triangle);
                    triangleTreeMap.put(triangle,lineCounter);
                }
                lineCounter++;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        FigureContainers<Triangle> triangleFigureContainers = new FigureContainers<>();
        triangleFigureContainers.setFigureList(triangleArrayList);
        triangleFigureContainers.setFigureTreeSet(triangleTreeSet);
        triangleFigureContainers.setFigureMapToLines(triangleTreeMap);
        return triangleFigureContainers;
    }

    public static FigureContainers<Rectangle> rectangles(String path) {
        ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
        TreeSet<Rectangle> rectangleTreeSet = new TreeSet<>();
        TreeMap<Rectangle,Integer> rectangleTreeMap = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String[] lines = reader.lines().map(Objects::toString).toArray(String[]::new);
            Pattern rectanglePattern = Pattern.compile("[.0-9]+-[.0-9]+");
            Matcher matcher;
            int lineCounter = 1;
            Rectangle rectangle;
            for (String line : lines) {
                matcher = rectanglePattern.matcher(line);
                if (matcher.find())
                {
                    String result = matcher.group();
                    String[] edges = result.split("-");
                    rectangle = new Rectangle(
                            Double.parseDouble(edges[0]),
                            Double.parseDouble(edges[1]));
                    rectangleTreeSet.add(rectangle);
                    rectangleArrayList.add(rectangle);
                    rectangleTreeMap.put(rectangle,lineCounter);
                }
                lineCounter++;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        FigureContainers<Rectangle> rectangleFigureContainers = new FigureContainers<>();
        rectangleFigureContainers.setFigureTreeSet(rectangleTreeSet);
        rectangleFigureContainers.setFigureList(rectangleArrayList);
        rectangleFigureContainers.setFigureMapToLines(rectangleTreeMap);
        return rectangleFigureContainers;
    }

    public static int getMaximumFiguresLine(TreeMap<? extends Figure,Integer> mapping)
    {
        Optional<? extends Figure> max = mapping.keySet().stream().max(Figure::compareTo);
        if(max.isPresent())
        {
            return mapping.get(max.get());
        }
        return 0;
    }

    /**task 5,6*/
    public static <T extends Number & Comparable<T>> AverageMinMax<T> function5(ArrayList<T> numbers)
    {

        Optional<T> max = numbers.stream().max(T::compareTo);
        Optional<T> min = numbers.stream().min(T::compareTo);
        Optional<Double> sum = numbers.stream().map(T::doubleValue).reduce(Double::sum);

        if(max.isEmpty() || min.isEmpty() || sum.isEmpty())
        {
            System.err.println("Empty list! null returned");
            return null;
        }
        return new AverageMinMax<T>(max.get(),min.get(),(sum.get() / numbers.size()));
    }
    public static AverageMinMax<String> function6(ArrayList<String> strings)
    {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                if(o1.length() == o2.length())
                    return 0;
                return o1.length() > o2.length() ? 1 : -1;
            }
        };

        Optional<String> max = strings.stream().max(comparator);
        Optional<String> min = strings.stream().min(comparator);
        Optional<Double> sum = strings.stream().map(String::length).map(Double::valueOf).reduce(Double::sum);

        if(max.isEmpty() || min.isEmpty() || sum.isEmpty())
        {
            System.err.println("Empty list! null returned");
            return null;
        }
        return new AverageMinMax<String>(max.get(),min.get(),(sum.get() / strings.size()));
    };
}