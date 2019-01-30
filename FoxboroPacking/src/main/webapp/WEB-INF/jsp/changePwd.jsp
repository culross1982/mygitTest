<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="allCommon/allHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="login">
	<div>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content" style="padding: 0px">
					<div class="x_content">
						<form class="form-horizontal form-label-left" action="changePwd" method="post" onsubmit="return check();">
							<h1>更改密码</h1>
							<div class="item form-group">
								<label for="oldPwd" class="control-label col-md-3">旧密码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="oldPwd" type="password" name="oldPwd" class="form-control col-md-7 col-xs-12" required="required" placeholder="请输入旧密码"
										pattern="[a-zA-Z0-9]*" style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<label for="password" class="control-label col-md-3">新密码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="password" type="password" name=password class="form-control col-md-7 col-xs-12" required="required" placeholder="字母数字不小于6位"
										pattern="[a-zA-Z0-9]*" style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<label for="repassword" class="control-label col-md-3 col-sm-3 col-xs-12">确认密码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="repassword" type="password" name="repassword" class="form-control col-md-7 col-xs-12" required="required" placeholder="请再次输入密码"
										pattern="[a-zA-Z0-9]{6,}" style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button id="send" type="submit" class="btn btn-success">更改</button>
									<button type="reset" class="btn btn-primary">重填</button>
								</div>
							</div>
						</form>
					</div>
				</section>
			</div>
		</div>
	</div>
</body>
<%@include file="allCommon/allFooter.jsp" %>
<script src="${pageContext.request.contextPath }/statics/packingjs/changePwd.js"></script>
</html>
