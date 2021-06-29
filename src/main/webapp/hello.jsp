<%-- 
    Document   : hello
    Created on : 21. maj 2021., 19:09:26
    Author     : Grupa1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime" %>
<%!
    int number = 0;

    public String randomMessage() {
        if (Math.random() < 0.5) {
            return "Dobro jutro dobri ljud...";
        } else {
            return "Kakvo crno jutro. Sunce je izašlo i zašlo već..";
        }
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table, th, td{
                border: 1 px solid green;
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <h1>Dobro veče, dobri ljudi...</h1>
        <h2>Danas je <%=LocalDateTime.now()%></h2>
        <h3><%=randomMessage()%></h3>
        <%@include file="table.jsp" %>
    </body>
</html>
