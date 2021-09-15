<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
<form action="/course">
    <input type="hidden" value="${action}" name="action">
    <input type="hidden" value="${courseId}" name="courseId">
    <label> Course Code: <input type="text" name="courseCode"/></label><br><br>
    <label> Course Title:<input type="text" name="courseTitle"/></label><br><br>
    <c:choose>
        <c:when test="${action == 'add'}">
            <input type="submit" value="Add"/><br><br>
        </c:when>
        <c:otherwise>
            <input type="submit" value="Update"/><br><br>
        </c:otherwise>
    </c:choose>
</form>

<br>
<c:url var="logoutLink" value="/logout"/>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>