<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>包材管理系统--主界面</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome图标字体库 -->
<link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress进度条插件 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet"> --%>
<!-- iCheck -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/green.css" rel="stylesheet"> --%>
<!-- bootstrap-progressbar实现大文件上传的进度条 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet"> --%>
<!-- JQVMap地图插件 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/jqvmap.min.css" rel="stylesheet"/> --%>
<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
<!-- add packingcss -->
<link href='${pageContext.request.contextPath }/statics/css/packinglist.css' rel='stylesheet'>
</head>
<body class="login">
	<div class="logo_top">
		<div class="row" style="margin: 0px;">
			<div class="col-md-1">
				<img src="${pageContext.request.contextPath }/statics/images/logo.jpg">
			</div>
			<div class="col-md-1" style="padding: 0 70px">
				<span style="font-size: 16px; font-weight: bold; color: #3DCD58;">SFCL Digital System</span>
			</div>
			<div class="col-md-9 pull-right" style="padding-right: 30px;">
				<div class="pull-left" style="padding-top: 25px;">
					<span style="color: #3DCD58;">${sessionScope.visitTime }</span>
				</div>
				<div class=" pull-right" style="padding-top: 25px;">
					<a href="${pageContext.request.contextPath }/logout" style="color: #3DCD58; width: 100px">
						<i class="fa fa-sign-out pull-right" style="padding: 3px 0 0 5px;"></i>${sessionScope.user.realname } Log Out
					</a>
				</div>
				<div class=" pull-right" style="padding-top: 10px;">
					<img class="img-responsive" style="width: 40px" src="${pageContext.request.contextPath }/statics/images/img.jpg" alt="..."
						class="img-circle profile_img">
				</div>
			</div>
		</div>
		<nav class="navbar navbar-default">
			<ul class="nav nav-pills nav-justified">
				<li>
					<a href="${pageContext.request.contextPath}/main">包材管理系统</a>
				</li>
				<li>
					<a href="javascript:void();">产线看板系统</a>
				</li>
			</ul>
		</nav>
	</div>
	<div></div>
</body>
<body class="nav-md footer_fixed" >
	<div class="top_fixed top"></div>
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col menu_fixed">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="${pageContext.request.contextPath }/main" class="site_title">
							<i class="fa fa-paw"></i>
							<span>包材管理</span>
						</a>
					</div>
					<div class="clearfix"></div>
					<!-- menu profile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="${pageContext.request.contextPath }/statics/images/img.jpg" alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h5>${sessionScope.user.realname }</h5>
						</div>
					</div>
					<!-- /menu profile quick info -->
					<br />
					<!-- sidebar menu -->
					<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>${sessionScope.user.username }</h3>
							<ul class="nav side-menu">
								<li>
									<a>
										<i class="fa fa-edit"></i> 包材使用
										<span class="fa fa-chevron-down"></span>
									</a>
									<ul class="nav child_menu">
										<li>
											<a href="">产品使用包材</a>
										</li>
										<li>
											<a href="">包材库存清单</a>
										</li>
										<li>
											<a href="">包材入库</a>
										</li>
										<li>
											<a href="">包材出库</a>
										</li>
										<li>
											<a href="">包材历史清单</a>
										</li>
									</ul>
								</li>
								<li>
									<a>
										<i class="fa fa-users"></i> 用户管理
										<span class="fa fa-chevron-down"></span>
									</a>
									<ul class="nav child_menu">
										<li>
											<a href="$">普通用户管理</a>
										</li>
									</ul>
								</li>
								<li>
									<a>
										<i class="fa fa-bug"></i> 包材基础数据维护
										<span class="fa fa-chevron-down"></span>
									</a>
									<ul class="nav child_menu">
										<li>
											<a href="$">包材基础数据列表</a>
										</li>
										<li>
											<a href="">产品分类列表</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a>
						<a data-toggle="tooltip" data-placement="top" title="FullScreen">
							<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
						</a>
						<a data-toggle="tooltip" data-placement="top" title="Lock">
							<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
						</a>
						<a data-toggle="tooltip" data-placement="top" title="Logout" href="${pageContext.request.contextPath }/logout">
							<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
						</a>
					</div>
					<!-- /menu footer buttons -->
				</div>
			</div>
			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
						<div class="nav toggle">
							<a id="menu_toggle">
								<i class="fa fa-bars" style="padding-bottom: 10px;"></i>
							</a>
						</div>
						<div class="row" style="margin:0px;">
							<div class="col-md-11 clearfix">
								<ul class=" pull-left nav">
									<li style="color: #3DCD58; padding-top: 20px; width: 300px">
										<span>${sessionScope.visitTime }</span>
									</li>
								</ul>
								<ul class=" pull-right nav">
									<li>
										<a href="${pageContext.request.contextPath }/logout" style="color: #3DCD58; padding-top: 20px; width: 100px">
											<i class="fa fa-sign-out pull-right" style="padding: 3px 0 0 5px;"></i> Log Out
										</a>
									</li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->
			<div class="right_col" role="main">
				<div class="">