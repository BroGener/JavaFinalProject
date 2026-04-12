package data.datasource;

import java.sql.*;

public final class DataSource {
    private DataSource() {}

    private static final String URL = "jdbc:mysql://localhost:3306/scooterdb";
    private static final String USER = "CST8288";
    private static final String PASSWORD = "CST8288"; // 改成你自己的密码

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getStatus() {
        return "Mock data source active";
    }
}