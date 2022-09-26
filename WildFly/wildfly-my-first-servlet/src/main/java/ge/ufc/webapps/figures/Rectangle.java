package ge.ufc.webapps.figures;

public class Rectangle
{
    private double length;
    private double width;
    public Rectangle(double length,double width)
    {
        this.length = length;
        this.width = width;
    }

    public double area() {
        return length * width;
    }


    public double perimeter() {
        return 2*(length + width);
    }
    @Override
    public String toString() {
        return "Rectangle{ " +
                "l=" + length +
                ", w=" + width +
                " }";
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}
