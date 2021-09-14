<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <c:if test="${currentUser.role == 'ADMIN'}">
            <th>Role</th>
        </c:if>
    </tr>
    <c:forEach var="user" items="${userList}">
        <c:url var="updateLink" value="/user">
            <c:param name="userId" value="${user.id}"/>
            <c:param name="action" value="updateClick"/>
        </c:url>
        <c:url var="deleteLink" value="/user">
            <c:param name="userId" value="${user.id}"/>
            <c:param name="action" value="delete"/>
        </c:url>
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.name}"/></td>
            <c:if test="${currentUser.role == 'ADMIN'}">
                <td><c:out value="${user.role}"/></td>
                <td>
                    <a href="${updateLink}"><c:out value="Edit"/></a>
                    |
                    <a href="${deleteLink}"
                       onclick="if(!(confirm('Are you sure to delete the selected user ?'))) return false">
                        <c:out value="Delete"/></a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<br>
<c:if test="${currentUser.role == 'ADMIN'}">
    <c:url var="addLink" value="/user">
        <c:param name="action" value="addClick"/>
    </c:url>
    <a href="${addLink}"><c:out value="Add User"/></a>
    <br><br>
</c:if>

<c:url var="logoutLink" value="/logout">
</c:url>
<a href="${logoutLink}"><c:out value="Logout"/></a>

</body>
</html>
