/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentation.controller;
import business.model.Report;
import data.dao.ReportDAO;
import data.daoimpl.MySQLReportDAO;
import transferobjects.MonthlySummaryDTO;
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
*This servlet handles requests for generating a user's monthly financial summary.
*/
@WebServlet("/monthly")
public class MonthlyStatementServlet extends HttpServlet {

    private Report service;

    @Override
    public void init() {

        Connection conn =
                (Connection) getServletContext().getAttribute("DBConnection");

        ReportDAO dao = new MySQLReportDAO(conn);

       
        service = new Report(dao);
    }
    /**
     * Handles GET request to generate monthly summary
     * 
     * 
     *  Read user ID from request
     *  Validate input
     * Fetch summary from business layer
     *  Forward to JSP
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

          // Read user input
            String userIdStr = request.getParameter("userId");
          // Validate input
            if (userIdStr == null || userIdStr.isEmpty()) {
                request.setAttribute("error", "User ID is required");
                request.getRequestDispatcher("monthly-summary.jsp")
                       .forward(request, response);
                return;
            }

            int userId = Integer.parseInt(userIdStr);
             // Get summary from business layer
            MonthlySummaryDTO summary =
                    service.getMonthlySummary(userId);

        
                request.setAttribute("summary", summary);
            

            
            request.getRequestDispatcher("monthly-summary.jsp")
                   .forward(request, response);  
        
            

        } catch (Exception e) {

          
            request.setAttribute("error", "Unable to load monthly statement.");

            request.getRequestDispatcher("monthly-summary.jsp")
                   .forward(request, response);
        }
    }
}