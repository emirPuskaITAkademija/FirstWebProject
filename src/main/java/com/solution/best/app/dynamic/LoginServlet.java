package com.solution.best.app.dynamic;

import com.solution.best.app.dynamic.ejb.LoginSessionBeanLocal;
import com.solution.best.app.dynamic.model.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    final static String KEY_FOR_USERS = "KORISNICI";
    
    @Inject
    private LoginSessionBeanLocal loginSessionBeanLocal;
    
    

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        String path = servletContext.getRealPath("korisnici.txt");
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            List<User> users = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                User user = new User(username, password);
                users.add(user);
            }
            servletContext.setAttribute(KEY_FOR_USERS, users);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isLogin = loginSessionBeanLocal.login(username, password);
            
            User userFromRequest = new User(username, password);
            ServletContext servletContext = getServletContext();
            List<User> appUsers = (List<User>) servletContext.getAttribute(KEY_FOR_USERS);
            boolean logovan = appUsers.stream().anyMatch(userFromRequest::equals);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Login page</h1>");
            if (logovan) {
                out.println("<p>Uspješno unesena lozinka i logovani ste na sistem</p>");
            } else {
                out.println("<p>Neuspješno unesena lozinka i niste logovani na sistem</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ja sam POST metoda i zovem se doPost i ne otkrivam password </h1>");
            out.println("<p>Username: " + username + "</p>");
            out.println("<br><p>Password SKRIVEN</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
