<%-- 
    Document   : projectForm
    Created on : Mar 5, 2025, 11:47:29 AM
    Author     : hanhhee
--%>

<%@page import="dto.ProjectDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                if (user.getRole().equals("Founder")) {
        %>

        <%
            ProjectDTO project = new ProjectDTO();
            try {
                project = (ProjectDTO) request.getAttribute("project");
            } catch (Exception e) {
                project = new ProjectDTO();
            }
            if (project == null) {
                project = new ProjectDTO();
            }
            //get error information
            String txtProjectID_error = request.getAttribute("projectID_error") + "";
            txtProjectID_error = txtProjectID_error.equals("null") ? "" : txtProjectID_error;

            String txtProjectName_error = request.getAttribute("projectName_error") + "";
            txtProjectName_error = txtProjectName_error.equals("null") ? "" : txtProjectName_error;

            String txtDescription = request.getAttribute("description_error") + "";
            txtDescription = txtDescription.equals("null") ? "" : txtDescription;

            String txtStatus = request.getAttribute("status_error") + "";
            txtStatus = txtStatus.equals("null") ? "" : txtStatus;

            String txtEstimatedLaunch = request.getAttribute("estimatedLaunch_error") + "";
            txtEstimatedLaunch = txtEstimatedLaunch.equals("null") ? "" : txtEstimatedLaunch;

            // Lấy message thông báo từ controller
            String message = request.getAttribute("message") + "";
            message = (message == null || message.equals("null")) ? "" : message;
        %>

        <%-- Hiển thị message success or fail --%>
        <% if (message != null && !message.isEmpty()) {%>
        <%= message%>
        <% }%>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>
            Project ID: <input type="text" name="project_id" value="<%=project.getProject_id()%>" required/> <br/>
            <% if (!txtProjectID_error.isEmpty()) {%>
            <p><%=txtProjectID_error%></p>
            <% }%>

            Project Name: <input type="text" name="projectName" value="<%=project.getProject_name()%>"/> <br/>
            <% if (!txtProjectName_error.isEmpty()) {%>
            <p><%=txtProjectName_error%></p>
            <% }%>

            Description: <textarea name="description"><%=project.getDescription()%></textarea> <br/>
            <% if (!txtDescription.isEmpty()) {%>
            <p><%=txtDescription%></p>
            <% }%>

            Status: <input type="text" name="status" value="<%=project.getStatus()%>"/> <br/>
            <% if (!txtStatus.isEmpty()) {%>
            <p><%=txtStatus%></p>
            <% }%>

            Estimated Launch: <input type="date" name="estimated_launch" value="<%= project.getEstimated_launch()%>"/> <br/>
            <% if (!txtEstimatedLaunch.isEmpty()) {%>
            <p><%=txtEstimatedLaunch%></p>
            <% }%>

            <input type="submit" value="Save"/>
            <input type="reset" value="Reset"/>
            <a href="MainController?action=search" class="back-link">Back to Book List</a>
        </form>

        <% } else { %>
        <p>You do not have permission to access this content!</p>
        <a href="MainController?action=search">Back to Projects List</a>
        <% } %>

        <% } else { %>
        <p>Please log in to access this page.</p>
        <a href="login.jsp" class="back-link">Go to Login</a>
        <% }%>
    </body>
</html>
