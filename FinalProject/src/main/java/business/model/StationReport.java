package business.model;

import java.util.List;

/**
 * DTO (Data Transfer Object) that aggregates a station with its current scooters.
 * Used for the FR-06 station reporting view.
 *
 * @author Albin 
 */
public class StationReport {

    private ChargingStation station;
    private List<Integer> scooterIds;
    private int scooterCount;

    /** Default constructor. */
    public StationReport() {}

    /**
     * Full constructor.
     *
     * @param station      the charging station
     * @param scooterIds   list of scooter IDs currently docked at this station
     * @param scooterCount total scooters present
     */
    public StationReport(ChargingStation station, List<Integer> scooterIds, int scooterCount) {
        this.station = station;
        this.scooterIds = scooterIds;
        this.scooterCount = scooterCount;
    }

    public ChargingStation getStation() { return station; }
    public void setStation(ChargingStation station) { this.station = station; }

    public List<Integer> getScooterIds() { return scooterIds; }
    public void setScooterIds(List<Integer> scooterIds) { this.scooterIds = scooterIds; }

    public int getScooterCount() { return scooterCount; }
    public void setScooterCount(int scooterCount) { this.scooterCount = scooterCount; }
}
