package presentation.controller;

import business.service.ScooterService;
import business.service.impl.ScooterServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/scooters")
public class ScooterServlet extends BaseServlet {
    private final ScooterService scooterService = new ScooterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("scooters", scooterService.getAllScooters());
            request.getRequestDispatcher("/scooter/scooter-list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
