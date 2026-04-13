<%@ page import="java.util.List" %>
<%@ page import="business.model.MaintenanceAlert" %>
<%@ page import="business.model.MaintenanceTask" %>
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
        <h1>Maintenance</h1>
        <h2>Open Alerts</h2>
        <table>
            <tr><th>ID</th><th>Scooter</th><th>Type</th><th>Status</th><th>Notes</th><th>Action</th></tr>
                    <% List<MaintenanceAlert> alerts = (List<MaintenanceAlert>) request.getAttribute(
                "alerts");
        for (MaintenanceAlert alert
                : alerts) {%>
            <tr>
                <td><%= alert.getAlertId()%></td>
                <td><%= alert.getScooterId()%></td>
                <td><%= alert.getAlertType()%></td>
                <td><%= alert.getStatus()%></td>
                <td><%= alert.getNotes()%></td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/maintenance">
                        <input type="hidden" name="alertId" value="<%= alert.getAlertId()%>"/>
                        <input type="hidden" name="scooterId" value="<%= alert.getScooterId()%>"/>
                        <input type="hidden" name="alertType" value="<%= alert.getAlertType()%>"/>
                        <input type="submit" value="Assign Task"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <h2>My Tasks</h2>
        <table>
            <tr><th>ID</th><th>Scooter</th><th>Description</th><th>Status</th><th>Due Date</th></tr>
                    <% List<MaintenanceTask> tasks = (List<MaintenanceTask>) request.getAttribute(
                "tasks");
        for (MaintenanceTask task
                : tasks) {%>
            <tr>
                <td><%= task.getTaskId()%></td>
                <td><%= task.getScooterId()%></td>
                <td><%= task.getDescription()%></td>
                <td><%= task.getStatus()%></td>
                <td><%= task.getDueDate()%></td>
                <td>
                    <% if ("PENDING".equals(task.getStatus())) {%>
                    <form method="post" action="${pageContext.request.contextPath}/maintenance">
                        <input type="hidden" name="action" value="complete"/>
                        <input type="hidden" name="taskId" value="<%= task.getTaskId()%>"/>
                        <input type="submit" value="Complete"/>
                    </form>
                    <% } %>
                </td>
            </tr>
            <% }%>
        </table>
    </body>
</html>