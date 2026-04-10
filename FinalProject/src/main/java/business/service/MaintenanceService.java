package business.service;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import java.util.List;

public interface MaintenanceService {
    int createAlert(MaintenanceAlert alert) throws Exception;
    int scheduleTask(MaintenanceTask task) throws Exception;
    List<MaintenanceAlert> getOpenAlerts() throws Exception;
    List<MaintenanceTask> getTasksByMaintainer(int maintainerUserId) throws Exception;
}