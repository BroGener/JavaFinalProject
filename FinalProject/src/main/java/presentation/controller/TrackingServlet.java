package presentation.controller;

import business.model.GPSLog;
import business.service.TrackingService;
import business.service.TrackingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller servlet for GPS Tracking operations.
 *
 * <p>Handles the following actions via the {@code action} request parameter:
 * <ul>
 *   <li>{@code view}    — show latest location per scooter (FR-03)</li>
 *   <li>{@code history} — full log for one scooter by {@code scooterId}</li>
 *   <li>{@code update}  — (POST) save a new GPS reading</li>
 * </ul>
 *
 * @author Albin (Member C)
 */
@WebServlet("/tracking")
public class TrackingServlet extends HttpServlet {

    private TrackingService trackingService;

    @Override
    public void init() throws ServletException {
        trackingService = new TrackingService();
    }

    /**
     * Handles GET requests — display tracking view or scooter history.
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
        if (action == null) action = "view";

        try {
            if ("history".equals(action)) {
                int scooterId = Integer.parseInt(request.getParameter("scooterId"));
                List<GPSLog> logs = trackingService.getLocationHistory(scooterId);
                request.setAttribute("logs", logs);
                request.setAttribute("scooterId", scooterId);
            } else {
                // Default: show all latest logs (one per scooter)
                // Maintainer tracking report — all scooters latest location
                List<GPSLog> allLogs = trackingService.getLocationHistory(0); // 0 = placeholder
                request.setAttribute("logs", allLogs);
            }
            request.getRequestDispatcher("/station/tracking.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Tracking error: " + e.getMessage());
            request.getRequestDispatcher("/common/error.jsp").forward(request, response);
        }
    }

    /**
     * Handles POST requests — saves a new GPS location log for a scooter.
     *
     * @param request  the HTTP request (expects scooterId, latitude, longitude, inTransit, stationId)
     * @param response the HTTP response
     * @throws ServletException on servlet error
     * @throws IOException      on I/O error
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GPSLog log = new GPSLog();
            log.setScooterId(Integer.parseInt(request.getParameter("scooterId")));
            log.setLatitude(Double.parseDouble(request.getParameter("latitude")));
            log.setLongitude(Double.parseDouble(request.getParameter("longitude")));
            log.setInTransit(Boolean.parseBoolean(request.getParameter("inTransit")));
            String sid = request.getParameter("stationId");
            if (sid != null && !sid.isEmpty()) log.setStationId(Integer.parseInt(sid));

            trackingService.saveLocation(log);
            response.sendRedirect(request.getContextPath() + "/tracking?action=view");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Save location error: " + e.getMessage());
            request.getRequestDispatcher("/common/error.jsp").forward(request, response);
        }
    }
}
