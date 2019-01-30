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

<!-- daterangepicker -->
<link href="${pageContext.request.contextPath }/statics/css/daterangepicker/daterangepicker.css" rel="stylesheet" id="theme" />

<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
</head>

<style type="text/css">
.daterange i {
	position: absolute;
	bottom: 10px;
	right: 14px;
	top: auto;
	cursor: pointer;
}

.daterange {
	position: relative;
}
</style>



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
					<a href="javascript:void();">包材管理系统</a>
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
	<!-- <div>
		<div class="login_wrapper">
			<div class="form-group daterange">
				<label>下单时间：</label>
				<input class="form-control" name="range_date" type="text">
				<i class="fa fa-calendar"></i>
			</div>
		</div>
	</div> -->

	<form class="form-horizontal form-label-left input_mask">
		<div class="form-group">
			<label class="control-label col-md-3 col-sm-3 col-xs-12">卡件串号</label>
			<div class="col-md-9 col-sm-9 col-xs-12">
				<input name="test" id="test" type="text" class="form-control col-md-4 col-xs-12">
				<span class="control-label col-md-5 col-sm-5 col-xs-12 "></span>
			</div>
		</div>
	</form>

	<form action="${pageContext.request.contextPath}/qds/addDinTestFromLog" method="post" enctype="multipart/form-data" onsubmit="return checkSuffix();"
		class="form-inline">
		<div class="form-group">
			<input type="file" style="width: 250px;" name="file" id="file" />
		</div>
		<div class="form-group">
			<input type="submit" class="btn btn-success btn-sm" value="点击上传测试数据" id="fileButton" style="color: withe" />
		</div>
		<div class="form-group">
			<em>上传.log的文件</em>
		</div>
	</form>
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js"></script>
	<!-- daterangepicker日期选择 -->
	<script src="${pageContext.request.contextPath }/statics/js/daterangepicker/moment.min.js"></script>
	<script src="${pageContext.request.contextPath }/statics/js/daterangepicker/daterangepicker.js"></script>



	<script type="text/javascript">
	//$("#test").bind("change input",function(){
	$("#test").onpropertychange(function(){
		alert("test");
	})
	
	
	/* 时间选择 */
		$(function() {
			$(".daterange input").each(function() {
				var $this = $(this);
				alert("xxxaaa");
				$this.daterangepicker({
					locale : {
						"format" : "YYYY-MM-DD",// 显示格式
						"separator" : " / ",// 两个日期之间的分割线
						// 中文化
						"applyLabel" : "确定",
						"cancelLabel" : "取消",
						"fromLabel" : "开始",
						"toLabel" : "结束",
						"daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
						"monthNames" : [ "一月", "二月", "三月", "四月", "五月", "六", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
						"firstDay" : 1
					},
				}, function(start, end, label) {
					// 点击确定后的事件，下面是为了bootstrap validate得校验，
					// 若未使用，可忽视
					if ($this.parents("form.required-validate").length > 0) {
						var $form = $this.parents("form.required-validate");

						var name = $this.attr("name");
						if ($form.length > 0) {
							var data = $form.data('bootstrapValidator');
							data.updateStatus(name, 'NOT_VALIDATED', null)
							// Validate the field
							.validateField(name);
						}
					}
					// 设置最小宽度，否则显示不全
				}).css("min-width", "210px").next("i").click(function() {
					// 对日期的i标签增加click事件，使其在鼠标点击时可以拉出日期选择
					$(this).parent().find('input').click();
				});
			});
		});
	</script>
</body>
</html>