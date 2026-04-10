/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferobjects.MonthlySummaryDTO;
import transferobjects.ActivityCreditDTO;
/**
 *
 * @author biyababu
 */
public class ReportDAOImpl implements ReportDAO {

    private Connection conn;

    public ReportDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public MonthlySummaryDTO getMonthlySummary(int userId) throws Exception {

       
        String sql = "SELECT " +
                "SUM(CASE WHEN transaction_type='CREDIT' THEN amount ELSE 0 END) AS totalCredits, " +
                "SUM(CASE WHEN transaction_type='DEBIT' THEN amount ELSE 0 END) AS totalDebits " +
                "FROM account_transactions WHERE user_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    double credits = rs.getDouble("totalCredits");
                    if (rs.wasNull()) credits = 0;

                    double debits = rs.getDouble("totalDebits");
                    if (rs.wasNull()) debits = 0;

                    return new MonthlySummaryDTO(userId, credits, debits);
                }
            }
        }
        return null;
    }

    @Override
    public List<ActivityCreditDTO> getActivityCredits() throws Exception {

        List<ActivityCreditDTO> list = new ArrayList<>();

        String sql = "SELECT user_id, source_type, COUNT(*) AS activityCount, SUM(amount) AS totalCredit " +
                     "FROM account_transactions WHERE transaction_type='CREDIT' " +
                     "GROUP BY user_id, source_type";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ActivityCreditDTO credit = new ActivityCreditDTO();

                credit.setUserId(rs.getInt("user_id"));
                credit.setActivityType(rs.getString("source_type"));
                credit.setActivityCount(rs.getInt("activityCount"));
                credit.setTotalCredit(rs.getDouble("totalCredit"));

                list.add(credit);
            }
        }
        return list;
    }
}