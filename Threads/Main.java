package com.company.Threads;



import java.time.LocalTime;
import java.util.LinkedList;


public class Main
{
    public static void main(String[] args)
    {
        Thread daemonThread = new Thread(new DaemonThread());
        daemonThread.setDaemon(true);
        daemonThread.start();

        //chawera in_fileshi
        Thread rectangle_in = new Thread(Rectangle.fillRectangle("Rectangle_in.dat"));
        Thread triangle_in = new Thread(Circle.fillCircle("Circle_in.dat"));
        Thread circle_in = new Thread(Triangle.fillTriangle("Triangle_in.dat"));

        rectangle_in.start();
        triangle_in.start();
        circle_in.start();


        try {
            rectangle_in.join();
            triangle_in.join();
            circle_in.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        //amokitxva
        LinkedList<Circle> circles = Circle.readCircles("Circle_in.dat");
        LinkedList<Triangle> triangles = Triangle.readTriangles("Triangle_in.dat");
        LinkedList<Rectangle> rectangles = Rectangle.readRectangles("Rectangle_in.dat");

        Thread circleThread = new Thread(new Circle(0));
        Thread triangleThread = new Thread(new Triangle(0,0,0));
        Thread rectangleThread = new Thread(new Rectangle(0,0));

        //chawera out_shi
        daemonThread.interrupt();
        circleThread.start();
        triangleThread.start();
        rectangleThread.start();
    }
}
