/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentation.controller;
import business.model.Maintenance;
import data.daoimpl.MySQLMaintenanceDAO;
import data.dao.MaintenanceDAO;
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
/*
*This servlet handles maintenance alert operations.
*/
@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {

    private Maintenance service;

    @Override
    public void init() {

        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");

        MaintenanceDAO dao = new MySQLMaintenanceDAO(conn);

       
        service = new Maintenance(dao);
    }
     /**
     * Handles GET request to display all maintenance alerts
     * 
     * 
     *  Fetch alerts from business layer
     *  Store in request scope
     *  Forward to JSP
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch all alerts
            List<MaintenanceAlertDTO> alerts = service.getAllAlerts();
            // Set attribute for JSP
            request.setAttribute("alerts", alerts);
            // Forward to JSP page
            request.getRequestDispatcher("maintenance-alerts.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            
            request.setAttribute("error", "Unable to load maintenance alerts.");
            request.getRequestDispatcher("maintenance-alerts.jsp")
                   .forward(request, response);
        }
    }
   /**
     * Handles POST request to update alert status
     * 
     * 
     *  Read alertId and status from request
     *  Validate inputs
     *  Update alert status using business layer
     * Redirect to refresh data
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

           
            String idParam = request.getParameter("alertId");
            String status = request.getParameter("status");
            // Validate input
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