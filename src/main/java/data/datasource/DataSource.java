package data.datasource;

import java.sql.Connection;

public final class DataSource {

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private DataSource() {}

    public static String getStatus() {
        return "Mock data source active";
    }
}
