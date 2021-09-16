/**
* @author rumi.dipto
* @since 9/10/21
*/
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form action="loginForm" method="post">
    <label> Username: <input type="text" name="userName"></label><br><br>
    <label> Password: <input type="password" name="password"></label><br><br>
    <input type="submit" value="login">
</form>
</body>
</html>