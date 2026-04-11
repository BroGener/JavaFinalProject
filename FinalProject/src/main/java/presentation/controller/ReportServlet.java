/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentation.controller;
import business.model.Report;
import data.dao.ReportDAO;
import data.daoimpl.MySQLReportDAO;
import transferobjects.ActivityCreditDTO;
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
*This servlet handles requests for generating activity credit reports
*/
@WebServlet("/reports")
public class ReportServlet extends HttpServlet {

    private Report service;

    @Override
    public void init() {

        Connection conn =
                (Connection) getServletContext().getAttribute("DBConnection");

        ReportDAO dao = new MySQLReportDAO(conn);

        
        service = new Report(dao);
    }
    /**
     * Handles GET request to generate activity credit report
     * 
     * 
     *  Fetch activity credit data from business layer
     *  Store in request scope
     *  Forward to reports.jsp for display
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Retrieve credit report data
            List<ActivityCreditDTO> credits = service.getActivityCredits();
             // Set attribute for JSP
            request.setAttribute("credits", credits);
              // Forward to JSP
            request.getRequestDispatcher("reports.jsp")
                   .forward(request, response);

        } catch (Exception e) {

            
            request.setAttribute("error", "Unable to load reports.");

            request.getRequestDispatcher("reports.jsp")
                   .forward(request, response);
        }
    }
}