import org.jdom2.Attribute;
import org.jdom2.Element;
import  org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.xpath.*;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        generateTriangles("tr.xml");
        ArrayList<Triangle> triangles = readTriangles("tr.xml");
        generateRectangles("rec.xml");
        ArrayList<Rectangle> rectangles = readRectangles("rec.xml");
        generateCircles("circle.xml");
        ArrayList<Circle> circles = readCircles("circle.xml");
        generateCirclesJdom("c.xml");
        ArrayList<Circle> circless = readCirclesXPath("c.xml");

    }
    // task 1.1
    public static void generateTriangles(String path)
    {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try {
            writer = factory.createXMLStreamWriter(new FileWriter(path));
            writer.writeStartDocument();
            writer.writeStartElement("TriangleList");
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < 10; i++)
        {
            Triangle triangle = new Triangle(Math.random() * 10 + 1,Math.random() * 10 + 1,Math.random() * 10 + 1);
            try {


                writer.writeStartElement("Triangle");
                writer.writeAttribute("id",String.valueOf(i));
                writer.writeStartElement("A");
                writer.writeCharacters(String.valueOf(triangle.getA()));
                writer.writeEndElement();
                writer.writeStartElement("B");
                writer.writeCharacters(String.valueOf(triangle.getB()));
                writer.writeEndElement();
                writer.writeStartElement("C");
                writer.writeCharacters(String.valueOf(triangle.getC()));
                writer.writeEndElement();
                writer.writeStartElement("Perimeter");
                writer.writeCharacters(String.valueOf(triangle.perimeter()));
                writer.writeEndElement();
                writer.writeEndElement();


            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.writeEndDocument();
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
    // task 1.2
    public static ArrayList<Triangle> readTriangles(String path)
    {
        ArrayList<Triangle> triangleArrayList = new ArrayList<>();
        try{
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(new File(path));
            Element root = doc.getRootElement();
            List<Element> trElements = root.getChildren();
            for (int i = 0; i < trElements.size(); i++) {
                Element triangle = trElements.get(i);
                Element perimeter = triangle.getChild("Perimeter");
                double value = Double.parseDouble(perimeter.getText());
                if(value > 20)
                {
                    triangleArrayList.add(
                      new Triangle(
                              Double.parseDouble(triangle.getChild("A").getText()),
                              Double.parseDouble(triangle.getChild("B").getText()),
                              Double.parseDouble(triangle.getChild("C").getText())

                      )
                    );
                }
            }
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return triangleArrayList;

    }
    // task 2.1
    public static void generateRectangles(String path)
    {

        try{
            Document doc = new Document(new Element("RectangleList"));
            for (int i = 0; i < 10; i++) {
                Rectangle rectangle = new Rectangle(Math.random() * 10 + 1, Math.random() * 10 + 1);
                Element rec = new Element("Rectangle");
                rec.setAttribute("id",String.valueOf(i));
                Element width = new Element("Width");
                width.setText(String.valueOf(rectangle.getWidth()));
                Element length = new Element("Length");
                length.setText(String.valueOf(rectangle.getLength()));
                Element perimeter = new Element("Perimeter");
                perimeter.setText(String.valueOf(rectangle.perimeter()));
                rec.addContent(width);
                rec.addContent(length);
                rec.addContent(perimeter);
                doc.getRootElement().addContent(rec);
            }
            XMLOutputter xmlOutput = new XMLOutputter();

            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileOutputStream(new File(path)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // task 2.2
    public static ArrayList<Rectangle> readRectangles(String path)
    {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        boolean  width = false;
        boolean length = false;
        double w = 0;
        double l = 0;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(path));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String name = startElement.getName().getLocalPart();
                        if (name.equalsIgnoreCase("Width")) {
                            width = true;
                        } else if (name.equalsIgnoreCase("Length")) {
                            length = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (width) {
                            w = Double.parseDouble(characters.getData());
                            width = false;
                        }
                        if (length) {
                            l = Double.parseDouble(characters.getData());
                            length = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase("Rectangle")) {
                            if((l + w) * 2 > 20)
                                rectangles.add(new Rectangle(l,w));
                        }
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return rectangles;
    }
    // task 3.1
    public static void generateCircles(String path)
    {
        try{
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(path));
            writer.writeStartDocument();
            writer.writeStartElement("CircleList");
            for (int i = 0; i < 10; i++) {
                Circle circle = new Circle(Math.random() * 100 + 1);
                writer.writeStartElement("Circle");
                writer.writeAttribute("radius",String.valueOf(circle.getRadius()));
                writer.writeEndElement();
            }
            writer.writeEndDocument();
            writer.close();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
    // task 3.2
    public static ArrayList<Circle> readCircles(String path)
    {
        ArrayList<Circle> circles = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            XPathExpression xPathExpression = xPath.compile("/CircleList/Circle[@radius > 30]");
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) nNode;
                    double rad = Double.parseDouble(element.getAttribute("radius"));
                    circles.add(new Circle(rad));
                }
            }
            XPathExpression secondXPathExpression = xPath.compile("/CircleList/Circle[@radius < 40][last()]");
            Node  node = (Node) secondXPathExpression.evaluate(doc,XPathConstants.NODE);
            String r = node.getAttributes().item(0).getTextContent();
            circles.add(new Circle(Double.parseDouble(r)));
        } catch (ParserConfigurationException | SAXException | XPathExpressionException | IOException e) {
            e.printStackTrace();
        }
        return  circles;
    }
    // task 4.1
    public static void generateCirclesJdom(String path)
    {
        try{
            Document doc = new Document(new Element("CircleList"));
            for (int i = 0; i < 10; i++) {
                Circle c = new Circle(Math.random() * 100 + 1);
                Element circle = new Element("Circle");
                Element radius = new Element("Radius");
                radius.setText(String.valueOf(c.getRadius()));
                circle.addContent(radius);
                doc.getRootElement().addContent(circle);
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileOutputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // task 4.2
    public static ArrayList<Circle> readCirclesXPath(String path)
    {
        ArrayList<Circle> circles = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            XPathExpression xPathExpression = xPath.compile("/CircleList/Circle/Radius[text() <= 30]");
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
                {
                    circles.add(new Circle(Double.parseDouble(nodeList.item(i).getTextContent())));
                }
            }
            XPathExpression secondXPathExpression = xPath.compile("/CircleList/Circle/Radius[text() > 40][1]");
            Node  node = (Node) secondXPathExpression.evaluate(doc,XPathConstants.NODE);
            String r = node.getTextContent();
            circles.add(new Circle(Double.parseDouble(r)));
        } catch (ParserConfigurationException | SAXException | XPathExpressionException | IOException e) {
            e.printStackTrace();
        }
        return  circles;
    }
}

