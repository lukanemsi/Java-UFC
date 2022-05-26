import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{	
		
		System.out.println("Input: ");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		scanner.close();
		
		//ინიციალიზაციის გარეშე მოქმედებები Compile ერორს გვაძლევს რადგან, ობიექტის ატრიბუტისგან განსხვავებით აქ მას მნიშვნელობა ავტომატურად არ ენიჭება		
		int i = 5;
		if(i + input > 10) 
		{
			int j = 94;
			i += input + j;
		}
		int j = 1;
		System.out.print(i+j);
		
		//if condition == true ->
		// ( j(94) + i(5) + j(1) ) 100 + input,
		// else ->  i (5) + j (1)
		
	}
	
}
