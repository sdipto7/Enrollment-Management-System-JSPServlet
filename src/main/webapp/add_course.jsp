<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/addCourse">
    Course Code:<input type="text" name="courseCode"><br>
    Course Title:<input type="text" name="courseTitle"><br>
    <input type="submit" value="add">
</form>
<form action="/LogoutButton">
    <input type="submit" value="Logout">
</form>
</body>
</html>
