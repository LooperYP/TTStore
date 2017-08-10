<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
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
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}
</style>
</head>
<body>

	<jsp:include page="/jsp/head.jsp" />

	<div class="container"
		style="height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/img/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>会员登录</font>
					USER LOGIN
					<h4>${msg }</h4>
					<div>&nbsp;</div>
					<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user?action=loginUser">

						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" name="username"
								value="${reserveUsername }"	placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="inputPassword3" name="password"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="inputPassword3"
									placeholder="请输入验证码" name="codeUser">
							</div>
							<div class="col-sm-3">
								<img onclick="changeCode(this)"
									src="${pageContext.request.contextPath}/user?action=generalCode" />
							</div>
							<script type="text/javascript">
								function changeCode(obj) {
									$(obj).prop(
											"src",
											"${pageContext.request.contextPath}/user?action=generalCode&"
													+ Math.random());
								}
							</script>

						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label>
										<input type="checkbox" name="autoLogin" value="autoLogin">
										自动登录
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>
										<c:if test="${empty reserveUsername }">
											<input type="checkbox" name="reserveUsername" value="reserveUsername">
										</c:if>
										<c:if test="${not empty reserveUsername }">
											<input type="checkbox" name="reserveUsername" value="reserveUsername" checked="checked">
										</c:if>
										记住用户名
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit"
									border="0"
									style="background: url('${pageContext.request.contextPath}/img/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
					    height:35px;width:100px;color:white;">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/jsp/bottom.jsp" />
</body>
</html>