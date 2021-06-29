<%-- 
    Document   : brojac
    Created on : 21. maj 2021., 20:15:54
    Author     : Grupa1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    int counter = 0;
%>
<%!
    synchronized int incrementCounter() {
        counter++;
        return counter;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ukupan broj posjeta je : <%=incrementCounter()%></h1>
        <%
            if (session.getAttribute("individual") == null) {
                session.setAttribute("individual", 1);
            } else {
                Integer brojac = (Integer) session.getAttribute("individual");
                brojac++;
                session.setAttribute("individual", brojac);
            }
        %>
        <h5>Pojedinačni broj posjeta je: <%=session.getAttribute("individual")%> </h5>
    </body>
</html>
