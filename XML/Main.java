import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //task1
        circleXML("Circle_in.dat");
        rectangleXML("Rectangle_in.dat");
        triangleXML("Triangle_in.dat");
        //task2
        parseCircleXML("circle.xml");
        parseRectangleXML("rectangle.xml");
        parseTriangleXML("triangle.xml");
        //task3
        modifyRectangleXML("rectangle.xml");
    }
    public static void parseTriangleXML(String path) {
        ArrayList<Triangle> triangleArrayList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Triangle");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String a = element.getElementsByTagName("A").item(0).getTextContent();
                    String b = element.getElementsByTagName("B").item(0).getTextContent();
                    String c = element.getElementsByTagName("C").item(0).getTextContent();
                    triangleArrayList.add(new Triangle(Double.parseDouble(a), Double.parseDouble(b), Double.parseDouble(c)));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(triangleArrayList);
    }
    public static void parseCircleXML(String path) {
        ArrayList<Circle> circleArrayList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Circle");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String radius = element.getElementsByTagName("radius").item(0).getTextContent();
                    circleArrayList.add(new Circle(Double.parseDouble(radius)));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(circleArrayList);
    }
    public static void parseRectangleXML(String path) {
        ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Rectangle");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String width = element.getElementsByTagName("Width").item(0).getTextContent();
                    String length = element.getElementsByTagName("Length").item(0).getTextContent();
                    rectangleArrayList.add(new Rectangle(Double.parseDouble(width), Double.parseDouble(length)));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println(rectangleArrayList);
    }
    // create
    public static void triangleXML(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String[]> edges = reader.lines().map(String::trim).filter(str -> str.matches("[.0-9]+-[.0-9]+-[.0-9]+")).map(str -> str.split("-")).collect(Collectors.toList());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Figure");
            doc.appendChild(rootElement);
            for (int i = 0; i < edges.size(); i++) {
                Element tri = doc.createElement("Triangle");
                Attr tri_id = doc.createAttribute("id");
                tri_id.setValue(String.valueOf(i));
                tri.setAttributeNode(tri_id);
                rootElement.appendChild(tri);
                Element a = doc.createElement("A");
                Element b = doc.createElement("B");
                Element c = doc.createElement("C");
                a.appendChild(doc.createTextNode(edges.get(i)[0]));
                b.appendChild(doc.createTextNode(edges.get(i)[1]));
                c.appendChild(doc.createTextNode(edges.get(i)[2]));
                tri.appendChild(a);
                tri.appendChild(b);
                tri.appendChild(c);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("triangle.xml"));
            transformer.transform(source, result);
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
    public static void rectangleXML(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String[]> edges = reader.lines().map(String::trim).filter(str -> str.matches("[.0-9]+-[.0-9]+")).map(str -> str.split("-")).collect(Collectors.toList());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Figure");
            doc.appendChild(rootElement);
            for (int i = 0; i < edges.size(); i++) {
                Element rec = doc.createElement("Rectangle");
                Attr rec_id = doc.createAttribute("id");
                rec_id.setValue(String.valueOf(i));
                rec.setAttributeNode(rec_id);
                rootElement.appendChild(rec);
                Element length = doc.createElement("Length");
                length.appendChild(doc.createTextNode(edges.get(i)[0]));
                rec.appendChild(length);
                Element width = doc.createElement("Width");
                width.appendChild(doc.createTextNode(edges.get(i)[1]));
                rec.appendChild(width);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("rectangle.xml"));
            transformer.transform(source, result);
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
    public static void circleXML(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> radiuses = reader.lines().filter(x -> x.matches("[.0-9]+")).collect(Collectors.toList());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Figure");
            doc.appendChild(rootElement);
            for (int i = 0; i < radiuses.size(); i++) {
                Element circle = doc.createElement("Circle");
                Attr circle_id = doc.createAttribute("id");
                circle_id.setValue(String.valueOf(i));
                circle.setAttributeNode(circle_id);
                rootElement.appendChild(circle);

                Element radius = doc.createElement("radius");
                radius.appendChild(doc.createTextNode(radiuses.get(i)));
                circle.appendChild(radius);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("circle.xml"));
            transformer.transform(source, result);
        } catch (IOException | TransformerException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void modifyRectangleXML(String path)
    {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.normalize();


            Node root = doc.getFirstChild();
            NodeList nodeList = root.getChildNodes();
            int rectangleCounter = 0;
            int updateRecCounter = 1;
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("Rectangle"))
                    rectangleCounter++;
            }

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");

                    if(id.equals("0") || id.equals(String.valueOf(rectangleCounter-1))) {
                        root.removeChild(element);
                        updateRecCounter--;
                    }

                }
            }
            rectangleCounter += updateRecCounter;

            Element addNewElem = doc.createElement("Rectangle");
            Attr attr = doc.createAttribute("id");
            attr.setValue(String.valueOf(rectangleCounter));
            rectangleCounter++;
            addNewElem.setAttributeNode(attr);
            Element width = doc.createElement("Width");
            Element length = doc.createElement("Length");
            width.setTextContent(String.valueOf(Math.random() * 100 + 1));
            length.setTextContent(String.valueOf(Math.random() * 100 + 1));
            addNewElem.appendChild(length);
            addNewElem.appendChild(width);
            root.appendChild(addNewElem);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }
}
