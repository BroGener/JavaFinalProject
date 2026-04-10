<%-- 
    Document   : maintenance-tasks.jsp
    Created on : Apr 10, 2026, 3:05:02 p.m.
    Author     : biyababu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="transferobjects.MaintenanceTaskDTO" %>

<html>
<head>
    <title>Maintenance Tasks</title>
</head>
<body>

<h2>Add Task</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p><%= error %></p>
<%
    }
%>

<form action="tasks" method="post">
    <input type="hidden" name="action" value="add"/>
    Alert ID: <input type="text" name="alertId"/><br/>
    Scooter ID: <input type="text" name="scooterId"/><br/>
    Maintainer ID: <input type="text" name="maintainerUserId"/><br/>
    Scheduled Time (YYYY-MM-DD HH:MM:SS):
    <input type="text" name="scheduledTime"/><br/>
    <input type="submit" value="Add Task"/>
</form>

<hr>

<h2>Task List</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Scooter</th>
    <th>Status</th>
    <th>Update</th>
</tr>

<%
    List<MaintenanceTaskDTO> tasks =
        (List<MaintenanceTaskDTO>) request.getAttribute("tasks");

    if (tasks == null || tasks.isEmpty()) {
%>
    <tr>
        <td colspan="4">No tasks available</td>
    </tr>
<%
    } else {
        for (MaintenanceTaskDTO t : tasks) {
%>

<tr>
    <td><%= t.getTaskId() %></td>
    <td><%= t.getScooterId() %></td>
    <td><%= t.getStatus() %></td>
    <td>
        <form action="tasks" method="post">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="taskId" value="<%= t.getTaskId() %>"/>

            <select name="status">
                <option value="PENDING">PENDING</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="COMPLETED">COMPLETED</option>
            </select>

            <input type="submit" value="Update"/>
        </form>
    </td>
</tr>

<%
        }
    }
%>

</table>

</body>
</html>