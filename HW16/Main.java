package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.err.println("Files expected!");
            return;
        }
        String triangle_in = null;
        String circle_in = null;
        String rectangle_in = null;
        for(String fileName: args)
        {
            switch (fileName)
            {
                case "Circle_in.dat": circle_in = fileName; break;
                case "Triangle_in.dat": triangle_in = fileName; break;
                case "Rectangle_in.dat": rectangle_in = fileName; break;
                default:
                    System.err.println("Invalid File");
                    return;
            }
        }
      readCircles(circle_in);
      readCirclesUnique(circle_in);
      readRectangles(rectangle_in);
      readTriangles(triangle_in);
    }


    /** ყველა Reader_ი ბოლოს იძახებს შესაბამისი ფიგურის Writer_ს*/
    public static void readTriangles(String fileName)
    {
        TreeSet<Triangle> triangleTreeSet = new TreeSet<>(new TriangleReverseSort());
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))))
        {
            String line;
            ArrayList<String> lines = new ArrayList<>();
            while((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            Pattern trianglePattern = Pattern.compile("[.0-9]+-[.0-9]+-[.0-9]+");
            Matcher matcher;
            for(String l: lines)
            {
                matcher = trianglePattern.matcher(l);
                if(matcher.find())
                {
                    String result = matcher.group();
                    String[] edges = result.split("-");
                    triangleTreeSet.add(
                      new Triangle(
                              Double.parseDouble(edges[0]),
                              Double.parseDouble(edges[1]),
                              Double.parseDouble(edges[2])
                      ));
                }
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        writeTriangles(triangleTreeSet,"Triangle_out.dat");
    }
    public static void writeTriangles(TreeSet<Triangle> triangleTreeSet,String fileName)
    {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName))))
        {
            int index = 0;
            for(Triangle triangle: triangleTreeSet)
            {
                if(index == triangleTreeSet.size() - 1)
                    writer.write(triangle.getA() + "-" + triangle.getB() + "-" + triangle.getC());
                else
                    writer.write(triangle.getA() + "-" + triangle.getB() + "-" + triangle.getC() + "\n");
                index++;
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    public static void readRectangles(String fileName)
    {
        TreeSet<Rectangle> rectangleTreeSet = new TreeSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String[] lines = reader.lines().map(Objects::toString).toArray(String[]::new);
            Pattern rectanglePattern = Pattern.compile("[.0-9]+-[.0-9]+");
            Matcher matcher;
            for(String line : lines)
            {
                matcher = rectanglePattern.matcher(line);
                if(matcher.find())
                {
                    String result = matcher.group();
                    String[] edges = result.split("-");
                    rectangleTreeSet.add(
                            new Rectangle(
                                    Double.parseDouble(edges[0]),
                                    Double.parseDouble(edges[1])
                            ));
                }
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        writeRectangle(rectangleTreeSet,"Rectangle_out.dat");
    }
    public static void writeRectangle(TreeSet<Rectangle> rectangleTreeSet, String fileName)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            int index = 0;
            for(Rectangle r: rectangleTreeSet)
            {
                if(index == rectangleTreeSet.size() - 1)
                    writer.write(r.getLength() + "-" + r.getWidth());
                else
                    writer.write(r.getLength() + "-" + r.getWidth() + "\n");
                index++;
            }
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    public static void readCirclesUnique(String fileName)
    {
        HashSet<Circle> circleHashSet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
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
                circleHashSet.add(new Circle(radius));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // საქმე გავიმარტივე უკვე hashSet arrayList_ში გადავიყვანე რომ იგივე მეთოდის გამოძახება შემძლებოდა
        ArrayList<Circle> circlesOfHash = new ArrayList<>();
        circleHashSet.forEach(circle -> circlesOfHash.add(circle));
        Collections.sort(circlesOfHash);
        writeCircles(circlesOfHash,"Circle_out_unique.dat");
    }
    public static void readCircles(String fileName)
    {
        ArrayList<Circle> circleArrayList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
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
                circleArrayList.add(new Circle(radius));
            }
        }catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
        Collections.sort(circleArrayList);
        writeCircles(circleArrayList,"Circle_out.dat");
    }
    public static void writeCircles(ArrayList<Circle> circleArrayList,String fileName)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            for (int i = 0; i < circleArrayList.size(); i++) {
                if(i == circleArrayList.size() - 1)
                    writer.write(String.valueOf(circleArrayList.get(i).getRadius()));
                else
                    writer.write(String.valueOf(circleArrayList.get(i).getRadius() + "\n"));
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}