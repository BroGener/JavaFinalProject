<%@ page import="java.util.List" %>
<%@ page import="business.model.Scooter" %>
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
        a.btn-green { padding: 4px 10px; background: #4CAF50; color: white; text-decoration: none; border-radius: 4px; }
        a.btn-red { padding: 4px 10px; background: #f44336; color: white; text-decoration: none; border-radius: 4px; }
    </style>
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