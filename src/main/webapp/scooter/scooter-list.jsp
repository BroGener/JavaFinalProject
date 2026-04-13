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
<%@ page import="business.model.Scooter" %>
<h1>Scooters</h1>
<table>
    <tr><th>ID</th><th>Vehicle</th><th>Make</th><th>Model</th><th>Status</th><th>Charge</th></tr>
    <% List<Scooter> scooters = (List<Scooter>) request.getAttribute("scooters");
       for (Scooter scooter : scooters) { %>
    <tr>
        <td><%= scooter.getScooterId() %></td>
        <td><%= scooter.getVehicleNumber() %></td>
        <td><%= scooter.getMake() %></td>
        <td><%= scooter.getModel() %></td>
        <td><%= scooter.getStatus() %></td>
        <td><%= scooter.getCurrentChargeLevel() %>%</td>
    </tr>
    <% } %>
    
</table>
    <a href="${pageContext.request.contextPath}/scooters?action=add">+ Register New Scooter</a>
</body>
</html>
