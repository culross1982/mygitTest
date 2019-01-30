<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet">
  	<!-- iCheck -->
    <link href="${pageContext.request.contextPath }/statics/css/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="${pageContext.request.contextPath }/statics/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="${pageContext.request.contextPath }/statics/css/jqvmap.min.css" rel="stylesheet"/>
    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
    <!-- add packingcss -->
    <link href='${pageContext.request.contextPath }/statics/css/packinglist.css' rel='stylesheet'>
</head>
<body>
<div class="logo_top">
   	<div class="row">
   		<div class="col-md-2" style="padding-left:40px">
   			<img src="${pageContext.request.contextPath }/statics/images/logo.jpg">
   		</div>
   		<div class="col-md-1" >
   			<span style="font-size:16px;font-weight:bold;color:#3DCD58;">SFCL Digital System</span>
   		</div>
			<div class="col-md-9 " >
				<ul class=" pull-right nav">
					<li><a href="${pageContext.request.contextPath }/logout" style="color: #3DCD58;padding-top:25px">
						<i class="fa fa-sign-out pull-right" style="padding:3px 0 0 5px;"></i> Log Out</a></li>
				</ul>
			</div>
		</div>
   	<div class="row">
   		<div class="col-md-2" style="background-color:#3DCD58;width:100%;height:30px;"></div>
   	</div>
   </div>
</body>
</html>