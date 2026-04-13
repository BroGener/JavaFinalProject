<%@ page import="java.util.List" %>
<%@ page import="business.model.MaintenanceAlert" %>
<%@ page import="business.model.MaintenanceTask" %>
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
        input, select { padding: 6px; margin: 4px 0; width: 280px; }
        input[type=submit] { background: #4CAF50; color: white; border: none; 
                             padding: 4px 10px; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<h1>Maintenance</h1>
<h2>Open Alerts</h2>
<table>
    <tr><th>ID</th><th>Scooter</th><th>Type</th><th>Status</th><th>Notes</th><th>Action</th></tr>
    <% List<MaintenanceAlert> alerts = (List<MaintenanceAlert>) request.getAttribute("alerts");
       for (MaintenanceAlert alert : alerts) { %>
    <tr>
        <td><%= alert.getAlertId() %></td>
        <td><%= alert.getScooterId() %></td>
        <td><%= alert.getAlertType() %></td>
        <td><%= alert.getStatus() %></td>
        <td><%= alert.getNotes() %></td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/maintenance">
                <input type="hidden" name="alertId" value="<%= alert.getAlertId() %>"/>
                <input type="hidden" name="scooterId" value="<%= alert.getScooterId() %>"/>
                <input type="hidden" name="alertType" value="<%= alert.getAlertType() %>"/>
                <input type="submit" value="Assign Task"/>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<h2>My Tasks</h2>
<table>
    <tr><th>ID</th><th>Scooter</th><th>Description</th><th>Status</th><th>Due Date</th></tr>
    <% List<MaintenanceTask> tasks = (List<MaintenanceTask>) request.getAttribute("tasks");
       for (MaintenanceTask task : tasks) { %>
    <tr>
        <td><%= task.getTaskId() %></td>
        <td><%= task.getScooterId() %></td>
        <td><%= task.getDescription() %></td>
        <td><%= task.getStatus() %></td>
        <td><%= task.getDueDate() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>