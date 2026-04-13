package business.observer;

import business.model.MaintenanceAlert;
import data.datasource.DAOFactory;

public class BatteryObserver implements Observer {
    @Override
    public void update(int scooterId, int currentChargeLevel, double usageHours) {
        if (currentChargeLevel < 20) {
            try {
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