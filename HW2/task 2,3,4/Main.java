
public class Main {
	public static void main(String[] args)
	{
		var r1 = new Rectangle();
		r1.setLength(10);
		r1.setWidth(5);
		
		var r2 = new Rectangle();
		r2.setLength(10);
		r2.setWidth(5);
		System.out.println(areaEquality(r1,r2)); // 0
		
		
		/**task3*/
		/* A - ობიექტის ფუნქციის გამოძახება მისი ინიციალიზაციის გარეშე Compile ერორს გვაძლევს
		 * B - ხოლო null ის მინიჭების შემდეგ, კომპილირდება მაგრამ გვაქვს nullPointerExceptioni რადგან ობიექტის Reference Null_ია და
		 * ვიძახებთ ფუნქციას.
		 * C - სეთერის გამოძახების გარეშე გეთერი 0_ს აბრუნებს რადგან დიფოლთად კლასის რიცხვითი ატრიბუტის მნიშვნელობა ნოლია
		 * D - == მცდარია როდესაც რეფერენსი ხდება სხვადასხვა მისამართზე, სწორია როდესაც ერთ ობიექტზე ხდება რეფერენცი -> 
		 * Rectangle r1 = new Rectangle();
		 * Rectangle r2 = r1;
		 * */
		
		Rectangle r3;
		r3 = new Rectangle();
		System.out.println(r3.getLength()); // 0.0	
		
		r2 = r1;
		r1.setLength(100);
		System.out.println(r2.getLength()); // 100.0
	}
	
	// task4
	public static int areaEquality(Rectangle rectangle,Rectangle other)
	{
		if(rectangle.area() > other.area())
			return 1;
		if(rectangle.area() < other.area())
			return -1;
		return 0;
	}
}
