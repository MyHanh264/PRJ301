<%@page import="dto.UserDTO"%>
<%@ page import="dto.ProjectDTO" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Project Status</title>
    </head>
    <body>
        <form action="MainController" method="get">
            <input type="hidden" name="action" value="searchById"/>
            <label for="id">Project ID:</label>
            <input type="text" name="id" id="id" required />
            <input type="submit" value="Search"/>
        </form>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                if (user.getRole().equals("Founder")) {
        %>

        <% //tt projects
            ProjectDTO project = (ProjectDTO) request.getAttribute("project");
            if (project == null) {
        %>
        
        <%
        } else {
        %>

        <h2>Update Project</h2>
        <p><b>Project ID:</b> <%= project.getProject_id()%></p>
        <p><b>Project Name:</b> <%= project.getProject_name()%></p>
        <p><b>Status:</b> <%= project.getStatus()%></p>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="<%= project.getProject_id()%>">

            <label for="status">Update Status:</label>
            <select name="status" id="status">
                <option value="Development" <%= project.getStatus().equals("Development") ? "selected" : ""%>>Development</option>
                <option value="Ideation" <%= project.getStatus().equals("Ideation") ? "selected" : ""%>>Ideation</option>
                <option value="Launch" <%= project.getStatus().equals("Launch") ? "selected" : ""%>>Launch</option>
                <option value="Scaling" <%= project.getStatus().equals("Scaling") ? "selected" : ""%>>Scaling</option>
            </select>

            <input type="submit" value="Update"/>
        </form>

        <%
            String message = request.getAttribute("message") + "";
            if (message != null && !message.isEmpty()) {
        %>
        <%= message.equals("null") ? "" : message%>
        <%
            }
        %>

        <br>
        <a href="search.jsp">Back to Project List</a>

        <%
            }
        %>

        <%
        } else {
        %>
        <p>You do not have permission to access.</p>
        <a href="MainController?action=search">Back to Projects List</a>
        <%
            }
        } else {
        %>
        <p>Please log in to access this page.</p>
        <a href="login.jsp" class="back-link">Go to Login</a>
        <%
            }
        %>
    </body>
</html>
