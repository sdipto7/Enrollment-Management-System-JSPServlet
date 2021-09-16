<%--
/**
* @author rumi.dipto
* @since 9/10/21
*/
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
<c:if test="${action == 'update'}">
    <c:out value="Course Code: ${course.courseCode}"/><br>
    <c:out value="Course Title: ${course.courseTitle}"/><br><br>
    <label>Update Information:</label><br>
</c:if>
<form action="/course" method="post">
    <input type="hidden" value="${action}" name="action">
    <input type="hidden" value="${courseId}" name="courseId">
    <label> Course Code: <input type="text" name="courseCode"/></label><br><br>
    <label> Course Title:<input type="text" name="courseTitle"/></label><br><br>
    <input type="submit" name="action" value="${action}"/><br><br>
</form>

<br>
<c:url var="logoutLink" value="/logout"/>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>