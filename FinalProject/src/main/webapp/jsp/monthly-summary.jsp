<%-- 
    Document   : monthly-summary.jsp
    Created on : Apr 10, 2026, 3:07:45 p.m.
    Author     : biyababu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="transferobjects.MonthlySummaryDTO" %>

<html>
<head>
    <title>Monthly Summary</title>
</head>
<body>

<h2>Monthly Summary</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p><%= error %></p>
<%
    }
%>

<form action="monthly" method="get">
    Enter User ID: <input type="text" name="userId"/>
    <input type="submit" value="View"/>
</form>

<hr>

<%
    MonthlySummaryDTO s =
        (MonthlySummaryDTO) request.getAttribute("summary");

    if (s != null) {
%>

<p>Total Credits: <%= s.getTotalCredits() %></p>
<p>Total Debits: <%= s.getTotalDebits() %></p>
<p><b>Amount Due: <%= s.getAmountDue() %></b></p>

<%
    }
%>

</body>
</html>
