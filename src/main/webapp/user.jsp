<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action="/user">
    <input type="hidden" name="userId" value="${userId}"/>
    <input type="hidden" value="${action}" name="action">
    <label>
        Name: <input type="text" name="name">
    </label><br><br>
    <c:choose>
        <c:when test="${action == 'add'}">
            <input type="submit" value="Add"/><br><br>
        </c:when>
        <c:otherwise>
            <input type="submit" value="Update"><br><br>
        </c:otherwise>
    </c:choose>
</form>

<br>
<c:url var="logoutLink" value="/logout">
</c:url>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>
