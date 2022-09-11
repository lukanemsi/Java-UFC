package ge.ufc.utils;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "triangle"
})
@XmlRootElement(name = "TriangleList")
public class TriangleList {

    @XmlElement(name = "Triangle")
    protected java.util.List<TriangleList.Triangle> triangle;


    public java.util.List<TriangleList.Triangle> getTriangle() {
        if (triangle == null)
            triangle = new ArrayList<>();
        return this.triangle;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "a",
            "b",
            "c"
    })
    public static class Triangle {

        protected int a;
        protected int b;
        protected int c;


        public int getA() {
            return a;
        }
        public void setA(int value) {
            this.a = value;
        }
        public int getB() {
            return b;
        }
        public void setB(int value) {
            this.b = value;
        }
        public int getC() {
            return c;
        }
        public void setC(int value) {
            this.c = value;
        }

    }

}