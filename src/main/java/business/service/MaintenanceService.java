package business.service;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import java.util.List;

/**
 * Service interface for managing scooter maintenance alerts and tasks.
 *
 * @see MaintenanceAlert
 * @see MaintenanceTask
 */
public interface MaintenanceService {

    /**
     * Creates and persists a new maintenance alert.
     *
     * @param alert the alert to create
     * @return the generated alert ID
     * @throws Exception if a data access error occurs
     */
    int createAlert(MaintenanceAlert alert) throws Exception;

    /**
     * Schedules a new maintenance task and persists it.
     *
     * @param task the task to schedule
     * @return the generated task ID
     * @throws Exception if a data access error occurs
     */
    int scheduleTask(MaintenanceTask task) throws Exception;

    /**
     * Retrieves all maintenance alerts with an {@code "OPEN"} status.
     *
     * @return a list of open {@link MaintenanceAlert}s
     * @throws Exception if a data access error occurs
     */
    List<MaintenanceAlert> getOpenAlerts() throws Exception;

    /**
     * Retrieves all maintenance tasks assigned to a specific maintainer.
     *
     * @param maintainerUserId the ID of the maintainer user
     * @return a list of {@link MaintenanceTask}s assigned to the given maintainer
     * @throws Exception if a data access error occurs
     */
    List<MaintenanceTask> getTasksByMaintainer(int maintainerUserId) throws Exception;

    /**
     * Creates a maintenance task without returning the generated ID.
     *
     * @param task the task to create
     * @throws Exception if a data access error occurs
     */
    void createTask(MaintenanceTask task) throws Exception;

    /**
     * Marks a maintenance alert as resolved.
     *
     * @param alertId the ID of the alert to resolve
     * @throws Exception if a data access error occurs or the alert is not found
     */
    void resolveAlert(int alertId) throws Exception;

    /**
     * Updates the status of an existing maintenance task.
     *
     * @param taskId the ID of the task to update
     * @param status the new status to assign (e.g., {@code "IN_PROGRESS"}, {@code "COMPLETED"})
     * @throws Exception if a data access error occurs or the task is not found
     */
    void updateTaskStatus(int taskId, String status) throws Exception;
}