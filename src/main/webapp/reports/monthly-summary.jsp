<%@ page import="java.util.List" %>
<%@ page import="business.model.ActivityCredit" %>
<%@ page import="business.model.MonthlySummary" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Final Project Mock App</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 24px;
            }
            nav a {
                margin-right: 12px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                margin-top: 12px;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }
            .card {
                border: 1px solid #ddd;
                padding: 16px;
                margin: 12px 0;
            }
        </style>
    </head>
    <body>
        <%@ include file="/common/navbar.jsp" %>
        <%
            MonthlySummary summary = (MonthlySummary) request.getAttribute(
                    "summary");
            List<ActivityCredit> credits = (List<ActivityCredit>) request.getAttribute(
                    "credits");
            String userRole = (String) session.getAttribute("userRole");
            double totalAmount = summary != null ? summary.getTotalAmount() : 0;
            int tripCount = summary != null ? summary.getTripCount() : 0;
            double totalMinutes = tripCount > 0 ? totalAmount / 5.00 : 0;
            if (totalMinutes < 0)
                totalMinutes = 0;
        %>
        <h1>Monthly Statement</h1>
        <div class="card">
            <p><strong>User:</strong> <%= session.getAttribute("userName")%></p>
            <p><strong>Period:</strong> <%= summary != null ? summary.getYear() + "-" + summary.getMonth() : ""%></p>
            <% double creditTotal = summary != null ? summary.getTotalDistanceKm() : 0;%>
            <% if ("USER".equals(userRole)) {%>
            <p><strong>Trips:</strong> <%= tripCount%></p>
            <p><strong>Total Time:</strong> <%= String.format("%.1f", totalMinutes)%> min</p>
            <p><strong>Total Debit:</strong> $<%= String.format("%.2f", totalAmount)%></p>
            <% if (totalAmount > 0) { %>
            <form method="post" action="${pageContext.request.contextPath}/monthly-statement">
                <input type="submit" value="Pay Now" 
                       style="padding:8px 20px; background:#4CAF50; color:white; border:none; border-radius:4px; cursor:pointer;"/>
            </form>
            <% } %>
            <% }
            else if ("SPONSOR".equals(userRole)) {%>
            <p><strong>Total Earnings:</strong> $<%= String.format("%.2f", creditTotal)%></p>
            <% }
            else if ("MAINTAINER".equals(userRole)) {%>
            <p><strong>Tasks Completed:</strong> <%= tripCount%></p>
            <p><strong>Total Credits:</strong> $<%= String.format("%.2f", creditTotal)%></p>
            <% } %>
            <%
                boolean hasUnpaid = false;
                if (credits != null) {
                    for (ActivityCredit c
                            : credits) {
                        if ("DEBIT".equals(c.getTransactionType()) && !c.isPaid()) {
                            hasUnpaid = true;
                            break;
                        }
                    }
                }
                if ("USER".equals(userRole) && hasUnpaid) {%>
            <form method="post" action="${pageContext.request.contextPath}/monthly-statement">
                <input type="submit" value="Pay All ($<%= String.format("%.2f", totalAmount)%>)"
                       style="padding:8px 20px; background:#4CAF50; color:white; border:none; border-radius:4px; cursor:pointer;"/>
            </form>
            <% } %>
        </div>
        <h2>Activity Breakdown</h2>
        <table>
            <tr><th>Activity</th><th>Type</th><th>Amount</th><th>Status</th><th>Time</th></tr>
                    <% if (credits != null) {
                            for (ActivityCredit credit
                                    : credits) {%>
            <tr>
                <td><%= credit.getActivityName()%></td>
                <td><%= credit.getTransactionType()%></td>
                <td>$<%= String.format("%.2f", credit.getAmount())%></td>   
                <td><% if ("DEBIT".equals(credit.getTransactionType())) {%>
                <%= credit.isPaid() ? "✅ Paid" : "❌ Unpaid"%>
                <% }
                else { %>
                —
                <% }%>
                </td>
                <td><%= credit.getCreatedAt()%></td>
                

            </tr>
            <% }
                }%>
        </table>
</html>