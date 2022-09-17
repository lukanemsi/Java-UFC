package ge.ufc.webapps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloWorld", urlPatterns = "/hello")
public class HelloWorld extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if(name == null) {
            out.println("<h2>Hello First Servlet!</h2>");
        }
        else {
            out.println("<h2>Hello " + name.trim() + "! It's a first servlet.</h2>");
        }

        out.close();
    }

}
