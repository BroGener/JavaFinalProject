<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="business.model.Scooter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Scooters</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 24px; }
        table { border-collapse: collapse; width: 100%; margin-top: 12px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        a.btn { padding: 4px 10px; background: #4CAF50; color: white; 
                text-decoration: none; border-radius: 4px; }
    </style>
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<h1>My Scooters</h1>
<a class="btn" href="${pageContext.request.contextPath}/scooters?action=add">+ Register New Scooter</a>
<br/><br/>
<table>
    <tr>
        <th>ID</th><th>Vehicle</th><th>Make</th><th>Model</th>
        <th>Status</th><th>Charge</th>
    </tr>
    <% List<Scooter> scooters = (List<Scooter>) request.getAttribute("scooters");
       if (scooters != null && !scooters.isEmpty()) {
           for (Scooter s : scooters) { %>
    <tr>
        <td><%= s.getScooterId() %></td>
        <td><%= s.getVehicleNumber() %></td>
        <td><%= s.getMake() %></td>
        <td><%= s.getModel() %></td>
        <td><%= s.getStatus() %></td>
        <td><%= s.getCurrentChargeLevel() %>%</td>
    </tr>
    <% } } else { %>
    <tr><td colspan="6">No scooters registered yet.</td></tr>
    <% } %>
</table>
</body>
</html>