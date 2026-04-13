package business.observer;

import business.model.MaintenanceAlert;
import data.datasource.DAOFactory;

public class WearObserver implements Observer {
    private static final double BRAKE_WEAR_THRESHOLD_HOURS = 0.005; // ~20秒 for demo
    private static final double BATTERY_WEAR_THRESHOLD_HOURS = 0.004; // ~15秒 for demo

    @Override
    public void update(int scooterId, int currentChargeLevel, double usageHours) {
        try {
            if (usageHours >= BRAKE_WEAR_THRESHOLD_HOURS) {
                MaintenanceAlert alert = new MaintenanceAlert();
                alert.setScooterId(scooterId);
                alert.setAlertType("BRAKE_WEAR");
                alert.setNotes("Brake wear threshold reached after " + 
                    String.format("%.1f", usageHours * 60) + " minutes of use");
                alert.setStatus("OPEN");
                DAOFactory.getMaintenanceDAO().insertAlert(alert);
            }
            if (usageHours >= BATTERY_WEAR_THRESHOLD_HOURS) {
                MaintenanceAlert alert = new MaintenanceAlert();
                alert.setScooterId(scooterId);
                alert.setAlertType("BATTERY_WEAR");
                alert.setNotes("Battery wear threshold reached");
                alert.setStatus("OPEN");
                DAOFactory.getMaintenanceDAO().insertAlert(alert);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}