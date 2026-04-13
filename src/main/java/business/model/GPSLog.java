package business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a GPS log entry recorded for a scooter at a point in time.
 * <p>
 * A {@code GPSLog} captures a snapshot of a scooter's geographic position,
 * movement status, and nearest station at a specific timestamp. These records
 * are used to track scooter location history and support transit monitoring.
 * This class implements {@link Serializable} to support persistence and data
 * transfer.
 * </p>
 */
public class GPSLog implements Serializable {

    /** The unique identifier for this GPS log entry. */
    private Integer logId;

    /** The ID of the scooter this log entry belongs to. */
    private Integer scooterId;

    /** The latitude coordinate of the scooter at the time of recording, in decimal degrees. */
    private double latitude;

    /** The longitude coordinate of the scooter at the time of recording, in decimal degrees. */
    private double longitude;

    /** Indicates whether the scooter was in transit at the time of recording. */
    private boolean inTransit;

    /** The ID of the nearest charging station to the scooter's recorded position, if any. */
    private Integer nearestStationId;

    /** The date and time at which this GPS log entry was recorded. */
    private LocalDateTime recordedAt;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public GPSLog() {}

    /**
     * Constructs a fully initialized {@code GPSLog} with all fields specified.
     *
     * @param logId            the unique identifier for this log entry, or {@code null} if not yet persisted
     * @param scooterId        the ID of the scooter this log entry belongs to
     * @param latitude         the latitude coordinate at the time of recording
     * @param longitude        the longitude coordinate at the time of recording
     * @param inTransit        {@code true} if the scooter was moving; {@code false} if stationary
     * @param nearestStationId the ID of the nearest charging station, or {@code null} if none
     * @param recordedAt       the date and time this entry was recorded
     */
    public GPSLog(Integer logId, Integer scooterId, double latitude, double longitude,
                  boolean inTransit, Integer nearestStationId, LocalDateTime recordedAt) {
        this.logId = logId;
        this.scooterId = scooterId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.inTransit = inTransit;
        this.nearestStationId = nearestStationId;
        this.recordedAt = recordedAt;
    }

    /**
     * Returns the unique identifier of this GPS log entry.
     *
     * @return the log ID, or {@code null} if not yet persisted
     */
    public Integer getLogId() { return logId; }

    /**
     * Sets the unique identifier of this GPS log entry.
     *
     * @param logId the log ID to assign
     */
    public void setLogId(Integer logId) { this.logId = logId; }

    /**
     * Returns the ID of the scooter this log entry belongs to.
     *
     * @return the scooter ID
     */
    public Integer getScooterId() { return scooterId; }

    /**
     * Sets the ID of the scooter this log entry belongs to.
     *
     * @param scooterId the scooter ID to assign
     */
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }

    /**
     * Returns the latitude coordinate of the scooter at the time of recording.
     *
     * @return the latitude in decimal degrees
     */
    public double getLatitude() { return latitude; }

    /**
     * Sets the latitude coordinate of the scooter at the time of recording.
     *
     * @param latitude the latitude in decimal degrees to assign
     */
    public void setLatitude(double latitude) { this.latitude = latitude; }

    /**
     * Returns the longitude coordinate of the scooter at the time of recording.
     *
     * @return the longitude in decimal degrees
     */
    public double getLongitude() { return longitude; }

    /**
     * Sets the longitude coordinate of the scooter at the time of recording.
     *
     * @param longitude the longitude in decimal degrees to assign
     */
    public void setLongitude(double longitude) { this.longitude = longitude; }

    /**
     * Returns whether the scooter was in transit at the time of recording.
     *
     * @return {@code true} if the scooter was moving; {@code false} if stationary
     */
    public boolean isInTransit() { return inTransit; }

    /**
     * Sets whether the scooter was in transit at the time of recording.
     *
     * @param inTransit {@code true} if the scooter was moving; {@code false} if stationary
     */
    public void setInTransit(boolean inTransit) { this.inTransit = inTransit; }

    /**
     * Returns the ID of the nearest charging station to the scooter's recorded position.
     *
     * @return the nearest station ID, or {@code null} if no station is associated
     */
    public Integer getNearestStationId() { return nearestStationId; }

    /**
     * Sets the ID of the nearest charging station to the scooter's recorded position.
     *
     * @param nearestStationId the nearest station ID to assign, or {@code null} if none
     */
    public void setNearestStationId(Integer nearestStationId) { this.nearestStationId = nearestStationId; }

    /**
     * Returns the date and time at which this GPS log entry was recorded.
     *
     * @return the recorded timestamp as a {@link LocalDateTime}
     */
    public LocalDateTime getRecordedAt() { return recordedAt; }

    /**
     * Sets the date and time at which this GPS log entry was recorded.
     *
     * @param recordedAt the recorded timestamp to assign
     */
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}