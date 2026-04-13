package data.daoimpl;

import business.model.ActivityCredit;
import business.model.MonthlySummary;
import business.model.StationReport;
import data.datasource.MockDataStore;
import java.util.List;

/**
 * Mock implementation of {@link data.dao.ReportDAO} backed by {@link MockDataStore}.
 * Used for development and testing in place of a real database.
 */
public class ReportDAO implements data.dao.ReportDAO {

    /**
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return the {@link MonthlySummary} from the mock store
     */
    public MonthlySummary getMonthlySummary(int userId, int year, int month) {
        return MockDataStore.getMonthlySummary(userId, year, month);
    }

    /**
     * @param userId the ID of the user
     * @param year   the calendar year (e.g., {@code 2024})
     * @param month  the calendar month ({@code 1}–{@code 12})
     * @return a list of {@link ActivityCredit}s from the mock store
     */
    public List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) {
        return MockDataStore.getCreditsByActivity(userId, year, month);
    }

    /**
     * @return a list of {@link StationReport}s from the mock store
     */
    public List<StationReport> getStationReports() {
        return MockDataStore.getStationReports();
    }
}