package presentation.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Constants;

@WebFilter(urlPatterns = {"/dashboard", "/profile", "/scooters", "/stations", "/maintenance", "/reports", "/monthly-statement", "/tracking"})
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) {}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        boolean loggedIn = session != null && session.getAttribute(Constants.SESSION_USER_ID) != null;
        if (!loggedIn) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(request, response);
    }
    public void destroy() {}
}
