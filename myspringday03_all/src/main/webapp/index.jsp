<%--
  Created by IntelliJ IDEA.
  User: GoGoing
  Date: 2018/11/28
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll">查询所有</a>

    <form action="/account/save" method="post">
        请输入姓名：<input type="text" name="name">
        请输入价格：<input type="text" name="money">
        <input type="submit" value="提交">
    </form>
</body>
</html>
