<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Enrollment</title>
</head>
<body>
<c:choose>
    <c:when test="${action == 'add'}">
        <form action="/addEnrollment">
            <c:out value="User's Name: "/><input type="text" name="name"><br>
            <c:out value="Course Code: "/><input type="text" name="courseCode"><br>
            <input type="submit" value="ADD"><br>
        </form>
    </c:when>

    <c:when test="${action == 'update'}">
        <form action="/updateEnrollment">
            <input type="hidden" name="enrollmentId" value="${enrollmentId}"/>
            <c:out value="User's Name: "/><input type="text" name="name"><br>
            <c:out value="Course Code: "/><input type="text" name="courseCode"><br>
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
