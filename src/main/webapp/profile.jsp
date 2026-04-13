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
<h1>Profile</h1>
<div class="card">
    <p><strong>ID:</strong> ${user.userId}</p>
    <p><strong>Name:</strong> ${user.name}</p>
    <p><strong>Email:</strong> ${user.email}</p>
    <p><strong>Role:</strong> ${user.role}</p>
</div>
</body>
</html>
