/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.daoimpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferobjects.AccountTransactionDTO;
import data.dao.TransactionDAO;
/**
 *
 * @author biyababu
 */
/*
 *This class handles insert transaction , fetch user trancsaction 
* 
 */
public class MySQLTransactionDAO implements TransactionDAO {

    private Connection conn;

    public MySQLTransactionDAO(Connection conn) {
        this.conn = conn;
    }
/**
 *Inserts a new transaction record into the database
 * 
 * @param t AccountTransactionDTO object containing transaction details
 * @throws Exception if any database error occurs
 */
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
    /**
     * Retrieves all transactions for a given user
     * 
     * @param userId of the user
     * @return List of AccountTransactionDTO objects
     * @throws Exception if any database error occurs
     */
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
