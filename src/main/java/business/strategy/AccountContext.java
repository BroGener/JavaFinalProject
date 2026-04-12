package business.strategy;

public class AccountContext {
    private final double distanceKm;
    private final double minutesAwayFromStation;
    private final int scootersReturned;

    public AccountContext(double distanceKm, double minutesAwayFromStation, int scootersReturned) {
        this.distanceKm = distanceKm;
        this.minutesAwayFromStation = minutesAwayFromStation;
        this.scootersReturned = scootersReturned;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public double getMinutesAwayFromStation() {
        return minutesAwayFromStation;
    }

    public int getScootersReturned() {
        return scootersReturned;
    }
}