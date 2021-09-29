<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/9/21
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            //踩坑经历加了"/"
            +request.getContextPath()
            +"/";
    pageContext.setAttribute("basePath", basePath);
%>
<%--<%=basePath%>--%>

<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
