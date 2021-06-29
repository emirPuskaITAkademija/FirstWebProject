package com.solution.best.app.dynamic.shop;

import com.solution.best.app.dynamic.shop.model.Product;
import com.solution.best.app.dynamic.shop.model.ShoppingCart;
import com.solution.best.app.dynamic.shop.model.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = {"/cart"})
public class ShoppingCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Artikli u korpi</title>");
            out.println("</head>");
            out.println("<body>");
            ServletContext application = getServletContext();
            HttpSession session = request.getSession();
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("korpa");
            if (shoppingCart == null) {
                shoppingCart = new ShoppingCart();
                session.setAttribute("korpa", shoppingCart);
            }
            /**
             * Dodavanje artikla u korpu
             */
            String[] quantities = request.getParameterValues("quantity");
            String[] productIds = request.getParameterValues("productId");
            if (quantities != null && quantities.length > 0) {
                for (int i = 0; i < quantities.length; i++) {
                    if (quantities[i].isEmpty()) {
                        continue;
                    }
                    int quantity = Integer.parseInt(quantities[i]);
                    int productId = Integer.parseInt(productIds[i]);
                    Product product = findProductById(productId);
                    shoppingCart.addItem(product, quantity);
                }
            }

            /**
             * Brisanje artikala iz korpe
             */
            String[] deletedQuantities = request.getParameterValues("deletedQuantity");
            String[] deletedProductIds = request.getParameterValues("deletedProductId");
            if (deletedQuantities != null && deletedQuantities.length > 0) {
                 for (int i = 0; i < deletedQuantities.length; i++) {
                    if (deletedQuantities[i].isEmpty()) {
                        continue;
                    }
                    int quantity = Integer.parseInt(deletedQuantities[i]);
                    int productId = Integer.parseInt(deletedProductIds[i]);
                    Product product = findProductById(productId);
                    shoppingCart.removeItem(product, quantity);
                }
            }
            /**
             * Iscrtavanje artikala u korpi
             */
            if (shoppingCart.containsShoppingCartItems()) {
                out.println("<h1>Artikli u korpi</h1>");
                out.println("<table border='1' cellspacing='0' cellpadding=\"3\">");
                out.println("<tr style='background-color:tomato;color:white;'>"
                        + "<th>Naziv</th>"
                        + "<th>Jedinična cijena</th>"
                        + "<th>Quantity</th>"
                        + "<th>Ukupna cijena</th>"
                        + "<th>Ukloni iz korpe</th>"
                        + "</tr>");
                for (ShoppingCartItem item : shoppingCart.getShoppingCartItems()) {
                    out.println("<tr>");
                    out.println("<td>" + item.getProduct().getName() + "</td>");
                    out.println("<td>" + item.getProduct().getUnitPrice().toPlainString() + "</td>");
                    out.println("<td>" + item.getQuantiy() + "</td>");
                    out.println("<td>" + item.getTotalPrice().toPlainString() + "</td>");
                    out.println("<td>");
                    out.println("<form action='' method='GET'>");
                    out.println("<input type='number' size='3' name='deletedQuantity'/>");
                    out.println("<input type='hidden' value='" + item.getProduct().getId() + "' name='deletedProductId'/>");
                    out.println("<input type='submit' value='Ukloni' style='background-color:tomato; color:white'/>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<h1>Niste dodali ni jedan artikl u korpu</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }

    }

    public Product findProductById(int productId) {
        ServletContext application = getServletContext();
        List<Product> products = (List<Product>) application.getAttribute(WebShopServlet.PRODUCTS_KEY);
        return products.stream()
                .filter(it -> it.getId() == productId)
                .findFirst()
                .orElse(null);
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
