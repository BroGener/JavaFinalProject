package business.observer;

public interface Observer {
    void update(int scooterId, int currentChargeLevel, double usageHours);
}