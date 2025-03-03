<%-- 
    Document   : example04
    Created on : Feb 10, 2025, 1:36:24 PM
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
        <!-- In ra bảng cửu chương -->
        <%
            for (int i = 1; i <= 9; i++) {
        %>

        <h3>Bảng cửu chương <%=i%></h3>
        <%
            for (int j = 2; j <= 9; j++) {
        %>        <%=i%> x <%=j%> = <%= i * j%> <br/>
        <%

            }
        %><hr/><%
            }
        %>
    </body>
</html>
