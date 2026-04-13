package business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a maintenance alert raised for a scooter.
 * <p>
 * A {@code MaintenanceAlert} records a specific maintenance issue detected for
 * a scooter, capturing the type of alert, its current resolution status, any
 * relevant notes, and the time it was created. These alerts are used to notify
 * maintainers of scooters requiring attention. This class implements
 * {@link Serializable} to support persistence and data transfer.
 * </p>
 */
public class MaintenanceAlert implements Serializable {

    /** The unique identifier for this maintenance alert. */
    private Integer alertId;

    /** The ID of the scooter this alert is raised for. */
    private Integer scooterId;

    /** The type of maintenance issue detected (e.g., {@code "LOW_BATTERY"}, {@code "MECHANICAL_FAULT"}). */
    private String alertType;

    /** The current resolution status of this alert (e.g., {@code "OPEN"}, {@code "RESOLVED"}). */
    private String status;

    /** Optional notes providing additional context or details about the alert. */
    private String notes;

    /** The date and time at which this alert was created. */
    private LocalDateTime createdAt;

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks that instantiate objects reflectively
     * (e.g., ORM or serialization libraries).
     * </p>
     */
    public MaintenanceAlert() {}

    /**
     * Constructs a fully initialized {@code MaintenanceAlert} with all fields specified.
     *
     * @param alertId   the unique identifier for this alert, or {@code null} if not yet persisted
     * @param scooterId the ID of the scooter this alert is raised for
     * @param alertType the type of maintenance issue (e.g., {@code "LOW_BATTERY"}, {@code "MECHANICAL_FAULT"})
     * @param status    the current resolution status (e.g., {@code "OPEN"}, {@code "RESOLVED"})
     * @param notes     optional additional notes or context for this alert
     * @param createdAt the date and time this alert was created
     */
    public MaintenanceAlert(Integer alertId, Integer scooterId, String alertType,
                            String status, String notes, LocalDateTime createdAt) {
        this.alertId = alertId;
        this.scooterId = scooterId;
        this.alertType = alertType;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    /**
     * Returns the unique identifier of this maintenance alert.
     *
     * @return the alert ID, or {@code null} if not yet persisted
     */
    public Integer getAlertId() { return alertId; }

    /**
     * Sets the unique identifier of this maintenance alert.
     *
     * @param alertId the alert ID to assign
     */
    public void setAlertId(Integer alertId) { this.alertId = alertId; }

    /**
     * Returns the ID of the scooter this alert is raised for.
     *
     * @return the scooter ID
     */
    public Integer getScooterId() { return scooterId; }

    /**
     * Sets the ID of the scooter this alert is raised for.
     *
     * @param scooterId the scooter ID to assign
     */
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }

    /**
     * Returns the type of maintenance issue detected.
     *
     * @return the alert type (e.g., {@code "LOW_BATTERY"}, {@code "MECHANICAL_FAULT"})
     */
    public String getAlertType() { return alertType; }

    /**
     * Sets the type of maintenance issue detected.
     *
     * @param alertType the alert type to assign (e.g., {@code "LOW_BATTERY"}, {@code "MECHANICAL_FAULT"})
     */
    public void setAlertType(String alertType) { this.alertType = alertType; }

    /**
     * Returns the current resolution status of this alert.
     *
     * @return the status (e.g., {@code "OPEN"}, {@code "RESOLVED"})
     */
    public String getStatus() { return status; }

    /**
     * Sets the current resolution status of this alert.
     *
     * @param status the status to assign (e.g., {@code "OPEN"}, {@code "RESOLVED"})
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns any additional notes or context for this alert.
     *
     * @return the notes string, or {@code null} if none provided
     */
    public String getNotes() { return notes; }

    /**
     * Sets additional notes or context for this alert.
     *
     * @param notes the notes to assign, or {@code null} if none
     */
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * Returns the date and time at which this alert was created.
     *
     * @return the creation timestamp as a {@link LocalDateTime}
     */
    public LocalDateTime getCreatedAt() { return createdAt; }

    /**
     * Sets the date and time at which this alert was created.
     *
     * @param createdAt the creation timestamp to assign
     */
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}