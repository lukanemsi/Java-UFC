import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project
{
    private static final Console console = System.console();

    public static void main(String[] args)
    {
        if(console == null)
        {
            System.out.println("Run From Console!");
            return;
        }
//        addUser("us4","pas4",new File("user.dat"));
        List<String> fileData;
        try(BufferedReader reader = new BufferedReader(new FileReader("user.dat")))
        {
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
            if(lineCounter % 2 == 0)
                usernames.add(line);
            else
                passwords.add(line);
            lineCounter++;
        }

        Scanner scanner = new Scanner(System.in);
        int input;
        int totalSum = 0;
        String line;
        System.out.println("Hello SUM");
        while (true)
        {
            // ყოველ შემოსვლისას პაროლის და სახელის ჩაწერა
            if(!validate(usernames,passwords))
                continue;
            System.out.println("Enter natural number: ");
            if(scanner.hasNextInt())
            {
                if((input = scanner.nextInt()) >= 0)
                {
                    totalSum += input;
                    System.out.println("Total sum is: " + totalSum);
                }
                else
                    System.err.println("Incorrect  whole number value");
            }
            else if(scanner.hasNextLine())
            {
                line = scanner.nextLine();
                if(line.equals(""))
                    line = scanner.nextLine();
                if("end".equals(line))
                    break;
                else
                    System.err.println("Input wasn't number");
            }
        }
        scanner.close();
        System.out.println("Bye SUM");
    }

    private static boolean validate(List<String> usernames,List<String> passwords)
    {
        String userInput;
        String passInput;
        System.out.println("Enter username: ");
        userInput = console.readLine();
        if(!isInList(usernames,userInput))
        {
            System.err.println("Wrong username!");
            return false;
        }
        System.out.println("Enter password: ");
        // პაროლის წაკითხვა დამალულად
        passInput = new String(console.readPassword());
        int userIndex = usernames.indexOf(userInput);
        if(!passInput.equals(passwords.get(userIndex)))
        {
            System.err.println("Wrong password!");
            return false;
        }
        return true;
    }
    // appends new user to file
    private static void addUser(String username,String password,File file)
    {	
		if(file == null || username == null || password == null)
			{
				System.out.println("Null inputs");
				return;
			}
        if(!file.exists())
        {
            System.out.println("File doesn't exists");
            return;
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)))
        {
            writer.write("\n" + username + "\n");
            writer.write(password);
        } catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
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





