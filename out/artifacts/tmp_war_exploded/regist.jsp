<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/9/26
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="registServlet" method="get">
    用户名：<input type="text" name="username"> <br>
    验证码：<input type="text" style="width: 50px;" name="code">
    <img src="http://localhost:8088/tmp/kaptcha.jpg" style="width: 100px; height: 29px;" alt=""><br>
    <input type="submit" value="登录">
    
</form>
</body>
</html>
