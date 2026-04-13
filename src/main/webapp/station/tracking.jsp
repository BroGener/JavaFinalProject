<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="business.model.GPSLog" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tracking</title>
    <link rel="stylesheet" href="style.css"/>

</head>

<script>
    setTimeout(function() {
        location.reload();
    }, 5000); // 🔥 faster refresh (5 sec)
</script>

<body>

<%
    String role = (String) session.getAttribute("role");
    GPSLog latest = (GPSLog) request.getAttribute("latest");
    List<GPSLog> history = (List<GPSLog>) request.getAttribute("history");
%>

<nav>
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/tracking">Tracking</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>

<hr/>

<h1>🚴 Scooter Tracking</h1>

<!-- 🔥 Role Display -->
<p><strong>Role:</strong> <%= role %></p>

<!-- 🔥 Role-based heading -->
<% if ("MAINTAINER".equals(role)) { %>
    <h3>All Scooters Tracking (Maintainer View)</h3>
<% } else if ("SPONSOR".equals(role)) { %>
    <h3>Your Scooters Tracking</h3>
<% } else { %>
    <h3>Tracking View</h3>
<% } %>

<!-- 🔥 Latest Info -->
<div class="card">
<% if (latest != null) { %>
    <p><strong>Scooter ID:</strong> <%= latest.getScooterId() %></p>
    <p><strong>Coordinates:</strong> <%= latest.getLatitude() %>, <%= latest.getLongitude() %></p>
    <p><strong>Nearest station:</strong> <%= latest.getNearestStationId() %></p>
<% } else { %>
    <p class="muted">No tracking data available.</p>
<% } %>
</div>

<h2>📊 History</h2>

<!-- 🔥 Empty check -->
<% if (history == null || history.isEmpty()) { %>
    <p class="muted">No scooters available for tracking.</p>
<% } else { %>

<table>
    <tr>
        <th>Log ID</th>
        <th>Scooter</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Status</th>
    </tr>

<%
    for (GPSLog log : history) {
%>
    <tr>
        <td><%= log.getLogId() %></td>
        <td><%= log.getScooterId() %></td>
        <td><%= log.getLatitude() %></td>
        <td><%= log.getLongitude() %></td>
        <td>
            <%= log.isInTransit() ? "🚴 In Transit" : "📍 At Station" %>
        </td>
    </tr>
<%
    }
%>

</table>

<% } %>

</body>
</html>