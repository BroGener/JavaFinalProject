package data.daoimpl;

import business.model.ChargingStation;
import business.model.StationReport;
import data.dao.StationDAO;
import data.datasource.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MySQL implementation of StationDAO.
 *
 * @author Albin 
 */
public class MySQLStationDAO implements StationDAO {

    @Override
    public Integer insert(ChargingStation station) throws Exception {
        String sql = "INSERT INTO charging_stations (station_name, location_description, capacity, latitude, longitude) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, station.getStationName());
            ps.setString(2, station.getLocationDescription());
            ps.setInt(3, station.getCapacity());
            ps.setDouble(4, station.getLatitude());
            ps.setDouble(5, station.getLongitude());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public boolean update(ChargingStation station) throws Exception {
        String sql = "UPDATE charging_stations SET station_name=?, location_description=?, "
                   + "capacity=?, latitude=?, longitude=? WHERE station_id=?";
        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, station.getStationName());
            ps.setString(2, station.getLocationDescription());
            ps.setInt(3, station.getCapacity());
            ps.setDouble(4, station.getLatitude());
            ps.setDouble(5, station.getLongitude());
            ps.setInt(6, station.getStationId());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(Integer id) throws Exception {
        String sql = "DELETE FROM charging_stations WHERE station_id=?";
        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Optional<ChargingStation> findById(Integer id) throws Exception {
        String sql = "SELECT * FROM charging_stations WHERE station_id=?";
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
    public List<ChargingStation> findAll() throws Exception {
        List<ChargingStation> list = new ArrayList<>();
        String sql = "SELECT * FROM charging_stations ORDER BY station_name";
        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    /**
     * Builds a StationReport for each station — includes scooter count and IDs
     * currently docked there. Used by FR-06.
     */
    @Override
    public List<StationReport> findStationReports() throws Exception {
        List<StationReport> reports = new ArrayList<>();
        String sql = "SELECT cs.station_id, cs.station_name, cs.location_description, "
                   + "cs.capacity, cs.latitude, cs.longitude, "
                   + "COUNT(s.scooter_id) AS scooter_count "
                   + "FROM charging_stations cs "
                   + "LEFT JOIN scooters s ON s.current_station_id = cs.station_id "
                   + "GROUP BY cs.station_id "
                   + "ORDER BY cs.station_name";

        try (Connection conn = DataSource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ChargingStation station = mapRow(rs);
                int count = rs.getInt("scooter_count");
                List<Integer> ids = getScooterIdsAtStation(conn, station.getStationId());
                reports.add(new StationReport(station, ids, count));
            }
        }
        return reports;
    }

    private List<Integer> getScooterIdsAtStation(Connection conn, int stationId) throws Exception {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT scooter_id FROM scooters WHERE current_station_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, stationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) ids.add(rs.getInt("scooter_id"));
            }
        }
        return ids;
    }

    private ChargingStation mapRow(ResultSet rs) throws SQLException {
        ChargingStation s = new ChargingStation();
        s.setStationId(rs.getInt("station_id"));
        s.setStationName(rs.getString("station_name"));
        s.setLocationDescription(rs.getString("location_description"));
        s.setCapacity(rs.getInt("capacity"));
        s.setLatitude(rs.getDouble("latitude"));
        s.setLongitude(rs.getDouble("longitude"));
        return s;
    }
}