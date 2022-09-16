package ge.ufc.webapps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;


@WebServlet(name = "Web", urlPatterns = "/web")
public class Web extends HttpServlet
{
    private static final Logger lgg = LogManager.getLogger(Web.class);
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try {
            Configuration.getConfiguration();
        } catch (IOException e) {
            lgg.error("IO error");
            throw e;
        }
        Configuration.User user = Configuration.getUser();
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        if(username == null || password == null)
        {
            try {
                response.sendError(401,"username or password was not passed");
            } catch (IOException e) {
                lgg.error("response error");
            }
            return;
        }
        else if(!user.validate(username,password))
        {
            try {
                response.sendError(403,"incorrect username or password");
            } catch (IOException e) {
                lgg.error("response error");
            }
            return;
        }
        String perimeter = request.getParameter("p");
        if(perimeter == null)
        {
            response.sendError(400,"No P value");
            return;
        }
        double p = 0;
        try
        {
            if((p = Double.parseDouble(perimeter)) <= 0)
                throw new NumberFormatException();
        }
        catch (NumberFormatException e)
        {
            try {
                response.sendError(400,"invalid P");
            } catch (IOException ex) {
                lgg.error("response error");
                throw e;
            }
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();

        ArrayList trianglesJson = null;
        try {
            trianglesJson = gson.fromJson(new FileReader(user.getPath()), ArrayList.class);
        } catch (FileNotFoundException e) {
            lgg.error("File not found");
            throw e;
        }
        ArrayList<Triangle> triangleArrayList = new ArrayList<>();
        trianglesJson.forEach(tr -> triangleArrayList.add(gson.fromJson(tr.toString(),Triangle.class)));

        double finalP = p;
        if(triangleArrayList.stream().noneMatch(tr -> tr.perimeter() == finalP))
        {
            try {
                response.sendError(404,"No such triangle found");
            } catch (IOException e) {
                lgg.error("response error");
                throw e;
            }
            return;
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }
        writer.write("<h2> doGet Successfully completed! <h2>");
        writer.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Configuration.getConfiguration();
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }
        Configuration.User user = Configuration.getUser();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        Triangle triangle = null;
        try {
            triangle = gson.fromJson(request.getReader(), Triangle.class);
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }

        if(!triangle.validate())
        {
            try {
                response.sendError(400,"invalid triangle");
            } catch (IOException e) {
                lgg.error("response error");
                throw e;
            }
            return;
        }

        ArrayList trianglesJson = null;
        try {
            trianglesJson = gson.fromJson(new FileReader(user.getPath()), ArrayList.class);
        } catch (FileNotFoundException e) {
            lgg.error("IO exception");
            throw e;
        }
        ArrayList<Triangle> triangleArrayList = new ArrayList<>();
        trianglesJson.forEach(tr -> triangleArrayList.add(gson.fromJson(tr.toString(),Triangle.class)));
        triangleArrayList.add(triangle);

        try(FileWriter writer = new FileWriter(user.getPath()))
        {
            writer.write(gson.toJson(triangleArrayList));
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }

        try(PrintWriter writer = response.getWriter())
        {
            writer.write("<h2> doPost Successfully completed! <h2>");
        } catch (IOException e) {
            lgg.error("IO exception");
            throw e;
        }
    }
}
