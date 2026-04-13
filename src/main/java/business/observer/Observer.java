package business.observer;

/**
 * Observer interface for monitoring scooter telemetry updates.
 * <p>
 * Part of the <b>Observer design pattern</b>. Implementations react to
 * scooter state changes such as battery level drops or extended usage.
 * </p>
 *
 * @see BatteryObserver
 * @see MaintenanceAlertObserver
 */
public interface Observer {

    /**
     * Called when a scooter's telemetry data is updated.
     *
     * @param scooterId          the ID of the scooter being monitored
     * @param currentChargeLevel the current battery charge level (percentage)
     * @param usageHours         the number of hours the scooter has been in use
     */
    void update(int scooterId, int currentChargeLevel, double usageHours);
}