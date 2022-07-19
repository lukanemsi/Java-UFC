package Annotation;
import java.util.Arrays;
import java.util.List;

public class Main
{
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        @SuppressWarnings("deprecation") double length = Triangle.getLengthStatic(new Triangle(1,2,3));
        @SuppressWarnings("rawtypes") List triangles = Triangle.readTriangles("Triangle_in.dat");
        FunctionalI addPer = (Figure f1,Figure f2) -> f1.perimeter() + f2.perimeter();
        FunctionalI addArea = (Figure f1,Figure f2)  -> f1.area() + f2.area();

    }

    @SuppressWarnings("unused")
    @SafeVarargs
    public static void method(List<Figure>... figures)
    {
        Arrays.stream(figures).forEach(list -> list.forEach(System.out::println));
    }
}
