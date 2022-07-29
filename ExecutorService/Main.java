package com.company.ExecutorService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main
{
    @SuppressWarnings({"OptionalGetWithoutIsPresent", "unused", "ResultOfMethodCallIgnored"})
    public static void main(String[] args) {
        // task1
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(Rectangle.fillRectangle("Rectangle_in.dat"));
        executorService.execute(Circle.fillCircle("Circle_in.dat"));
        executorService.execute(Triangle.fillTriangle("Triangle_in.dat"));

        try {
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //amokitxva da Listebis dabruneba
        Callable<LinkedList<Circle>> circles = Circle.readCircles("Circle_in.dat");
        Callable<LinkedList<Triangle>> triangles = Triangle.readTriangles("Triangle_in.dat");
        Callable<LinkedList<Rectangle>> rectangles = Rectangle.readRectangles("Rectangle_in.dat");

        //task 2
        Future<LinkedList<Circle>> futureCircleList = executorService.submit(circles);
        Future<LinkedList<Triangle>> futureTriangleList = executorService.submit(triangles);
        Future<LinkedList<Rectangle>> futureRectangleList = executorService.submit(rectangles);
        executorService.shutdown();

        try
        {
            Circle maxCircle = futureCircleList.get().stream().max((c1,c2) -> Double.compare(c1.area(),c2.area())).get();
            Triangle maxTriangle = futureTriangleList.get().stream().max((t1,t2) -> Double.compare(t1.area(), t2.area())).get();
            Rectangle maxRectangle =  futureRectangleList.get().stream().max((r1,r2) -> Double.compare(r1.area(),r2.area())).get();
            System.out.println("maxCircle: " + maxCircle);
            System.out.println("maxTriangle: " + maxTriangle);
            System.out.println("maxRectangle: " + maxRectangle);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //task 3
        ExecutorService limitTimeService = Executors.newFixedThreadPool(3);
        limitTimeService.execute(new Circle(0));
        limitTimeService.execute(new Triangle(0,0,0));
        limitTimeService.execute(new Rectangle(0,0));
        limitTimeService.shutdown();
        try {
            if(args.length == 0)
                throw new RuntimeException("Args timeout expected!");
            boolean isDone = limitTimeService.awaitTermination(Long.parseLong(args[0]), TimeUnit.SECONDS);
            if(!isDone)
            {
                limitTimeService.shutdownNow();
            }
        } catch (InterruptedException | NumberFormatException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
            System.err.println(e.getMessage());
        }

        //task 4
        Callable<Rectangle> maxAreaRecCallable = () ->
        {
            return Rectangle.getRectangles().stream().max((r1,r2) -> Double.compare(r1.area(),r2.area())).get();
        };
        Callable<Rectangle> minAreaRecCallable = () ->
        {
            return Rectangle.getRectangles().stream().min((r1,r2) -> Double.compare(r1.area(),r2.area())).get();
        };
        Callable<Rectangle> maxPerRecCallable = () ->
        {
            return Rectangle.getRectangles().stream().max((r1,r2) -> Double.compare(r1.perimeter(),r2.perimeter())).get();
        };
        Callable<Rectangle> minPerRecCallable = () ->
        {
            return Rectangle.getRectangles().stream().min((r1,r2) -> Double.compare(r1.perimeter(),r2.perimeter())).get();
        };

        ExecutorService task4Service = Executors.newFixedThreadPool(3);
        Future<Rectangle> maxAreaRec = task4Service.submit(maxAreaRecCallable);
        Future<Rectangle> minAreaRec = task4Service.submit(minAreaRecCallable);
        Future<Rectangle> maxPerRec = task4Service.submit(maxPerRecCallable);
        Future<Rectangle> minPerRec = task4Service.submit(minPerRecCallable);
        task4Service.shutdown();
        try {
            System.out.println("Rectangle with Max Area: " + maxAreaRec.get());
            System.out.println("Rectangle with Min Area: " + minAreaRec.get());
            System.out.println("Rectangle with Max Perimeter: " + maxPerRec.get());
            System.out.println("Rectangle with Min Perimeter: " + minPerRec.get());

        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //task 5
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Callable<Triangle> maxAreaTriangleCallable = () ->
        {
            return Triangle.getTriangles().stream().max((t1,t2) -> Double.compare(t1.perimeter(),t2.perimeter())).get();
        };
        System.out.println("Sleep 5 sec: ");
        Future<Triangle> maxAreaTriangle = scheduledExecutorService.schedule(maxAreaTriangleCallable,5,TimeUnit.SECONDS);
        try {
            System.out.println("Triangle with Max Area: " + maxAreaTriangle.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //task 6
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable task6Runnable = () ->
        {
            int counter = 0;
            try(BufferedReader reader = new BufferedReader(new FileReader("Circle_in.dat")))
            {
                while(counter <= 150)
                {
                    System.out.println(new Circle(Double.parseDouble(reader.readLine().replaceAll("-",""))));
                    counter++;
                    atomicInteger.incrementAndGet();
                }
                if(atomicInteger.get() > 500)
                    throw new RuntimeException();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        };
        System.out.println("500 Circles will print in 5 sec: ");
        try
        {
            scheduledExecutorService.scheduleAtFixedRate(task6Runnable, 5, 2, TimeUnit.SECONDS).get();
        }
        catch (InterruptedException | ExecutionException ignored) {}

        //task 7

        AtomicInteger atomicInteger1 = new AtomicInteger(0);
        Runnable task7Runnable = () ->
        {
            int counter = 0;
            try(BufferedReader reader = new BufferedReader(new FileReader("Circle_in.dat")))
            {
                while(counter <= 250)
                {
                    System.out.println(new Circle(Double.parseDouble(reader.readLine().replaceAll("-",""))));
                    counter++;
                    atomicInteger1.incrementAndGet();
                }
                if(atomicInteger1.get() > 700)
                    throw new RuntimeException();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        };
        try {
            scheduledExecutorService.scheduleWithFixedDelay(task7Runnable,0,3,TimeUnit.SECONDS).get();
        } catch (InterruptedException | ExecutionException ignored) {}
        finally {
            scheduledExecutorService.shutdown();
        }
    }
}
