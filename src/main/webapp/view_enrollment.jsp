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
        <th>User name</th>
        <th>User Id</th>
        <th>Course Code</th>
        <th>Course Title</th>

    </tr>
    <c:forEach var="enrollment" items="${enrollment_list}">
        <tr>
            <td>${enrollment.id}</td>
            <td>${enrollment.getUser().getName()}</td>
            <td>${enrollment.getUser().getId()}</td>
            <td>${enrollment.getCourse().getCourseCode()}</td>
            <td>${enrollment.getCourse().getCourseTitle()}</td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="/LogoutButton">
    <input type="submit" value="Logout">
</form>
</body>
</html>
