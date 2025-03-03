<%-- 
    Document   : output
    Created on : Feb 10, 2025, 2:12:02 PM
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
        <%-- giúp lấy dc giá trị từ PrintServlet gửi sang --%>
        <%
        int n = (int)request.getAttribute("n"); 
        %>
        <h3> Cửu chương <%=n%> </h3>
        <%
            for(int j = 1; j <= 10; j++){
                %>
                <%=n%> x <%=j%> = <%= n * j%> <br/>
        <%
            }
        %>
    </body>
</html>
