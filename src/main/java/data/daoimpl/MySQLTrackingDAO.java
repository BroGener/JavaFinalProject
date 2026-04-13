package data.daoimpl;

import business.model.GPSLog;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

public class MySQLTrackingDAO implements data.dao.TrackingDAO {

    @Override
    public Integer insert(GPSLog log) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO gps_logs(scooter_id, latitude, longitude, nearest_station_id, in_transit) VALUES (?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, log.getScooterId());
            ps.setDouble(2, log.getLatitude());
            ps.setDouble(3, log.getLongitude());
            ps.setInt(4, log.getNearestStationId());
            ps.setBoolean(5, log.isInTransit());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    @Override
    public Optional<GPSLog> findLatestByScooterId(int scooterId) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM gps_logs WHERE scooter_id=? ORDER BY recorded_at DESC LIMIT 1")) {
            ps.setInt(1, scooterId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public List<GPSLog> findByScooterId(int scooterId) {
        List<GPSLog> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM gps_logs WHERE scooter_id=? ORDER BY recorded_at DESC")) {
            ps.setInt(1, scooterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean update(GPSLog log) { return false; }

    @Override
    public boolean deleteById(Integer id) { return false; }

    @Override
    public Optional<GPSLog> findById(Integer id) { return Optional.empty(); }

    @Override
    public List<GPSLog> findAll() { return new ArrayList<>(); }

    private GPSLog extract(ResultSet rs) throws Exception {
        GPSLog log = new GPSLog();
        log.setLogId(rs.getInt("log_id"));
        log.setScooterId(rs.getInt("scooter_id"));
        log.setLatitude(rs.getDouble("latitude"));
        log.setLongitude(rs.getDouble("longitude"));
        log.setNearestStationId(rs.getInt("nearest_station_id"));
        log.setInTransit(rs.getBoolean("in_transit"));
        return log;
    }
}