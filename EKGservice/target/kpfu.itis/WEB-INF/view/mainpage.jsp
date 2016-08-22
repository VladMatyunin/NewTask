<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad.M
  Date: 20.08.2016
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<table>
    <caption>All Images</caption>
    <tr>
        <th>UID</th>
        <th>EXT_UID</th>
        <th>Наименование</th>
        <th>уменьшенное изображение</th>
        <th>удалить/изменить</th>
    </tr>
    <c:forEach var = "image" items="${allimages}">
        <tr><td><c:out value="image.id"/></td>
            <td><c:out value="image.foreignId"/></td>
            <td><c:out value="image.name"/></td>
            <td><img src="/service/getImage/${image.id}.png" alt="car_image"/></td>
            <td><a>Удалить</a> | <a>Изменить</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
