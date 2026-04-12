package presentation.controller;

import business.model.ChargingStation;
import business.model.StationReport;
import business.service.StationService;
import business.service.StationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller servlet for Charging Station operations.
 *
 * <p>Handles the following actions via the {@code action} request parameter:
 * <ul>
 *   <li>{@code list}    — show all stations (default)</li>
 *   <li>{@code details} — show one station by {@code stationId}</li>
 *   <li>{@code report}  — show scooter-distribution report (FR-06)</li>
 * </ul>
 *
 * @author Albin (Member C)
 */
@WebServlet("/station")
public class StationServlet extends HttpServlet {

    private StationService stationService;

    @Override
    public void init() throws ServletException {
        stationService = new StationService();
    }

    /**
     * Handles GET requests — display station list, details, or report.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws ServletException on servlet error
     * @throws IOException      on I/O error
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "details":
                    showDetails(request, response);
                    break;
                case "report":
                    showReport(request, response);
                    break;
                default:
                    showList(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Station error: " + e.getMessage());
            request.getRequestDispatcher("/common/error.jsp").forward(request, response);
        }
    }

    // ------------------------------------------------------------------ LIST
    /**
     * Loads all stations and forwards to station-list.jsp.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws Exception on any error
     */
    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<ChargingStation> stations = stationService.getAllStations();
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("/station/station-list.jsp").forward(request, response);
    }

    // ---------------------------------------------------------------- DETAILS
    /**
     * Loads one station by ID and forwards to station-details.jsp.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws Exception on any error
     */
    private void showDetails(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("stationId"));
        Optional<ChargingStation> opt = stationService.getStationById(id);
        if (opt.isPresent()) {
            request.setAttribute("station", opt.get());
            request.getRequestDispatcher("/station/station-details.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/station?action=list");
        }
    }

    // ---------------------------------------------------------------- REPORT
    /**
     * Generates a station distribution report and forwards to station-report.jsp.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @throws Exception on any error
     */
    private void showReport(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<StationReport> reports = stationService.getStationReports();
        request.setAttribute("reports", reports);
        request.getRequestDispatcher("/station/station-report.jsp").forward(request, response);
    }
}
