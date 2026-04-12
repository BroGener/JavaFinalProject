package business.model;

import java.io.Serializable;

public class ChargingStation implements Serializable {
    private Integer stationId;
    private String stationName;
    private String location;
    private int capacity;
    private int availableSlots;

    public ChargingStation() {}

    public ChargingStation(Integer stationId, String stationName, String location, int capacity, int availableSlots) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.location = location;
        this.capacity = capacity;
        this.availableSlots = availableSlots;
    }

    public Integer getStationId() { return stationId; }
    public void setStationId(Integer stationId) { this.stationId = stationId; }
    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getAvailableSlots() { return availableSlots; }
    public void setAvailableSlots(int availableSlots) { this.availableSlots = availableSlots; }
}
