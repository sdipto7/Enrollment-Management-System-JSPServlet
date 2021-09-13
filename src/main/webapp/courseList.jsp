<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Courses</title>
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
            <c:if test="${role == admin}">
                <td>
                    <a href="${updateLink}"><c:out value="Edit"/></a>
                    |
                    <a href="${deleteLink}"
                       onclick="if(!(confirm('Are you sure to delete the selected course ?'))) return false">
                        <c:out value="Delete"/></a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<br>
<c:choose>
    <c:when test="${role == admin}">
        <form action="/addCourseButton">
            <input type="submit" value="Add Course">
        </form>
        <br>
    </c:when></c:choose>

<c:url var="logoutLink" value="/logout">
</c:url>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>