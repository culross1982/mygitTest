<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 左部导航开始 -->
<div class="nav-md footer_fixed" style="margin-top: 119px;">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col menu_fixed">
				<div class="left_col scroll-view">
					<!-- sidebar menu -->
					<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
						<div class="menu_section" style="width: 230px; padding-top: 10px;">
							<ul class="nav side-menu" id="configMenu">
								<!-- Admin、包材系统管理员菜单 -->
								<%-- <c:if test="${sessionScope.user.category==1 || sessionScope.user.category==2}">--%>
									 <li>
									<a>
											<i class="fa fa-users"></i> 用户管理
											<span class="fa fa-chevron-down"></span>
										</a>
										<ul class="nav child_menu">
											<li>
												<a href="${pageContext.request.contextPath }/config/usersManageWindows">用户列表</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath }/config/cAddUserWin">新增用户</a>
											</li>
										</ul> 
									</li>
								<%-- </c:if>  --%>
							</ul>
						</div>
					</div>
					<!-- /sidebar menu -->

					<!-- /menu footer buttons -->
					<div class="sidebar-footer hidden-small">
						<a data-toggle="tooltip" data-placement="top" title="Settings">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						</a>
						<a id="FullScreen" data-toggle="tooltip" data-placement="top" title="FullScreen" >
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
			<div class="right_col" role="main">
				<div class="">