package business.service.impl;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import business.service.MaintenanceService;
import data.datasource.DAOFactory;
import java.util.List;

/**
 * Implementation of {@link MaintenanceService}.
 * Delegates all persistence operations to the DAO layer via {@link DAOFactory}.
 */
public class MaintenanceServiceImpl implements MaintenanceService {

    /**
     * @param alert the alert to create
     * @return the generated alert ID
     * @throws Exception if a data access error occurs
     */
    public int createAlert(MaintenanceAlert alert) throws Exception {
        return DAOFactory.getMaintenanceDAO().insertAlert(alert);
    }

    /**
     * @param task the task to schedule
     * @return the generated task ID
     * @throws Exception if a data access error occurs
     */
    public int scheduleTask(MaintenanceTask task) throws Exception {
        return DAOFactory.getMaintenanceDAO().insertTask(task);
    }

    /**
     * @return a list of all open {@link MaintenanceAlert}s
     * @throws Exception if a data access error occurs
     */
    public List<MaintenanceAlert> getOpenAlerts() throws Exception {
        return DAOFactory.getMaintenanceDAO().findOpenAlerts();
    }

    /**
     * @param maintainerUserId the ID of the maintainer user
     * @return a list of {@link MaintenanceTask}s assigned to the given maintainer
     * @throws Exception if a data access error occurs
     */
    public List<MaintenanceTask> getTasksByMaintainer(int maintainerUserId) throws Exception {
        return DAOFactory.getMaintenanceDAO().findTasksByMaintainerId(maintainerUserId);
    }

    /**
     * Creates a maintenance task without returning the generated ID.
     *
     * @param task the task to create
     * @throws Exception if a data access error occurs
     */
    public void createTask(MaintenanceTask task) throws Exception {
        DAOFactory.getMaintenanceDAO().insertTask(task);
    }

    /**
     * Marks a maintenance alert as {@code "RESOLVED"}.
     *
     * @param alertId the ID of the alert to resolve
     * @throws Exception if a data access error occurs
     */
    public void resolveAlert(int alertId) throws Exception {
        DAOFactory.getMaintenanceDAO().updateAlertStatus(alertId, "RESOLVED");
    }

    /**
     * @param taskId the ID of the task to update
     * @param status the new status to assign (e.g., {@code "IN_PROGRESS"}, {@code "COMPLETED"})
     * @throws Exception if a data access error occurs
     */
    public void updateTaskStatus(int taskId, String status) throws Exception {
        DAOFactory.getMaintenanceDAO().updateTaskStatus(taskId, status);
    }
}