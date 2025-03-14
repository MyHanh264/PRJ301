/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    private ProjectDAO pdao = new ProjectDAO();

    public UserDTO getUser(String username) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByID(username);
        return user;
    }

    public boolean isValidLogin(String username, String password) {
        UserDTO user = getUser(username);
        System.out.println(user);
        return user != null && user.getPassword().equals(password);
    }

    //tìm project
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<ProjectDTO> projects = pdao.searchByName(searchTerm);
        request.setAttribute("projects", projects); //gửi kq sang jsp
        request.setAttribute("searchTerm", searchTerm);//giữ lại từ khóa đã tìm hiện lên ô
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //lấy username và password người dùng gửi lên
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    String username = request.getParameter("txtUsername");
                    String password = request.getParameter("txtPassword");
                    if (isValidLogin(username, password)) {
                        url = "search.jsp";
                        UserDTO user = getUser(username);//lấy user ra lưu vào session
                        request.getSession().setAttribute("user", user);
                        search(request, response);
                    } else {
                        request.setAttribute("message", "Incorrect Username or Password");
                        url = "login.jsp";
                    }
                } else if (action.equals("search")) {
                    url = "search.jsp";
                    HttpSession session = request.getSession();
                    UserDTO user = (UserDTO) session.getAttribute("user");//tt ng dùng khi login, lưu ở session
                    String searchTerm = request.getParameter("searchTerm");
                    if (user.getRole().equals("Founder")) {
                        //searchByName & gửi danh sách kq sang trang jsp
                        search(request, response);
                    } else {
                        request.setAttribute("message", "You do not have permission to search!");
                        List<ProjectDTO> projects = pdao.readAll();//cho member xem projects
                        //gửi danh sách pr sang trang jsp
                        request.setAttribute("projects", projects);
                    }
                } else if (action.equals("logout")) {
                    //hủy session trc tránh lưu thông tin ng dùng, đảm bảo ng dùng thực sự đăng xuất
                    request.getSession().invalidate();
                    url = "login.jsp";
                } else if (action.equals("searchById")) {
                    String projectId = request.getParameter("id");
                    try {
                        int id = Integer.parseInt(projectId);
                        ProjectDTO project = pdao.readById(id);
                        if (project != null) {
                            request.setAttribute("project", project);
                            url = "updateProject.jsp";
                        } else {
                            request.setAttribute("message", "Project not found!");
                            url = "search.jsp";
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("message", "Invalid project ID format!");
                        url = "search.jsp";
                    }
                } else if (action.equals("update")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            String projectId = request.getParameter("id");
                            String nStatus = request.getParameter("status");
                            if (projectId == null || projectId.trim().isEmpty()) {
                                request.setAttribute("message", "Project ID is required!");
                                return;
                            }
                            try {
                                int id = Integer.parseInt(projectId);
                                ProjectDTO project = pdao.readById(id);
                                if (project == null) {
                                    request.setAttribute("message", "Project not found");
                                    return;
                                } else {
                                    //tìm thấy project
                                    boolean updated = pdao.updateStatus(projectId, nStatus);
                                    if (updated) {
                                        project.setStatus(nStatus); // Cập nhật trạng thái mới
                                        request.setAttribute("project", project);
                                        request.setAttribute("message", "Update successfully!");
                                        search(request, response);
                                        url ="search.jsp";
                                    } else {
                                        request.setAttribute("message", "Update Failed!");
                                    }
                                    url = "search.jsp"; // Luôn quay lại trang update

                                }
                            } catch (Exception e) {
                                request.setAttribute("message", "Invalid project ID format!");
                            }
                        }
                    }

                } else if (action.equals("delete")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            try {
                                int projectID = Integer.parseInt(request.getParameter("project_id"));
                                boolean deleted = pdao.delete(projectID);
                                if (deleted) {
                                    request.setAttribute("message", "Project deleted successfully!");
                                } else {
                                    request.setAttribute("message", "Failed to delete!");
                                }
                                search(request, response); // Load lại porojects còn lại sau khi xóa, nếu ko sẽ k cập nhật ds mới
                                url = "search.jsp";
                            } catch (NumberFormatException e) {
                                request.setAttribute("message", "Invalid Project ID format!");
                                url = "search.jsp";
                            }
                        } else {
                            request.setAttribute("message", "You do not have permission to delete projects!");
                            url = "search.jsp";
                        }
                    }
                } else if (action.equals("add")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            try {
                                boolean checkError = false;

                                String projectIDStr = request.getParameter("project_id");
                                int projectID = Integer.parseInt(projectIDStr);
                                String projectName = request.getParameter("projectName");
                                String description = request.getParameter("description");
                                String status = request.getParameter("status");
                                String estimatedLaunchStr = request.getParameter("estimated_launch");
                                Date estimatedLaunch = Date.valueOf(estimatedLaunchStr);

                                if (projectIDStr == null || projectIDStr.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("projectID_error", "Project ID cannot be empty");
                                }
                                if (pdao.readById(projectID) != null) {
                                    checkError = true;
                                    request.setAttribute("projectID_error", "Project ID already exists!");
                                }

                                if (projectName == null || projectName.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("projectName_error", "Project Name cannot be empty");
                                }

                                if (description == null || description.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("description_error", "Description cannot be empty");
                                }

                                if (status == null || status.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("status_error", "Status cannot be empty");
                                }

                                if (estimatedLaunchStr == null || estimatedLaunchStr.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("estimatedLaunch_error", "Estimated Launch cannot be empty");
                                }
                                ProjectDTO project = new ProjectDTO(projectID, projectName, description, status, estimatedLaunch);

                                if (!checkError) {
                                    pdao.create(project);
                                    request.setAttribute("message", "Project added successfully!");
                                    search(request, response);
                                    url = "search.jsp";
                                } else {
                                    request.setAttribute("message", "Project added failly!");
                                    url = "projectForm.jsp";
                                    //giữ lại dữ liệu đã nhập
                                    request.setAttribute("project", project);
                                }
                            } catch (Exception e) {
                                request.setAttribute("message", "Invalid Project ID!");
                                url = "projectForm.jsp";
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);//chuyển trang theo url dc set
            rd.forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
