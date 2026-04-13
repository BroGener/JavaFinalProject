package business.service.impl;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import business.service.ReportService;
import data.datasource.DAOFactory;
import java.util.List;

/**
 * Implementation of {@link ReportService}.
 * Delegates all report queries to the DAO layer via {@link DAOFactory}.
 */
public class ReportServiceImpl implements ReportService {

    /**
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return the {@link MonthlySummary} for the given user and period
     * @throws Exception if a data access error occurs
     */
    public MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception {
        return DAOFactory.getReportDAO().getMonthlySummary(userId, year, month);
    }

    /**
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return a list of {@link ActivityCredit}s for the given user and period
     * @throws Exception if a data access error occurs
     */
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception {
        return DAOFactory.getReportDAO().getCreditsByActivity(userId, year, month);
    }

    /**
     * @return a list of {@link StationReport}s for all charging stations
     * @throws Exception if a data access error occurs
     */
    public List<StationReport> getStationReports() throws Exception {
        return DAOFactory.getReportDAO().getStationReports();
    }
}