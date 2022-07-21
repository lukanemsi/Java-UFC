package com.company.Annotations;

public class Main
{
    public static void main(String[] args)
    {
        Triangle triangle = new Triangle(3,4,5);
        ObjectToCustomStringConverter converter = new ObjectToCustomStringConverter();
        String converted = converter.convert(triangle);
        System.out.println(converted);

        Circle circle = new Circle(5);
        converted = converter.convert(circle);
        System.out.println(converted);

        Rectangle rectangle = new Rectangle(4,10);
        converted = converter.convert(rectangle);
        System.out.println(converted);

        try
        {
            Triangle invalidTriangle  = new Triangle(1,2,3);
            converter.convert(invalidTriangle);
        }catch (CustomStringSerializationException e)
        {
            System.err.println(e.getMessage());
        }
        try {
            Circle invalidCircle = new Circle(0);
            converter.convert(invalidCircle);
        }catch (CustomStringSerializationException e)
        {
            System.err.println(e.getMessage());
        }

    }
}
