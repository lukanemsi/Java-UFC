import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name ="",propOrder = {"rectangle"})
@XmlRootElement(name = "RectangleList")
public class RectangleList
{
    @XmlElement(name = "Rectangle")
    protected java.util.List<RectangleList.Rectangle> rectangle;

    public List<Rectangle> getRectangle() {
        if(rectangle == null)
            rectangle = new ArrayList<>();
        return rectangle;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "width",
            "length"
    })
    public static class Rectangle {

        protected int length;
        protected int width;
        public int getLength() {
            return length;
        }
        public void setLength(int length) {
            this.length = length;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

}