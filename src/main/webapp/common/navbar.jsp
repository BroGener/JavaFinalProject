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
<hr/><h1>common/navbar.jsp</h1><p>Placeholder page for integration testing.</p></body>
</html>
