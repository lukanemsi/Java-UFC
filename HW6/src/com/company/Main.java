package com.company;


import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.css.Rect;

public class Main{

    public static void main(String[] args)
    {
        Rectangle r = new Rectangle(10,20);
        Figure r1 = new Rectangle(10,20);
        Triangle t = new Triangle(3,4,5);
        Figure f = null;
        System.out.println(compareFigure(f,r1));// false
        System.out.println(compareFigure(r,r1)); // true
        System.out.println(compareFigure(r1,t));// false
        System.out.println(compareFigure(r,t));// false

        System.out.println("---");
        printFigure(r1);

        System.out.println("---");
        printFigure(t);

        System.out.println("---");
        printFigure(new Figure());



        /**
         * Triangle test
         * */
        System.out.println("\n__________________");
        Triangle tr = new Triangle(3,4,4);
        System.out.println(tr.area());// -> 5.562148865321747
        System.out.println(tr.area(3.7081));// 3 ზე დაშვებული სიმაღლე -> 5.56215
        System.out.println(tr.typeOfTriangle());// 1 -> მახვილია
        System.out.println(tr.typeOfTriangle(67,67));// 1 -> მახვილია


        System.out.println("\n__________________");
        Triangle tr2 = new Triangle(8,9,3);
        System.out.println(tr2.area());// -> 11.832159566199232
        System.out.println(tr2.area(2.95804));// 8 ზე დაშვებული სიმაღლე -> 11.83216
        System.out.println(tr2.typeOfTriangle());// -1 -> ბლაგვია
        System.out.println(tr2.typeOfTriangle(99,19));// -1 -> ბლაგვია


        System.out.println("\n__________________");
        Triangle tr3 = new Triangle(3,4,5);
        System.out.println(tr3.area());// 3*4 -> 6.0
        System.out.println(tr3.area(4));// 3 ზე დაშვებული სიმაღლე -> 6.0
        System.out.println(tr3.typeOfTriangle());// 0 -> მართკუთხა
        System.out.println(tr.typeOfTriangle(90,36));// 0 -> მართკუთხა
        System.out.println(tr3.perimeter());// 3 + 4 + 5 -> 12.0
        System.out.println(tr3.equals(null));// false
        System.out.println(tr3.equals(new Triangle(4,4,4))); // 4+4+4 = 3+4+5 -> true
        System.out.println(tr3.equals(new Triangle(3,2,7)));//არარსებული სამკუთხედი -> No such triangle exists with this type of edges
                                                                    // false
        System.out.println(tr3.equals(new Triangle(7,8,9)));// 7 + 8 + 9 != 12 -> false
        System.out.println(tr3.equals(new Main())); // სხვა ინსტანსის ობიექტი -> false
        System.out.println(tr3); // toString -> Rectangle: (3.0, 4.0, 5.0)



        /**
         * Rectangle test
         * */
        System.out.println("\n__________________");
        Rectangle rectangle = new Rectangle(10,20);
        System.out.println(rectangle.calculateDiagonal());// Square Root(100+400) -> diagonal^2 = 500 -> 22.360679774997898
        System.out.println(rectangle.perimeter());// 2*30 -> 60
        System.out.println(rectangle.area());// 10 * 20 -> 200
        System.out.println(rectangle.equals(new Rectangle(4,50))); // 200 = 200 -> true
        System.out.println(rectangle.equals(new Triangle(10,13,4)));// triangle != rectangle -> false
        System.out.println(rectangle.equals(null));//false
    }

    public static void printFigure(Figure figure)
    {
        if(figure == null)
        {
            System.out.println("nullPointer input!");
            return;
        }

        System.out.println("Area: " + figure.area());
        System.out.println("Perimeter: " + figure.perimeter());
        if(figure instanceof Rectangle)
        {
            System.out.println("Diagonal: " + ((Rectangle) figure).calculateDiagonal());
        }
        else if(figure instanceof Triangle)
        {
            switch (((Triangle) figure).typeOfTriangle())
            {
                case 0 -> System.out.println("Triangle is Right angled");
                case 1 -> System.out.println("Triangle is acute angled");
                case -1 -> System.out.println("Triangle is obtuse angled");
            }
        }
        else
            figure.sayHello();
    }
    public static boolean compareFigure(Figure figure,Figure other)
    {
        if(figure == null || other == null)
            return false;
        if(figure.getClass() != other.getClass())
            return false;
        return figure.equals(other);
    }

}