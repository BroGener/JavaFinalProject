package business.observer;

/**
 * Subject interface for the scooter telemetry observer pattern.
 * <p>
 * Part of the <b>Observer design pattern</b>. Defines the contract for
 * managing and notifying {@link Observer}s of scooter state changes.
 * </p>
 *
 * @see ScooterMonitor
 * @see Observer
 */
public interface Subject {

    /** @param observer the observer to register */
    void addObserver(Observer observer);

    /** @param observer the observer to unregister */
    void removeObserver(Observer observer);

    /**
     * Broadcasts the latest scooter telemetry to all registered observers.
     *
     * @param scooterId          the ID of the scooter being monitored
     * @param currentChargeLevel the current battery charge level (percentage)
     * @param usageHours         the number of hours the scooter has been in use
     */
    void notifyObservers(int scooterId, int currentChargeLevel, double usageHours);
}