<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 25.09.2025
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">

    <label for="login"> Login:
        <input id="login" type="text" name="login" required>
    </label><br>
    <label for="password">Password:
        <input id="password" type="text" name="password" required>
    </label><br>
    <button type="submit">Logins</button>
    <a href="${pageContext.request.contextPath}/register">
        <button type="button">Register</button>
    </a>
</form>
</body>
</html>
