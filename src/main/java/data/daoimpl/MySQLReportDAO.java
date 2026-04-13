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
        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception {
        // 查 DEBIT 总额（用于 USER）
        String debitSql = "SELECT COUNT(*) AS cnt, SUM(amount) AS total " +
                "FROM account_transactions " +
                "WHERE user_id=? AND transaction_type='DEBIT' " +
                "AND YEAR(created_at)=? AND MONTH(created_at)=?";
        // 查 CREDIT 总额（用于 SPONSOR/MAINTAINER）
        String creditSql = "SELECT COUNT(*) AS cnt, SUM(amount) AS total " +
                "FROM account_transactions " +
                "WHERE user_id=? AND transaction_type='CREDIT' " +
                "AND YEAR(created_at)=? AND MONTH(created_at)=?";

        double debitTotal = 0, creditTotal = 0;
        int debitCount = 0, creditCount = 0;

        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(
                debitSql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                debitCount = rs.getInt("cnt");
                debitTotal = rs.getDouble("total");
            }
        }
        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(
                creditSql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                creditCount = rs.getInt("cnt");
                creditTotal = rs.getDouble("total");
            }
        }

        // tripCount 存 debit 次数，totalAmount 存两者之和供 JSP 判断
        // 用 totalDistanceKm 存 creditTotal（复用字段）
        MonthlySummary s = new MonthlySummary();
        s.setUserId(userId);
        s.setYear(year);
        s.setMonth(month);
        s.setTripCount(debitCount + creditCount);
        s.setTotalAmount(debitTotal);
        s.setTotalDistanceKm(creditTotal); // 借用这个字段存 credit 总额
        return s;
    }

    @Override
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception {
        List<ActivityCredit> list = new ArrayList<>();
       // getMonthlySummary 去掉 paid=false 条件，显示全部
String debitSql = "SELECT COUNT(*) AS cnt, SUM(amount) AS total " +
                  "FROM account_transactions " +
                  "WHERE user_id=? AND transaction_type='DEBIT' " +
                  "AND YEAR(created_at)=? AND MONTH(created_at)=?";
String sql = "SELECT activity_name, amount, transaction_type, paid, created_at " +
             "FROM account_transactions " +
             "WHERE user_id=? AND YEAR(created_at)=? AND MONTH(created_at)=? " +
             "ORDER BY created_at DESC";




        try (Connection con = DataSource.getConnection(); PreparedStatement ps = con.prepareStatement(
                sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ActivityCredit c = new ActivityCredit();
                c.setActivityName(rs.getString("activity_name"));
                c.setAmount(rs.getDouble("amount"));
                c.setTransactionType(rs.getString("transaction_type"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                c.setPaid(rs.getBoolean("paid"));
                list.add(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
