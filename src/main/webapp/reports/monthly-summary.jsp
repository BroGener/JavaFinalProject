<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
      <link rel="stylesheet" href="style.css"/>

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
<%@ page import="business.model.ActivityCredit" %>
<h1>Monthly Statement</h1>
<div class="card">
    <p><strong>User:</strong> ${summary.userId}</p>
    <p><strong>Period:</strong> ${summary.year}-${summary.month}</p>
    <p><strong>Trips:</strong> ${summary.tripCount}</p>
    <p><strong>Distance:</strong> ${summary.totalDistanceKm} km</p>
    <p><strong>Total:</strong> $${summary.totalAmount}</p>
</div>
<h2>Activity Breakdown</h2>
<table>
    <tr><th>Activity</th><th>Amount</th></tr>
    <% List<ActivityCredit> credits = (List<ActivityCredit>) request.getAttribute("credits");
       for (ActivityCredit credit : credits) { %>
    <tr>
        <td><%= credit.getActivityName() %></td>
        <td>$<%= credit.getAmount() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
