<%-- 
    Document   : maintenance-alerts.jsp
    Created on : Apr 10, 2026, 3:02:58?p.m.
    Author     : biyababu
--%>

<%@ page import="java.util.List" %>
<%@ page import="transferobjects.MaintenanceAlertDTO" %>

<html>
<head>
    <title>Maintenance Alerts</title>
</head>
<body>

<h2>Maintenance Alerts</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p><%= error %></p>
<%
    }
%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Scooter</th>
        <th>Type</th>
        <th>Message</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

<%
    List<MaintenanceAlertDTO> alerts =
        (List<MaintenanceAlertDTO>) request.getAttribute("alerts");

    if (alerts == null || alerts.isEmpty()) {
%>
    <tr>
        <td colspan="6">No maintenance alerts found</td>
    </tr>
<%
    } else {
        for (MaintenanceAlertDTO a : alerts) {
%>

    <tr>
        <td><%= a.getAlertId() %></td>
        <td><%= a.getScooterId() %></td>
        <td><%= a.getAlertType() %></td>
        <td><%= a.getMessage() %></td>
        <td><%= a.getStatus() %></td>
        <td>
            <form action="maintenance" method="post">
                <input type="hidden" name="alertId" value="<%= a.getAlertId() %>"/>
                <select name="status">
                    <option value="OPEN">OPEN</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="RESOLVED">RESOLVED</option>
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
