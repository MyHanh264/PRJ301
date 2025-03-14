<%-- 
    Document   : login
    Created on : Mar 2, 2025, 6:26:50 PM
    Author     : hanhhee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Đăng nhập</h1>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/>
            Username: <input type="textt" name="txtUsername"/> <br/>
            Password: <input type="password" name="txtPassword"/> <br/>
            <input type="submit" value="Login"/>
        </form>
        <%
            String message = request.getAttribute("message") + "";
        %>
        <%=message.equals("null") ? "" : message%>
    </body>
</html>
