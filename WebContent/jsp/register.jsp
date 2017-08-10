<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head></head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
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
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>
</head>
<body>
	<jsp:include page="/jsp/head.jsp" />

	<div class="container"
		style="background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
		<div class="row">

			<div class="col-md-1"></div>




			<div class="col-md-10"
				style="background: #fff; padding: 40px 40px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>
				USER REGISTER
				<form class="form-horizontal" style="margin-top: 5px;" method="post"
					action="${pageContext.request.contextPath }/user?action=registUser">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="inputPassword3" name="password"
								placeholder="请输入密码" onchange="confimPassword()">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" id="confirmpwd"
								placeholder="请输入确认密码" onchange="confimPassword()">
						</div>
						<div class="col-sm-3"><span><font id="confirmpwdSpan"></font></span></div>
					</div>
					<script type="text/javascript">
						function confimPassword() {
							var Password = $("#inputPassword3").val();
							var confirmpwd = $("#confirmpwd").val();
							if (Password!=confirmpwd) {
								$("#confirmpwdSpan").html("两次输入的密码不一致")
							}else {
								$("#confirmpwdSpan").html("");
							}
						}
					</script>
					
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-7">
							<input type="email" class="form-control" id="inputEmail3" name="email"
								placeholder="Email">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="usercaption" name="name"
								placeholder="请输入姓名">
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-7">
							<label class="radio-inline">
								<input type="radio" name="sex" id="inlineRadio1"
									value="男">
								男
							</label>
							<label class="radio-inline">
								<input type="radio" name="sex" id="inlineRadio2"
									value="女">
								女
							</label>

						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-7">
							<input type="date" class="form-control" name="birthday">
						</div>
						<div class="col-sm-3"></div>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="codeUser">
						</div>
						<div class="col-sm-2">
							<img onclick="changeCode(this)" src="${pageContext.request.contextPath}/user?action=generalCode" />
						</div>
						<div class="col-sm-3">${msg }</div>
					</div>
					<script type="text/javascript">
						function changeCode(obj) {
							$(obj).prop(
									"src",
									"${pageContext.request.contextPath}/user?action=generalCode&"
											+ Math.random());
						}
					</script>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								border="0"
								style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						    height:35px;width:100px;color:white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-1"></div>

		</div>
	</div>
	<jsp:include page="/jsp/bottom.jsp" />
</body>
</html>




