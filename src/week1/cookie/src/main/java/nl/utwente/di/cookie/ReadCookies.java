package nl.utwente.di.cookie;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet(description = "ReadCookies Servlet", urlPatterns = { "/readCookies" })
public class ReadCookies extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
		Cookie[] cookies = null;
		// Get an array of Cookies associated with this domain
		cookies = request.getCookies();

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Reading Cookies Example";
		
		String docType = "<!DOCTYPE HTML>\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title>"
				+ "<link rel=\"stylesheet\" href=\"styles.css\">\n" + "<meta charset=\"UTF-8\">\n" + "</head>"
				+ "<h1>" + title + "</h1>");
		if (cookies != null) {
			out.println("<p> Found Cookies Name and Value</p>");
			out.println("<table border=\"1\"><tr><th>Name</th><th>Value</th></tr>");
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				out.print("<tr><td>" + cookie.getName() + "</td>");
				out.print("<td>" + cookie.getValue() + "</td></tr>");
			}
			out.println("</table>");
		} else {
			out.println("<h2>No cookies founds</h2>");
		}
		out.println("<p><a href=\"index.html\">Home</a><br /><a href=\"readCookies\">Read Cookies</a> <br /> <a href=\"deleteCookies\">Delete Cookies</a></p>");
		out.println("</body>");
		out.println("</html>");
	}
		
		
}