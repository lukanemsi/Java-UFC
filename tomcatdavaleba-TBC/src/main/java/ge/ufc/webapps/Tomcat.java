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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Tomcat", urlPatterns = "/test")
public class Tomcat extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final long serialVersionUID = 1L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            FileConf.getFileConf();
        } catch (IOException e) {
            logger.error("error");
        }
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        if(username == null || password == null || !(username.equals(FileConf.getUsername()) || password.equals(FileConf.getPassword()))) {
            try{
                response.sendError(401,"problem in logging in");
            } catch (IOException e) {
                logger.error("error");
            }
            return;
        }
        String p = request.getHeader("p");
        if(p == null){
            try{
                response.sendError(400,"P is empty");
            } catch (IOException e) {
                logger.error("error");
            }
            return;
        }
        try{
            Double.parseDouble(p);
        }catch (NumberFormatException e) {
            try {
                response.sendError(400,"P is incorrect");
            } catch (IOException ex) {
                logger.error("error");
            }
            return;
        }
        double doubleP = Double.parseDouble(p);
        if(doubleP <= 0) {
            try {
                response.sendError(400,"P is incorrect");
            } catch (IOException ex) {
                logger.error("error");
            }
            return;
        }
        Gson gson = new GsonBuilder().create();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(FileConf.getDatabase());
        } catch (FileNotFoundException e) {
            logger.error("error");
            return;
        }
        Triangles triangles = gson.fromJson(fileReader,Triangles.class);
        List<Triangle> triangleArrayList = triangles.getTriangles();
        boolean isSuchTriangle = false;

        for(Triangle triangle : triangleArrayList) {
            if(doubleP == triangle.perimeter()) {
                isSuchTriangle = true;
            }
        }
        if(!isSuchTriangle) {
            try {
                response.sendError(404,"No such triangle found");
            } catch (IOException e) {
                logger.error("response error");
            }
        }
    }
}
