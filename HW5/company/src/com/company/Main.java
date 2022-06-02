package com.company;


import com.company.ufc.Square;

public class Main{

    public static void main(String[] args)
    {
        Figure figure = new Figure("abstract Figure");
        Rectangle rectangle = new Rectangle(10,20);

        System.out.println(figure.area()); // -1
        System.out.println(figure.perimeter()); // -1
        System.out.println("--------------");

        System.out.println(rectangle.area());// 10 * 20 -> 200
        System.out.println(rectangle.perimeter());// 2 * 30 -> 60
        System.out.println("--------------");

        System.out.println(
                rectangle.area(rectangle.getLength(),rectangle.getWidth())
        );// 200, იგივე შედეგი უბრალოდ, overloaded მეთოდის გამოზახებით
        System.out.println("--------------");

        System.out.println(figure instanceof Figure); // true, ობიექთი ყოველთვის ინსტანსია თავისივე კლასის
        System.out.println(figure instanceof Rectangle);// false, რადგან Figure კლასი Rectangle_ კლასისგან არ იღებს შთამომავლობას
        System.out.println(rectangle instanceof Figure);// true, რადგან Rectangle კლასი Figure კლასის შთამომავალია
        System.out.println("--------------");


        Square square1 = new Square(10);
        System.out.println(square1.area()); // 10 * 10 -> 100
        System.out.println("--------------");


    }
}