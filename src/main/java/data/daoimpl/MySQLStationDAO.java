package data.daoimpl;

import business.model.ChargingStation;
import business.model.StationReport;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of {@link data.dao.StationDAO}.
 * Executes SQL queries against the live database for charging station CRUD and reporting.
 */
public class MySQLStationDAO implements data.dao.StationDAO {

    /**
     * Inserts a new charging station and returns the generated ID.
     *
     * @param s the station to insert
     * @return the generated station ID, or {@code -1} if the insert failed
     */
    @Override
    public Integer insert(ChargingStation s) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO charging_stations(name, location, capacity, available_slots) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getStationName());
            ps.setString(2, s.getLocation());
            ps.setInt(3, s.getCapacity());
            ps.setInt(4, s.getAvailableSlots());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Updates an existing charging station's details.
     *
     * @param s the station with updated fields
     * @return {@code true} if at least one row was updated; {@code false} otherwise
     */
    @Override
    public boolean update(ChargingStation s) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "UPDATE charging_stations SET name=?, location=?, capacity=?, available_slots=? WHERE station_id=?")) {
            ps.setString(1, s.getStationName());
            ps.setString(2, s.getLocation());
            ps.setInt(3, s.getCapacity());
            ps.setInt(4, s.getAvailableSlots());
            ps.setInt(5, s.getStationId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a charging station by its ID.
     *
     * @param id the ID of the station to delete
     * @return {@code true} if at least one row was deleted; {@code false} otherwise
     */
    @Override
    public boolean deleteById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "DELETE FROM charging_stations WHERE station_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a charging station by its ID.
     *
     * @param id the ID of the station to retrieve
     * @return an {@link Optional} containing the {@link ChargingStation}, or empty if not found
     */
    @Override
    public Optional<ChargingStation> findById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM charging_stations WHERE station_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Retrieves all charging stations with their available slot count computed
     * via a LEFT JOIN on scooters with status {@code 'AVAILABLE'}.
     *
     * @return a list of all {@link ChargingStation}s with live availability counts
     */
    @Override
    public List<ChargingStation> findAll() {
        List<ChargingStation> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(
                "SELECT cs.*, " +
                "COUNT(s.scooter_id) AS available_count " +
                "FROM charging_stations cs " +
                "LEFT JOIN scooters s ON cs.station_id = s.current_station_id " +
                "AND s.status = 'AVAILABLE' " +
                "GROUP BY cs.station_id")) {
            while (rs.next()) {
                ChargingStation s = extract(rs);
                s.setAvailableSlots(rs.getInt("available_count")); // override with live count
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Queries all stations and aggregates total, available, and low-battery
     * scooter counts via a LEFT JOIN with the scooters table.
     *
     * @return a list of {@link StationReport}s for all charging stations
     */
    @Override
    public List<StationReport> findStationReports() {
        List<StationReport> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT cs.station_id, cs.name, " +
                "COUNT(s.scooter_id) AS total, " +
                "SUM(CASE WHEN s.status='AVAILABLE' THEN 1 ELSE 0 END) AS available, " +
                "SUM(CASE WHEN s.current_charge_level < 20 THEN 1 ELSE 0 END) AS low_battery " +
                "FROM charging_stations cs " +
                "LEFT JOIN scooters s ON cs.station_id = s.current_station_id " +
                "GROUP BY cs.station_id, cs.name")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StationReport r = new StationReport();
                r.setStationId(rs.getInt("station_id"));
                r.setStationName(rs.getString("name"));
                r.setTotalScooters(rs.getInt("total"));
                r.setAvailableScooters(rs.getInt("available"));
                r.setLowBatteryScooters(rs.getInt("low_battery"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Maps the current row of a {@link ResultSet} to a {@link ChargingStation} object.
     *
     * @param rs the result set positioned at the row to extract
     * @return a populated {@link ChargingStation}
     * @throws Exception if a column cannot be read
     */
    private ChargingStation extract(ResultSet rs) throws Exception {
        ChargingStation s = new ChargingStation();
        s.setStationId(rs.getInt("station_id"));
        s.setStationName(rs.getString("name"));
        s.setLocation(rs.getString("location"));
        s.setCapacity(rs.getInt("capacity"));
        s.setAvailableSlots(rs.getInt("available_slots"));
        return s;
    }
}