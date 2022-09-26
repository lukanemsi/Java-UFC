package ge.ufc.webapps.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ge.ufc.webapps.figures.Rectangle;
import ge.ufc.webapps.figures.Rectangles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
@WebServlet(name="GetRectangles",urlPatterns = "/rectangles")
public class GetRectangles extends HttpServlet
{
    private static final Logger logger = LogManager.getLogger(GetRectangles.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Configuration.getConfiguration();
        Configuration.User user = Configuration.getUser();

        String p = request.getParameter("p");
        if(p == null)
        {
            response.sendError(401,"P required");
            logger.error("request denied");
            return;
        }
        double doubleP;
        try
        {
            if((doubleP = Double.parseDouble(p)) <= 0)
                throw new NumberFormatException();
        }catch (NumberFormatException e)
        {
            response.sendError(400,"P must be positive");
            logger.error("request denied");
            return;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Rectangles rectangles = gson.fromJson(new FileReader(user.getJsonPath()), Rectangles.class);
        List<Rectangle> rectangleList = rectangles.getRectangleList();
        List<Rectangle> filteredRecList = rectangleList.stream().filter(r -> r.perimeter() == doubleP).collect(Collectors.toList());
        if(filteredRecList.isEmpty())
        {
            response.sendError(400,"No such rectangle found");
            logger.error("request denied");
            return;
        }
        Rectangles filteredRectangles = new Rectangles();
        filteredRectangles.setRectangleList(filteredRecList);
        String result = gson.toJson(filteredRectangles);
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.close();
        logger.debug("json elements sent");
    }
}
