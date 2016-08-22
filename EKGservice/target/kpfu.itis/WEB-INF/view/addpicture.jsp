<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<label>Внешний ключ</label>:  <input type="number" name="foreignid">
<label>Имя изображения</label>:  <input type="text" name="name">
<input type="file" name="pic" accept="image/*">
<input type="submit">
</body>
</html>
