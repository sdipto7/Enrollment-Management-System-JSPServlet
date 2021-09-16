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
    <title>Edit Enrollment</title>
</head>
<body>
<c:if test="${action == 'update'}">
    <c:out value="User Name: ${enrollment.user.name}"/><br>
    <c:out value="Course Code: ${enrollment.course.courseCode}"/><br><br><br>
    <label>Update Information:</label><br><br>
</c:if>
<form action="/enrollment" method="post">
    <input type="hidden" name="enrollmentId" value="${enrollmentId}"/>
    <input type="hidden" value="${action}" name="action">
    <label> User's Name: <input type="text" name="name"></label><br><br>
    <label> Course Code: <input type="text" name="courseCode"></label><br><br>
    <input type="submit" name="action" value="${action}"><br><br>
</form>

<br>
<c:url var="logoutLink" value="/logout"/>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>
