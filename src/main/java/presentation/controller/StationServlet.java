package presentation.controller;

import business.model.ChargingStation;
import business.model.StationReport;
import business.service.StationService;
import business.service.impl.StationServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/stations")
public class StationServlet extends BaseServlet {

    private StationService stationService;

    @Override
    public void init() throws ServletException {
        stationService = new StationServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "details":
                    int id = Integer.parseInt(request.getParameter("stationId"));
                    Optional<ChargingStation> opt = stationService.getStationById(id);
                    if (opt.isPresent()) {
                        request.setAttribute("station", opt.get());
                        request.getRequestDispatcher("/station/station-details.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/stations");
                    }
                    break;
                case "report":
                    List<StationReport> reports = stationService.getStationReports();
                    request.setAttribute("reports", reports);
                    request.getRequestDispatcher("/station/station-report.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("stations", stationService.getAllStations());
                    request.getRequestDispatcher("/station/station-list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}