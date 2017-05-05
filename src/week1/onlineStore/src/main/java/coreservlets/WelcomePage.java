package coreservlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by martijn.slot on 04/05/2017.
 */
public class WelcomePage extends HttpServlet {

    private String title;
    private String header;
    private String body;
    private static final long serialVersionUID = 1L;

    public void init() {
        header = "Catalogus Pagina";
        title = "Catalogus opties: ";
        body = "Geneuzel in de ruimte .......";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        response.setContentType("text/html");


        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("<html>");
        out.println("<h1>" + title + "</h1>");
        out.println("<body bgcolor=\"red\">");
        out.println("<head>");
        out.println("<title> " + header + " </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + body + "</h2>");

        out.println("<a href=\"/kidsBooksPage\" accesskey=\"1\" title=\"\">Kid Book Page</a><br />");
        out.println("<a href=\"/techBooksPage\" accesskey=\"1\" title=\"\">Tech Book Page</a><br />");
        out.println("<a href=\"/orderPage\" accesskey=\"1\" title=\"\">Order Page</a><br />");

        out.print("</body>");
        out.print("</html>");
    }

    public void destroy() {

    }
}
