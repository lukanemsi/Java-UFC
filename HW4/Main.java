package ge.tbc.java.main;

public class Main 
{

	public static void main(String[] args)
	{	
		Rectangle[] rectangles = {
				new Rectangle(60,60),
				new Rectangle(70,70),
				new Rectangle(80,80),
				new Rectangle(10,10),
				new Rectangle(100,100)
				};
		
		var maxRec = Rectangle.rectangleOfMaxArea(rectangles);
		System.out.println(maxRec);  /* Rectangle [length=90.0, width=90.0] -> 
									   radgan maqsimaluri 100 seterdeba rogorc 90*/ 
		
		Rectangle rec = new Rectangle();
		System.out.println(rec.area()); // 25.0 -> 5*5
		
		Rectangle r = new Rectangle(-10,-100);
		System.out.println(rec.area()); // 25.0 -> 5*5
		
		Rectangle r1 = new Rectangle(10);
		System.out.println(r1.area()); //50 -> 10*5
		
		
	}
}
