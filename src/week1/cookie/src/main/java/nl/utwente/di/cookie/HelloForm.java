package nl.utwente.di.cookie;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Example of a Servlet that gets an ISBN number and returns the book price
 */
@WebServlet(description = "HelloForm Servlet", urlPatterns = { "/hello" })
public class HelloForm extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create cookies for first and last names.
		Cookie firstName = new Cookie("first_name", request.getParameter("first_name"));
		Cookie lastName = new Cookie("last_name", request.getParameter("last_name"));
		Cookie age = new Cookie("age", request.getParameter("age"));

		// Set expiry date after 24 Hrs for both the cookies.
		firstName.setMaxAge(60 * 60 * 24);
		lastName.setMaxAge(60 * 60 * 24);
		age.setMaxAge(60 * 60 * 24);

		// Add both the cookies in the response header.
		response.addCookie(firstName);
		response.addCookie(lastName);
		response.addCookie(age);

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Setting Cookies Example";
		String docType = "<!DOCTYPE HTML>\n";
		out.println(docType + "<html>\n" + "<head><title>" + title + "</title>"
				+ "<link rel=\"stylesheet\" href=\"styles.css\">\n" + "<meta charset=\"UTF-8\">\n" + "</head>"
				+ "<h1>" + title + "</h1>"
				+ "<table border=\"1\">"
				+ "<tr><th>Name</th><th>Value</th></tr>"
				+ "<tr><td>first_name</td><td>" + request.getParameter("first_name") + "</td></tr>\n"
				+ "<tr><td>last_name</td><td>" + request.getParameter("last_name") + "</td></tr>\n" 
				+ "<tr><td>age</td><td>" + request.getParameter("age") + "</td></tr>\n" + "</table>\n" 
				+ "<p><a href=\"index.html\">Home</a><br /><a href=\"readCookies\">Read Cookies</a> <br /> <a href=\"deleteCookies\">Delete Cookies</a></p>"
				+ "</body></html>");
	}
}