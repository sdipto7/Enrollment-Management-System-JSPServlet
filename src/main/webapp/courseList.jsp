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
    <c:forEach var="course" items="${courseList}">
        <c:url var="updateLink" value="/course">
            <c:param name="courseId" value="${course.id}"/>
            <c:param name="action" value="updateClick"/>
        </c:url>
        <c:url var="deleteLink" value="/course">
            <c:param name="courseId" value="${course.id}"/>
            <c:param name="action" value="delete"/>
        </c:url>
        <tr>

            <td><c:out value="${course.courseCode}"/></td>
            <td><c:out value="${course.courseTitle}"/></td>
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
<c:if test="${role == admin}">
    <c:url var="addLink" value="/course">
        <c:param name="action" value="addClick"/>
    </c:url>
    <a href="${addLink}"><c:out value="Add Course"/></a>
    <br><br>
</c:if>

<c:url var="logoutLink" value="/logout">
</c:url>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>