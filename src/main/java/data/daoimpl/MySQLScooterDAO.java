package data.daoimpl;

import business.model.Scooter;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

public class MySQLScooterDAO implements data.dao.ScooterDAO {

    private Connection getConnection() throws Exception {
        return DataSource.getConnection();
    }

    @Override
    public Integer insert(Scooter s) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO scooters(sponsor_user_id, vehicle_number, make, model, color, battery_capacity, current_charge_level, status, current_station_id) VALUES (?,?,?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, s.getSponsorUserId());
            ps.setString(2, s.getVehicleNumber());
            ps.setString(3, s.getMake());
            ps.setString(4, s.getModel());
            ps.setString(5, s.getColor());
            ps.setInt(6, s.getBatteryCapacity());
            ps.setInt(7, s.getCurrentChargeLevel());
            ps.setString(8, s.getStatus());
            ps.setObject(9, s.getCurrentStationId());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

    @Override
    public boolean update(Scooter s) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                "UPDATE scooters SET vehicle_number=?, make=?, model=?, color=?, battery_capacity=?, current_charge_level=?, status=?, current_station_id=? WHERE scooter_id=?")) {
            ps.setString(1, s.getVehicleNumber());
            ps.setString(2, s.getMake());
            ps.setString(3, s.getModel());
            ps.setString(4, s.getColor());
            ps.setInt(5, s.getBatteryCapacity());
            ps.setInt(6, s.getCurrentChargeLevel());
            ps.setString(7, s.getStatus());
            ps.setObject(8, s.getCurrentStationId());
            ps.setInt(9, s.getScooterId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM scooters WHERE scooter_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Optional<Scooter> findById(Integer id) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM scooters WHERE scooter_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public List<Scooter> findAll() {
        List<Scooter> list = new ArrayList<>();
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM scooters")) {
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<Scooter> findByVehicleNumber(String vehicleNumber) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM scooters WHERE vehicle_number=?")) {
            ps.setString(1, vehicleNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public List<Scooter> findBySponsorId(int sponsorUserId) {
        List<Scooter> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM scooters WHERE sponsor_user_id=?")) {
            ps.setInt(1, sponsorUserId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Scooter> findByStationId(int stationId) {
        List<Scooter> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM scooters WHERE current_station_id=?")) {
            ps.setInt(1, stationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    private Scooter extract(ResultSet rs) throws Exception {
        return new Scooter(
            rs.getInt("scooter_id"),
            rs.getInt("sponsor_user_id"),
            rs.getString("vehicle_number"),
            rs.getString("make"),
            rs.getString("model"),
            rs.getString("color"),
            rs.getInt("battery_capacity"),
            rs.getInt("current_charge_level"),
            rs.getString("status"),
            rs.getInt("current_station_id")
        );
    }
}