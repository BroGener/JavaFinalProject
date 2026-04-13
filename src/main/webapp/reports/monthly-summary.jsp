<%@ page import="java.util.List" %>
<%@ page import="business.model.ActivityCredit" %>
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
<%@ include file="/common/navbar.jsp" %>
<hr/>

<h1>Monthly Statement</h1>
<div class="card">
    <p><strong>User:</strong> ${sessionScope.userName}</p>
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
