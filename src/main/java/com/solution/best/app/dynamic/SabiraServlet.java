package com.solution.best.app.dynamic;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SabiraServlet extends HttpServlet {

 
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Bosanka u računovodstvu</title>");            
            out.println("</head>");
            out.println("<body>");
            String sabirak1Text = request.getParameter("sabirak1");
            double sabirak1  = Double.parseDouble(sabirak1Text);
            String sabirak2Text = request.getParameter("sabirak2");
            double sabirak2  = Double.parseDouble(sabirak2Text);
            double suma = sabirak1+sabirak2;
            out.println("<h1>Sabira kaže da je suma = " + suma + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
