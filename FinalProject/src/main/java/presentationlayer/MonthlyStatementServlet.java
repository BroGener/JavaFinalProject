/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentationlayer;
import businesslayer.ReportService;
import datalayer.ReportDAO;
import datalayer.ReportDAOImpl;
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
@WebServlet("/monthly")
public class MonthlyStatementServlet extends HttpServlet {

    private ReportService service;

    @Override
    public void init() {

        Connection conn =
                (Connection) getServletContext().getAttribute("DBConnection");

        ReportDAO dao = new ReportDAOImpl(conn);

       
        service = new ReportService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

          
            String userIdStr = request.getParameter("userId");

            if (userIdStr == null || userIdStr.isEmpty()) {
                request.setAttribute("error", "User ID is required");
                request.getRequestDispatcher("monthly-summary.jsp")
                       .forward(request, response);
                return;
            }

            int userId = Integer.parseInt(userIdStr);

            MonthlySummaryDTO summary =
                    service.getMonthlySummary(userId);

        if (summary == null) {
                request.setAttribute("error", "No data found for this user");
            } else {
                request.setAttribute("summary", summary);
            }

            
            request.getRequestDispatcher("monthly-summary.jsp")
                   .forward(request, response);  
        
            

        } catch (Exception e) {

          
            request.setAttribute("error", "Unable to load monthly statement.");

            request.getRequestDispatcher("monthly-summary.jsp")
                   .forward(request, response);
        }
    }
}