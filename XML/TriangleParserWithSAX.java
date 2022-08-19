import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class TriangleParserWithSAX
{
    public static void main(String[] args) {

        try {
            File inputFile = new File("Triangle.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            TriangleHandler triangleHandler = new TriangleHandler();
            saxParser.parse(inputFile,triangleHandler);
            System.out.println(triangleHandler.getTriangleArrayList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class TriangleHandler extends DefaultHandler
{
    boolean A = false;
    boolean B = false;
    boolean C = false;
    private double a;
    private double b;
    private double c;

    private ArrayList<Triangle> triangleArrayList = new ArrayList<>();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("A"))
            A = true;
        else if(qName.equalsIgnoreCase("B"))
            B = true;
        else if(qName.equalsIgnoreCase("C"))
            C = true;
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("Triangle")) {
            triangleArrayList.add(new Triangle(a,b,c));
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (A) {
            a = Double.parseDouble(new String(ch, start, length));
            A = false;
        }
        else if(B) {
            b = Double.parseDouble(new String(ch, start, length));
            B = false;
        }
        else if(C) {
            c = Double.parseDouble(new String(ch, start, length));
            C = false;
        }

    }

    public ArrayList<Triangle> getTriangleArrayList() {
        return triangleArrayList;
    }
}
