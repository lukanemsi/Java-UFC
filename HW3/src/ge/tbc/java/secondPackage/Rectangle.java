package ge.tbc.java.secondPackage;

/** 
 * TASK 4,5
 * **/

public class Rectangle 
{
	private float length;
	private float width;
	
	protected float area()
	{
		return length * width;
	}
	protected float perimeter()
	{
		return 2*(length + width);
	}
	
	
	public static int equals(Rectangle r,Rectangle other) 
	{
		if(r.area() > other.area())
			return 1;
		if(r.area() < other.area())
			return -1;
		return 0;
	}
	public void setLength(float length) {this.length = length;}
	public void setWidth(float width) {this.width = width;}
	public float getLength() {return length;}
	public float getWidth() {return width;}

}
