<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
<c:if test="${action == 'update'}">
    <c:out value="Code: ${course.courseCode}"/><br>
    <c:out value="Title: ${course.courseTitle}"/><br><br><br>
    <label>Update Information:</label><br>
</c:if>
<form action="/course" method="post">
    <input type="hidden" value="${action}" name="action">
    <input type="hidden" value="${courseId}" name="courseId">
    <label> Code: <input type="text" name="courseCode"/></label><br><br>
    <label> Title:<input type="text" name="courseTitle"/></label><br><br>
    <input type="submit" name="action" value="${action}"/><br><br>
</form>

<br>
<c:url var="logoutLink" value="/logout"/>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>