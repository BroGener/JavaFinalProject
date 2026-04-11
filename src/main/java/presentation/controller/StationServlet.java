package presentation.controller;

import business.service.StationService;
import business.service.impl.StationServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stations")
public class StationServlet extends BaseServlet {
    private final StationService stationService = new StationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("stations", stationService.getAllStations());
            request.getRequestDispatcher("/station/station-list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
