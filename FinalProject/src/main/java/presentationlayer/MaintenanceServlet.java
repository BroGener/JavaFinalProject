/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentationlayer;
import businesslayer.MaintenanceService;
import datalayer.MaintenanceDAOImpl;
import datalayer.MaintenanceDAO;
import transferobjects.MaintenanceAlertDTO;

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
@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {

    private MaintenanceService service;

    @Override
    public void init() {

        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        MaintenanceDAO dao = new MaintenanceDAOImpl(conn);

       
        service = new MaintenanceService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<MaintenanceAlertDTO> alerts = service.getAllAlerts();

            request.setAttribute("alerts", alerts);

            request.getRequestDispatcher("maintenance-alerts.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            
            request.setAttribute("error", "Unable to load maintenance alerts.");
            request.getRequestDispatcher("maintenance-alerts.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

           
            String idParam = request.getParameter("alertId");
            String status = request.getParameter("status");

            if (idParam == null || status == null || status.isEmpty()) {
                request.setAttribute("error", "Invalid input data");
                request.getRequestDispatcher("maintenance-alerts.jsp")
                       .forward(request, response);
                return;
            }

            int alertId = Integer.parseInt(idParam);

           if ("RESOLVED".equals(status)) {
           service.resolveAlert(alertId);
          }
            

            response.sendRedirect("maintenance");

        } catch (Exception e) {

           
            request.setAttribute("error", "Failed to update alert status.");
            request.getRequestDispatcher("maintenance-alerts.jsp")
                   .forward(request, response);
        }
    }
}