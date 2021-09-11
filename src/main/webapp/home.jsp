<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Home</title>
</head>
    <body>
        <h1>WELCOME ${userName}</h1>

        <c:choose>
            <c:when test="${role == admin}">
        <form action="/viewCourseButton">
            <input type="submit" value="View Courses">
        </form>

        <form action="/viewUserButton">
            <input type="submit" value="View Users">
        </form>

        <form action="/viewEnrollmentButton">
            <input type="submit" value="View Enrollments">
        </form>
            </c:when>

        </c:choose>

        <form action="/LogoutButton" method="post">
            <input type="submit" value="Logout">
        </form>

    </body>
</html>
