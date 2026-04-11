package business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GPSLog implements Serializable {
    private Integer logId;
    private Integer scooterId;
    private double latitude;
    private double longitude;
    private boolean inTransit;
    private Integer nearestStationId;
    private LocalDateTime recordedAt;

    public GPSLog() {}

    public GPSLog(Integer logId, Integer scooterId, double latitude, double longitude, boolean inTransit, Integer nearestStationId, LocalDateTime recordedAt) {
        this.logId = logId;
        this.scooterId = scooterId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.inTransit = inTransit;
        this.nearestStationId = nearestStationId;
        this.recordedAt = recordedAt;
    }

    public Integer getLogId() { return logId; }
    public void setLogId(Integer logId) { this.logId = logId; }
    public Integer getScooterId() { return scooterId; }
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public boolean isInTransit() { return inTransit; }
    public void setInTransit(boolean inTransit) { this.inTransit = inTransit; }
    public Integer getNearestStationId() { return nearestStationId; }
    public void setNearestStationId(Integer nearestStationId) { this.nearestStationId = nearestStationId; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}
