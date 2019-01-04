<%--
  Created by IntelliJ IDEA.
  User: GoGoing
  Date: 2019/1/3
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>一品優購</title>
</head>
<body>
歡迎來到一平有夠
<%=request.getRemoteUser()%>
<br>
<a href="http:192.168.25.130:9100/cas/logout">退出</a>
</body>
</html>
