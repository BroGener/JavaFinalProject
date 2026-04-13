<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Final Project Mock App</title>
        <link rel="stylesheet" href="style.css"/>

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
<h1>Register</h1>
<p>${message}</p>
<form method="post" action="${pageContext.request.contextPath}/register">
    <div><input type="text" name="name" placeholder="Name"/></div>
    <div><input type="email" name="email" placeholder="Email"/></div>
    <div><input type="password" name="password" placeholder="Password"/></div>
    <div>
        <select name="role">
            <option value="USER">USER</option>
            <option value="MAINTAINER">MAINTAINER</option>
            <option value="SPONSOR">SPONSOR</option>
        </select>
    </div>
    <button type="submit">Create mock account</button>
</form>
</body>
</html>
