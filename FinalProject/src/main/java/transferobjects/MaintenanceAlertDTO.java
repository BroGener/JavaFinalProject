/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;
import java.sql.Timestamp;

/**
 *
 * @author biyababu
 */
public class MaintenanceAlertDTO {


    private int alertId;
    private int scooterId;
    private String alertType;
    private String message;
    private String status;
    private Timestamp createdAt;

    public MaintenanceAlertDTO() {}

    public MaintenanceAlertDTO(int alertId, int scooterId, String alertType, String message, String status, Timestamp createdAt) {
        this.alertId = alertId;
        this.scooterId = scooterId;
        this.alertType = alertType;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public int getScooterId() {
        return scooterId;
    }

    public void setScooterId(int scooterId) {
        this.scooterId = scooterId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }   
}
