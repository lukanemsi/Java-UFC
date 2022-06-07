package com.company;


import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.css.Rect;

public class Main{
    /** Rectangle  და Triangle კლასების კონსტრუქტორებიდან შეზღუდვები მოვსენი რადგან
     * invalid ფიგურა შექმნილიყო და შემდეგ ინტერფეისის მეთოდს გაერკვია
     * */
    public static void main(String[] args)
    {
        Triangle triangle = new Triangle(10,9,8);
        Rectangle rectangle = new Rectangle(10,20);

        System.out.println("---------------");
        method((LFigure) triangle);
        method((LFigureAnother) triangle);

        System.out.println("---------------");
        method((LFigure) rectangle);
        method((LFigureAnother) rectangle);

        // invalid inputs
        triangle = new Triangle(10,100,20);
        rectangle = new Rectangle(-1,20);

        System.out.println("---------------");
        method((LFigureAnother) triangle); // invalid Figure
        method((LFigureAnother) rectangle);// invalid Figure

        System.out.println("---------------");
        // მეთოდში დაქასთვა აღარ ჭირდება რადგან არსებული რეფერენსი მეთოდის სიგნატურას ემთხვევა
        LFigure tr = new Triangle(3,4,5);
        LFigure re = new Rectangle(10,10);
        method(re);
        method(tr);

        System.out.println("---------------");
        // invalid inputs
        LFigureAnother laTr = new Triangle(-1,0,10);
        LFigureAnother laRe = new Rectangle(-1,-1);
        method(laRe);
        method(laTr);

        System.out.println("---------------");
        Figure figureR = new Rectangle(30,40);
        Figure figureT = null;
        method((LFigure) figureR);
        method((LFigureAnother) figureT);
    }
    public static void method(LFigure lFigure)
    {
        if(lFigure == null)
        {
            System.out.println("Null input");
            return;
        }
        lFigure.printFigureData();
        lFigure.printFigureAreaAndLength();
    }
    public static void method(LFigureAnother lFigureAnother)
    {
        if(lFigureAnother == null)
        {
            System.out.println("Null input");
            return;
        }
        if(lFigureAnother.validateFigure())
            lFigureAnother.sayHelloToFigure();
        else
            System.out.println("Invalid Figure");

    }

}