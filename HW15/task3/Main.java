package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)
    {
        String number = "(+995) [599] 12-34-56";
        // whitespace_ის ამოშლა
        number = number.replaceAll("\\s","");
        // ბრჩხილების და პლიუსის ამოშლა ბოლო კვადრატული ბრჩხილის გარდა
        number = number.replaceAll("[\\+()\\[]","");
        // კვადრატულის ჩანაცვლება ტირეთი
        number = number.replaceFirst("\\]","-");
        // პირველი სამი რიცხვის ამოშლა
        number = number.replaceFirst("[0-9]{3}","");
        // შევამოკლოთ თუ ნომერს გაგრძელება აქვს მაგალითდ '599-12-34-56llk1' -> '599-12-34-56'
        if(number.length() > 12)
            number = number.substring(0,12);

        checkNumber(number);
    }
    private static void checkNumber(String number)
    {
        Pattern pattern = Pattern.compile("5[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(number);
        if(matcher.find(0))
            System.out.println("matches!");
        else
            throw new RuntimeException("Invalid Regex");
    }
}
