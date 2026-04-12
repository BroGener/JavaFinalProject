package business.observer;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(int scooterId, int currentChargeLevel, double usageHours);
}