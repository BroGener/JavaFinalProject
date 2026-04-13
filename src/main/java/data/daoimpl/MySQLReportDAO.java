package data.daoimpl;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import data.datasource.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL implementation of {@link data.dao.ReportDAO}.
 * Executes SQL queries against the live database to generate user and station reports.
 */
public class MySQLReportDAO implements data.dao.ReportDAO {

    /**
     * Queries all charging stations and aggregates scooter availability and
     * low-battery counts via a LEFT JOIN with the scooters table.
     *
     * @return a list of {@link StationReport}s for all charging stations
     */
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Builds a monthly financial summary for a user by separately querying
     * DEBIT and CREDIT totals from {@code account_transactions}.
     * <p>
     * <b>Note:</b> {@code totalDistanceKm} is repurposed to store the CREDIT total,
     * and {@code tripCount} reflects the combined DEBIT + CREDIT transaction count.
     * </p>
     *
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return a {@link MonthlySummary} populated with aggregated transaction data
     * @throws Exception if a data access error occurs
     */
    @Override
    public MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception {
        // Query total DEBIT amount and count (used for regular users)
        String debitSql = "SELECT COUNT(*) AS cnt, SUM(amount) AS total " +
                "FROM account_transactions " +
                "WHERE user_id=? AND transaction_type='DEBIT' " +
                "AND YEAR(created_at)=? AND MONTH(created_at)=?";

        // Query total CREDIT amount and count (used for sponsors/maintainers)
        String creditSql = "SELECT COUNT(*) AS cnt, SUM(amount) AS total " +
                "FROM account_transactions " +
                "WHERE user_id=? AND transaction_type='CREDIT' " +
                "AND YEAR(created_at)=? AND MONTH(created_at)=?";

        double debitTotal = 0, creditTotal = 0;
        int debitCount = 0, creditCount = 0;

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(debitSql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                debitCount = rs.getInt("cnt");
                debitTotal = rs.getDouble("total");
            }
        }

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(creditSql)) {
            ps.setInt(1, userId);
            ps.setInt(2, year);
            ps.setInt(3, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                creditCount = rs.getInt("cnt");
                creditTotal = rs.getDouble("total");
            }
        }

        // tripCount = total transaction count (debit + credit)
        // totalAmount = debit total; totalDistanceKm = credit total (field repurposed)
        MonthlySummary s = new MonthlySummary();
        s.setUserId(userId);
        s.setYear(year);
        s.setMonth(month);
        s.setTripCount(debitCount + creditCount);
        s.setTotalAmount(debitTotal);
        s.setTotalDistanceKm(creditTotal); // repurposed to store credit total
        return s;
    }

    /**
     * Retrieves all transaction records for a user in the given month,
     * ordered by most recent first. Returns both DEBIT and CREDIT entries.
     *
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return a list of {@link ActivityCredit}s ordered by {@code created_at} descending
     * @throws Exception if a data access error occurs
     */
    @Override
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception {
        List<ActivityCredit> list = new ArrayList<>();

        // Retrieve all transactions for the user in the given month (both DEBIT and CREDIT)
        String sql = "SELECT activity_name, amount, transaction_type, paid, created_at " +
                     "FROM account_transactions " +
                     "WHERE user_id=? AND YEAR(created_at)=? AND MONTH(created_at)=? " +
                     "ORDER BY created_at DESC";

        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}