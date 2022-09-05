import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
import java.util.UUID;

public class Main
{
    static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args)
    {
        ThreadContext.put("uniqID", String.valueOf(UUID.randomUUID()));
        generateCircleXML();
        generateRectangleXML();
        generateTriangleXML();
        generateTriangleSchema();
    }
    public static void generateCircleXML()
    {
        File file = new File("circles_output.xml");
        CircleList circleList = new CircleList();
        List<CircleList.Circle> list = circleList.getCircle();
        for (int i = 0; i < 1000; i++) {
            CircleList.Circle circle = new CircleList.Circle();
            circle.setRadius((int) (Math.random() * 100) + 1);
            list.add(circle);
        }
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(CircleList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(circleList,file);
        } catch (JAXBException e)
        {
            logger.error("Error Jaxb");
        }
    }
    public static void generateRectangleXML()
    {
        File file = new File("rectangles_output.xml");
        RectangleList rectangleList = new RectangleList();
        List<RectangleList.Rectangle> list = rectangleList.getRectangle();
        for (int i = 0; i < 1000; i++) {
            RectangleList.Rectangle rectangle = new RectangleList.Rectangle();
            rectangle.setLength((int)((Math.random() * 100) + 1));
            rectangle.setWidth((int)((Math.random() * 100) + 1));
            list.add(rectangle);
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RectangleList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(rectangleList,file);
            throw new JAXBException("");
        } catch (JAXBException e) {
            logger.error("Jaxb Error");
        }
    }
    public static void generateTriangleXML()
    {
        File file = new File("triangles_input.xml");
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(TriangleList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            TriangleList triangleList = (TriangleList) unmarshaller.unmarshal(file);
            triangleList.getTriangle().remove(0);
            TriangleList.Triangle modifyTriangle = triangleList.getTriangle().get(1);
            modifyTriangle.setA(10);
            modifyTriangle.setB(20);
            modifyTriangle.setC(29);
            TriangleList.Triangle addTriangle = new TriangleList.Triangle();
            addTriangle.setA(100);
            addTriangle.setB(200);
            addTriangle.setC(299);
            triangleList.getTriangle().add(addTriangle);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(triangleList,file);
        } catch (JAXBException e) {
            logger.error("Jaxb Error");
        }
    }
    public static void generateTriangleSchema()
    {
        try{
            JAXBContext context = JAXBContext.newInstance(TriangleList.class);
            context.generateSchema(new SchemaOutputResolver() {
                @Override
                public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {

                    StreamResult result = new StreamResult(new File(suggestedFileName));
                    result.setSystemId(suggestedFileName);
                    return result;
                }

            });
        } catch (JAXBException e )
        {
            logger.error("Jaxb Error");
        } catch (IOException e) {
            logger.error("IO Error");
        }

    }
}
