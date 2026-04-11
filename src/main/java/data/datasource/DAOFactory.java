package data.datasource;

public final class DAOFactory {
    private DAOFactory() {}

    public static data.dao.UserDAO getUserDAO() { return new data.daoimpl.UserDAO(); }
    public static data.dao.ScooterDAO getScooterDAO() { return new data.daoimpl.ScooterDAO(); }
    public static data.dao.MaintenanceDAO getMaintenanceDAO() { return new data.daoimpl.MaintenanceDAO(); }
    public static data.dao.ReportDAO getReportDAO() { return new data.daoimpl.ReportDAO(); }
    public static data.dao.StationDAO getStationDAO() { return new data.daoimpl.StationDAO(); }
    public static data.dao.TrackingDAO getTrackingDAO() { return new data.daoimpl.TrackingDAO(); }
    public static data.dao.TransactionDAO getTransactionDAO() { return new data.daoimpl.TransactionDAO(); }
}
