import java.util.Scanner;

public class Project
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int input;
        int totalSum = 0;
        String line;
        System.out.println("Hello SUM");
        System.out.println("Enter natural number: ");
        while (scanner.hasNext())
        {

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
                /**
                 * ამ პირობის გარეშე კონსულში ჩაწერისას ჯერ რამდენჯერმე დაენთერება და შემდეგ სტრინგის მითიტება გამოიწვევდა ტექსტის მრავალჯერ დაპრინტვას,
                 * ასევე ინტეჯერის შემდეგ არასწორი ტექსტის შეყვანითაც ტექსტი ორჯერ დაიბეჭდებოდა,
                 * ხოლო ამით ცარიელ nextLine()_ს  ე.წ. leftover_ს ვახტები რადგან ტექსტი მრავალჯერ არ გაამეოროს
                 * */
                if(line.equals(""))
                    continue;
                if("end".equals(line))
                    break;
                else
                    System.err.println("Input wasn't number");

            }
            System.out.println("Enter natural number: ");
        }
        scanner.close();
        System.out.println("Bye SUM");
    }
}





