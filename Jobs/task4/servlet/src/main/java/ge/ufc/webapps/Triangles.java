package ge.ufc.webapps;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
@XmlRootElement
public class Triangles {
    private List<Triangle> triangles;


    @XmlElement(name = "triangle")
    public List<Triangle> getTriangles() {
        return triangles;
    }

    public void setTriangles(List<Triangle> triangles) {
        this.triangles = triangles;
    }
}
