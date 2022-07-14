package com.company.RTI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        /** task 1
         * კონსტრუქტორი კითხულობს ინახავს arrayList_ში და შემდეგ ბეჭდავს*/
        try
        {
            Constructor<Circle> circleConstructor = Circle.class.getDeclaredConstructor(String.class);
            circleConstructor.setAccessible(true);
            Circle circle = circleConstructor.newInstance("Circle_in.dat");

            Constructor<Rectangle> rectangleConstructor = Rectangle.class.getDeclaredConstructor(String.class, boolean.class);
            rectangleConstructor.setAccessible(true);
            Rectangle rectangle = rectangleConstructor.newInstance("Rectangle_in.dat", true);


            Constructor<Triangle> triangleConstructor = Triangle.class.getDeclaredConstructor(String.class, boolean.class, boolean.class);
            triangleConstructor.setAccessible(true);
            Triangle triangle = triangleConstructor.newInstance("Triangle_in.dat", true, true);

        }
        catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        /** task 3*/
        System.out.println("------------------------------");
        System.out.println(readFigureChild(new Triangle()));
        System.out.println(readFigureChild(new Circle()));
        System.out.println(readFigureChild(new Rectangle()));
        System.out.println("------------------------------");

        /** task 4,5*/
        System.out.println(function2(new Triangle(3,4,5)));

        System.out.println(FigureMethod.method4(new Circle(1),FigureMethod.GET_AREA));

        List<Figure> figures = List.of(
                new Circle(10),
                new Triangle(100,200,133),
                new Rectangle(1,2),
                new Triangle(1,2,3));
        System.out.println(triangleCounter(figures));

        /** task 6*/
        try
        {
            Method hashCode = Circle.class.getMethod("hashCode");
            System.out.println(hashCode.invoke(new Circle(10)));
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        /** task 7 */
        try {
            Triangle t = new Triangle(3, 4, 5);
            Method isRight = t.getClass().getDeclaredMethod("isRightAngled");
            isRight.setAccessible(true);
            System.out.println(isRight.invoke(t));

            Rectangle r = new Rectangle(10,10);
            Method isSquare = r.getClass().getDeclaredMethod("isSquare");
            isSquare.setAccessible(true);
            System.out.println(isSquare.invoke(r));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
    /** task 5 */
    private static long triangleCounter(List<Figure> figures)
    {
        return figures.stream().filter(figure -> figure.getClass() == Triangle.class).count();
    }

    /** task 2*/
    private static HashMap<String,Double> function2(Figure figure)
    {
        HashMap<String,Double> result = new HashMap<>();
        Method[] methods = figure.getClass().getDeclaredMethods();
        List<Method> methodList = Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get"))
                .filter(method -> Arrays.equals(method.getParameterTypes(), new Class[]{}))
                .filter(method -> method.getReturnType().equals(double.class))
                .collect(Collectors.toList());

        for(Method m: methodList)
        {
            m.setAccessible(true);
            double methodResult = -1;
            try
            {
                methodResult = (double) m.invoke(figure);
            } catch (IllegalAccessException | InvocationTargetException e)
            {
                System.err.println(e.getMessage());
            }
            result.put(m.getName(),methodResult);
        }
        return result;
    }

    /** task 3 */
    /**
     *  ქვედა მეთოდებში(createCircle,createTriangle,createRectangle) Field ებად ვიყენებ მხოლოდ ისეთ field_ს რომელიც double_ს აბრუნებს
     *  და მათ რაოდენობაზე დამოკიდებულია, თუ რაიმეს დავამატებთ exception_ი გვექნება
     * */
    private static ArrayList<? extends Figure> readFigureChild(Figure figure)
    {
        ArrayList<? extends Figure> figures = new ArrayList<>();
        String regex = null;
        String path = null;
        if (Triangle.class.equals(figure.getClass()))
        {
            regex = "[.0-9]+-[.0-9]+-[.0-9]+";
            path = "Triangle_in.dat";
        } else if (Rectangle.class.equals(figure.getClass()))
        {
            regex = "[.0-9]+-[.0-9]+";
            path = "Rectangle_in.dat";
        } else if (Circle.class.equals(figure.getClass()))
        {
            regex = "[.0-9]+";
            path = "Circle_in.dat";
        }
        if(path == null)
            return figures;


        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            final String finalRegex = regex;
            List<String> validLines =  reader.lines().map(String::trim).filter(str -> str.matches(finalRegex)).collect(Collectors.toList());
            if(figure.getClass() == Triangle.class)
            {
                   return createTriangles(validLines);
            }
            else if(figure.getClass() == Circle.class)
            {
                return createCircles(validLines);
            }
            return createRectangles(validLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return figures;
    }
    private  static ArrayList<Triangle> createTriangles(List<String> validLines)
    {
        ArrayList<Triangle> triangles = new ArrayList<>();
        Function<String,Double[]> function = new Function<String, Double[]>() {
            @Override
            public Double[] apply(String s) {
                String[] arr = s.split("-");
                Double[] result = {
                        Double.valueOf(arr[0]),
                        Double.valueOf(arr[1]),
                        Double.valueOf(arr[2])
                };
                return result;
            }
        };
        List<Double[]> doubleArrList = validLines.stream().map(function).collect(Collectors.toList());
        /** main part using fields*/
        for(Double[] doubles: doubleArrList)
        {
            Triangle triangle = new Triangle();
            Field[] fields = triangle.getClass().getDeclaredFields();
            fields = Arrays.stream(fields).filter(f -> f.getType().equals(double.class)).toArray(Field[]::new);
            try{
                for (int i = 0; i < fields.length; i++)
                {
                    fields[i].setAccessible(true);
                    fields[i].set(triangle,doubles[i]);

                }
                triangles.add(triangle);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return triangles;
    }
    private static ArrayList<Circle> createCircles(List<String> validLines)
    {
        ArrayList<Circle> circles = new ArrayList<>();
        List<Double> radius = validLines.stream().map(Double::valueOf).collect(Collectors.toList());
        try
        {
            /** main part using fields*/
            for(Double r: radius)
            {
                Circle circle = new Circle();
                Field field = circle.getClass().getDeclaredField("radius");
                field.setAccessible(true);
                field.set(circle,r);
                circles.add(circle);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return circles;
    }
    private static ArrayList<Rectangle> createRectangles(List<String> validLines)
    {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        Function<String,Double[]> function = new Function<String, Double[]>()
        {
            @Override
            public Double[] apply(String s) {
                String[] arr = s.split("-");
                Double[] result = {Double.valueOf(arr[0]),Double.valueOf(arr[1])};
                return result;
            }
        };
        List<Double[]> doubleArrList = validLines.stream().map(function).collect(Collectors.toList());

        for(Double[] doubles: doubleArrList)
        {
            /** main part using fields*/
            Rectangle rectangle = new Rectangle();
            Field[] fields = rectangle.getClass().getDeclaredFields();
            fields = Arrays.stream(fields).filter(f -> f.getType().equals(double.class)).toArray(Field[]::new);
            try{
                for (int i = 0; i < fields.length; i++)
                {
                    fields[i].setAccessible(true);
                    fields[i].set(rectangle,doubles[i]);

                }
                rectangles.add(rectangle);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return rectangles;
    }
}
