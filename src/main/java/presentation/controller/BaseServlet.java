package presentation.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import util.Constants;

public abstract class BaseServlet extends HttpServlet {

    protected Integer getLoggedInUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null ? null : (Integer) session.getAttribute(Constants.SESSION_USER_ID);
    }

    protected String getLoggedInUserRole(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null ? null : (String) session.getAttribute(Constants.SESSION_USER_ROLE);
    }

    protected boolean isLoggedIn(HttpServletRequest request) {
        return getLoggedInUserId(request) != null;
    }
}
