<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
        

 
    <link rel="stylesheet" href="style.css">
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
<h1>Login</h1>
<p class="muted">Use user@test.com / 1234, maintainer@test.com / 1234, or sponsor@test.com / 1234.</p>
<p>${message}</p>
<form method="post" action="${pageContext.request.contextPath}/login">
    <div><input type="email" name="email" placeholder="Email"/></div>
    <div><input type="password" name="password" placeholder="Password"/></div>
    <button type="submit">Login</button>
</form>
</body>
</html>
