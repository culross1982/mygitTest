<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>包材系统</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome图标字体库 -->
<link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress进度条插件 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet"> --%>
<!-- iCheck -->
<link href="${pageContext.request.contextPath }/statics/css/green.css" rel="stylesheet">
<!-- Datatables -->
<!-- sorting button -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/dataTables.bootstrap.min.css" rel="stylesheet"> --%>
<%-- <link href="${pageContext.request.contextPath }/statics/css/buttons.bootstrap.min.css" rel="stylesheet"> --%>
<%-- <link href="${pageContext.request.contextPath }/statics/css/fixedHeader.bootstrap.min.css" rel="stylesheet"> --%>
<!-- 左边展开按钮 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/responsive.bootstrap.min.css" rel="stylesheet"> --%>
<%-- <link href="${pageContext.request.contextPath }/statics/css/scroller.bootstrap.min.css" rel="stylesheet"> --%>
<!-- bootstrap-progressbar实现大文件上传的进度条 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet"> --%>
<!-- JQVMap地图插件 -->
<%-- <link href="${pageContext.request.contextPath }/statics/css/jqvmap.min.css" rel="stylesheet"/> --%>
<!-- Custom Theme Style -->
<%-- <link href="${pageContext.request.contextPath }/statics/tablescss/custom.min.css" rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
<!-- add packingcss -->
<link href='${pageContext.request.contextPath }/statics/css/packinglist.css' rel='stylesheet'>
<!-- humane弹出框样式 -->
<link href="${pageContext.request.contextPath }/statics/humane/themes/flatty.css" rel="stylesheet" id="theme" />
<!-- daterangepicker -->
<link href="${pageContext.request.contextPath }/statics/css/daterangepicker/daterangepicker.css" rel="stylesheet" id="theme" />

<script type="text/javascript">var packingMain='${packingMainList}';var modifyStatus='${modifyStatus}'</script>
</head>

<style type="text/css">
#allHeaderCharacter a, span {
	color: #3DCD58;
}

#allHeaderCharacter a:HOVER {
	color: #468847;
}
</style>

<body class="nav-md footer_fixed">
	<div class="top_fixed top"></div>
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col menu_fixed">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 5px;">
						<a href="${pageContext.request.contextPath }/main" class="site_title">
							<i class="fa fa-paw"></i>
							<span style="color:white;">包材系统</span>
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
							<ul class="nav side-menu" id="packingMenu">
								<!-- use packingjs/packingReady.js to onload packingMenu -->
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
						<!-- 返回主界面 -->
						<a data-toggle="tooltip" data-placement="top" title="Main" href="${pageContext.request.contextPath }/welcomeWindow">
							<span class="fa fa-reply" aria-hidden="true"></span>
						</a>
						<!-- 注销 -->
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
						<div class="col-md-11 pull-right" id="allHeaderCharacter">
							<div class="pull-left" style="padding-top: 20px;">
								<span>您上次登录的时间是：<fm:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sessionScope.loginTime }"/></span>
							</div>
							<div class=" pull-right " style="padding-top: 20px;">
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
					</nav>
				</div>
			</div>

			<!-- /top navigation -->
			<div class="right_col" role="main">
				<div class="">