package business.observer;

import business.model.MaintenanceAlert;
import data.datasource.DAOFactory;

/**
 * Observer that monitors scooter battery levels and raises a maintenance alert
 * when the charge drops below 20%.
 *
 * @see Observer
 * @see MaintenanceAlert
 */
public class BatteryObserver implements Observer {

    /**
     * Checks the scooter's current charge level and creates a {@code LOW_BATTERY}
     * maintenance alert if it falls below 20%.
     *
     * @param scooterId          the ID of the scooter being monitored
     * @param currentChargeLevel the current battery charge level (percentage)
     * @param usageHours         the number of hours the scooter has been in use (unused by this observer)
     */
    @Override
    public void update(int scooterId, int currentChargeLevel, double usageHours) {
        if (currentChargeLevel < 20) {
            try {
                // Build and persist a LOW_BATTERY alert for the scooter
                MaintenanceAlert alert = new MaintenanceAlert();
                alert.setScooterId(scooterId);
                alert.setAlertType("LOW_BATTERY");
                alert.setNotes("Battery below 20%, current level: " + currentChargeLevel + "%");
                alert.setStatus("OPEN");
                DAOFactory.getMaintenanceDAO().insertAlert(alert);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}