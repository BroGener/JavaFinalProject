/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferobjects.MaintenanceAlertDTO;
import transferobjects.MaintenanceTaskDTO;

/**
 *
 * @author biyababu
 */
public class MaintenanceDAOImpl implements MaintenanceDAO {

    private Connection conn;

    public MaintenanceDAOImpl(Connection conn) {
        this.conn = conn;
    }

   
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

    @Override
    public void updateAlertStatus(int alertId, String status) throws Exception {
        String sql = "UPDATE maintenance_alerts SET status=? WHERE alert_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, alertId);
            ps.executeUpdate();
        }
    }

    
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