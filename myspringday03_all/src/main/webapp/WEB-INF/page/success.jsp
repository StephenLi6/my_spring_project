<%--
  Created by IntelliJ IDEA.
  User: GoGoing
  Date: 2018/11/28
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${account}" var="one">
        ${one.name}
    </c:forEach>
</body>
</html>
