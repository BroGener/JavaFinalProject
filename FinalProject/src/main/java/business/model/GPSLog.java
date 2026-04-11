package business.model;

import java.sql.Timestamp;

/**
 * Model representing a single GPS location log entry for an e-scooter.
 *
 * @author Albin 
 */
public class GPSLog {

    private Integer gpsLogId;
    private int scooterId;
    private double latitude;
    private double longitude;
    private Timestamp logTime;
    private Integer stationId;
    private boolean inTransit;

    public GPSLog() {}

    public GPSLog(Integer gpsLogId, int scooterId, double latitude, double longitude,
                  Timestamp logTime, Integer stationId, boolean inTransit) {
        this.gpsLogId = gpsLogId;
        this.scooterId = scooterId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.logTime = logTime;
        this.stationId = stationId;
        this.inTransit = inTransit;
    }

    public Integer getGpsLogId() { return gpsLogId; }
    public void setGpsLogId(Integer gpsLogId) { this.gpsLogId = gpsLogId; }

    public int getScooterId() { return scooterId; }
    public void setScooterId(int scooterId) { this.scooterId = scooterId; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public Timestamp getLogTime() { return logTime; }
    public void setLogTime(Timestamp logTime) { this.logTime = logTime; }

    public Integer getStationId() { return stationId; }
    public void setStationId(Integer stationId) { this.stationId = stationId; }

    public boolean isInTransit() { return inTransit; }
    public void setInTransit(boolean inTransit) { this.inTransit = inTransit; }

    @Override
    public String toString() {
        return "GPSLog{gpsLogId=" + gpsLogId + ", scooterId=" + scooterId +
               ", lat=" + latitude + ", lng=" + longitude + ", inTransit=" + inTransit + "}";
    }
}