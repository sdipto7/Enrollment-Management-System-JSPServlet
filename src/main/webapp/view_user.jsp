<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <c:forEach var="user" items="${user_list}">
        <c:url var="updateLink" value="/updateUserLink">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <c:url var="deleteLink" value="/deleteUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <c:choose>
                <c:when test="${role == admin}">
                    <td>
                        <a href="${updateLink}">Edit</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if(!(confirm('Are you sure to delete the selected user ?'))) return false">
                            Delete</a>
                    </td>
                </c:when></c:choose>
        </tr>
    </c:forEach>
</table>
<br>
<c:choose>
    <c:when test="${role == admin}">
        <form action="/addUserButton">
            <input type="submit" value="Add User">
        </form>
        <br>
    </c:when></c:choose>
<form action="/LogoutButton">
    <input type="submit" value="Logout">
</form>
</body>
</html>