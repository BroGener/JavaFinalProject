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
<%@ page import="business.model.MaintenanceAlert" %>
<%@ page import="business.model.MaintenanceTask" %>
<h1>Maintenance</h1>
<h2>Open Alerts</h2>
<table>
    <tr><th>ID</th><th>Scooter</th><th>Type</th><th>Status</th><th>Notes</th></tr>
    <% List<MaintenanceAlert> alerts = (List<MaintenanceAlert>) request.getAttribute("alerts");
       for (MaintenanceAlert alert : alerts) { %>
    <tr>
        <td><%= alert.getAlertId() %></td>
        <td><%= alert.getScooterId() %></td>
        <td><%= alert.getAlertType() %></td>
        <td><%= alert.getStatus() %></td>
        <td><%= alert.getNotes() %></td>
    </tr>
    <% } %>
</table>
<h2>Tasks</h2>
<table>
    <tr><th>ID</th><th>Scooter</th><th>Description</th><th>Priority</th><th>Status</th></tr>
    <% List<MaintenanceTask> tasks = (List<MaintenanceTask>) request.getAttribute("tasks");
       for (MaintenanceTask task : tasks) { %>
    <tr>
        <td><%= task.getTaskId() %></td>
        <td><%= task.getScooterId() %></td>
        <td><%= task.getDescription() %></td>
        <td><%= task.getPriority() %></td>
        <td><%= task.getStatus() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
