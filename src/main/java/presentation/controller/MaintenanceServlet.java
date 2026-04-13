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
import business.strategy.AccountContext;
import business.strategy.MaintainerCreditStrategy;

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
        String action = request.getParameter("action");
        Integer maintainerId = (Integer) request.getSession().getAttribute("userId");

        if ("complete".equals(action)) {
            int taskId = Integer.parseInt(request.getParameter("taskId"));

            // 更新任务状态
            maintenanceService.updateTaskStatus(taskId, "COMPLETED");

            // MaintainerCreditStrategy 计算积分
            AccountContext ctx = new AccountContext(0, 0, 1);
            double credit = new MaintainerCreditStrategy().calculate(ctx);

            // 插入 CREDIT
            try (java.sql.Connection con = data.datasource.DataSource.getConnection();
                 java.sql.PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO account_transactions(user_id, activity_name, amount, transaction_type) VALUES (?,?,?,?)")) {
                ps.setInt(1, maintainerId);
                ps.setString(2, "TASK_COMPLETE");
                ps.setDouble(3, credit);
                ps.setString(4, "CREDIT");
                ps.executeUpdate();
            }

        } else {
            // 原来的 assign task 逻辑
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
        }

        response.sendRedirect(request.getContextPath() + "/maintenance");
    } catch (Exception e) {
        throw new ServletException(e);
    }
}

}
