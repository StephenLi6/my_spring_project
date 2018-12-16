<%--
  Created by IntelliJ IDEA.
  User: GoGoing
  Date: 2018/11/26
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
    <script src="hs/jquery.min.js"></script>
    <script>
        $(function(){
            $("#btn").click(function(){
                $.ajax({
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"hehe","age":20 ,"password":"123"}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        alert(JSON.stringify(data))
                    }
                })
            })
        })
    </script>
</head>
<body>
    <a href="/user/testString">測試testString</a><br/>
    <a href="/user/testModelAndView">測試modelandView</a><br/>
    <a href="/user/testRequestOrForward">測試testRequestOrForward</a><br/>

    <form action = "/user/testFileUpload" method="post" enctype="multipart/form-data" style="border: aqua 1px">
        <input type="text" name="username"><br/>
        選擇文件：<input type="file" name="upload" ><br/>
        <input type="submit" value="上傳"><br/>
    </form><br/>

    <form action = "/user/testFileUploadMVC" method="post" enctype="multipart/form-data" style="border: aqua 1px">
        <input type="text" name="username"><br/>
        選擇文件：<input type="file" name="upload" ><br/>
        <input type="submit" value="上傳"><br/>
    </form>

    <a href="/user/findAll">測試錯誤總通知</a><br/>

    <h3>異步相應</h3>
    <button id="btn">發送請求</button>
</body>
</html>
