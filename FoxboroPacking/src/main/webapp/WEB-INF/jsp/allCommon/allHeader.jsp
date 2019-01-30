<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<!DOCTYPE html>
<html lang="en" style="width: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SFCL Digital System</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet">
<!-- Animate.css -->
<link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
<!-- humane弹出框样式 -->
<link href="${pageContext.request.contextPath }/statics/humane/themes/flatty.css" rel="stylesheet" id="theme" />
<script type="text/javascript">var tt='${moduleList}';</script>
</head>

<style type="text/css">
#allHeaderCharacter a, span {
	color: #3DCD58;
}

#allHeaderCharacter a:HOVER {
	color: #468847;
}
</style>

<!-- #EDEDED -->
<body class="login">
	<!-- 顶部导航开始 -->
	<div class="logo_top navbar-fixed-top">
		<nav class="navbar navbar-default" style="background:white;border-radius: 0px;border:0px;">
		<div class="row" style="margin: 0px;">
			<div class="col-md-1">
				<img src="${pageContext.request.contextPath }/statics/images/logo.jpg">
			</div>
			<div class="col-md-1" style="padding: 0 70px">
				<span style="font-size: 16px; font-weight: bold;">SFCL Digital System</span>
			</div>
			<div class="col-md-9 pull-right" id="allHeaderCharacter" style="padding-right: 30px;">
				<div class="pull-left" style="padding-top: 15px;">
					<%-- <span>${sessionScope.visitTime }</span> --%>
					<span>您上次登录的时间是：<fm:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${sessionScope.loginTime }"/></span><br/>
					<span>权限为：<c:forEach items="${sessionScope.role}" var="role">${role.roleName}&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach></span>
				</div>
				<div class=" pull-right " style="padding-top: 25px;">
					<a href="javascript:void();">${sessionScope.user.realname } | </a>
					<a href="${pageContext.request.contextPath }/config/changePwdWin">修改密码 | </a>
					<a href="${pageContext.request.contextPath }/logout" style="width: 100px;">
						<i class="fa fa-sign-out pull-right" style="padding: 3px 0 0 5px;"></i> Log Out
					</a>
				</div>
				<div class=" pull-right" style="padding-top: 10px;">
					<img class="img-responsive" style="width: 40px" src="${pageContext.request.contextPath }/statics/images/img.jpg" alt="..."
						class="img-circle profile_img">
				</div>
			</div>
		</div>
		</nav>
		<nav class="navbar navbar-default" style="border-radius: 0px;border:0px;"><!-- 导航条取消圆角;取消边框线 -->
			<ul class="nav nav-pills nav-justified" id="moduleMenu">
				<!-- use localjs/localReady.js to onload moduleMenu -->
			</ul>
		</nav>
	</div>
	<!-- 顶部导航结束 -->