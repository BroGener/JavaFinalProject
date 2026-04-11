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
            request.setAttribute("summary", reportService.getMonthlySummary(1, now.getYear(), now.getMonthValue()));
            request.setAttribute("credits", reportService.getCreditsByActivity(1, now.getYear(), now.getMonthValue()));
            request.getRequestDispatcher("/reports/monthly-summary.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
