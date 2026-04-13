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
<%@ page import="java.util.List" %>
<%@ page import="business.model.Scooter" %>
<%@ page import="business.model.ChargingStation" %>
<h1>Dashboard</h1>
<p>Mock dashboard loaded successfully.</p>
<div class="card">
    <h3>Scooter count: ${scooters.size()}</h3>
    <h3>Station count: ${stations.size()}</h3>
</div>
</body>
</html>
