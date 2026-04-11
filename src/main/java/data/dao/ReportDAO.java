package data.dao;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import java.util.List;

public interface ReportDAO {
    MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception;
    List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception;
    List<StationReport> getStationReports() throws Exception;
}