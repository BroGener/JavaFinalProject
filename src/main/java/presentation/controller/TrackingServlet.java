package presentation.controller;

import business.service.ScooterService;
import business.service.impl.ScooterServiceImpl;
import business.model.Scooter;
import java.util.List;
import business.service.TrackingService;
import business.service.impl.TrackingServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tracking")
public class TrackingServlet extends BaseServlet {
    private final TrackingService trackingService = new TrackingServiceImpl();
    private business.service.ScooterService scooterService = new business.service.impl.ScooterServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            String role = (String) request.getSession().getAttribute("role");
           List<Scooter> scooters;
    if ("MAINTAINER".equals(role)) {
        scooters = scooterService.getAllScooters();
    } else {
        scooters = scooterService.getScootersBySponsor(userId);
    }

    if (scooters != null && !scooters.isEmpty()) {
        int scooterId = scooters.get(0).getScooterId();

        trackingService.simulateAndSave(scooterId);

        request.setAttribute("latest", trackingService.getLatestLocation(scooterId).orElse(null));
        request.setAttribute("history", trackingService.getLocationHistory(scooterId));
    } else {
        request.setAttribute("latest", null);
        request.setAttribute("history", null);
    }


            
            request.getRequestDispatcher("/station/tracking.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }}
    