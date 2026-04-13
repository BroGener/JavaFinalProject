<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="business.model.ChargingStation" %>
<%@ page import="business.model.StationReport" %>
<% String role = (String) session.getAttribute("userRole"); %>
<!DOCTYPE html>
<html>
<head><title>Select Station</title>
<style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    table { border-collapse: collapse; width: 100%; margin-top: 12px; }
    th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
    a.btn { padding: 4px 10px; background: #4CAF50; color: white; text-decoration: none; border-radius: 4px; }
</style>
</head>
<body>
<nav>
    <a href="${pageContext.request.contextPath}/stations">Stations</a>
    <a href="${pageContext.request.contextPath}/monthly-statement">Monthly Statement</a>
    <a href="${pageContext.request.contextPath}/profile">Profile</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>
<hr/>
<h1>Select a Charging Station</h1>
<table>
    <tr><th>Station</th><th>Location</th><th>Capacity</th><th>Available Scooters</th><th>Action</th></tr>
    <% List<ChargingStation> stations = (List<ChargingStation>) request.getAttribute("stations");
       for (ChargingStation st : stations) { %>
    <tr>
        <td><%= st.getStationName() %></td>
        <td><%= st.getLocation() %></td>
        <td><%= st.getCapacity() %></td>
        <td><%= st.getAvailableSlots() %></td>
        <td><a class="btn" href="${pageContext.request.contextPath}/scooters?action=byStation&stationId=<%= st.getStationId() %>">View Scooters</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>