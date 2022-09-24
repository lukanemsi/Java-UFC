package ge.ufc.webapps;
import jakarta.xml.bind.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "Web", urlPatterns = "/web")
public class Web extends HttpServlet {
    private static final Logger lgg = LogManager.getLogger(Web.class);
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Configuration.getConfiguration();
        } catch (IOException e) {
            lgg.error("IO error");
            throw e;
        }
        Configuration.User user = Configuration.getUser();

        String p = request.getParameter("p");
        if (p == null) {
            response.sendError(401, "P required");
            lgg.error("request denied");
            return;
        }
        double doubleP;
        try {
            if ((doubleP = Double.parseDouble(p)) <= 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            response.sendError(400, "S must be positive");
            lgg.error("request denied");
            return;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Triangles.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Triangles triangles = (Triangles) unmarshaller.unmarshal(new File(user.getPath()));
            List<Triangle> triangleList = triangles.getTriangles().stream().filter(x -> x.perimeter() == doubleP).collect(Collectors.toList());
            if (triangleList.isEmpty()) {
                lgg.error("No triangle found");
                response.sendError(404, "No triangle found");
                return;
            }
            Triangles saveInFile = new Triangles();
            saveInFile.setTriangles(triangleList);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(saveInFile, response.getOutputStream());
        } catch (JAXBException e) {
            lgg.error("request denied");
            throw new RuntimeException(e);
        }

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            Thread.sleep(60_000);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}