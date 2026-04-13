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
</body>
</html>
