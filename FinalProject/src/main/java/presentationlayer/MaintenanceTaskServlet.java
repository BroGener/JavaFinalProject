/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentationlayer;
import businesslayer.MaintenanceService;
import datalayer.MaintenanceDAO;
import datalayer.MaintenanceDAOImpl;
import transferobjects.MaintenanceTaskDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author biyababu
 */
@WebServlet("/tasks")
public class MaintenanceTaskServlet extends HttpServlet {

    private MaintenanceService service;

    @Override
    public void init() {

        Connection conn =
                (Connection) getServletContext().getAttribute("DBConnection");

        MaintenanceDAO dao = new MaintenanceDAOImpl(conn);

        
        service = new MaintenanceService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            List<MaintenanceTaskDTO> tasks = service.getAllTasks();

            request.setAttribute("tasks", tasks);

            request.getRequestDispatcher("maintenance-tasks.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            
            request.setAttribute("error", "Unable to load maintenance tasks.");

            request.getRequestDispatcher("maintenance-tasks.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String action = request.getParameter("action");

            
            if ("add".equals(action)) {

                
                String alertIdStr = request.getParameter("alertId");
                String scooterIdStr = request.getParameter("scooterId");
                String maintainerIdStr = request.getParameter("maintainerUserId");
                String scheduledTimeStr = request.getParameter("scheduledTime");

                if (alertIdStr == null || scooterIdStr == null ||
                        maintainerIdStr == null || scheduledTimeStr == null) {

                    request.setAttribute("error", "Missing required fields");
                    request.getRequestDispatcher("maintenance-tasks.jsp")
                           .forward(request, response);
                    return;
                }

                MaintenanceTaskDTO task = new MaintenanceTaskDTO();

                task.setAlertId(Integer.parseInt(alertIdStr));
                task.setScooterId(Integer.parseInt(scooterIdStr));
                task.setMaintainerUserId(Integer.parseInt(maintainerIdStr));

               
                task.setScheduledTime(Timestamp.valueOf(scheduledTimeStr));

               
                task.setStatus(null);

                service.createTask(task);
            }

           
            if ("update".equals(action)) {

                String taskIdStr = request.getParameter("taskId");
                String status = request.getParameter("status");

                if (taskIdStr == null || status == null) {
                    request.setAttribute("error", "Invalid update data");
                    request.getRequestDispatcher("maintenance-tasks.jsp")
                           .forward(request, response);
                    return;
                }

               int taskId = Integer.parseInt(taskIdStr);
               service.updateTaskStatus(taskId, status);
            }

            response.sendRedirect("tasks");

        } catch (Exception e) {

           
            request.setAttribute("error", "Operation failed on task management.");
            request.getRequestDispatcher("maintenance-tasks.jsp")
                   .forward(request, response);
        }
    }
}
