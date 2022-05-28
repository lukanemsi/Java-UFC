package ge.tbc.java.main;

/** 
 * TASK 6.1
 * **/
public class Main 
{
	public static void main(String[] args)
	{
		ge.tbc.java.homework.Rectangle rec = new ge.tbc.java.homework.Rectangle();
		rec.setLength(10);
		rec.setWidth(12);
		
		ge.tbc.java.homework.Rectangle rec2 = new ge.tbc.java.homework.Rectangle();
		rec2.setLength(9);
		rec2.setWidth(12);
		
		System.out.println(rec.equals(rec2)); // rec > rec2 +1
		
		
		ge.tbc.java.secondPackage.Rectangle secondRec = new ge.tbc.java.secondPackage.Rectangle();
		secondRec.setLength(20);
		secondRec.setWidth(22);
		
		ge.tbc.java.secondPackage.Rectangle secondRec2 = new ge.tbc.java.secondPackage.Rectangle();
		secondRec2.setLength(20);;
		secondRec2.setWidth(23);
		
		System.out.println(
				ge.tbc.java.secondPackage.Rectangle.equals(secondRec, secondRec2)
				); // secRec < secRec2 -1
		
	}
}
