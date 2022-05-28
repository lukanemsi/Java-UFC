package ge.tbc.java.homework;
/** 
 * TASK 2
 * **/
public class Rectangle
{
	private float length;
	private float width;
	
	float area()
	{
		return length * width;
	}
	float perimeter()
	{
		return 2*(length + width);
	}
	
	
	public int equals(Rectangle other) 
	{
		if(this.area() > other.area())
			return 1;
		if(this.area() < other.area())
			return -1;
		return 0;
	}
	public void setLength(float length) {this.length = length;}
	public void setWidth(float width) {this.width = width;}
	public float getLength() {return length;}
	public float getWidth() {return width;}
}
