package business.model;

import java.io.Serializable;

/** Summary report of scooter availability and battery status at a charging station. */
public class StationReport implements Serializable {

    private int stationId;
    private String stationName;
    private int totalScooters;      // total scooters docked at this station
    private int availableScooters;  // scooters ready for use
    private int lowBatteryScooters; // scooters with critically low charge

    /** No-arg constructor for reflection-based frameworks. */
    public StationReport() {}

    /**
     * @param stationId          station ID
     * @param stationName        station name
     * @param totalScooters      total scooters docked
     * @param availableScooters  scooters ready for use
     * @param lowBatteryScooters scooters with low battery
     */
    public StationReport(int stationId, String stationName, int totalScooters,
                         int availableScooters, int lowBatteryScooters) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.totalScooters = totalScooters;
        this.availableScooters = availableScooters;
        this.lowBatteryScooters = lowBatteryScooters;
    }

    /** @return the station ID */
    public int getStationId() { return stationId; }
    /** @param stationId the station ID to assign */
    public void setStationId(int stationId) { this.stationId = stationId; }

    /** @return the station name */
    public String getStationName() { return stationName; }
    /** @param stationName the station name to assign */
    public void setStationName(String stationName) { this.stationName = stationName; }

    /** @return total number of scooters docked at this station */
    public int getTotalScooters() { return totalScooters; }
    /** @param totalScooters total scooter count to assign */
    public void setTotalScooters(int totalScooters) { this.totalScooters = totalScooters; }

    /** @return number of scooters available for use */
    public int getAvailableScooters() { return availableScooters; }
    /** @param availableScooters available scooter count to assign */
    public void setAvailableScooters(int availableScooters) { this.availableScooters = availableScooters; }

    /** @return number of scooters with low battery */
    public int getLowBatteryScooters() { return lowBatteryScooters; }
    /** @param lowBatteryScooters low-battery scooter count to assign */
    public void setLowBatteryScooters(int lowBatteryScooters) { this.lowBatteryScooters = lowBatteryScooters; }
}