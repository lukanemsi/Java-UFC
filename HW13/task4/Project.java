import java.io.*;
import java.util.Scanner;

public class Project {
    public static void main(String[] args)
    {
        BufferedReader scanner;
        try {
            scanner = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Hello SUM");
        int totalSum = 0;
        String input;
        while(true)
        {
            System.out.println("Enter whole number: ");
            try
            {
                input = scanner.readLine();
            } catch (IOException e)
            {
                System.err.println(e.getMessage());
                return;
            }
            if(isNumber(input))
            {
                totalSum += Integer.parseInt(input);
                System.out.println("TotalSum: " + totalSum);
            }
            else if("end".equals(input))
            {
                System.out.println("Bye SUM");
                break;
            }
            else
            {
                // ხანდახან დაბეჭვდას აგვიანებს და ჯერ Enter whole number_ ეს იბეჭდება და შემდეგ ეს მესიჯი წითლად, როდესაც System.out.println_ით შევცვალე სრულიად გამოსწორდა.
                System.err.println("Incorrect whole number value");
            }
        }
    }
    private static boolean isNumber(String input)
    {
        try {
            Integer.parseInt(input);
        }catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
}





