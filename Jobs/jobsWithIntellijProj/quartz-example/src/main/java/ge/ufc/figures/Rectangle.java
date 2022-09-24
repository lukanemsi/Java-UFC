package ge.ufc.figures;

public class Rectangle
{
    private double width,length;
    public Rectangle(){}
    public Rectangle(double width, double length)
    {
        this.width = width;
        this.length = length;
    }
    public double area()
    {
        return width * length;
    }
    public double perimeter()
    {
        return 2 * (width + length);
    }
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", length=" + length +
                '}';
    }
}
