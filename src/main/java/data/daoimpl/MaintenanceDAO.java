package data.daoimpl;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import data.datasource.MockDataStore;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO implements data.dao.MaintenanceDAO {
    public int insertAlert(MaintenanceAlert alert) {
        alert.setAlertId(MockDataStore.nextAlertId());
        MockDataStore.ALERTS.add(alert);
        return alert.getAlertId();
    }
    public int insertTask(MaintenanceTask task) {
        task.setTaskId(MockDataStore.nextTaskId());
        MockDataStore.TASKS.add(task);
        return task.getTaskId();
    }
    public List<MaintenanceAlert> findOpenAlerts() { return new ArrayList<MaintenanceAlert>(MockDataStore.ALERTS); }
    public List<MaintenanceTask> findTasksByMaintainerId(int maintainerUserId) {
        List<MaintenanceTask> list = new ArrayList<MaintenanceTask>();
        for (MaintenanceTask task : MockDataStore.TASKS) {
            if (task.getMaintainerUserId() != null && task.getMaintainerUserId() == maintainerUserId) list.add(task);
        }
        return list;
    }
    public boolean updateAlertStatus(int alertId, String status) { return true; }
    public boolean updateTaskStatus(int taskId, String status) { return true; }
}
