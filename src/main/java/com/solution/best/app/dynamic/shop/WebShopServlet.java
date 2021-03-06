package com.solution.best.app.dynamic.shop;

import com.solution.best.app.dynamic.shop.model.Product;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * <li> 1. HttpServlet
 * <li>2. HttpServletRequest request
 * <li>3. HttpServletResponse response
 * <li>4. dogGet doPost -> na svaki F5 ili refresh u browser
 * <li>5. init destroy -> 1 objekat koji živi u ServletContainer
 * <li>6. ServletContext application = getServletContext();
 *
 * @author Grupa1
 */
@WebServlet(name = "WebShopServlet", urlPatterns = {"/shop"})
public class WebShopServlet extends HttpServlet {

    static final String PRODUCTS_KEY = "proizvodi";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext application = getServletContext();
        String path = application.getRealPath("products.txt");
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            List<Product> products = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                Product product = new Product(Integer.parseInt(tokenizer.nextToken()),
                        tokenizer.nextToken(), new BigDecimal(tokenizer.nextToken()));
                products.add(product);
            }
            application.setAttribute(PRODUCTS_KEY, products);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Raspoloživi proizvod</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Raspoloživi proizvodi</h1>");
            ServletContext application = getServletContext();
            List<Product> products = (List<Product>) application.getAttribute(PRODUCTS_KEY);
            if (products != null && !products.isEmpty()) {
                out.println("<table border='1' cellspacing='0' cellpadding=\"3\">");
                out.println("<tr style='background-color:tomato; color:white;'><th>Ime proizvoda</th><th>Cijena</th><th>Dodaj u korpu</th></tr>");
                for (Product product : products) {
                    out.println("<tr>");
                    out.println("<td>" + product.getName() + "</td>");
                    out.println("<td>" + product.getUnitPrice().toPlainString() + "</td>");
                    out.println("<td>");
                    out.println("<form action='cart' method='GET'>");
                    out.println("<input type='number' size='3' name='quantity'/>");
                    out.println("<input type='hidden' value='" + product.getId() + "' name='productId'/>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<input type='submit' value='Dodaj'/>");
                out.println("</form>");
            } else {
                out.println("<h5> Trenutno nemamo proizvoda na stanju. Posjetite nas drugi put</h5>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
