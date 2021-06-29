package com.solution.best.app.dynamic;

import com.solution.best.app.dynamic.model.Counter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "VisitCounterServlet", urlPatterns = {"/countVisit"})
public class VisitCounterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VisitCounterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Posjete</h1>");

            //UKUPAN BROJ POSJETA 
            ServletContext servletContext = getServletContext();
            Counter counter = (Counter) servletContext.getAttribute("UKUPNO");
            if(counter == null){
                counter = new Counter();
                servletContext.setAttribute("UKUPNO", counter);
            }
            counter.increment();
            out.println("<h5>Ukupan broj posjeta: " + counter.getCounter() + "</h5>");
            //INDIVIDUALNI BROJ POSJETA
            HttpSession session = request.getSession();
            Counter sessionCounter = (Counter) session.getAttribute("INDIVIDUALNI");
            if(sessionCounter == null){
                sessionCounter = new Counter();
                session.setAttribute("INDIVIDUALNI", sessionCounter);
            }
            sessionCounter.increment();
            out.println("<h6>Moj udio u posjetama: " + sessionCounter.getCounter()+"</h6>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
