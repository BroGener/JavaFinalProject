package data.daoimpl;

import business.model.User;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

/**
 * MySQL implementation of {@link data.dao.UserDAO}.
 * Handles all user CRUD and lookup operations against the live database.
 */
public class MySQLUserDAO implements data.dao.UserDAO {

    /**
     * Inserts a new user and returns the generated ID.
     *
     * @param u the user to insert
     * @return the generated user ID, or {@code -1} if the insert failed
     */
    @Override
    public Integer insert(User u) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name, email, password, user_type) VALUES (?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) return keys.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Updates an existing user's details.
     *
     * @param u the user with updated fields
     * @return {@code true} if at least one row was updated; {@code false} otherwise
     */
    @Override
    public boolean update(User u) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET name=?, email=?, password=?, user_type=? WHERE user_id=?")) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            ps.setInt(5, u.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return {@code true} if at least one row was deleted; {@code false} otherwise
     */
    @Override
    public boolean deleteById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "DELETE FROM users WHERE user_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return an {@link Optional} containing the {@link User}, or empty if not found
     */
    @Override
    public Optional<User> findById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE user_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Retrieves all users registered in the system.
     *
     * @return a list of all {@link User}s
     */
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address to look up
     * @return an {@link Optional} containing the {@link User}, or empty if not found
     */
    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Retrieves a user by matching both email and password (used for login).
     *
     * @param email    the user's email address
     * @param password the user's password
     * @return an {@link Optional} containing the matched {@link User}, or empty if credentials are invalid
     */
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Maps the current row of a {@link ResultSet} to a {@link User} object.
     *
     * @param rs the result set positioned at the row to extract
     * @return a populated {@link User}
     * @throws Exception if a column cannot be read
     */
    private User extract(ResultSet rs) throws Exception {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRole(rs.getString("user_type"));
        return u;
    }
}