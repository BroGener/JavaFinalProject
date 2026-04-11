package business.service.impl;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import business.service.ReportService;
import data.datasource.DAOFactory;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    public MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception { return DAOFactory.getReportDAO().getMonthlySummary(userId, year, month); }
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception { return DAOFactory.getReportDAO().getCreditsByActivity(userId, year, month); }
    public List<StationReport> getStationReports() throws Exception { return DAOFactory.getReportDAO().getStationReports(); }
}
