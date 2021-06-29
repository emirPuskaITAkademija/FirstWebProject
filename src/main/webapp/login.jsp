<%-- 
    Document   : login
    Created on : 21. maj 2021., 20:37:31
    Author     : Grupa1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="user" class="com.solution.best.app.dynamic.model.User" scope="session"/>
<jsp:setProperty name="user" property="username" param="korime"/>
<jsp:setProperty name="user" property="password" param="lozinka"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello "<%=user.getUsername()%>" </h1>
        <h3>Hello  "<%=user.message()%>"</h3>
    </body>
</html>
