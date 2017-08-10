﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>购物车</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<script type="text/javascript">
		function changeQuantity(pid) {
			var quantity = $("[name=quantity"+pid+"]").val();
			/* alert(quantity)
			alert(pid) */
			$.post(
				"${pageContext.request.contextPath}/cart?action=changeQuantityCart",
				{"quantity":quantity,
				 "pid":pid
				},
				function(data) {
					window.location.href = "${pageContext.request.contextPath}/"+data;
				},
				"text"
			);
		}
	</script>
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
	 float:left; */
	
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
</head>

<body>
	<jsp:include page="/jsp/head.jsp" />

	<c:if test="${empty map }">
		<center>
			<img src="${pageContext.request.contextPath}/img/emptyCart.jpg">
		</center>
	</c:if>
	<c:if test="${not empty map }">
		<div class="container">
			<div class="row">

				<div style="margin: 0 auto; margin-top: 10px; width: 950px;">

					<strong style="font-size: 16px; margin: 5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<c:set var="amount" value="0"></c:set>
							<c:forEach items="${map }" var="item">
								<tr class="active" id="tableOrder">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img
											src="${pageContext.request.contextPath}/${item.key.pimage }"
											width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank">${item.key.pname }</a>
									</td>
									<td width="20%">￥${item.key.shop_price }</td>
									<td width="10%">
										<input type="text" name="quantity${item.key.pid }" value="${item.value }"
											onblur="changeQuantity('${item.key.pid }')" maxlength="4" size="10">
									</td>
									<td width="15%">
										<span class="subtotal">￥${item.key.shop_price*item.value }</span>
									</td>
									<td>
										<a
											href="${pageContext.request.contextPath}/cart?action=delProductCartByPid&pid=${item.key.pid }"
											class="delete">删除</a>
									</td>
								</tr>
								<c:set var="amount"
									value="${amount+item.key.shop_price*item.value }"></c:set>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right: 130px;">
				<div style="text-align: right;">
					<em style="color: #ff6600;"> 登录后确认是否享有优惠&nbsp;&nbsp; </em>
					赠送积分:
					<em style="color: #ff6600;">${amount/10 }</em>
					&nbsp; 商品金额:
					<strong style="color: #ff6600;">￥${amount }</strong>
				</div>
				<div
					style="text-align: right; margin-top: 10px; margin-bottom: 10px;">
					<a
						href="${pageContext.request.contextPath}/cart?action=clearProductCart"
						id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/order?action=saveOrder">
						<%--提交表单 --%>
						<input type="submit" width="100" value="提交订单" name="submit"
							border="0"
							style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>

		</div>
	</c:if>
	<div class="container">
		<div style="margin-top: 50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg"
				width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center; margin-top: 5px;">
			<ul class="list-inline">
				<li>
					<a href="${pageContext.request.contextPath}/jsp/info.jsp">关于我们</a>
				</li>
				<li>
					<a>联系我们</a>
				</li>
				<li>
					<a>招贤纳士</a>
				</li>
				<li>
					<a>法律声明</a>
				</li>
				<li>
					<a>友情链接</a>
				</li>
				<li>
					<a target="_blank">支付方式</a>
				</li>
				<li>
					<a target="_blank">配送方式</a>
				</li>
				<li>
					<a>服务声明</a>
				</li>
				<li>
					<a>广告声明</a>
				</li>
			</ul>
		</div>
		<div style="text-align: center; margin-top: 5px; margin-bottom: 20px;">
			Copyright &copy; 2005-2016 传智商城 版权所有</div>
	</div>

</body>

</html>