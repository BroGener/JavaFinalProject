package business.observer;

import java.util.ArrayList;
import java.util.List;

public class ScooterMonitor implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(int scooterId, int currentChargeLevel, double usageHours) {
        for (Observer observer : observers) {
            observer.update(scooterId, currentChargeLevel, usageHours);
        }
    }
}