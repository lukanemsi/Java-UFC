package ge.ufc.webapps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(name = "TomcatPost", urlPatterns = "/testPost")
public class TomcatPost extends HttpServlet
{
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            FileConf.getFileConf();
        } catch (IOException e) {
            logger.error("error");

        }

        Gson gson = new GsonBuilder().create();
        Triangle triangle = null;
        try {
            triangle = gson.fromJson(request.getReader(), Triangle.class);
        } catch (IOException e) {
            logger.error("IO error");
        }
        if(!(triangle.getA() + triangle.getB() > triangle.getC() || triangle.getB() + triangle.getC() > triangle.getA() || triangle.getA() + triangle.getC() > triangle.getB())) {
            try {
                response.sendError(404,"incorrect triangle");

            } catch (IOException e) {
                logger.error("error");
            }
            return;
        }

        Triangles triangles = null;
        try {
            triangles = gson.fromJson(new FileReader(FileConf.getDatabase()), Triangles.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        triangles.getTriangles().add(triangle);
        String jsonString = gson.toJson(triangles);

        try {
            FileWriter writer = new FileWriter(FileConf.getDatabase());
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            logger.error("error");
        }

    }
}
