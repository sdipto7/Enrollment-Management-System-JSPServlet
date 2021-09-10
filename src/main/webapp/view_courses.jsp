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
        <th>Course Code</th>
        <th>Course Title</th>

    </tr>
    <c:forEach var="course" items="${course_list}">
        <tr>
            <td>${course.id}</td>
            <td>${course.courseCode}</td>
            <td>${course.courseTitle}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
