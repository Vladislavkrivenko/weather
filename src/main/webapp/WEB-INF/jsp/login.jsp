<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 05.09.2025
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">

    <label for="login" Login:>
        <input type="text" name="login" id="login" required>
    </label><br>
    <label for="password" Password:>
        <input type="text" name="password" id="password" required>
    </label><br>
    <button type="submit">Logins</button>
    <a href="${pageContext.request.contextPath}/register">
        <button type="button">Register</button>
    </a>
</form>
</body>
</html>
