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
        <hr/><hr/>
        <h1>Register Scooter</h1>
        
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
