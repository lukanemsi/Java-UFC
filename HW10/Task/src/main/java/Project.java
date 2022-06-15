import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Project {
    public static void main(String[] args) throws ValidateException
    {
        try {
            Triangle tr = new Triangle(10, 0, 0);
        }
        catch (TriangleValidateException e) {System.out.println(e.getMessage());}

        try
        {
            Triangle tr = new Triangle(1,2,3);
        }
        catch (TriangleValidateException e){System.out.println(e.getMessage());}

        // valid
        Triangle triangle = new Triangle(3,4,5);
        System.out.println(triangle);

        System.out.println("------------------");
        try{
            Rectangle rec = new Rectangle(1,-100);
        }catch (RectangleValidateException e){System.out.println(e.getMessage());}

        try
        {
            Rectangle rec = new Rectangle(0,1);
        }catch(RectangleValidateException e){System.out.println(e.getMessage());}

        Rectangle r1 = new Rectangle(10,10);
        System.out.println(r1);

        Rectangle r2 = new Rectangle(10,11);
        Rectangle r3 = new Rectangle(14,15);
        Rectangle r4 = new Rectangle(20,30);
        Rectangle r5 = new Rectangle(10,10);

        Rectangle r6 = new Rectangle(13,14);//RectangleLimitException

    }

}
