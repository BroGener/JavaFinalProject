package data.datasource;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public final class DataSource {
    private DataSource() {}

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream is = DataSource.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            props.load(is);
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getStatus() {
        return "DataSource active: " + URL;
    }
}