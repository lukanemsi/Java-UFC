package ge.tbc.java.secondMain;

import ge.tbc.java.secondPackage.Rectangle;

public class Main extends Rectangle
{
	public static void main(String[] args)
	{
		
		Rectangle rec = new Rectangle();
		//rec.area(); -> არ კომპილირდება რადგან Area protected არის და ვიმყოფებით სხვა პაკეტში
		
		
		// კომპილირდება რადგან Main კლასი Rectangle კლასის შთამომავალია
		Main main = new Main();
		main.setLength(12);
		main.setWidth(12);
		System.out.println(main.area());
		
	}
}
