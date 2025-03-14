<%-- 
    Document   : search
    Created on : Mar 3, 2025, 12:31:46 PM
    Author     : hanhhee
--%>

<%@page import="java.util.List"%>
<%@page import="dto.ProjectDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Projects</title>
    </head>
    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
        %>
        Welcome <b><%= user.getName()%></b>
        <br/>
        <form action="MainController">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Logout"/>
        </form>
        <br/>
        <%
            String searchTerm = request.getAttribute("searchTerm") + "";
            searchTerm = searchTerm.equals("null") ? "" : searchTerm;
            String message = (String) request.getAttribute("message");
        %>
        
        <% if (message != null && !message.isEmpty()) { %>
            <p><%= message %></p>
        <% } %>
        
        <form action="MainController">
            <input type="hidden" name="action" value="search"/>
            Start up Projects <input type="text" name="searchTerm" value="<%=searchTerm%>"/>
            <input type="submit" value="Search"/>
        </form>
        <a href="projectForm.jsp">Add New Project</a> <br/>
        <a href="updateProject.jsp">Update Project</a>
        <br/>
        
        <%
            if (request.getAttribute("projects") != null) {
                List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("projects");
        %>
        
        <table border="1">
            <tr>
                <td>Project Name</td>
                <td>Description</td>
                <td>Status</td>
                <td>Estimated Launch</td>
                <td>Actions</td>
            </tr>
            <% for (ProjectDTO p : projects) { %>
            <tr>
                <td><%= p.getProject_name() %></td>
                <td><%= p.getDescription() %></td>
                <td><%= p.getStatus() %></td>
                <td><%= p.getEstimated_launch() %></td>
                <td>
                    <form action="MainController" method="post">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="project_id" value="<%= p.getProject_id() %>"/>
                        <button type="submit">
                            <img src="assets/img/x-circle.svg" alt="Delete" width="35" height="20"/>
                        </button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        
        <%
            }
        %>
        <% } else { %>
        You do not have permission to access this content.
        <% }%>
    </body>
</html>
