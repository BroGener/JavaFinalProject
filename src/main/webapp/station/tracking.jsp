<%@ page import="java.util.List" %>
<%@ page import="business.model.GPSLog" %>
<%@ page import="business.model.Scooter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GPS Tracking</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<h1>GPS Tracking</h1>
<h2>Latest Scooter Locations</h2>
<table>
    <tr>
        <th>Scooter ID</th>
        <th>Vehicle</th>
        <th>Status</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Nearest Station</th>
        <th>In Transit</th>
    </tr>
    <%
        List<Scooter> scooters = (List<Scooter>) request.getAttribute("scooters");
        List<GPSLog> latestLogs = (List<GPSLog>) request.getAttribute("latestLogs");
        for (Scooter s : scooters) {
            GPSLog log = null;
            for (GPSLog g : latestLogs) {
                if (g.getScooterId().equals(s.getScooterId())) { log = g; break; }
            }
    %>
    <tr>
        <td><%= s.getScooterId() %></td>
        <td><%= s.getVehicleNumber() %></td>
        <td><%= s.getStatus() %></td>
        <td><%= log != null ? String.format("%.6f", log.getLatitude()) : "N/A" %></td>
        <td><%= log != null ? String.format("%.6f", log.getLongitude()) : "N/A" %></td>
        <td><%= log != null ? "Station #" + log.getNearestStationId() : "N/A" %></td>
        <td><%= log != null ? (log.isInTransit() ? "Yes" : "No") : "N/A" %></td>
    </tr>
    <% } %>
</table>
</body>
</html>