<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/9/20
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("key", "值");
    %>
    表达式脚本输出key的值是: <%= request.getAttribute("key")%><br>
    EL表达式输出的key值是：${key}
</body>
</html>
