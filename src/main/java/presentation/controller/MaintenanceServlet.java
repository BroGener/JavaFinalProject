package presentation.controller;

import business.service.MaintenanceService;
import business.service.impl.MaintenanceServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import business.model.MaintenanceTask;
import java.time.LocalDate;

@WebServlet("/maintenance")
public class MaintenanceServlet extends BaseServlet {

    private final MaintenanceService maintenanceService = new MaintenanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer maintainerId = (Integer) request.getSession().getAttribute(
                    "userId");
            if (maintainerId == null) {
                maintainerId = 1;
            }
            request.setAttribute("alerts", maintenanceService.getOpenAlerts());
            request.setAttribute("tasks",
                    maintenanceService.getTasksByMaintainer(maintainerId));
            request.getRequestDispatcher("/maintenance/maintenance-tasks.jsp").forward(
                    request, response);
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer maintainerId = (Integer) request.getSession().getAttribute(
                    "userId");
            int alertId = Integer.parseInt(request.getParameter("alertId"));
            int scooterId = Integer.parseInt(request.getParameter("scooterId"));

            MaintenanceTask task = new MaintenanceTask();
            task.setScooterId(scooterId);
            task.setMaintainerUserId(maintainerId);
            task.setDescription("Fix: " + request.getParameter("alertType"));
            task.setStatus("PENDING");
            task.setDueDate(LocalDate.now().plusDays(1));

            maintenanceService.createTask(task);
            maintenanceService.resolveAlert(alertId);

            response.sendRedirect(request.getContextPath() + "/maintenance");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
