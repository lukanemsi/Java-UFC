import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class RectangleParserWithSAX
{
    public static void main(String[] args) {
        try {
            File inputFile = new File("rectangle.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            RectangleHandler rectangleHandler = new RectangleHandler();
            saxParser.parse(inputFile,rectangleHandler);
            System.out.println(rectangleHandler.getRectangleArrayList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class RectangleHandler extends DefaultHandler
{
    private boolean width = false;
    private boolean length = false;
    private double w,l;
    private ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("width"))
            width = true;
        else if(qName.equalsIgnoreCase("length"))
            length = true;

    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("Rectangle")) {
            rectangleArrayList.add(new Rectangle(l,w));
        }

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (width) {
            w = Double.parseDouble(new String(ch, start, length));
            width = false;
        }
        else if(this.length) {
            l = Double.parseDouble(new String(ch, start, length));
            this.length = false;
        }


    }
    public ArrayList<Rectangle> getRectangleArrayList() {
        return rectangleArrayList;
    }
}
