<%@ page import="java.util.List" %>
<%@ page import="business.model.Scooter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<h1>Scooters</h1>
<table>
    <tr><th>ID</th><th>Vehicle</th><th>Make</th><th>Model</th><th>Status</th><th>Charge</th><th>Action</th></tr>
    <% List<Scooter> scooters = (List<Scooter>) request.getAttribute("scooters");
       String userRole = (String) session.getAttribute("userRole");
       for (Scooter scooter : scooters) { %>
    <tr>
        <td><%= scooter.getScooterId() %></td>
        <td><%= scooter.getVehicleNumber() %></td>
        <td><%= scooter.getMake() %></td>
        <td><%= scooter.getModel() %></td>
        <td><%= scooter.getStatus() %></td>
        <td><%= scooter.getCurrentChargeLevel() %>%</td>
        <td>
            <% if ("USER".equals(userRole)) { %>
                <% if ("AVAILABLE".equals(scooter.getStatus())) { %>
                    <a class="btn-green" href="${pageContext.request.contextPath}/scooters?action=unlock&scooterId=<%= scooter.getScooterId() %>">Unlock</a>
                <% } else if ("IN_USE".equals(scooter.getStatus())) { %>
                    <a class="btn-red" href="${pageContext.request.contextPath}/scooters?action=return&scooterId=<%= scooter.getScooterId() %>">Return</a>
                <% } %>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>