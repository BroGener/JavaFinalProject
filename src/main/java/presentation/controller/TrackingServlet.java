package presentation.controller;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            int scooterId = userId;
            trackingService.simulateAndSave(scooterId);
            request.setAttribute("latest", trackingService.getLatestLocation(scooterId).orElse(null));
            request.setAttribute("history", trackingService.getLocationHistory(scooterId));
            
            request.getRequestDispatcher("/station/tracking.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
