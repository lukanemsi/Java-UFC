package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        /**task 2 method*/
        validateFileRegex(usernames,passwords);

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
    private static void validateFileRegex(List<String> fileUsernames,List<String> filePasswords)
    {
        Pattern userPattern = Pattern.compile("\\w{5,20}");
        Matcher matcher;
        for(String username : fileUsernames)
        {
            username = username.trim();
            matcher = userPattern.matcher(username);
            if(!matcher.matches())
                throw new RuntimeException("Invalid username Regex");
        }
        // აუცილებლად დაბალი და მაღალი რეგისტრი, და რაიმე სიმბოლო \\^w
        Pattern passPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{7,}");
        for(String password: filePasswords)
        {
            password = password.trim();
            matcher = passPattern.matcher(password);
            if(!matcher.matches())
                throw new RuntimeException("Invalid password Regex");
        }

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
