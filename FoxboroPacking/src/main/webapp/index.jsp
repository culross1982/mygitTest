<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- Font Awesome图标字体库 -->
<link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress进度条插件 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet"> --%>
<!-- iCheck -->
<link href="${pageContext.request.contextPath }/statics/css/green.css" rel="stylesheet">
<!-- Animate.css -->
<link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">
<!-- humane弹出框样式 -->
<link href="${pageContext.request.contextPath }/statics/humane/themes/original.css" rel="stylesheet" id="theme" />
<script src="${pageContext.request.contextPath }/statics/humane/humane.js"></script>

<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
</head>
<!-- #EDEDED -->
<body class="login">
	<div class="logo_top">
		<div class="row" style="margin: 0px;">
			<div class="col-md-1">
				<img src="${pageContext.request.contextPath }/statics/images/logo.jpg">
			</div>
			<div class="col-md-1" style="padding-left: 70px">
				<span style="font-size: 16px; font-weight: bold; color: #3DCD58;">SFCL Digital System</span>
			</div>
		</div>
		<nav class="navbar navbar-default" style="border-radius: 0px; border: 0px;">
			<!-- 导航条取消圆角;取消边框线 -->
			<ul class="nav nav-pills nav-justified">
				<li class="disabled">
					<a href="javascript:void();">包材系统</a>
				</li>
				<li class="disabled">
					<a href="javascript:void();">QDS系统</a>
				</li>
				<li class="disabled">
					<a href="javascript:void();">系统设置</a>
				</li>
			</ul>
		</nav>
	</div>
	<div class="login_wrapper">
		<div class="animate form login_form">
			<section class="login_content" style="padding: 0px">
				<form action="dologin" method="post">
					<h1>SFCL Digital System</h1>
					<div>
						<input type="text" class="form-control" name="username" placeholder="请输入用户名" required="required" style="height:34px"/>
					</div>
					<div>
						<input type="password" class="form-control" name="password" placeholder="请输入密码" required="required" style="height:34px"/>
					</div>
					<span>${error }</span>
					<div>
						<button type="submit" class="btn btn-success" style="height:34px;padding:6px 12px;">登录</button>
						<button type="reset" class="btn btn-default" style="height:34px;padding:6px 12px;">重填</button>
					</div>
					<div class="clearfix"></div>

					<div class="separator">
						<div>
							<p>©2019 All Rights Reserved.</p>
						</div>
					</div>
				</form>
			</section>
		</div>
	</div>
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/statics/localjs/index.js"></script>
</body>
</html>