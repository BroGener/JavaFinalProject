package business.model;
import data.dao.MaintenanceDAO;
import transferobjects.MaintenanceAlertDTO;
import transferobjects.MaintenanceTaskDTO;
import java.util.List;
/**
 *
 * @author biyababu
 */
public class Maintenance{
  private MaintenanceDAO maintenanceDAO;

    public Maintenance(MaintenanceDAO maintenanceDAO) {
        this.maintenanceDAO = maintenanceDAO;
    }

   /**
    * Retrieves all maintenance alerts
    * 
    * @return  list of alerts
    * @throws Exception  if database error occurs
    */
    public List<MaintenanceAlertDTO> getAllAlerts() throws Exception {
        return maintenanceDAO.getAllAlerts();
    }
   /**
    * Retrieves a specific alert by ID
    * 
    * @param alertId  alert identifier
    * @return MaintenanceAlertDTO object
    * @throws Exception   if not found or database error occurs
    */
    public MaintenanceAlertDTO getAlertById(int alertId) throws Exception {
        return maintenanceDAO.getAlertById(alertId);
    }
    /**
     *  Creates a new maintenance alert
     * 
     * @param alert alert object
     * @throws Exception  if validation fails
     */
    public void createAlert(MaintenanceAlertDTO alert) throws Exception {
         if (alert == null) {
            throw new Exception("Alert cannot be null");
        }
      
        if (alert.getStatus() == null || alert.getStatus().isEmpty()) {
            alert.setStatus("OPEN");
        }

        maintenanceDAO.addAlert(alert);
    }
    /**
     * Marks an alert as resolved
     * 
     * @param alertId alert identifier
     * @throws Exception if database error occurs
     */
    public void resolveAlert(int alertId) throws Exception {
        maintenanceDAO.updateAlertStatus(alertId, "RESOLVED");
    }

    /**
     * Retrieves all maintenance tasks
     * 
     * @return list of tasks
     * @throws Exception if database error occurs
     */
    public List<MaintenanceTaskDTO> getAllTasks() throws Exception {
        return maintenanceDAO.getAllTasks();
    }
    /**
     * Creates a new maintenance task
     * 
     * @param task task object
     * @throws Exception  if validation fails
     */
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
     /**
      *  Marks a task as completed
      * 
      * @param taskId task identifier
      * @throws Exception if database error occurs
      */
    public void completeTask(int taskId) throws Exception {
        maintenanceDAO.updateTaskStatus(taskId, "COMPLETED");
    }  
    /**
     * Updates task status manually
     * 
     * @param taskId  task ID
     * @param status new status
     * @throws Exception if invalid input or database error occurs
     */
    public void updateTaskStatus(int taskId, String status) throws Exception {
    if (status == null || status.isEmpty()) {
        throw new Exception("Invalid status");
    }
    
    maintenanceDAO.updateTaskStatus(taskId, status);
}
}