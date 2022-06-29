package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int input;
        int totalSum = 0;

        List<String> fileData;
        try(BufferedReader reader = new BufferedReader(new FileReader("user.dat")))
        {
            //წინა შენიშვნა წავიკითხე, ექსტრემალურ სიტუაციაში მომიწია ამ კოდის წერა და აღარ შევცვალე ბოდიშს გიხდი
            fileData = reader.lines().toList();
        }catch (IOException e)
        {
            System.err.println(e.getMessage());
            return;
        }
        List<String> passwords = new ArrayList<>();
        List<String> usernames = new ArrayList<>();
        int lineCounter = 0;
        for(String line: fileData)
        {
            /**პირველი ნაწილი მოვაშორება regex_ით*/
            String[] twoLines = line.split("=");
            if(lineCounter % 2 == 0)
                usernames.add(twoLines[1]);
            else
                passwords.add(twoLines[1]);
            lineCounter++;
        }
        String line;
        System.out.println("Hello SUM");

        String username,password;

        while (true)
        {
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            System.out.println("Enter password: ");
            password = scanner.nextLine();
            if(!validate(usernames,passwords,username,password))
                continue;
            System.out.println("Enter natural number: ");
            if(scanner.hasNextInt())
            {
                if((input = scanner.nextInt()) >= 0)
                {
                    totalSum += input;
                    System.out.println("Total sum is: " + totalSum);
                    scanner.nextLine();
                }
                else
                    System.err.println("Incorrect  whole number value");
            }
            else if(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                if(line.equals(""))
                    continue;
                if("end".equals(line))
                    break;
                else
                    System.err.println("Input wasn't number");

            }
        }
        scanner.close();
        System.out.println("Bye SUM");
    }

    private static boolean validate(List<String> usernames,List<String> passwords,String userInput,String passInput)
    {

        if(!isInList(usernames,userInput))
        {
            System.err.println("Wrong username!");
            return false;
        }
        int userIndex = usernames.indexOf(userInput);
        if(!passInput.equals(passwords.get(userIndex)))
        {
            System.err.println("Wrong password!");
            return false;
        }
        return true;
    }
    private static <T> boolean isInList(List<T> list,T t)
    {
        for(T elem: list)
        {
            if(t.equals(elem))
                return true;
        }
        return false;
    }
}
