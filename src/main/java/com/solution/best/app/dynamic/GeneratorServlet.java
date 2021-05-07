package com.solution.best.app.dynamic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GeneratorServlet", urlPatterns = {"/generatePassword"})
public class GeneratorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Generator lozinke</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Generator lozinke</h1>");
            out.println("<h5>Lozinka: " + generatePassword() + "</h5>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String generatePassword() {
        String moguciKarakteri = "abcdefghijklmnoprqrstuvzy123456!?";//23 0-22
        char[] moguciKarakterNiz = moguciKarakteri.toCharArray();
        //minimalna dužina lozinke 5 - max 15
        Random random = new Random();
        int duzinaLozinke = 5 + random.nextInt(11);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<duzinaLozinke; i++){
            int slucajnaPozicija = random.nextInt(moguciKarakterNiz.length);
            char karakterSaSlucajnePozicije = moguciKarakterNiz[slucajnaPozicija];
            sb.append(karakterSaSlucajnePozicije);
        }
        return sb.toString();
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
