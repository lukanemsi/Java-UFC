package ge.tbc.java.secondMain;

import ge.tbc.java.secondPackage.Rectangle;

public class Main extends Rectangle
{
	public static void main(String[] args)
	{
		
		Rectangle rec = new Rectangle();
		//rec.area(); -> compile error because its other package and area is protected
		
		
		
		Main main = new Main();
		main.setLength(12);
		main.setWidth(12);
		System.out.println(main.area());
		
	}
}
