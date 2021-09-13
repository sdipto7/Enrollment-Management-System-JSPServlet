<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'add'}">
        <form action="/addCourse">
            <c:out value="Course Code: "/><input type="text" name="courseCode"><br>
            <c:out value="Course Title: "/><input type="text" name="courseTitle"><br>
            <input type="submit" value="ADD"><br>
        </form>
    </c:when>

    <c:when test="${action == 'update'}">
        <form action="/updateCourse">
            <input type="hidden" name="courseId" value="${courseId}"/>
            <c:out value="Course Code: "/><input type="text" name="courseCode"><br>
            <c:out value="Course Title: "/><input type="text" name="courseTitle"><br>
            <input type="submit" value="UPDATE"><br>
        </form>
    </c:when>
</c:choose>
<br>

<c:url var="logoutLink" value="/logout">
</c:url>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>