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
<%@ page import="business.model.ChargingStation" %>
<h1>Stations</h1>
<table>
    <tr><th>ID</th><th>Name</th><th>Location</th><th>Capacity</th><th>Available Slots</th></tr>
    <% List<ChargingStation> stations = (List<ChargingStation>) request.getAttribute("stations");
       for (ChargingStation station : stations) { %>
    <tr>
        <td><%= station.getStationId() %></td>
        <td><%= station.getStationName() %></td>
        <td><%= station.getLocation() %></td>
        <td><%= station.getCapacity() %></td>
        <td><%= station.getAvailableSlots() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
