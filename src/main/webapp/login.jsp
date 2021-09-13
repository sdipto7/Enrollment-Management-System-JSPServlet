<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="loginForm" method="post">
    <c:out value="Name: "/><input type="text" name="name"><br><br>
    <c:out value="Password: "/><input type="password" name="password"><br><br>
    <input type="submit" value="login">
</form>
</body>
</html>