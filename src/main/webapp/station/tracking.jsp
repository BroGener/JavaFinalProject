<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        nav a { margin-right: 12px; }
        table { border-collapse: collapse; width: 100%; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        .card { border: 1px solid #ddd; padding: 16px; margin: 12px 0; }
        .muted { color: #666; }
        input, select { padding: 6px; margin: 4px 0; width: 280px; }
    </style>
</head>
<script>
    setTimeout(function() {
        location.reload();
    }, 15000); // refresh every 3 seconds
</script>
<body>
<nav>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/register">Register</a>
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/profile">Profile</a>
    <a href="${pageContext.request.contextPath}/scooters">Scooters</a>
    <a href="${pageContext.request.contextPath}/stations">Stations</a>
    <a href="${pageContext.request.contextPath}/maintenance">Maintenance</a>
    <a href="${pageContext.request.contextPath}/reports">Reports</a>
    <a href="${pageContext.request.contextPath}/monthly-statement">Monthly Statement</a>
    <a href="${pageContext.request.contextPath}/tracking">Tracking</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<hr/>
<%@ page import="java.util.List" %>
<%@ page import="business.model.GPSLog" %>
<h1>Tracking</h1>
<div class="card">
    <p><strong>Latest scooter ID:</strong> ${latest.scooterId}</p>
    <p><strong>Coordinates:</strong> ${latest.latitude}, ${latest.longitude}</p>
    <p><strong>Nearest station:</strong> ${latest.nearestStationId}</p>
</div>
<h2>History</h2>
<table>
    <tr><th>Log ID</th><th>Scooter</th><th>Latitude</th><th>Longitude</th><th>In Transit</th></tr>
    <% List<GPSLog> history = (List<GPSLog>) request.getAttribute("history");
       for (GPSLog log : history) { %>
    <tr>
        <td><%= log.getLogId() %></td>
        <td><%= log.getScooterId() %></td>
        <td><%= log.getLatitude() %></td>
        <td><%= log.getLongitude() %></td>
        <td><%= log.isInTransit() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
