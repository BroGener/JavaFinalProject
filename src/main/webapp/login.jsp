<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Login</h1>
<% if (request.getAttribute("message") != null) { %>
    <p style="color:red;"><%= request.getAttribute("message") %></p>
<% } %>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="email" name="email" placeholder="Email" required/>
    <input type="password" name="password" placeholder="Password" required/>
    <button type="submit">Login</button>
</form>
<p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register</a></p>
</body>
</html>