public class SwapValues
{
	public static void main(String[] args)
	{
		int x = 5;
		int y = 4;
		System.out.println("x: " + x + "\n" + "y: " + y + "\n");
		x = x + 4; 
		y = x - y; 
		x = x - y; 
		System.out.println("x: " + x + "\n" + "y: " + y);
	}
}