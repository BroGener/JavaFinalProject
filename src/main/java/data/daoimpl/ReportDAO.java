package data.daoimpl;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import data.datasource.MockDataStore;
import java.util.List;

public class ReportDAO implements data.dao.ReportDAO {
    public MonthlySummary getMonthlySummary(int userId, int year, int month) { return MockDataStore.getMonthlySummary(userId, year, month); }
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) { return MockDataStore.getCreditsByActivity(userId, year, month); }
    public List<StationReport> getStationReports() { return MockDataStore.getStationReports(); }
}
