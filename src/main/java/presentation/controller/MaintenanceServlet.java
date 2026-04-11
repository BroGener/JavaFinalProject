package presentation.controller;

import business.service.MaintenanceService;
import business.service.impl.MaintenanceServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintenance")
public class MaintenanceServlet extends BaseServlet {
    private final MaintenanceService maintenanceService = new MaintenanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("alerts", maintenanceService.getOpenAlerts());
            request.setAttribute("tasks", maintenanceService.getTasksByMaintainer(2));
            request.getRequestDispatcher("/maintenance/maintenance-tasks.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
