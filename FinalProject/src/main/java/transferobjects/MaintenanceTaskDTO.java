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
public class MaintenanceTaskDTO {
 private int taskId;
    private int alertId;
    private int scooterId;
    private int maintainerUserId;
    private Timestamp scheduledTime;
    private Timestamp completedTime;
    private String status;

    public MaintenanceTaskDTO() {}

    public MaintenanceTaskDTO(int taskId,int alertId, int scooterId, int maintainerUserId, Timestamp scheduledTime,Timestamp completedTime, String status) {
        this.taskId = taskId;
        this.alertId = alertId;
        this.scooterId = scooterId;
        this.maintainerUserId = maintainerUserId;
        this.scheduledTime = scheduledTime;
        this.completedTime = completedTime;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public int getMaintainerUserId() {
        return maintainerUserId;
    }

    public void setMaintainerUserId(int maintainerUserId) {
        this.maintainerUserId = maintainerUserId;
    }

    public Timestamp getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Timestamp scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Timestamp getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(Timestamp completedTime) {
        this.completedTime = completedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   
}
