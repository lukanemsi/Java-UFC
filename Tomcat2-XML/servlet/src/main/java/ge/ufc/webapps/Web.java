package ge.ufc.webapps;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



@WebServlet(name = "Web", urlPatterns = "/web")
public class Web extends HttpServlet
{
    private static final Logger lgg = LogManager.getLogger(Web.class);
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");

        try {
            Configuration.getConfiguration();
        } catch (IOException e) {
            lgg.error("IO error");
            throw e;
        }
        Configuration.User user = Configuration.getUser();

        String s = request.getParameter("s");
        if(s == null)
        {
            response.sendError(401,"S required");
            lgg.error("request denied");
            return;
        }
        double doubleS = 0;
        try
        {
            if((doubleS = Double.parseDouble(s)) <= 0)
                throw new NumberFormatException();
        }catch (NumberFormatException e)
        {
            response.sendError(400,"S must be positive");
            lgg.error("request denied");
            return;
        }

        List<Rectangle> rectangleList = new ArrayList<>();
        try
        {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(user.getPath()));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("rectangle");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                NodeList nodeList1 = node.getChildNodes();
                double length = 0;
                double width = 0;
                for (int j = 0; j < nodeList1.getLength(); j++) {

                    if("length".equals(nodeList1.item(j).getNodeName()))
                    {
                        length = Double.parseDouble(nodeList1.item(j).getTextContent());
                    }
                    else if("width".equals(nodeList1.item(j).getNodeName()))
                    {
                        width = Double.parseDouble(nodeList1.item(j).getTextContent());
                    }
                    if(length != 0 && width != 0)
                    {
                        rectangleList.add(new Rectangle(width,length));
                        length = 0;
                        width = 0;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            lgg.error("Parser error");
            return;
        }catch (NumberFormatException e)
        {
            lgg.error("XML wrong Format");
            throw e;
        }
        double finalDoubleS = doubleS;
        if(rectangleList.stream().noneMatch(r -> r.area() == finalDoubleS))
        {
            response.sendError(404,"no such rectangle found");
            lgg.error("request denied");
            return;
        }
        PrintWriter writer = response.getWriter();
        writer.write("<h2> Successfully completed! <h2>");
        writer.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        Configuration.getConfiguration();
        Configuration.User user = Configuration.getUser();
        try {
            JAXBContext context = null;
            context = JAXBContext.newInstance(Rectangle.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Rectangle rectangle = (Rectangle) unmarshaller.unmarshal(request.getReader());

            if (rectangle.getWidth() <= 0 || rectangle.getLength() <= 0) {
                lgg.error("invalid rectangle");
                response.sendError(400, "Incorrect value of rectangle's height or width");
                return;
            }
            context = JAXBContext.newInstance(Rectangles.class);
            unmarshaller = context.createUnmarshaller();
            Rectangles rectangles = (Rectangles) unmarshaller.unmarshal(new File(user.getPath()));
            rectangles.getRectangles().add(rectangle);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(rectangles, new FileWriter(user.getPath()));

        } catch (JAXBException e) {
            lgg.error("Error!");
            return;
        }
        PrintWriter writer = response.getWriter();
        writer.write("<h2> Successfully completed! <h2>");
        writer.close();
    }
}