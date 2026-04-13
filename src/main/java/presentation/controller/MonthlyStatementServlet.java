package presentation.controller;

import business.service.ReportService;
import business.service.impl.ReportServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/monthly-statement")
public class MonthlyStatementServlet extends BaseServlet {

    private final ReportService reportService = new ReportServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate now = LocalDate.now();
        try {
            Integer userId = (Integer) request.getSession().getAttribute(
                    "userId");
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            request.setAttribute("summary", reportService.getMonthlySummary(
                    userId, now.getYear(), now.getMonthValue()));
            request.setAttribute("credits", reportService.getCreditsByActivity(
                    userId, now.getYear(), now.getMonthValue()));
            request.getRequestDispatcher("/reports/monthly-summary.jsp").forward(
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
            Integer userId = (Integer) request.getSession().getAttribute(
                    "userId");
            try (java.sql.Connection con = data.datasource.DataSource.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(
                    "UPDATE account_transactions SET paid=true WHERE user_id=? AND transaction_type='DEBIT' AND paid=false")) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }
            response.sendRedirect(
                    request.getContextPath() + "/monthly-statement");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
