<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--	静态包含base标签、css样式 jQuer文件--%>
	<%@include file="/pages/common/head.jsp"%>
	<script>
		$(function () {
			// 给删除的a标签绑定单击事件，用于删除的确认提示操作
			$("a.deleteClass").click(function () {
				// 在事件的function函数中，有一个this对象，是当前正在响应事件的dom对象
				/**
				 * confirm是确认提示框函数
				 * 参数是他的提示内容
				 * 它有两个按钮，一个确认，一个取消
				 * 返回true表示点击了确认，返回false表示点击了取消
				 */
				return confirm("你确实要删除[" + $(this).parent().parent().find("td:first").text()+ "]");
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}">删除</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="manager/bookServlet?action=page&pageNo=1">首页</a>
				<a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>

			<a href="#">3</a>
			【${ requestScope.page.pageNo}】
			<a href="#">5</a>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="manager/bookServlet?action=page&pageNo=
					${requestScope.page.pageNo+1}">下一页</a>
				<a href="manager/bookServlet?action=page&pageNo=${ requestScope.page.pageTotal }">末页</a>
			</c:if>

			共${ requestScope.page.pageTotal}页，${ requestScope.page.pageTotalCount}条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">
			<script>
				$(function () {
					// 跳到指定页码
					$("#searchPageBtn").click(function () {
						var pageNo = $("#pn_input").val();
						if (pageNo < 1 || pageNo >${requestScope.page.pageTotal}){
							alert("非法下标");
							return false;
						}
						// javasrcipt语言中提供了一个location地址栏对象
						// 它有一个属性叫href。它可以获取浏览器地址中的地址
						location.href = "${pageScope.basePath}manager/bookServlet?action=page&pageNo=" +
								pageNo;
					});
				});
			</script>
		</div>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>