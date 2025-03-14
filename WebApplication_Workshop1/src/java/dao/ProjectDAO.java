/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author hanhhee
 */
public class ProjectDAO implements IDAO<ProjectDTO, Integer> {

    @Override
    public boolean create(ProjectDTO entity) {
        String sql = "INSERT INTO tblStartupProjects( project_id, project_name, description, status, estimated_launch)"
                  + "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entity.getProject_id());
            ps.setString(2, entity.getProject_name());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getStatus());
            ps.setDate(5, entity.getEstimated_launch());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ProjectDTO> readAll() {
        List<ProjectDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblStartupProjects";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProjectDTO project = new ProjectDTO(
                          rs.getInt("project_id"),
                          rs.getString("project_name"),
                          rs.getString("description"),
                          rs.getString("status"),
                          rs.getDate("estimated_launch"));
                list.add(project);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean update(ProjectDTO entity) {
        String sql = "UPDATE tblStartupProjects SET "
                  + "project_name = ?, "
                  + "description = ?,"
                  + "status= ?, "
                  + "estimated_launch= ? "
                  + "WHERE project_id = ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getProject_name());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getStatus());
            ps.setDate(4, entity.getEstimated_launch());
            ps.setInt(5, entity.getProject_id());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ProjectDTO> searchByName(String searchTerm) {
        List<ProjectDTO> list = new ArrayList<>();
        String sql = "SELECT project_id, project_name, description, status, estimated_launch "
                  + "FROM tblStartupProjects "
                  + "WHERE project_name LIKE ? ";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProjectDTO project = new ProjectDTO(
                          rs.getInt("project_id"),
                          rs.getString("project_name"),
                          rs.getString("description"),
                          rs.getString("status"),
                          rs.getDate("estimated_launch"));
                list.add(project);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean updateStatus(String projectID, String nStatus) {
        String sql = "UPDATE tblStartupProjects SET status = ? WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nStatus);
            ps.setString(2, projectID);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ProjectDTO readById(int id) {
        String sql = "SELECT * FROM tblStartupProjects WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProjectDTO project = new ProjectDTO(
                          rs.getInt("project_id"),
                          rs.getString("project_name"),
                          rs.getString("description"),
                          rs.getString("status"),
                          rs.getDate("estimated_launch"));
                return project;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean delete(int projectID) {
        String sql = "DELETE FROM tblStartupProjects WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, projectID);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
}
