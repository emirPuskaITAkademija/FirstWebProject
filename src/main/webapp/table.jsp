<%-- 
    Document   : table
    Created on : 21. maj 2021., 19:56:03
    Author     : Grupa1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table overview</title>
    </head>
    <body>
        <h1>Tabelarni prikaz</h1>
        <table border="1">
            <tr style="background-color: tomato; color: white;">
                <th>NIN</th>
                <th>Ime</th>
            </tr> 
            <%
                String[] names = {"Aida", "Amila", "Benjamin", "Amer"};
                for (int i = 0; i < names.length; i++) {
            %>
            <tr>
                <td><%=i%></td>
                <td><%=names[i]%></td>
            </tr>
            <%}%>

        </table>
    </body>
</html>
