<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/9/20
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%!
    private Integer id;
    %>
    jsp页面 <br>

    <table>
        <%
            for (int i = 0; i < 10; i++) {
        %>
        <tr>
            <td>第<%=i%>行</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
