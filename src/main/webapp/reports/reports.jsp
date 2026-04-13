<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
        <link rel="stylesheet" href="style.css"/>

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
<%@ page import="business.model.StationReport" %>
<h1>Reports</h1>
<table>
    <tr><th>Station</th><th>Total Scooters</th><th>Available</th><th>Low Battery</th></tr>
    <% List<StationReport> reports = (List<StationReport>) request.getAttribute("stationReports");
       for (StationReport report : reports) { %>
    <tr>
        <td><%= report.getStationName() %></td>
        <td><%= report.getTotalScooters() %></td>
        <td><%= report.getAvailableScooters() %></td>
        <td><%= report.getLowBatteryScooters() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
