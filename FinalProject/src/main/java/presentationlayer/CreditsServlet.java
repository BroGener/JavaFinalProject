/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentationlayer;
import businesslayer.ReportService;
import datalayer.ReportDAO;
import datalayer.ReportDAOImpl;
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
@WebServlet("/credits")
public class CreditsServlet extends HttpServlet {

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

            List<ActivityCreditDTO> credits =
                    service.getActivityCredits();

            request.setAttribute("credits", credits);

            request.getRequestDispatcher("credits.jsp")
                   .forward(request, response);

        } catch (Exception e) {

           
            request.setAttribute("error", "Unable to load credits report.");

            request.getRequestDispatcher("credits.jsp")
                   .forward(request, response);
        }
    }
}
