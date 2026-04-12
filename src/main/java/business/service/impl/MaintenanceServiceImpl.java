package business.service.impl;

import business.model.MaintenanceAlert;
import business.model.MaintenanceTask;
import business.service.MaintenanceService;
import data.datasource.DAOFactory;
import java.util.List;

public class MaintenanceServiceImpl implements MaintenanceService {
    public int createAlert(MaintenanceAlert alert) throws Exception { return DAOFactory.getMaintenanceDAO().insertAlert(alert); }
    public int scheduleTask(MaintenanceTask task) throws Exception { return DAOFactory.getMaintenanceDAO().insertTask(task); }
    public List<MaintenanceAlert> getOpenAlerts() throws Exception { return DAOFactory.getMaintenanceDAO().findOpenAlerts(); }
    public List<MaintenanceTask> getTasksByMaintainer(int maintainerUserId) throws Exception { return DAOFactory.getMaintenanceDAO().findTasksByMaintainerId(maintainerUserId); }
}
