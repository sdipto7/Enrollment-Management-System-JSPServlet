/**
* @author rumi.dipto
* @since 9/10/21
*/
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Enrollment</title>
</head>
<body>
<form action="/enrollment" method="post">
    <input type="hidden" name="enrollmentId" value="${enrollmentId}"/>
    <input type="hidden" value="${action}" name="action">
    <label> User's Name: <input type="text" name="name"></label><br><br>
    <label> Course Code: <input type="text" name="courseCode"></label><br><br>
    <c:choose>
        <c:when test="${action == 'add'}">
            <input type="submit" value="Add"><br><br>
        </c:when>
        <c:otherwise>
            <input type="submit" value="Update"><br><br>
        </c:otherwise>
    </c:choose>
</form>

<br>
<c:url var="logoutLink" value="/logout"/>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>
