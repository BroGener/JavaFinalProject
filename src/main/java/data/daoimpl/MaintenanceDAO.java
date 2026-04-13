package data.daoimpl;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of {@link data.dao.MaintenanceDAO} backed by {@link MockDataStore}.
 * Used for development and testing in place of a real database.
 */
public class MaintenanceDAO implements data.dao.MaintenanceDAO {

    /**
     * Assigns a generated ID to the alert and adds it to the mock store.
     *
     * @param alert the alert to insert
     * @return the generated alert ID
     */
    public int insertAlert(MaintenanceAlert alert) {
        alert.setAlertId(MockDataStore.nextAlertId());
        MockDataStore.ALERTS.add(alert);
        return alert.getAlertId();
    }

    /**
     * Assigns a generated ID to the task and adds it to the mock store.
     *
     * @param task the task to insert
     * @return the generated task ID
     */
    public int insertTask(MaintenanceTask task) {
        task.setTaskId(MockDataStore.nextTaskId());
        MockDataStore.TASKS.add(task);
        return task.getTaskId();
    }

    /**
     * Returns a copy of all alerts in the mock store (regardless of status).
     *
     * @return a list of all {@link MaintenanceAlert}s
     */
    public List<MaintenanceAlert> findOpenAlerts() {
        return new ArrayList<>(MockDataStore.ALERTS);
    }

    /**
     * Filters tasks from the mock store by maintainer user ID.
     *
     * @param maintainerUserId the ID of the maintainer to filter by
     * @return a list of {@link MaintenanceTask}s assigned to the given maintainer
     */
    public List<MaintenanceTask> findTasksByMaintainerId(int maintainerUserId) {
        List<MaintenanceTask> list = new ArrayList<>();
        for (MaintenanceTask task : MockDataStore.TASKS) {
            if (task.getMaintainerUserId() != null && task.getMaintainerUserId() == maintainerUserId) {
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Status update is not implemented in the mock; always returns {@code true}.
     *
     * @param alertId the ID of the alert to update
     * @param status  the new status to assign
     * @return {@code true} always
     */
    public boolean updateAlertStatus(int alertId, String status) { return true; }

    /**
     * Status update is not implemented in the mock; always returns {@code true}.
     *
     * @param taskId the ID of the task to update
     * @param status the new status to assign
     * @return {@code true} always
     */
    public boolean updateTaskStatus(int taskId, String status) { return true; }
}