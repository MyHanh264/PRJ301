<%-- 
    Document   : example03
    Created on : Feb 10, 2025, 1:26:04 PM
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
        <%! int a = 100;%>
        <% if (a % 2 == 0) {
        %>
        a = <%=a%> là số chẵn
        <%
        } else {
        %> 
        b là số lẻ
        <% }
        %>

    </body>
</html>
