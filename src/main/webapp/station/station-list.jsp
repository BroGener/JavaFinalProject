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
