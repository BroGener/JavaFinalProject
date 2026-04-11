package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class SessionUtils {
    private SessionUtils() {}

    public static void login(HttpServletRequest request, Integer userId, String role, String userName) {
        HttpSession session = request.getSession(true);
        session.setAttribute(Constants.SESSION_USER_ID, userId);
        session.setAttribute(Constants.SESSION_USER_ROLE, role);
        session.setAttribute(Constants.SESSION_USER_NAME, userName);
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
