package business.observer;

public class MaintenanceAlertObserver implements Observer {
    @Override
    public void update(int scooterId, int currentChargeLevel, double usageHours) {
        // wear / servicing logic later
    }
}