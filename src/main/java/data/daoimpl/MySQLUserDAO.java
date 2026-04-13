package data.daoimpl;

import business.model.User;
import data.datasource.DataSource;
import java.sql.*;
import java.util.*;

public class MySQLUserDAO implements data.dao.UserDAO {

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
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }

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
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "DELETE FROM users WHERE user_id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Optional<User> findById(Integer id) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE user_id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {
            while (rs.next()) list.add(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return Optional.of(extract(rs));
        } catch (Exception e) { e.printStackTrace(); }
        return Optional.empty();
    }

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