/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.dao;
import java.util.List;
import transferobjects.MaintenanceAlertDTO;
import transferobjects.MaintenanceTaskDTO;
/**
 *
 * @author biyababu
 */
public interface MaintenanceDAO {
    //gets all maintenance alerts 
  List<MaintenanceAlertDTO> getAllAlerts() throws Exception;
    
  MaintenanceAlertDTO getAlertById(int alertId) throws Exception;
   
  void addAlert(MaintenanceAlertDTO alert) throws Exception;
  
  void updateAlertStatus(int alertId, String status) throws Exception;
    // gets all maintenance tasks
    List<MaintenanceTaskDTO> getAllTasks() throws Exception;
   
    void addTask(MaintenanceTaskDTO task) throws Exception;
    
    void updateTaskStatus(int taskId, String status) throws Exception;   
}
