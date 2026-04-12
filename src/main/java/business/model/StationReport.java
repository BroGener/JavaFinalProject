package business.model;

import java.io.Serializable;

public class StationReport implements Serializable {
    private int stationId;
    private String stationName;
    private int totalScooters;
    private int availableScooters;
    private int lowBatteryScooters;

    public StationReport() {}

    public StationReport(int stationId, String stationName, int totalScooters, int availableScooters, int lowBatteryScooters) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.totalScooters = totalScooters;
        this.availableScooters = availableScooters;
        this.lowBatteryScooters = lowBatteryScooters;
    }

    public int getStationId() { return stationId; }
    public void setStationId(int stationId) { this.stationId = stationId; }
    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public int getTotalScooters() { return totalScooters; }
    public void setTotalScooters(int totalScooters) { this.totalScooters = totalScooters; }
    public int getAvailableScooters() { return availableScooters; }
    public void setAvailableScooters(int availableScooters) { this.availableScooters = availableScooters; }
    public int getLowBatteryScooters() { return lowBatteryScooters; }
    public void setLowBatteryScooters(int lowBatteryScooters) { this.lowBatteryScooters = lowBatteryScooters; }
}
