<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="page-title">
	<div>
		<h3>
			<span>您的权限为：<c:forEach items="${sessionScope.role}" var="role">${role.roleName}&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach></span>
		</h3>
	</div>
</div>
<div class="clearfix"></div>
<%@include file="common/footer.jsp"%>
