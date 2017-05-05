package coreservlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by martijn.slot on 04/05/2017.
 */

public class CheckoutPage extends HttpServlet {
    private String title;
    private String header;
    private String body;
    private static final long serialVersionUID = 1L;

    public void init() {
        header = "Checkout";
        title = "Checkout";
        body = null;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
        String totalCost = getTotalPayment(cart);
        body = "Bedankt for die blumen. <br /> The damage is: " + totalCost;
        emptyCart(cart);

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("<html>");
        out.println("<h1>" + title + "</h1>");
        out.println("<body bgcolor=\"pink\">");
        out.println("<head>");
        out.println("<title> " + header + " </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + body + "</h2>");

        String formURL = "/orderPage";
        // Pass URLs that reference own site through encodeURL.
        formURL = response.encodeURL(formURL);
        out.println("<FORM ACTION=\" " + formURL + " \">\n "
                + "<INPUT TYPE=\"SUBMIT\" "
                + "VALUE=\"To stopping cart\">\n"
                + "</CENTER>\n<P>\n</FORM>");
        String homeURL = "";
        // Pass URLs that reference own site through encodeURL.
        homeURL = response.encodeURL(homeURL);
        out.println("<FORM ACTION=\" " + homeURL + " \">\n "
                + "<INPUT TYPE=\"SUBMIT\" "
                + "VALUE=\"To home\">\n"
                + "</CENTER>\n<P>\n</FORM>");

        out.print("</body>");
        out.print("</html>");
    }

    private String getTotalPayment(ShoppingCart cart) {
        List<ItemOrder> itemsOrdered = cart.getItemsOrdered();
        double cost = 0.0;
        for (ItemOrder itemOrder : itemsOrdered) {
            cost += itemOrder.getTotalCost();
        }
        BigDecimal b = new BigDecimal(cost);
        return NumberFormat.getCurrencyInstance().format(b);
    }

    private void emptyCart(ShoppingCart cart) {
        cart.clearCart();
    }

    public void destroy() {

    }
}
