<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Enrollments</title>
</head>
<body>
<table>
    <tr>
        <th>User Id</th>
        <th>User name</th>
        <th>Course Code</th>
        <th>Course Title</th>
    </tr>
    <c:forEach var="enrollment" items="${enrollment_list}">
        <c:url var="updateLink" value="/updateEnrollmentLink">
            <c:param name="enrollmentId" value="${enrollment.id}"/>
        </c:url>
        <c:url var="deleteLink" value="/deleteEnrollment">
            <c:param name="enrollmentId" value="${enrollment.id}"/>
        </c:url>
        <tr>
            <td>${enrollment.user.id}</td>
            <td>${enrollment.user.name}</td>
            <td>${enrollment.course.courseCode}</td>
            <td>${enrollment.course.courseTitle}</td>
            <c:if test="${role == admin}">
                <td>
                    <a href="${updateLink}"><c:out value="Edit"/></a>
                    |
                    <a href="${deleteLink}"
                       onclick="if(!(confirm('Are you sure to delete the selected enrollment ?'))) return false">
                        <c:out value="Delete"/></a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<br>
<c:choose>
    <c:when test="${role == admin}">
        <form action="/addEnrollmentButton">
            <input type="submit" value="Add Enrollment">
        </form>
        <br>
    </c:when></c:choose>
<form action="/LogoutButton">
    <input type="submit" value="Logout">
</form>
</body>
</html>
