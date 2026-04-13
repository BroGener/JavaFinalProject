package data.daoimpl;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import data.datasource.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLMaintenanceDAO implements data.dao.MaintenanceDAO {

    @Override
    public int insertAlert(MaintenanceAlert a) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO maintenance_alerts(scooter_id, alert_type, notes, status) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, a.getScooterId());
            ps.setString(2, a.getAlertType());
            ps.setString(3, a.getNotes());
            ps.setString(4, a.getStatus());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    @Override
    public int insertTask(MaintenanceTask t) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO maintenance_tasks(scooter_id, maintainer_user_id, description, status, due_date) VALUES (?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getScooterId());
            ps.setInt(2, t.getMaintainerUserId());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getStatus());
            ps.setDate(5, t.getDueDate() != null ? Date.valueOf(t.getDueDate()) : null);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    @Override
    public List<MaintenanceAlert> findOpenAlerts() {
        List<MaintenanceAlert> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM maintenance_alerts WHERE status='OPEN'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MaintenanceAlert a = new MaintenanceAlert();
                a.setAlertId(rs.getInt("alert_id"));
                a.setScooterId(rs.getInt("scooter_id"));
                a.setAlertType(rs.getString("alert_type"));
                a.setNotes(rs.getString("notes"));
                a.setStatus(rs.getString("status"));
                Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) a.setCreatedAt(ts.toLocalDateTime());
                list.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<MaintenanceTask> findTasksByMaintainerId(int maintainerUserId) {
        List<MaintenanceTask> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM maintenance_tasks WHERE maintainer_user_id=?")) {
            ps.setInt(1, maintainerUserId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MaintenanceTask t = new MaintenanceTask();
                t.setTaskId(rs.getInt("task_id"));
                t.setScooterId(rs.getInt("scooter_id"));
                t.setMaintainerUserId(rs.getInt("maintainer_user_id"));
                t.setDescription(rs.getString("description"));
                t.setStatus(rs.getString("status"));
                Date d = rs.getDate("due_date");
                if (d != null) t.setDueDate(d.toLocalDate());
                list.add(t);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean updateAlertStatus(int alertId, String status) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "UPDATE maintenance_alerts SET status=? WHERE alert_id=?")) {
            ps.setString(1, status);
            ps.setInt(2, alertId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean updateTaskStatus(int taskId, String status) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "UPDATE maintenance_tasks SET status=? WHERE task_id=?")) {
            ps.setString(1, status);
            ps.setInt(2, taskId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}