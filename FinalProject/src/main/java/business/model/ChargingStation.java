package business.model;

/**
 * Model representing a charging station on the Woodroffe Campus.
 *
 * @author Albin 
 */
public class ChargingStation {

    private Integer stationId;
    private String stationName;
    private String locationDescription;
    private int capacity;
    private double latitude;
    private double longitude;

    public ChargingStation() {}

    public ChargingStation(Integer stationId, String stationName,
                           String locationDescription, int capacity,
                           double latitude, double longitude) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.locationDescription = locationDescription;
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getStationId() { return stationId; }
    public void setStationId(Integer stationId) { this.stationId = stationId; }

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    @Override
    public String toString() {
        return "ChargingStation{stationId=" + stationId + ", stationName='" + stationName + "'}";
    }
}