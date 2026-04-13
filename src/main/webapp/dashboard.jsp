<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
<link rel="stylesheet" href="styles.css">
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
