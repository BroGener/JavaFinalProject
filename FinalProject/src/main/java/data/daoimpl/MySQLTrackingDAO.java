package data.daoimpl;

import business.model.GPSLog;
import data.daoimpl.TrackingDAO;
import data.datasource.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MySQL implementation of TrackingDAO.
 *
 * @author Albin 
 */
public class MySQLTrackingDAO implements TrackingDAO {

    @Override
    public Integer insert(GPSLog log) throws Exception {
        String sql = "INSERT INTO gps_logs "
                   + "(scooter_id, latitude, longitude, log_time, station_id, in_transit) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, log.getScooterId());
            ps.setDouble(2, log.getLatitude());
            ps.setDouble(3, log.getLongitude());
            ps.setTimestamp(4, log.getLogTime() != null
                    ? log.getLogTime()
                    : new Timestamp(System.currentTimeMillis()));

            if (log.getStationId() != null)
                ps.setInt(5, log.getStationId());
            else
                ps.setNull(5, Types.INTEGER);

            ps.setBoolean(6, log.isInTransit());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public boolean update(GPSLog log) throws Exception {
        String sql = "UPDATE gps_logs "
                   + "SET latitude=?, longitude=?, log_time=?, station_id=?, in_transit=? "
                   + "WHERE gps_log_id=?";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, log.getLatitude());
            ps.setDouble(2, log.getLongitude());
            ps.setTimestamp(3, log.getLogTime());

            if (log.getStationId() != null)
                ps.setInt(4, log.getStationId());
            else
                ps.setNull(4, Types.INTEGER);

            ps.setBoolean(5, log.isInTransit());
            ps.setInt(6, log.getGpsLogId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(Integer id) throws Exception {
        String sql = "DELETE FROM gps_logs WHERE gps_log_id=?";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<GPSLog> findById(Integer id) throws Exception {
        String sql = "SELECT * FROM gps_logs WHERE gps_log_id=?";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<GPSLog> findAll() throws Exception {
        List<GPSLog> list = new ArrayList<>();
        String sql = "SELECT * FROM gps_logs ORDER BY log_time DESC";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public Optional<GPSLog> findLatestByScooterId(int scooterId) throws Exception {
        String sql = "SELECT * FROM gps_logs "
                   + "WHERE scooter_id=? ORDER BY log_time DESC LIMIT 1";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, scooterId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<GPSLog> findByScooterId(int scooterId) throws Exception {
        List<GPSLog> list = new ArrayList<>();
        String sql = "SELECT * FROM gps_logs "
                   + "WHERE scooter_id=? ORDER BY log_time DESC";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, scooterId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    // ----------------------------------------------------------------- HELPER
    private GPSLog mapRow(ResultSet rs) throws SQLException {
        GPSLog log = new GPSLog();
        log.setGpsLogId(rs.getInt("gps_log_id"));
        log.setScooterId(rs.getInt("scooter_id"));
        log.setLatitude(rs.getDouble("latitude"));
        log.setLongitude(rs.getDouble("longitude"));
        log.setLogTime(rs.getTimestamp("log_time"));
        int sid = rs.getInt("station_id");
        log.setStationId(rs.wasNull() ? null : sid);
        log.setInTransit(rs.getBoolean("in_transit"));
        return log;
    }
}