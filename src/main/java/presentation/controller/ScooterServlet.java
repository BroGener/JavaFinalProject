package presentation.controller;

import business.service.ScooterService;
import business.service.impl.ScooterServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import business.model.Scooter;

@WebServlet("/scooters")
public class ScooterServlet extends BaseServlet {

    private final ScooterService scooterService = new ScooterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                request.getRequestDispatcher("/scooter/scooter-form.jsp").forward(
                        request, response);
            }
            else {
                request.setAttribute("scooters", scooterService.getAllScooters());
                request.getRequestDispatcher("/scooter/scooter-list.jsp").forward(
                        request, response);
            }
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vehicleNumber = "SC-" + new java.text.SimpleDateFormat(
                "yyyyMMddHHmmss").format(new java.util.Date());
        try {
            Scooter s = new Scooter();
            s.setSponsorUserId(1); // TODO: get from session

            s.setVehicleNumber(vehicleNumber);
            s.setMake(request.getParameter("make"));
            s.setModel(request.getParameter("model"));
            s.setColor(request.getParameter("color"));
            s.setBatteryCapacity(Integer.parseInt(request.getParameter(
                    "batteryCapacity")));
            s.setCurrentChargeLevel(Integer.parseInt(request.getParameter(
                    "currentChargeLevel")));
            s.setStatus("AVAILABLE");
            s.setCurrentStationId(Integer.parseInt(request.getParameter(
                    "currentStationId")));
            scooterService.registerScooter(s);
            response.sendRedirect(request.getContextPath() + "/scooters");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
