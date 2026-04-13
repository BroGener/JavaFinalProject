package data.daoimpl;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import data.datasource.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLReportDAO implements data.dao.ReportDAO {

    @Override
    public List<StationReport> getStationReports() {
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

    @Override
    public MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception {
        // placeholder - needs account_transactions table
        return new MonthlySummary(userId, year, month, 0, 0.0, 0.0);
    }

    @Override
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception {
        // placeholder - needs account_transactions table
        return new ArrayList<>();
    }
}