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
            .muted {
                color: #666;
            }
            input, select {
                padding: 6px;
                margin: 4px 0;
                width: 280px;
            }
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
        <hr/><hr/>
        <h1>Register Scooter</h1>
        <a href="${pageContext.request.contextPath}/scooters?action=add">+ Register New Scooter</a>
        <form method="post" action="${pageContext.request.contextPath}/scooters">
            <table>
                
                <tr><td>Make</td><td><input type="text" name="make" required/></td></tr>
                <tr><td>Model</td><td><input type="text" name="model" required/></td></tr>
                <tr><td>Color</td><td><input type="text" name="color"/></td></tr>
                <tr><td>Battery Capacity</td><td><input type="number" name="batteryCapacity" value="100"/></td></tr>
                <tr><td>Current Charge</td><td><input type="number" name="currentChargeLevel" value="100"/></td></tr>
                <tr><td>Station ID</td><td><input type="number" name="currentStationId" value="1"/></td></tr>
            </table>
            <br/>
            <input type="submit" value="Register Scooter"/>
        </form>
        <% if (request.getAttribute("message") != null) {%>
        <p><%= request.getAttribute("message")%></p>
        <% }%>
        </body>
</html>
