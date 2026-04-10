package data.dao;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import java.util.List;

public interface MaintenanceDAO {
    int insertAlert(MaintenanceAlert alert) throws Exception;
    int insertTask(MaintenanceTask task) throws Exception;
    List<MaintenanceAlert> findOpenAlerts() throws Exception;
    List<MaintenanceTask> findTasksByMaintainerId(int maintainerUserId) throws Exception;
    boolean updateAlertStatus(int alertId, String status) throws Exception;
    boolean updateTaskStatus(int taskId, String status) throws Exception;
}