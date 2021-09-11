<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Course Code</th>
        <th>Course Title</th>
    </tr>
    <c:forEach var="course" items="${course_list}">
        <c:url var="updateLink" value="/updateCourseLink">
            <c:param name="courseId" value="${course.id}"/>
        </c:url>
        <c:url var="deleteLink" value="/deleteCourse">
            <c:param name="courseId" value="${course.id}"/>
        </c:url>
        <tr>
            <td>${course.courseCode}</td>
            <td>${course.courseTitle}</td>
            <td>
                <a href="${updateLink}">Edit</a>
                |
                <a href="${deleteLink}"
                   onclick="if(!(confirm('Are you sure to delete the selected course ?'))) return false">
                    Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="/addCourseButton">
    <input type="submit" value="Add Course">
</form>
<br>
<form action="/LogoutButton">
    <input type="submit" value="Logout">
</form>
</body>
</html>