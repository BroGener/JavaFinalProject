package presentation.controller;

import business.service.ScooterService;
import business.service.StationService;
import business.service.impl.ScooterServiceImpl;
import business.service.impl.StationServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends BaseServlet {
    private final ScooterService scooterService = new ScooterServiceImpl();
    private final StationService stationService = new StationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("scooters", scooterService.getAllScooters());
            request.setAttribute("stations", stationService.getAllStations());
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
