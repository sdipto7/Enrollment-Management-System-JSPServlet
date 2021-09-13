<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
<h1><c:out value="WELCOME ${userName}"/></h1><br>

<form action="/viewCourseButton">
    <input type="submit" value="View Courses">
</form>
<br>

<form action="/viewUserButton">
    <input type="submit" value="View Users">
</form>
<br>

<form action="/viewEnrollmentButton">
    <input type="submit" value="View Enrollments">
</form>
<br>

<form action="/LogoutButton" method="post">
    <input type="submit" value="Logout">
</form>

</body>
</html>
