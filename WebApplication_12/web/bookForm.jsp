<%-- 
    Document   : bookForm
    Created on : Feb 27, 2025, 1:49:49 PM
    Author     : hanhhee
--%>

<%@page import="dto.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            BookDTO book = new BookDTO();
            try {
                book = (BookDTO) request.getAttribute("book");
            } catch (Exception e) {
                book = new BookDTO();
            }
            if(book == null){
                book = new BookDTO();
            }
            
            String txtBookID_error = request.getAttribute("txtBookID_error") + "";
            txtBookID_error = txtBookID_error.equals("null")?"":txtBookID_error;
            String 
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>
            BookID <input type="text" name="txtBookID"/> <br/>
            Title <input type="text" name="txtTitle"/> <br/>
            Author <input type="text" name="txtAuthor"/> <br/>
            Publish Year <input type="number" name="txtPublishYear"/> <br/>
            Price <input type="number" name="txtPrice"/> <br/>
            Quantity <input type="number" name="txtQuantity"/> <br/>
            <input type="submit" value="Save"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
