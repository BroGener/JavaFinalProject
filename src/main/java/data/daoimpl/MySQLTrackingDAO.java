package data.daoimpl;

import business.model.GPSLog;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of {@link data.dao.TrackingDAO}.
 * Handles persistence and retrieval of GPS log entries from the database.
 */
public class MySQLTrackingDAO implements data.dao.TrackingDAO {

    /**
     * Inserts a new GPS log entry and returns the generated ID.
     *
     * @param log the {@link GPSLog} to insert
     * @return the generated log ID, or {@code -1} if the insert failed
     */
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Retrieves the most recent GPS log entry for a given scooter.
     *
     * @param scooterId the ID of the scooter
     * @return an {@link Optional} containing the latest {@link GPSLog}, or empty if none found
     */
    @Override
    public Optional<GPSLog> findLatestByScooterId(int scooterId) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM gps_logs WHERE scooter_id=? ORDER BY recorded_at DESC LIMIT 1")) {
            ps.setInt(1, scooterId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Retrieves the full GPS location history for a given scooter, ordered most recent first.
     *
     * @param scooterId the ID of the scooter
     * @return a list of {@link GPSLog} entries ordered by {@code recorded_at} descending
     */
    @Override
    public List<GPSLog> findByScooterId(int scooterId) {
        List<GPSLog> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM gps_logs WHERE scooter_id=? ORDER BY recorded_at DESC")) {
            ps.setInt(1, scooterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /** Not implemented — always returns {@code false}. */
    @Override
    public boolean update(GPSLog log) { return false; }

    /** Not implemented — always returns {@code false}. */
    @Override
    public boolean deleteById(Integer id) { return false; }

    /** Not implemented — always returns {@link Optional#empty()}. */
    @Override
    public Optional<GPSLog> findById(Integer id) { return Optional.empty(); }

    /** Not implemented — always returns an empty list. */
    @Override
    public List<GPSLog> findAll() { return new ArrayList<>(); }

    /**
     * Maps the current row of a {@link ResultSet} to a {@link GPSLog} object.
     *
     * @param rs the result set positioned at the row to extract
     * @return a populated {@link GPSLog}
     * @throws Exception if a column cannot be read
     */
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