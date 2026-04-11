package presentation.controller;

import business.model.User;
import business.service.AuthService;
import business.service.impl.AuthServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import util.SessionUtils;
import util.ValidationUtils;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
    private final AuthService authService = new AuthServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (ValidationUtils.isBlank(email) || ValidationUtils.isBlank(password)) {
            request.setAttribute("message", "Please enter email and password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        try {
            java.util.Optional<User> user = authService.login(email, password);
            if (user.isPresent()) {
                SessionUtils.login(request, user.get().getUserId(), user.get().getRole(), user.get().getName());
                response.sendRedirect(request.getContextPath() + "/dashboard");
            } else {
                request.setAttribute("message", "Mock login failed. Try user@test.com / 1234.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
