<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" style="width: 99%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>注册</title>

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

<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/register.js"></script>
</head>

<body class="login">
	<div class="logo_top">
		<div class="row">
			<div class="col-md-1">
				<img src="${pageContext.request.contextPath }/statics/images/logo.jpg">
			</div>
			<div class="col-md-1" style="padding-left: 70px">
				<span style="font-size: 16px; font-weight: bold; color: #3DCD58;">SFCL Digital System</span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" style="background-color: #3DCD58; width: 100%; height: 30px;"></div>
		</div>
	</div>
	<div>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content" style="padding: 0px">
					<div class="x_content">
						<form class="form-horizontal form-label-left" action="config/addUser" method="post" onsubmit="return check();">
							<h1>用户注册</h1>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">
									用户名
									<span class="required"></span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="username" class="form-control col-md-7 col-xs-12" data-validate-length-range="6" data-validate-words="2" name="username"
										placeholder="字母或数字组成" pattern="[a-zA-Z0-9]*" required="required" type="text" style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12" for="realname">
									姓名
									<span class="required"></span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="realname" class="form-control col-md-7 col-xs-12" name="realname" placeholder="汉字或英文字母" required="required" type="text"
										style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<label for="password" class="control-label col-md-3">密码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="password" type="password" name="password" class="form-control col-md-7 col-xs-12" required="required" placeholder="字母数字不小于6位"
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
							<div class="item form-group">
								<label for="code" class="control-label col-md-3 col-sm-3 col-xs-12">验证码</label>
								<div class="col-md-6 col-sm-6 col-xs-12" style="width: 230px">
									<input id="code" type="text" name="text" class="form-control col-md-7 col-xs-12" required="required" pattern="[a-zA-Z]*"
										style="margin-bottom: 0; width: 160px">
									<span style="padding: 9px"></span>
								</div>
							</div>
							<div class="item form-group">
								<img src="${pageContext.request.contextPath}/config/getVerify" id="codeImage" alt="点击更换验证码"></img>
								<a href="javascript:location.reload();" rel="nofollow">看不清，换一张</a>
							</div>
							<div class="item form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3 ">
									<label class="radio-inline">
										<input type="radio" id="category" name="category" value="0">
										管理员
									</label>
									<label class="radio-inline">
										<input type="radio" id="category" name="category" value="1" checked="checked">
										普通用户
									</label>
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<button id="send" type="submit" class="btn btn-success">注册</button>
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
</html>