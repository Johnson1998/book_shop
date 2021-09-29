<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/9/26
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    这是登录页面 <br>
    <form action="http://localhost:8088/15_filter/loginServlet" method="get">
    用户名: <input type="text" name="username"><br>
    密码: <input type="password" name="password">
        <input type="submit">
    </form>
</body>
</html>
