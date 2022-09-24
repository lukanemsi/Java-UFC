package ge.ufc.webapps;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Triangle
{
    private double a,b,c;

    public Triangle(){}
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double pHalf = this.perimeter() / 2;
        return Math.sqrt(pHalf * (pHalf - a) * (pHalf - b) * (pHalf - c));
    }
    public boolean validate()
    {
        return a + b > c && a + c > b && c + b > a;
    }
    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }


    @XmlElement
    public void setA(double a) {
        this.a = a;
    }
    @XmlElement
    public void setB(double b) {
        this.b = b;
    }
    @XmlElement
    public void setC(double c) {
        this.c = c;
    }
}