package ge.tbc.java.main;

public class Rectangle
{
	
	/*** task 1 **/
	private float length;
	private float width;
	private final static float MAXIMUM;
	private final static float DEFAULT;
	
	/*** task 2 **/
	static {
		MAXIMUM = 90f;
		DEFAULT = 5f;
	}
	
	/*** task 3 **/
	public Rectangle(float length,float width) 
	{	
	
		setLength(length);
		setWidth(width);
	}
	public Rectangle(float length)
	{
		this(length,DEFAULT);
	}
	public Rectangle()
	{
		this(DEFAULT,DEFAULT);
	}
	
	/*** task 4 **/
	public static Rectangle rectangleOfMaxArea(Rectangle[] rectangles)
	{
		// 0,0 it shevqmeni radgan winaagmdeg shemtxvevashi default valueti fartobi 5*5 iqneboda
		// xolo sheidzleba masivshi yvelas masze dabali area hqonoda shesabamisad araswori return value iqneboda
		Rectangle maxRec = new Rectangle(0,0);
		for(Rectangle rec: rectangles)
		{	
			if(rec.area() > maxRec.area())
				maxRec = rec;
		}
		return maxRec;
	}
	
	public float area()
	{
		return length*width;
	}
	public float perimeter()
	{
		return 2*(length + width);
	}
	
	private void setLength(float length)
	{	
		if(length <= 0)
			this.length = 0;
		else
			this.length = length > MAXIMUM ? MAXIMUM : length;
	}
	private void setWidth(float width)
	{
		if(width <= 0)
			this.width = 0;
		else
			this.width = width > MAXIMUM ? MAXIMUM : width;
	}

	public float getLength() {return length;}
	public float getWidth() {return width;}

	@Override
	public String toString()
	{
		return "Rectangle [length=" + length + ", width=" + width + "]";
	}
}
