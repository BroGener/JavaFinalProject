<%@ page import="java.util.List" %>
<%@ page import="business.model.StationReport" %>
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
<hr/>

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
