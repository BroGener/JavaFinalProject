package business.observer;

public class BatteryObserver implements Observer {
    @Override
    public void update(int scooterId, int currentChargeLevel, double usageHours) {
        // threshold check later
    }
}