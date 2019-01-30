<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="allCommon/allHeader.jsp" %>
		<div class="login_wrapper" style="margin-top:180px">
			<div class="animate form login_form">
				<section class="login_content" style="padding: 0px">
					<form action="dologin" method="post">
						<h1>SFCL Digital System</h1>
						<div>
							<input type="text" class="form-control" value="Welcome：${sessionScope.user.realname}" disabled="disabled" style="height:34px"/>
						</div>
						<div>
							<input type="text" class="form-control" value="权限为：<c:forEach items="${sessionScope.role}" var="role" >${role.roleName}&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>" disabled="disabled" style="height:34px"/>
							<%-- <h2>权限为：<c:forEach items="${sessionScope.role}" var="role">${role.roleName}&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach></h2> --%>
						</div>
						<span>${error }</span>
						<div>
							<button type="submit" class="btn btn-success" disabled="disabled" style="height:34px;padding:6px 12px;">请在菜单栏选择所需功能</button>
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
<%@include file="allCommon/allFooter.jsp" %>
