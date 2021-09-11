<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'add'}">
        <form action="/addCourse">
            Course Code:<input type="text" name="courseCode"><br>
            Course Title:<input type="text" name="courseTitle"><br>
            <input type="submit" value="ADD"><br>
        </form>
    </c:when>

    <c:when test="${action == 'update'}">
        <form action="/updateCourse">
            <input type="hidden" name="courseId" value="${courseId}"/>
            Course Code:<input type="text" name="courseCode"><br>
            Course Title:<input type="text" name="courseTitle"><br>
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