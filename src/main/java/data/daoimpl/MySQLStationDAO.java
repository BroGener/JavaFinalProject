package data.daoimpl;

import business.model.ChargingStation;
import business.model.StationReport;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

public class MySQLStationDAO implements data.dao.StationDAO {

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
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

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
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "DELETE FROM charging_stations WHERE station_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Optional<ChargingStation> findById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM charging_stations WHERE station_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public List<ChargingStation> findAll() {
        List<ChargingStation> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM charging_stations")) {
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

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
    } catch (Exception e) { e.printStackTrace(); }
    return list;
}

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