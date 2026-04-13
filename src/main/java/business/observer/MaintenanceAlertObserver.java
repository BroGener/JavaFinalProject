package business.observer;

/**
 * Observer that monitors scooter usage for wear and servicing needs.
 * <p>Alert logic to be implemented in a future iteration.</p>
 *
 * @see Observer
 */
public class MaintenanceAlertObserver implements Observer {

    /**
     * Intended to evaluate scooter wear and trigger servicing alerts based on usage.
     * <p><b>Note:</b> Not yet implemented.</p>
     *
     * @param scooterId          the ID of the scooter being monitored
     * @param currentChargeLevel the current battery charge level (percentage)
     * @param usageHours         the number of hours the scooter has been in use
     */
    @Override
    public void update(int scooterId, int currentChargeLevel, double usageHours) {
        // wear / servicing logic to be implemented
    }
}