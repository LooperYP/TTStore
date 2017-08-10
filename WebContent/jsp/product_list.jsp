<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		<jsp:include page="/jsp/head.jsp" />
		
		<div class="container">
			<div class="row" style="margin:0 auto;">
				<div class="col-md-12">
					<ol class="breadcrumb">
						<li><a href="#">首页</a></li>
					</ol>
				</div>
				
				<c:forEach items="${result.datas }" var="item">
					<div class="col-md-2">
						<a href="${pageContext.request.contextPath}/product?action=findProductByPid&pid=${item.pid}">
							<img src="${pageContext.request.contextPath}/${item.pimage}" width="170" height="170" style="display: inline-block;">
						</a>
						<p style="text-align: center;">
							<a href="${pageContext.request.contextPath}/product?action=findProductByPid&pid=${item.pid}" style='color:green'>
								${fn:substring(item.pname,0,12) }
							</a>
						</p>
						<p style="text-align: center;"><font color="#FF0000" >商城价：&yen;${item.shop_price }</font></p>
					</div>
				</c:forEach>
				
			</div>

			<!--分页 -->
			<div style="width:380px; margin:0 auto; margin-top:50px; text-align:center;">
				<ul class="pagination" style="text-align:center; margin-top:10px;">
					<c:if test="${result.currentPageNum==1 }">
						<li class="disabled">
							<a href="#" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					<c:if test="${result.currentPageNum!=1 }">
						<li>
							<a href="${pageContext.request.contextPath}/product?action=findPageDataByCid&cid=${result.datas[0].cid }&pnum=1" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					
					<c:forEach begin="1" end="${result.totalPageNum }" var="item">
						<c:if test="${result.currentPageNum==item }">
							<li class="active">
								<a href="#">
									${item }
								</a>
							</li>
						</c:if>
						<c:if test="${result.currentPageNum!=item }">
							<li>
								<a href="${pageContext.request.contextPath}/product?action=findPageDataByCid&cid=${result.datas[0].cid }&pnum=${item}">
									${item }
								</a>
							</li>
						</c:if>
					</c:forEach>
					
					<c:if test="${result.currentPageNum!=result.totalPageNum }">
						<li>
							<a href="${pageContext.request.contextPath}/product?action=findPageDataByCid&cid=${result.datas[0].cid }&pnum=${result.totalPageNum}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
					<c:if test="${result.currentPageNum==result.totalPageNum }">
						<li class="disabled">
							<a href="#" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
					
				</ul>
			</div>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
        <div class="container">
			<div style="width:100%;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 286px;">
	
				<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
				<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
				<div style="clear: both;"></div>
	
				<div style="overflow: hidden;height: 286;">
			
					<ul style="list-style: none;">
						<c:forEach items="${retainList }" var="item">
						<li style="width: 150px;height: 276;float: left;margin: 0 30px 0 30px;padding: 0 2px 0px;text-align: center;">
							<a href="${pageContext.request.contextPath}/product?action=findProductByPid&pid=${item.pid}">
								<img src="${pageContext.request.contextPath}/${item.pimage}" width="170" height="170" style="display: inline-block;">
							</a>
							<p style="text-align: center;">
								<a href="${pageContext.request.contextPath}/product?action=findProductByPid&pid=${item.pid}" style='color:green'>
									${fn:substring(item.pname,0,12) }
								</a>
							</p>
							<p style="text-align: center;"><font color="#FF0000" >商城价：&yen;${item.shop_price }</font></p>
						</li>
						</c:forEach>
					</ul>
	
				</div>
			</div>
		</div>
		<!--
            	描述：页脚部分
         -->
        <jsp:include page="/jsp/bottom.jsp" />
	
	</body>

</html>