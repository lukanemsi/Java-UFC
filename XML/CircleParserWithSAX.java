import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class CircleParserWithSAX
{
    public static void main(String[] args) {

        try {
            File inputFile = new File("Circle.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            CircleHandler circleHandler = new CircleHandler();
            saxParser.parse(inputFile,circleHandler);
            System.out.println(circleHandler.getCircleArrayList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class CircleHandler extends DefaultHandler
{
    boolean radius = false;
    private ArrayList<Circle> circleArrayList = new ArrayList<>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Radius"))
            radius = true;
    }



    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (radius) {
            String r = new String(ch, start, length);
            circleArrayList.add(new Circle(Double.parseDouble(r)));
            radius = false;
        }
    }

    public ArrayList<Circle> getCircleArrayList() {
        return circleArrayList;
    }
}

