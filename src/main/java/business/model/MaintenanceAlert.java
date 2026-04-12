package business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MaintenanceAlert implements Serializable {
    private Integer alertId;
    private Integer scooterId;
    private String alertType;
    private String status;
    private String notes;
    private LocalDateTime createdAt;

    public MaintenanceAlert() {}

    public MaintenanceAlert(Integer alertId, Integer scooterId, String alertType, String status, String notes, LocalDateTime createdAt) {
        this.alertId = alertId;
        this.scooterId = scooterId;
        this.alertType = alertType;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    public Integer getAlertId() { return alertId; }
    public void setAlertId(Integer alertId) { this.alertId = alertId; }
    public Integer getScooterId() { return scooterId; }
    public void setScooterId(Integer scooterId) { this.scooterId = scooterId; }
    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
