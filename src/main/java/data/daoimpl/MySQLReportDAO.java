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
    String sql = "SELECT COUNT(*) AS tripCount, " +
                 "SUM(CASE WHEN transaction_type='DEBIT' THEN amount ELSE 0 END) AS totalAmount " +
                 "FROM account_transactions " +
                 "WHERE user_id=? AND YEAR(created_at)=? AND MONTH(created_at)=?";
    try (Connection con = DataSource.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, userId);
        ps.setInt(2, year);
        ps.setInt(3, month);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new MonthlySummary(
                userId, year, month,
                rs.getInt("tripCount"),
                0.0,
                rs.getDouble("totalAmount")
            );
        }
    } catch (Exception e) { e.printStackTrace(); }
    return new MonthlySummary(userId, year, month, 0, 0.0, 0.0);
}

@Override
public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception {
    List<ActivityCredit> list = new ArrayList<>();
    String sql = "SELECT activity_name, SUM(amount) AS total " +
                 "FROM account_transactions " +
                 "WHERE user_id=? AND transaction_type='CREDIT' " +
                 "AND YEAR(created_at)=? AND MONTH(created_at)=? " +
                 "GROUP BY activity_name";
    try (Connection con = DataSource.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, userId);
        ps.setInt(2, year);
        ps.setInt(3, month);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new ActivityCredit(
                rs.getString("activity_name"),
                rs.getDouble("total")
            ));
        }
    } catch (Exception e) { e.printStackTrace(); }
    return list;
}
}