<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%--	静态包含base标签、css样式 jQuer文件--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.myOrdersList}" var="myOrder">
				<tr>
					<td>${myOrder.createTime}</td>
					<td>${myOrder.price}</td>
					<c:choose>
					<c:when test="${myOrder.status == 0}">
						<td>未发货</td>
					</c:when>
					<c:when test="${myOrder.status == 1}">
						<td>已发货</td>
					</c:when>
					<c:when test="${myOrder.status == 2}">
						<td>已签收</td>
					</c:when>
					</c:choose>
					<td><a href="${basePath}OrderServlet?action=showOrderDetail&orderId=${myOrder.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>

			</tr>		
		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>