package business.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete subject that manages and notifies scooter telemetry observers.
 * <p>
 * Part of the <b>Observer design pattern</b>. Maintains a list of registered
 * {@link Observer}s and broadcasts scooter state updates to all of them.
 * </p>
 *
 * @see Subject
 * @see Observer
 */
public class ScooterMonitor implements Subject {

    /** List of registered observers to be notified on telemetry updates. */
    private final List<Observer> observers = new ArrayList<>();

    /** @param observer the observer to register */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /** @param observer the observer to unregister */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers with the latest scooter telemetry.
     *
     * @param scooterId          the ID of the scooter being monitored
     * @param currentChargeLevel the current battery charge level (percentage)
     * @param usageHours         the number of hours the scooter has been in use
     */
    @Override
    public void notifyObservers(int scooterId, int currentChargeLevel, double usageHours) {
        for (Observer observer : observers) {
            observer.update(scooterId, currentChargeLevel, usageHours);
        }
    }
}