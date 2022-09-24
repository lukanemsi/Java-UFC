package ge.ufc.figures;

public class Circle
{
    private double radius;
    public Circle(double radius)
    {
        this.radius = radius;
    }

    public double area()
    {
        return Math.PI * radius * radius;
    }
    public double length()
    {
        return Math.PI * radius * 2;
    }
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
