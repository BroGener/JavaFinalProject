
package data.datasource;

public final class DAOFactory {
    private DAOFactory() {}

    // Switch to true to use real MySQL, false for Mock
    private static final boolean USE_MYSQL = true;

    public static data.dao.UserDAO getUserDAO() {
        return USE_MYSQL ? new data.daoimpl.MySQLUserDAO() : new data.daoimpl.UserDAO();
    }
    public static data.dao.ScooterDAO getScooterDAO() {
        return USE_MYSQL ? new data.daoimpl.MySQLScooterDAO() : new data.daoimpl.ScooterDAO();
    }
    public static data.dao.MaintenanceDAO getMaintenanceDAO() { return USE_MYSQL ? new data.daoimpl.MySQLMaintenanceDAO() : new data.daoimpl.MaintenanceDAO(); }
    public static data.dao.ReportDAO getReportDAO() { return USE_MYSQL ? new data.daoimpl.MySQLReportDAO() : new data.daoimpl.ReportDAO(); }
    public static data.dao.StationDAO getStationDAO() {
    return USE_MYSQL ? new data.daoimpl.MySQLStationDAO() : new data.daoimpl.StationDAO();
}
    public static data.dao.TrackingDAO getTrackingDAO() { return new data.daoimpl.TrackingDAO(); }
    public static data.dao.TransactionDAO getTransactionDAO() { return new data.daoimpl.TransactionDAO(); }
    
}
