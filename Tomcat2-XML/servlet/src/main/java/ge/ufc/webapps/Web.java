package ge.ufc.webapps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        try {
            Configuration.getConfiguration();
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }
        Configuration.User user = Configuration.getUser();
        BufferedReader requestReader = null;
        try {
            requestReader = request.getReader();
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }
        double width = 0,length = 0;
        Matcher matcher;
        Pattern widthPattern = Pattern.compile("<width>\\s*(-?[.0-9]+)\\s*</width>");
        Pattern lengthPattern = Pattern.compile("<length>\\s*(-?[.0-9]+)\\s*</length>");

        String fileContent = requestReader.lines().reduce("",(a,b) -> a+b);
        matcher = widthPattern.matcher(fileContent);
        if(matcher.find())
        {
            width = Double.parseDouble(matcher.group(1));
        }
        matcher = lengthPattern.matcher(fileContent);
        if(matcher.find())
        {
            length = Double.parseDouble(matcher.group(1));
        }
        if(length <= 0 || width <= 0)
        {
            response.sendError(400,"width or length is not positive");
            return;
        }
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(user.getPath()));
            document.getDocumentElement().normalize();
            Node rootNode = document.getFirstChild();
            Node rec = document.createElement("rectangle");
            Node w = document.createElement("width");
            w.setTextContent(String.valueOf(width));
            Node l = document.createElement("length");
            l.setTextContent(String.valueOf(length));
            rec.appendChild(w);
            rec.appendChild(l);
            rootNode.appendChild(rec);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            System.out.println("-----------Modified File-----------");
            StreamResult originalFile = new StreamResult(new FileWriter(user.getPath()));
            transformer.transform(source, originalFile);
        } catch (SAXException | ParserConfigurationException | TransformerException e){
            lgg.error("xml Document error");
            return;
        }
        PrintWriter writer = response.getWriter();
        writer.write("<h2> Successfully completed! <h2>");
        writer.close();
    }
}
