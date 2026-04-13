package presentation.controller;

import business.model.Scooter;
import business.service.ScooterService;
import business.service.impl.ScooterServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sponsor")
public class SponsorServlet extends BaseServlet {
    private final ScooterService scooterService = new ScooterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            List<Scooter> myScooters = scooterService.getScootersBySponsor(userId);
            request.setAttribute("scooters", myScooters);
            request.getRequestDispatcher("/scooter/sponsor-scooters.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}