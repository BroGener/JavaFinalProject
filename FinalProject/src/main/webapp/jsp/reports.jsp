<%-- 
    Document   : reports.jsp
    Created on : Apr 10, 2026, 3:06:32 p.m.
    Author     : biyababu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="transferobjects.ActivityCreditDTO" %>

<html>
<head>
    <title>Reports</title>
</head>
<body>

<h2>Activity Credits Report</h2>

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
    <th>User ID</th>
    <th>Activity</th>
    <th>Count</th>
    <th>Total Credit</th>
</tr>

<%
    List<ActivityCreditDTO> list =
        (List<ActivityCreditDTO>) request.getAttribute("credits");

    if (list == null || list.isEmpty()) {
%>
    <tr>
        <td colspan="4">No report data found</td>
    </tr>
<%
    } else {
        for (ActivityCreditDTO c : list) {
%>

<tr>
    <td><%= c.getUserId() %></td>
    <td><%= c.getActivityType() %></td>
    <td><%= c.getActivityCount() %></td>
    <td><%= c.getTotalCredit() %></td>
</tr>

<%
        }
    }
%>

</table>

</body>
</html>