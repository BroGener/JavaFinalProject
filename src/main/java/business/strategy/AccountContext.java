package business.strategy;

/**
 * Immutable context object carrying the data required for billing calculations.
 * Passed to an {@link AccountCalculationStrategy} to compute the billing amount.
 */
public class AccountContext {

    private final double distanceKm;             // total distance travelled in kilometres
    private final double minutesAwayFromStation; // time spent away from a charging station in minutes
    private final int scootersReturned;          // number of scooters returned during the session

    /**
     * @param distanceKm             total distance travelled in kilometres
     * @param minutesAwayFromStation time spent away from a charging station in minutes
     * @param scootersReturned       number of scooters returned during the session
     */
    public AccountContext(double distanceKm, double minutesAwayFromStation, int scootersReturned) {
        this.distanceKm = distanceKm;
        this.minutesAwayFromStation = minutesAwayFromStation;
        this.scootersReturned = scootersReturned;
    }

    /** @return total distance travelled in kilometres */
    public double getDistanceKm() { return distanceKm; }

    /** @return time spent away from a charging station in minutes */
    public double getMinutesAwayFromStation() { return minutesAwayFromStation; }

    /** @return number of scooters returned during the session */
    public int getScootersReturned() { return scootersReturned; }
}