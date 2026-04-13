package business.service;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import java.util.List;

/**
 * Service interface for generating user and station reports.
 *
 * @see MonthlySummary
 * @see ActivityCredit
 * @see StationReport
 */
public interface ReportService {

    /**
     * Retrieves a monthly usage and billing summary for a specific user.
     *
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return a {@link MonthlySummary} for the given user and period
     * @throws Exception if a data access error occurs
     */
    MonthlySummary getMonthlySummary(int userId, int year, int month) throws Exception;

    /**
     * Retrieves a breakdown of activity credits earned by a user in a given month.
     *
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return a list of {@link ActivityCredit}s for the given user and period
     * @throws Exception if a data access error occurs
     */
    List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) throws Exception;

    /**
     * Retrieves availability and battery status reports for all charging stations.
     *
     * @return a list of {@link StationReport}s for all stations
     * @throws Exception if a data access error occurs
     */
    List<StationReport> getStationReports() throws Exception;
}