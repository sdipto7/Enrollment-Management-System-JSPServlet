<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'add'}">
        <form action="/addUser">
            <c:out value="Name: "/><input type="text" name="name"><br>
            <input type="submit" value="ADD"><br>
        </form>
    </c:when>

    <c:when test="${action == 'update'}">
        <form action="/updateUser">
            <input type="hidden" name="userId" value="${userId}"/>
            <c:out value="Name: "/><input type="text" name="name"><br>
            <input type="submit" value="UPDATE"><br>
        </form>
    </c:when>
</c:choose>
<br>
<form action="/LogoutButton">
    <input type="submit" value="Logout">
</form>
</body>
</html>
