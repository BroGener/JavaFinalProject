package presentation.controller;

import business.service.ScooterService;
import business.service.impl.ScooterServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import business.model.Scooter;
import business.observer.BatteryObserver;
import business.observer.ScooterMonitor;
import java.util.List;
import business.strategy.AccountContext;
import business.strategy.UserDebitStrategy;
import business.strategy.SponsorCreditStrategy;
import business.observer.ScooterMonitor;
import business.observer.BatteryObserver;
import business.observer.WearObserver;
import business.model.GPSLog;
import business.model.ChargingStation;
import java.util.List;
import data.datasource.DAOFactory;

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
            else if ("unlock".equals(action)) {
                int scooterId = Integer.parseInt(request.getParameter(
                        "scooterId"));
                scooterService.updateScooterStatus(scooterId, "IN_USE");
                request.getSession().setAttribute("rideStart_" + scooterId,
                        System.currentTimeMillis());
                // 改成跳转到骑行页面而不是 scooter 列表
                response.sendRedirect(
                        request.getContextPath() + "/scooter/riding.jsp?scooterId=" + scooterId);
            }
            else if ("return".equals(action)) {
                int scooterId = Integer.parseInt(request.getParameter(
                        "scooterId"));
                double[] coords = util.GPSUtils.randomLocation();

                // 
                Long startTime = (Long) request.getSession().getAttribute(
                        "rideStart_" + scooterId);
                double minutesUsed = startTime != null
                        ? (System.currentTimeMillis() - startTime) : 5.0;
                List<ChargingStation> stations = new business.service.impl.StationServiceImpl().getAllStations();
                int nearestStationId = util.GPSUtils.findNearestStation(
                        coords[0], coords[1], stations);

                // Strategy 
                AccountContext ctx = new AccountContext(0, minutesUsed, 0);
                double userDebit = new UserDebitStrategy().calculate(ctx);
                double sponsorCredit = new SponsorCreditStrategy().calculate(ctx);
                GPSLog gpsLog = new GPSLog();
                gpsLog.setScooterId(scooterId);
                gpsLog.setLatitude(coords[0]);
                gpsLog.setLongitude(coords[1]);
                gpsLog.setNearestStationId(nearestStationId);
                gpsLog.setInTransit(false); // returned = not in transit
                DAOFactory.getTrackingDAO().insert(gpsLog);

                // 
                userDebit = Math.round(userDebit);

                // userId
                Integer userId = (Integer) request.getSession().getAttribute(
                        "userId");
                if (userId == null) {
                    userId = 1;
                }

                //  User DEBIT
                try (java.sql.Connection con = data.datasource.DataSource.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO account_transactions(user_id, activity_name, amount, transaction_type) VALUES (?,?,?,?)")) {
                    ps.setInt(1, userId);
                    ps.setString(2, "SCOOTER_USE");
                    ps.setDouble(3, userDebit);
                    ps.setString(4, "DEBIT");
                    ps.executeUpdate();
                }

                //  Sponsor CREDIT（找到这个 scooter 的 sponsor）
                java.util.Optional<Scooter> scooterOpt = scooterService.getScooterById(
                        scooterId);
                if (scooterOpt.isPresent() && scooterOpt.get().getSponsorUserId() != null) {
                    int sponsorId = scooterOpt.get().getSponsorUserId();
                    try (java.sql.Connection con = data.datasource.DataSource.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(
                            "INSERT INTO account_transactions(user_id, activity_name, amount, transaction_type) VALUES (?,?,?,?)")) {
                        ps.setInt(1, sponsorId);
                        ps.setString(2, "SPONSOR_CREDIT");
                        ps.setDouble(3, sponsorCredit);
                        ps.setString(4, "CREDIT");
                        ps.executeUpdate();
                    }
                }

                //  AVAILABLE
                scooterService.updateScooterStatus(scooterId, "AVAILABLE");

                //  Observer
                ScooterMonitor monitor = new ScooterMonitor();
                monitor.addObserver(new BatteryObserver());
                monitor.addObserver(new WearObserver());
                int chargeLevel = scooterOpt.isPresent()
                        ? scooterOpt.get().getCurrentChargeLevel() : 100;
                monitor.notifyObservers(scooterId, chargeLevel,
                        minutesUsed / 60.0);

                request.getSession().removeAttribute("rideStart_" + scooterId);
                response.sendRedirect(request.getContextPath() + "/scooters");
            }
            else if ("byStation".equals(action)) {
                int stationId = Integer.parseInt(request.getParameter(
                        "stationId"));
                List<Scooter> scooters = scooterService.getScootersByStation(
                        stationId);
                request.setAttribute("scooters", scooters);
                request.setAttribute("stationId", stationId);
                request.getRequestDispatcher("/scooter/scooter-list.jsp").forward(
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
            Integer sponsorId = (Integer) request.getSession().getAttribute(
                    "userId");
            s.setSponsorUserId(sponsorId);

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
