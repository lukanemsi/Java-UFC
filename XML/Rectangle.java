
public class Rectangle implements Figure{
    private double length;
    private double width;
    public Rectangle(double length,double width)
    {
        this.length = length;
        this.width = width;
    }
    @Override
    public double area() {
        return length * width;
    }

    @Override
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
}
