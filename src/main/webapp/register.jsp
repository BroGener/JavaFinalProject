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
