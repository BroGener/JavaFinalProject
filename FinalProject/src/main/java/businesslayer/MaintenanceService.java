/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;
import datalayer.MaintenanceDAO;
import transferobjects.MaintenanceAlertDTO;
import transferobjects.MaintenanceTaskDTO;
import java.util.List;
/**
 *
 * @author biyababu
 */
public class MaintenanceService {
  private MaintenanceDAO maintenanceDAO;

    public MaintenanceService(MaintenanceDAO maintenanceDAO) {
        this.maintenanceDAO = maintenanceDAO;
    }

   
    public List<MaintenanceAlertDTO> getAllAlerts() throws Exception {
        return maintenanceDAO.getAllAlerts();
    }

    public MaintenanceAlertDTO getAlertById(int alertId) throws Exception {
        return maintenanceDAO.getAlertById(alertId);
    }

    public void createAlert(MaintenanceAlertDTO alert) throws Exception {
         if (alert == null) {
            throw new Exception("Alert cannot be null");
        }
      
        if (alert.getStatus() == null || alert.getStatus().isEmpty()) {
            alert.setStatus("OPEN");
        }

        maintenanceDAO.addAlert(alert);
    }

    public void resolveAlert(int alertId) throws Exception {
        maintenanceDAO.updateAlertStatus(alertId, "RESOLVED");
    }

    
    public List<MaintenanceTaskDTO> getAllTasks() throws Exception {
        return maintenanceDAO.getAllTasks();
    }

    public void createTask(MaintenanceTaskDTO task) throws Exception {
         if (task == null) {
            throw new Exception("Task cannot be null");
        }
        
        if (task.getScooterId() <= 0) {
            throw new Exception("Invalid scooter ID");
        }
          
        if (task.getMaintainerUserId() <= 0) {
            throw new Exception("Invalid maintainer ID");
        }
        
        if (task.getStatus() == null || task.getStatus().isEmpty()) {
            task.setStatus("SCHEDULED");
        }

        maintenanceDAO.addTask(task);
    }

    public void completeTask(int taskId) throws Exception {
        maintenanceDAO.updateTaskStatus(taskId, "COMPLETED");
    }  
    
    public void updateTaskStatus(int taskId, String status) throws Exception {
    if (status == null || status.isEmpty()) {
        throw new Exception("Invalid status");
    }
    
    maintenanceDAO.updateTaskStatus(taskId, status);
}
}
