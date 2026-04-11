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
* handles requests for viewing sponsor and maintainer credits.
*/
@WebServlet("/credits")
public class CreditsServlet extends HttpServlet {

    private Report service;
    
    @Override
    public void init() {

        Connection conn =
                (Connection) getServletContext().getAttribute("DBConnection");

        ReportDAO dao = new MySQLReportDAO(conn);

      
        service = new Report(dao);
    }
   /**
     * Handles HTTP GET requests to retrieve activity credit report.
     * 
     * 
     *  Call business layer to get credit data
     *  Store data in request scope
     *  Forward to credits.jsp for display
     * 
     * Error Handling:
     * - If any exception occurs, an error message is shown on the same page
     * 
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if servlet error occurs
     * @throws IOException if input/output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch activity credit data from business layer
            List<ActivityCreditDTO> credits =
                    service.getActivityCredits();
             // Store data in request scope
            request.setAttribute("credits", credits);
              // Forward to JSP page
            request.getRequestDispatcher("credits.jsp")
                   .forward(request, response);

        } catch (Exception e) {

           
            request.setAttribute("error", "Unable to load credits report.");

            request.getRequestDispatcher("credits.jsp")
                   .forward(request, response);
        }
    }
}
