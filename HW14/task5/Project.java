public class Project {
    public static void main(String[] args)
    {
        if(args.length < 1){
            System.out.println("Argument expected!");
            return;
        }
        switch (args[0])
        {
            case "January":
                System.out.println("position 1");
                System.out.println("Odd");
                break;
            case "February":
                System.out.println("position 2");
                System.out.println("Even");
                break;
            case "March":
                System.out.println("position 3");
                System.out.println("Odd");
                break;
            case "April":
                System.out.println("position 4");
                System.out.println("Even");
                break;
            case "May":
                System.out.println("position 5");
                System.out.println("Odd");
                break;
            case "June":
                System.out.println("position 6");
                System.out.println("Even");
                break;
            case "July":
                System.out.println("position 7");
                System.out.println("Odd");
                break;
            case "August":
                System.out.println("position 8");
                System.out.println("Even");
                break;
            case "September":
                System.out.println("position 9");
                System.out.println("Odd");
                break;
            case "October":
                System.out.println("position 10");
                System.out.println("Even");
                break;
            case "November":
                System.out.println("position 11");
                System.out.println("Odd");
                break;
            case "December":
                System.out.println("position 12");
                System.out.println("Even");
                break;
            default:
                System.err.println("No such Month exists");
        }
    }
}