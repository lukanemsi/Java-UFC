package ge.ufc.webapps.servlets;

import ge.ufc.webapps.figures.Triangle;
import ge.ufc.webapps.figures.Triangles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

@WebServlet(name="GetMaxTriangle",urlPatterns = "/triangle")
public class GetMaxTriangle extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(GetMaxTriangle.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try {
            Configuration.getConfiguration();
            Configuration.User user = Configuration.getUser();
            JAXBContext context = JAXBContext.newInstance(Triangles.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Triangles triangles = (Triangles) unmarshaller.unmarshal(new File(user.getXmlPath()));
            Triangle maxTr = triangles.getTriangles().stream().max(Comparator.comparing(Triangle::perimeter)).orElse(new Triangle(0,0,0));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(maxTr, response.getWriter());
            logger.debug("xml element sent");
        } catch (JAXBException | IOException e) {
            logger.error("Error");
            throw new RuntimeException(e);
        }
    }

}
