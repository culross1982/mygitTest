<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
  </head>

  <body class="login">
   <div class="logo_top">
   	<div class="row">
   		<div class="col-md-1">
   			<img src="${pageContext.request.contextPath }/statics/images/logo.jpg">
   		</div>
   		<div class="col-md-1" style="padding-left:70px">
   			<span style="font-size:16px;font-weight:bold;color:#3DCD58;">SFCL Digital System</span>
   		</div>
   	</div>
   	<div class="row">
   		<div class="col-md-2" style="background-color:#3DCD58;width:100%;height:30px;"></div>
   	</div>
   </div>
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>
<a onclick="return confirm('')"></a>
      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content" style="padding:0px">
            <form action="dologin" method="post">
              <h1>SFCL Digital System</h1>
              <div>
                <input type="text" class="form-control" name="username" placeholder="请输入用户名" required="required" />
              </div>
              <div>
                <input type="password" class="form-control" name="password" placeholder="请输入密码" required="required" />
              </div>
              <span>${error }</span>
              <div>
              	<button type="submit" class="btn btn-success">登录</button>
              	<button type="reset" class="btn btn-default">重填</button>
              	<button type="button" class="btn btn-default " onclick="window.location.href='${pageContext.request.contextPath }/register'">注册</button>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <div>
                  <p>©2019 All Rights Reserved. </p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>