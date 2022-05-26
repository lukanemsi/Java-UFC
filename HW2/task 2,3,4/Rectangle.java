
public class Rectangle
{
	private float length;
	private float width;
	
	public float area()
	{
		return length * width;	
	}
	public float perimeter()
	{
		return 2*(length + width);
	}
	
	public void setLength(float length)
	{
		this.length = length; 
	}
	public void setWidth(float width)
	{
		this.width = width; 
	}
	public float getLength() { return length; }
	public float getWidth() { return width; }

}
