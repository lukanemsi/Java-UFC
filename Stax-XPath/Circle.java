public class Circle implements Figure{

    private double radius;
    public Circle(double radius)
    {
        this.radius = radius;
    }
    @Override
    public double perimeter()
    {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area()
    {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle(" +
                radius +
                ')';
    }

    public double getRadius() {
        return radius;
    }
}