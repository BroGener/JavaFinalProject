package presentation.controller;

import business.model.GPSLog;
import business.model.Scooter;
import business.service.ScooterService;
import business.service.TrackingService;
import business.service.impl.ScooterServiceImpl;
import business.service.impl.TrackingServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tracking")
public class TrackingServlet extends BaseServlet {
    private final TrackingService trackingService = new TrackingServiceImpl();
    private final ScooterService scooterService = new ScooterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Scooter> scooters = scooterService.getAllScooters();
            List<GPSLog> latestLogs = new ArrayList<>();
            for (Scooter s : scooters) {
                trackingService.getLatestLocation(s.getScooterId())
                    .ifPresent(latestLogs::add);
            }
            request.setAttribute("scooters", scooters);
            request.setAttribute("latestLogs", latestLogs);
            request.getRequestDispatcher("/station/tracking.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}