import java.util.ArrayList;
import java.util.List;

public class Triangle
{
    private double a,b,c;
    public Triangle(double a,double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public boolean isRightTriangle()
    {
        var edges = new ArrayList<Double>();
        edges.add(a);
        edges.add(b);
        edges.add(c);
        edges.sort(Double::compare);
        return Math.pow(edges.get(0),2) + Math.pow(edges.get(1),2) == Math.pow(edges.get(2),2);
    }
    @Override
    public String toString() {
        return "Triangle(" +
                 + a +
                ", "+ b +
                ", " + c +
                ')';
    }
}
