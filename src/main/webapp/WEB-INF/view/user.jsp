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
    <title>Edit User</title>
</head>
<body>
<c:if test="${action == 'update'}">
    <c:out value="Name: ${user.name}"/><br>
    <c:out value="Role: ${user.role}"/><br><br><br>
    <label>Update Information:</label><br><br>
</c:if>
<form action="/user" method="post">
    <input type="hidden" name="userId" value="${userId}"/>
    <input type="hidden" value="${action}" name="action"/>
    <label> Name: <input type="text" name="name"></label><br><br>
    <label> Role: </label><br>
    <label><input type="radio" name="role" value="admin"> ADMIN</label><br>
    <label><input type="radio" name="role" value="user"> USER</label><br><br>
    <input type="submit" name="action" value="${action}"/><br><br>
</form>

<br>
<c:url var="logoutLink" value="/logout"/>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>
