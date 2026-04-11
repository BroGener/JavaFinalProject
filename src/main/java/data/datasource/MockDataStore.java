package data.datasource;

import business.model.AccountTransaction;
import business.model.ActivityCredit;
import business.model.ChargingStation;
import business.model.GPSLog;
import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import business.model.MonthlySummary;
import business.model.Scooter;
import business.model.StationReport;
import business.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public final class MockDataStore {
    private static final AtomicInteger USER_IDS = new AtomicInteger(10);
    private static final AtomicInteger SCOOTER_IDS = new AtomicInteger(10);
    private static final AtomicInteger ALERT_IDS = new AtomicInteger(10);
    private static final AtomicInteger TASK_IDS = new AtomicInteger(10);
    private static final AtomicInteger LOG_IDS = new AtomicInteger(10);

    public static final List<User> USERS = new ArrayList<User>();
    public static final List<Scooter> SCOOTERS = new ArrayList<Scooter>();
    public static final List<ChargingStation> STATIONS = new ArrayList<ChargingStation>();
    public static final List<MaintenanceAlert> ALERTS = new ArrayList<MaintenanceAlert>();
    public static final List<MaintenanceTask> TASKS = new ArrayList<MaintenanceTask>();
    public static final List<GPSLog> LOGS = new ArrayList<GPSLog>();
    public static final List<AccountTransaction> TRANSACTIONS = new ArrayList<AccountTransaction>();

    static {
        USERS.add(new User(1, "Demo User", "user@test.com", "1234", "USER"));
        USERS.add(new User(2, "Demo Maintainer", "maintainer@test.com", "1234", "MAINTAINER"));
        USERS.add(new User(3, "Demo Sponsor", "sponsor@test.com", "1234", "SPONSOR"));

        SCOOTERS.add(new Scooter(1, 3, "SC-001", "Segway", "Ninebot", "Black", 100, 82, "AVAILABLE", 1));
        SCOOTERS.add(new Scooter(2, 3, "SC-002", "Xiaomi", "Pro 2", "White", 100, 41, "CHARGING", 1));
        SCOOTERS.add(new Scooter(3, null, "SC-003", "Apollo", "City", "Blue", 100, 19, "MAINTENANCE", 2));

        STATIONS.add(new ChargingStation(1, "Main Campus", "Building A", 12, 4));
        STATIONS.add(new ChargingStation(2, "Library Station", "Building B", 8, 2));

        ALERTS.add(new MaintenanceAlert(1, 3, "LOW_BATTERY", "OPEN", "Battery below 20%", LocalDateTime.now().minusHours(4)));
        TASKS.add(new MaintenanceTask(1, 3, 2, "Inspect rear brake and battery", "HIGH", "ASSIGNED", LocalDate.now().plusDays(1)));

        LOGS.add(new GPSLog(1, 1, 45.4215, -75.6972, false, 1, LocalDateTime.now().minusMinutes(15)));
        LOGS.add(new GPSLog(2, 2, 45.4230, -75.6950, true, 1, LocalDateTime.now().minusMinutes(3)));

        TRANSACTIONS.add(new AccountTransaction(1, 1, "Ride charge", 6.50, "DEBIT", LocalDate.now().minusDays(3)));
        TRANSACTIONS.add(new AccountTransaction(2, 2, "Scooter return bonus", 7.00, "CREDIT", LocalDate.now().minusDays(2)));
        TRANSACTIONS.add(new AccountTransaction(3, 3, "Sponsor monthly bonus", 5.00, "CREDIT", LocalDate.now().minusDays(1)));
    }

    private MockDataStore() {}

    public static int nextUserId() { return USER_IDS.incrementAndGet(); }
    public static int nextScooterId() { return SCOOTER_IDS.incrementAndGet(); }
    public static int nextAlertId() { return ALERT_IDS.incrementAndGet(); }
    public static int nextTaskId() { return TASK_IDS.incrementAndGet(); }
    public static int nextLogId() { return LOG_IDS.incrementAndGet(); }

    public static Optional<User> findUserByEmail(String email) {
        for (User user : USERS) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public static MonthlySummary getMonthlySummary(int userId, int year, int month) {
        return new MonthlySummary(userId, year, month, 8, 23.4, 18.75);
    }

    public static List<ActivityCredit> getCreditsByActivity(int userId, int year, int month) {
        return Arrays.asList(
                new ActivityCredit("Ride charge", 6.50),
                new ActivityCredit("Late parking fee", 2.25),
                new ActivityCredit("Return bonus", -3.00)
        );
    }

    public static List<StationReport> getStationReports() {
        return Arrays.asList(
                new StationReport(1, "Main Campus", 12, 7, 1),
                new StationReport(2, "Library Station", 8, 4, 2)
        );
    }
}
