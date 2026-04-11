/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.daoimpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferobjects.MaintenanceAlertDTO;
import transferobjects.MaintenanceTaskDTO;
import data.dao.MaintenanceDAO;

/**
 *
 * @author biyababu
 */
public class MySQLMaintenanceDAO implements MaintenanceDAO {
    // Database connection used by this DAO.
    private Connection conn;

    public MySQLMaintenanceDAO(Connection conn) {
        this.conn = conn;
    }

   /**
    * gets all maintenance alerts ordered by newest first.
    * 
    * @return  List of all maintenance alerts
    * @throws Exception if a database error occurs
    */
    @Override
    public List<MaintenanceAlertDTO> getAllAlerts() throws Exception {
        List<MaintenanceAlertDTO> alerts = new ArrayList<>();

        String sql = "SELECT * FROM maintenance_alerts ORDER BY created_at DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                alerts.add(new MaintenanceAlertDTO(
                        rs.getInt("alert_id"),
                        rs.getInt("scooter_id"),
                        rs.getString("alert_type"),
                        rs.getString("message"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                ));
            }
        }
        return alerts;
    }
     /**
      * gets one maintenance alert by its ID.
      * 
      * @param alertId Unique alert ID
      * @return  Matching MaintenanceAlertDTO, or null if not found
      * @throws Exception if a database error occurs
      */
    @Override
    public MaintenanceAlertDTO getAlertById(int alertId) throws Exception {
        String sql = "SELECT * FROM maintenance_alerts WHERE alert_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alertId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new MaintenanceAlertDTO(
                            rs.getInt("alert_id"),
                            rs.getInt("scooter_id"),
                            rs.getString("alert_type"),
                            rs.getString("message"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        }
        return null;
    }
    /**
     * Inserts a new maintenance alert into the database.
     * 
     * @param alert Alert information to insert
     * @throws Exception if a database error occurs
     */
    @Override
    public void addAlert(MaintenanceAlertDTO alert) throws Exception {
        String sql = "INSERT INTO maintenance_alerts (scooter_id, alert_type, message, status, created_at) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alert.getScooterId());
            ps.setString(2, alert.getAlertType());
            ps.setString(3, alert.getMessage());
            ps.setString(4, alert.getStatus());
            ps.setTimestamp(5, alert.getCreatedAt());

            ps.executeUpdate();
        }
    }
    /**
     * Updates the status of an existing maintenance alert
     * 
     * @param alertId Alert ID to update
     * @param status  New alert status
     * @throws Exception if a database error occurs 
     */
    @Override
    public void updateAlertStatus(int alertId, String status) throws Exception {
        String sql = "UPDATE maintenance_alerts SET status=? WHERE alert_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, alertId);
            ps.executeUpdate();
        }
    }

    /**
     * get all maintenance tasks
     * 
     * @return  List of all maintenance tasks
     * @throws Exception Exception if a database error occurs
     */
    @Override
    public List<MaintenanceTaskDTO> getAllTasks() throws Exception {
        List<MaintenanceTaskDTO> tasks = new ArrayList<>();

        String sql = "SELECT * FROM maintenance_tasks";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tasks.add(new MaintenanceTaskDTO(
                        rs.getInt("task_id"),
                        rs.getInt("alert_id"),
                        rs.getInt("scooter_id"),
                        rs.getInt("maintainer_user_id"),
                        rs.getTimestamp("scheduled_time"),
                        rs.getTimestamp("completed_time"),
                        rs.getString("status")
                ));
            }
        }
        return tasks;
    }
    /**
     * Inserts a new maintenance task into the database
     * 
     * @param task MaintenanceTaskDTO object containing task details
     * @throws Exception if a database error occurs
     */
    @Override
    public void addTask(MaintenanceTaskDTO task) throws Exception {
        String sql = "INSERT INTO maintenance_tasks (alert_id, scooter_id, maintainer_user_id, scheduled_time, completed_time, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, task.getAlertId());
            ps.setInt(2, task.getScooterId());
            ps.setInt(3, task.getMaintainerUserId());
            ps.setTimestamp(4, task.getScheduledTime());
            ps.setTimestamp(5, task.getCompletedTime());
            ps.setString(6, task.getStatus());

            ps.executeUpdate();
        }
    }
    /**
     * Updates the status of a maintenance task
     * 
     * @param taskId ID of the task to update
     * @param status New status
     * @throws Exception if a database error occurs
     */
    @Override
    public void updateTaskStatus(int taskId, String status) throws Exception {
        String sql = "UPDATE maintenance_tasks SET status=? WHERE task_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, taskId);
            ps.executeUpdate();
        }
    }
}