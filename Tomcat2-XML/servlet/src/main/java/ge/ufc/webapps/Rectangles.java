package ge.ufc.webapps;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
@XmlRootElement
public class Rectangles
{
    private List<Rectangle> rectangles;

    public List<Rectangle> getRectangles() {
        return rectangles;
    }
    @XmlElement(name = "rectangle")
    public void setRectangles(List<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }
}
