<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>

<table  cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
	<%-- <s:iterator var="orderItem" value="list">
	<tr>
		<td><img width="40" height="45" src="${ pageContext.request.contextPath }/${}orderItem.product.image"/>"></td>
		<td>${}orderItem.product.pname"/></td>
		<td>${}orderItem.count"/></td>
		<td>${}orderItem.subtotal"/></td>
	</tr>
	</s:iterator> --%>
		<tr class="ta_01" align="center" bgColor="#f5fafe">
			<th>商品图片</th>
			<th>商品名</th>
			<th>商品数量</th>
			<th>小计</th>
		</tr>
	<c:forEach items="${list }" var="orderItem">
		<tr class="ta_01" align="center" bgColor="#f5fafe">
			<td>
				<img width="40" height="45"
					src="${ pageContext.request.contextPath }/${orderItem.product.pimage}">
			</td>
			<td>${orderItem.product.pname}</td>
			<td>${orderItem.count}</td>
			<td>${orderItem.subtotal}</td>
		</tr>
	</c:forEach>
</table>