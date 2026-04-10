/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datalayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferobjects.AccountTransactionDTO;
/**
 *
 * @author biyababu
 */
public class TransactionDAOImpl implements TransactionDAO {

    private Connection conn;

    public TransactionDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addTransaction(AccountTransactionDTO t) throws Exception {

      
        String sql = "INSERT INTO account_transactions (user_id, transaction_type, source_type, amount, description, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, t.getUserId());
            ps.setString(2, t.getTransactionType());
            ps.setString(3, t.getSourceType());
            ps.setDouble(4, t.getAmount());
            ps.setString(5, t.getDescription());
            ps.setTimestamp(6, t.getCreatedAt());

            ps.executeUpdate();
        }
    }

    @Override
    public List<AccountTransactionDTO> getTransactionsByUser(int userId) throws Exception {

        List<AccountTransactionDTO> list = new ArrayList<>();

       
        String sql = "SELECT * FROM account_transactions WHERE user_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new AccountTransactionDTO(
                            rs.getInt("transaction_id"),
                            rs.getInt("user_id"),
                            rs.getString("transaction_type"),
                            rs.getString("source_type"),
                            rs.getDouble("amount"),
                            rs.getString("description"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        }
        return list;
    }
}
